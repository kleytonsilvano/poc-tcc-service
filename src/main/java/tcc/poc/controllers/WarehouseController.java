package tcc.poc.controllers;

import gen.api.WarehousesApi;
import gen.models.DepositWarehouseModel;
import gen.models.WarehouseModel;
import io.swagger.util.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import tcc.poc.constants.ScopeConstants;
import tcc.poc.exceptions.BadRequestException;
import tcc.poc.kafka.TopicProducer;
import tcc.poc.models.Warehouse;
import tcc.poc.models.enums.ValidationMessage;
import tcc.poc.security.SecuredApi;
import tcc.poc.service.EisService;
import tcc.poc.utils.ConverterUtils;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class WarehouseController implements WarehousesApi {

    @Autowired
    private EisService eisService;

    @Autowired
    private ConverterUtils converterUtils;

    @Autowired
    @Qualifier("topicWarehouseProducer")
    private TopicProducer topicWarehouseProducer;

    @Autowired
    @Qualifier("topicDepositWarehouseProducer")
    private TopicProducer topicDepositWarehouse;

    @Override
    @SecuredApi(allowedScopes = {ScopeConstants.MIC_WRITE})
    public ResponseEntity<Void> addWarehouse(@Valid @RequestBody(required = false) WarehouseModel warehouseModel) {

        if(warehouseModel != null) {
           String messageJson = Json.pretty(warehouseModel);
            topicWarehouseProducer.send(messageJson);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

        throw new BadRequestException(ValidationMessage.REQUEST_ERROR);
    }

    @Override
    @SecuredApi(allowedScopes = {ScopeConstants.MIC_READ})
    public ResponseEntity<List<WarehouseModel>> findWarehouse(@RequestHeader(value="x-id-merchandise", required=true) String xIdMerchandise) {

        List<Warehouse> list = eisService.findWarehouseByIdMerchandise(xIdMerchandise);
        if(!CollectionUtils.isEmpty(list)) {
            List<WarehouseModel> listWarehouseModel = new ArrayList<>();
            list.forEach(warehouse -> {
                if(warehouse.getIsActive()) {
                    listWarehouseModel.add(converterUtils.getWarehouseModel(warehouse));
                }
            });
            return new ResponseEntity<>(listWarehouseModel, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @Override
    @SecuredApi(allowedScopes = {ScopeConstants.MIC_WRITE})
    public ResponseEntity<Void> registerDepositWarehouse(@Valid @RequestBody(required = false) DepositWarehouseModel depositWarehouseModel) {

        if(depositWarehouseModel != null) {
            String messageJson = Json.pretty(depositWarehouseModel);
            topicWarehouseProducer.send(messageJson);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

        throw new BadRequestException(ValidationMessage.REQUEST_ERROR);
    }

}
