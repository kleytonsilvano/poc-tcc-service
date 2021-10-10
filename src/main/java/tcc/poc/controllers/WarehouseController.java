package tcc.poc.controllers;

import gen.api.WarehousesApi;
import gen.models.DepositWarehouseModel;
import gen.models.WarehouseAddressModel;
import gen.models.WarehouseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class WarehouseController implements WarehousesApi {

    @Override
    public ResponseEntity<Void> addWarehouse(@RequestHeader(value="x-id-merchandise", required=true) String xIdMerchandise,
                                             @Valid @RequestBody(required = false) WarehouseAddressModel warehouseAddressModel) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<List<WarehouseModel>> findWarehouse(@RequestHeader(value="x-id-merchandise", required=true) String xIdMerchandise) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @Override
    public ResponseEntity<Void> registerDepositWarehouse(@Valid @RequestBody(required = false) DepositWarehouseModel depositWarehouseModel) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
