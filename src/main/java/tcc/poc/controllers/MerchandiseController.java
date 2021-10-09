package tcc.poc.controllers;

import gen.api.MerchandiseApi;
import gen.models.MerchandiseModel;
import gen.models.MerchandiseRequestModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MerchandiseController implements MerchandiseApi {

    @Override
    public ResponseEntity<List<MerchandiseModel>> findMerchandises(@RequestHeader(value="x-cpf-customer", required=true) String xCpfCustomer) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

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
