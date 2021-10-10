package tcc.poc.controllers;

import gen.api.WarehousesApi;
import gen.models.DepositWarehouseModel;
import gen.models.WarehouseModel;
import io.swagger.util.Json;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import tcc.poc.exceptions.BadRequestException;
import tcc.poc.exceptions.ValidationException;
import tcc.poc.kafka.TopicWarehouse;
import tcc.poc.models.enums.StatusMerchandise;
import tcc.poc.models.enums.ValidationMessage;
import tcc.poc.service.EisService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
public class WarehouseController implements WarehousesApi {

    @Autowired
    private EisService eisService;

    @Autowired
    @Qualifier("topicWarehouseProducer")
    private TopicWarehouse topicWarehouse;

    @Override
    public ResponseEntity<Void> addWarehouse(@Valid @RequestBody(required = false) WarehouseModel warehouseModel) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<List<WarehouseModel>> findWarehouse(@RequestHeader(value="x-id-merchandise", required=true) String xIdMerchandise) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Void> registerDepositWarehouse(@Valid @RequestBody(required = false) DepositWarehouseModel depositWarehouseModel) {

        if(depositWarehouseModel != null) {

            StatusMerchandise status = eisService.getStatusMerchandise(depositWarehouseModel.getIdMerchandise());

            if(status != null && !StatusMerchandise.RECEIVED.equals(status)) {

                String messageJson = Json.pretty(depositWarehouseModel);
                topicWarehouse.send(messageJson);

            }
            throw new ValidationException(ValidationMessage.INVALID_MERCHANDISE);
        }

        throw new BadRequestException(ValidationMessage.REQUEST_ERROR);
    }

}
