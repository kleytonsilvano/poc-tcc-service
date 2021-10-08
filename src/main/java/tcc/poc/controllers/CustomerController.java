package tcc.poc.controllers;

import gen.api.CustomersApi;
import gen.models.CustomerModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CustomerController implements CustomersApi {

    @Override
    public ResponseEntity<List<CustomerModel>> findCustomers() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    @Override
    public ResponseEntity<Void> registerCustomer(@Valid @RequestBody(required = false) CustomerModel customerModel) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}