package tcc.poc.controllers;

import gen.api.MerchandisesApi;
import gen.models.MerchandiseModel;
import gen.models.MerchandiseRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import tcc.poc.models.Deposit;
import tcc.poc.models.Merchandise;
import tcc.poc.service.EisService;
import tcc.poc.utils.ConverterUtils;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MerchandiseController implements MerchandisesApi {

    @Autowired
    private EisService eisService;

    @Autowired
    private ConverterUtils converterUtils;

    @Override
    public ResponseEntity<List<MerchandiseModel>> findMerchandises(@RequestHeader(value="x-cpf-customer", required=true) String xCpfCustomer) {

        List<Merchandise> list = eisService.findMerchandises(xCpfCustomer);
        if(!CollectionUtils.isEmpty(list)) {
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
    public ResponseEntity<Void> registerMerchandise(@RequestHeader(value="x-cnpj-supplier", required=true) String xCnpjSupplier,
                                                    @Valid @RequestBody(required = false) MerchandiseRequestModel merchandiseRequestModel) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    @Override
    public ResponseEntity<Void> registerMerchandiseDelivered(@RequestHeader(value="x-id-merchandise", required=true) String xIdMerchandise) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
