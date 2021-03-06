package tcc.poc.controllers;

import gen.api.MerchandisesApi;
import gen.models.MerchandiseModel;
import gen.models.MerchandiseRequestModel;
import gen.models.MerchandiseResponse;
import io.swagger.util.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import tcc.poc.constants.ScopeConstants;
import tcc.poc.exceptions.BadRequestException;
import tcc.poc.kafka.TopicProducer;
import tcc.poc.models.Deposit;
import tcc.poc.models.Merchandise;
import tcc.poc.models.enums.ValidationMessage;
import tcc.poc.models.vo.MechandiseQueueVO;
import tcc.poc.security.SecuredApi;
import tcc.poc.service.EisService;
import tcc.poc.utils.ConverterUtils;
import tcc.poc.utils.MerchandiseUtils;
import tcc.poc.utils.ValidateUtils;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MerchandiseController implements MerchandisesApi {

    @Autowired
    private EisService eisService;

    @Autowired
    private ConverterUtils converterUtils;

    @Autowired
    @Qualifier("topicMerchandiseProducer")
    private TopicProducer topicMerchandiseProducer;

    @Override
    @SecuredApi(allowedScopes = {ScopeConstants.MIC_READ})
    public ResponseEntity<List<MerchandiseModel>> findMerchandises(@RequestHeader(value = "x-cpf-customer", required = true) String xCpfCustomer) {

        List<Merchandise> list = eisService.findMerchandises(xCpfCustomer);
        if (!CollectionUtils.isEmpty(list)) {
            List<MerchandiseModel> listMerchandiseModel = new ArrayList<>();
            list.forEach(merchandise -> {
                List<Deposit> deposits = eisService.findDepositByMerchandise(merchandise.getId());
                listMerchandiseModel.add(converterUtils.getMerchandiseModel(merchandise, deposits));
            });
            return new ResponseEntity<>(listMerchandiseModel, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @Override
    @SecuredApi(allowedScopes = {ScopeConstants.MIC_WRITE})
    public ResponseEntity<MerchandiseResponse> registerMerchandise(@RequestHeader(value = "x-cnpj-supplier", required = true) String xCnpjSupplier,
                                                                   @Valid @RequestBody(required = false) MerchandiseRequestModel merchandiseRequestModel) {
        MerchandiseResponse r = new MerchandiseResponse();
        if (merchandiseRequestModel != null) {
            ValidateUtils.validateCpf(merchandiseRequestModel.getCustomerCpf());
            ValidateUtils.validateAddress(merchandiseRequestModel.getDeliveryAddress());
            String code = MerchandiseUtils.createUniqueCode();
            r.setCode(code);
            MechandiseQueueVO vo = MechandiseQueueVO.builder()
                    .code(code)
                    .cnpj(xCnpjSupplier).merchandiseRequest(merchandiseRequestModel).build();
            String messageJson = Json.pretty(vo);
            topicMerchandiseProducer.send(messageJson);
            return new ResponseEntity<>(r, HttpStatus.CREATED);
        }
        throw new BadRequestException(ValidationMessage.REQUEST_ERROR);
    }

    @Override
    @SecuredApi(allowedScopes = {ScopeConstants.MIC_WRITE})
    public ResponseEntity<Void> registerMerchandiseDelivered(@RequestHeader(value = "x-id-merchandise", required = true) Integer xIdMerchandise) {
        if (xIdMerchandise != null) {
            MechandiseQueueVO vo = MechandiseQueueVO.builder().delivered(true).idMerchandise(xIdMerchandise).build();
            String messageJson = Json.pretty(vo);
            topicMerchandiseProducer.send(messageJson);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        throw new BadRequestException(ValidationMessage.REQUEST_ERROR);
    }

    @Override
    @SecuredApi(allowedScopes = {ScopeConstants.MIC_READ})
    public ResponseEntity<MerchandiseModel> findMerchandiseByCpfAndCode(@PathVariable("cpfPath") String cpfPath,
                                                                        @PathVariable("codePath") String codePath) {

        Merchandise merchandise = eisService.findMerchandiseCpfCode(cpfPath, codePath);
        if (merchandise != null) {
            List<Deposit> deposits = eisService.findDepositByMerchandise(merchandise.getId());
            return new ResponseEntity<>(converterUtils.getMerchandiseModel(merchandise, deposits), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}