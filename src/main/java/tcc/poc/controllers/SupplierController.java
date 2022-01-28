package tcc.poc.controllers;

import gen.api.SuppliersApi;
import gen.models.SupplierModel;
import io.swagger.util.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tcc.poc.exceptions.BadRequestException;
import tcc.poc.kafka.TopicProducer;
import tcc.poc.models.Supplier;
import tcc.poc.models.enums.ValidationMessage;
import tcc.poc.service.EisService;
import tcc.poc.utils.ConverterUtils;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SupplierController implements SuppliersApi {

    @Autowired
    private EisService eisService;

    @Autowired
    private ConverterUtils converterUtils;

    @Autowired
    @Qualifier("topicSupplierProducer")
    private TopicProducer topicSupplierProducer;

    @Override
    public ResponseEntity<List<SupplierModel>> findSuppliers() {
        List<Supplier> list = eisService.findSuppliers();
        if(!CollectionUtils.isEmpty(list)) {
            List<SupplierModel> listSupplierModel = new ArrayList<>();
            list.forEach(supplier -> {
                listSupplierModel.add(converterUtils.getSupplierModel(supplier));
            });
            return new ResponseEntity<>(listSupplierModel, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Void> registerSupplier(@Valid @RequestBody(required = false) SupplierModel supplierModel) {
        if(supplierModel != null) {
            String messageJson = Json.pretty(supplierModel);
            topicSupplierProducer.send(messageJson);
        }

        throw new BadRequestException(ValidationMessage.REQUEST_ERROR);
    }

    @Override
    public ResponseEntity<SupplierModel> findSupplierByCnpj(String cnpj) {
        Supplier supplier = eisService.findSupplierByCnpj(cnpj);
        if (supplier != null) {
            SupplierModel sm = converterUtils.getSupplierModel(supplier);
            return new ResponseEntity<>(sm, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
