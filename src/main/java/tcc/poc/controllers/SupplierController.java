package tcc.poc.controllers;

import gen.api.SuppliersApi;
import gen.models.SupplierModel;
import io.swagger.util.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tcc.poc.exceptions.BadRequestException;
import tcc.poc.kafka.TopicProducer;
import tcc.poc.models.enums.ValidationMessage;

import javax.validation.Valid;
import java.util.List;

@RestController
public class SupplierController implements SuppliersApi {

    @Autowired
    @Qualifier("topicSupplierProducer")
    private TopicProducer topicSupplierProducer;

    @Override
    public ResponseEntity<List<SupplierModel>> findSuppliers() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    @Override
    public ResponseEntity<Void> registerSupplier(@Valid @RequestBody(required = false) SupplierModel supplierModel) {
        if(supplierModel != null) {
            String messageJson = Json.pretty(supplierModel);
            topicSupplierProducer.send(messageJson);
        }

        throw new BadRequestException(ValidationMessage.REQUEST_ERROR);
    }

}
