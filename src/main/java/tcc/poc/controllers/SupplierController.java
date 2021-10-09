package tcc.poc.controllers;

import gen.api.SuppliersApi;
import gen.models.SupplierModel;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class SupplierController implements SuppliersApi {

    @Override
    public ResponseEntity<List<SupplierModel>> findSuppliers() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    @Override
    public ResponseEntity<Void> registerSupplier(@Valid @RequestBody(required = false) SupplierModel supplierModel) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
