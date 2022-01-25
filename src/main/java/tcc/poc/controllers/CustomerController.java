package tcc.poc.controllers;

import gen.api.CustomersApi;
import gen.models.CustomerModel;
import io.swagger.util.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tcc.poc.constants.ScopeConstants;
import tcc.poc.exceptions.BadRequestException;
import tcc.poc.kafka.TopicProducer;
import tcc.poc.models.Customer;
import tcc.poc.models.enums.ValidationMessage;
import tcc.poc.security.SecuredApi;
import tcc.poc.service.EisService;
import tcc.poc.utils.ConverterUtils;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CustomerController implements CustomersApi {

    @Autowired
    @Qualifier("topicCustomerProducer")
    private TopicProducer topicCustomerProducer;

    @Autowired
    private ConverterUtils converterUtils;

    @Autowired
    private EisService eisService;

    @Override
    @SecuredApi(allowedScopes = {ScopeConstants.MIC_READ})
    public ResponseEntity<List<CustomerModel>> findCustomers() {

        List<Customer> list = eisService.findCustomers();
        if(!CollectionUtils.isEmpty(list)) {
            List<CustomerModel> listCustomerModel = new ArrayList<>();
            list.forEach(customer -> {
                listCustomerModel.add(converterUtils.getCustomerModel(customer));
            });
            return new ResponseEntity<>(listCustomerModel, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @Override
    @SecuredApi(allowedScopes = {ScopeConstants.MIC_WRITE})
    public ResponseEntity<Void> registerCustomer(@Valid @RequestBody(required = false) CustomerModel customerModel) {
        if(customerModel != null) {
            String messageJson = Json.pretty(customerModel);
            topicCustomerProducer.send(messageJson);
        }
        throw new BadRequestException(ValidationMessage.REQUEST_ERROR);
    }
}