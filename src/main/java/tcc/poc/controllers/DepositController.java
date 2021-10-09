package tcc.poc.controllers;

import gen.api.DepositsApi;
import gen.models.DepositAddressModel;
import gen.models.DepositModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class DepositController implements DepositsApi {

    @Override
    public ResponseEntity<Void> addDeposit(@RequestHeader(value="x-id-merchandise", required=true) String xIdMerchandise,
                                           @Valid @RequestBody(required = false) DepositAddressModel depositAddressModel) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    @Override
    public ResponseEntity<List<DepositModel>> findDeposits(
                                            @RequestHeader(value="x-id-merchandise", required=true) String xIdMerchandise) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }
}
