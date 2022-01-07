package tcc.poc.utils;

import gen.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import tcc.poc.models.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class ConverterUtils {

    @Autowired
    private DateUtils dateUtils;

    public MerchandiseModel getMerchandiseModel(Merchandise merchandise, List<Deposit> deposits) {
        MerchandiseModel r = new MerchandiseModel();
        r.setValue(BigDecimal.valueOf(merchandise.getValue()));
        r.setIdSupplier(merchandise.getSupplier().getId());
        r.setDelivered(merchandise.getIsDelivered());
        r.setCustomerCpf(merchandise.getCustomerCpf());
        r.setArrivalForecast(dateUtils.getDate(merchandise.getArrivalForecast()));
        r.setId(merchandise.getId());
        r.setDescription(merchandise.getDescription());
        r.setDeposits(getDepositList(deposits));
        return r;
    }

    public List<DepositAddressModel> getDepositList(List<Deposit> deposits) {
        List<DepositAddressModel> r = new ArrayList<>();
        if(!CollectionUtils.isEmpty(deposits)) {
            deposits.forEach(deposit -> {
                DepositAddressModel depositAddressModel = new DepositAddressModel();
                depositAddressModel.setState(deposit.getWarehouse().getAddress().getState());
                depositAddressModel.setCity(deposit.getWarehouse().getAddress().getCity());
                depositAddressModel.setDateHour(dateUtils.getDate(deposit.getDateDeposit()));
                depositAddressModel.setId(deposit.getId());
                r.add(depositAddressModel);
            });
        }
        return r;
    }

    public CustomerModel getCustomerModel(Customer customer) {
        CustomerModel r = new CustomerModel();
        r.setId(customer.getId());
        r.setAddress(getAddress(customer.getAddress()));
        r.setBirthDate(dateUtils.getDate(customer.getBirthDate(), "yyyy-MM-dd"));
        r.setCpf(customer.getCpf());
        r.setEmail(customer.getEmail());
        r.setName(customer.getName());
        r.setTelephone(customer.getTelephone());
        return r;
    }

    private AdressModel getAddress(Address address) {
        AdressModel r = new AdressModel();
        r.setId(address.getId());
        r.setCity(address.getCity());
        r.setState(address.getState());
        r.setDistrict(address.getDistrict());
        r.setNumber(address.getNumber());
        r.setStreet(address.getStreet());
        r.setZipCode(address.getZipCode());
        return r;
    }

    public WarehouseModel getWarehouseModel(Warehouse warehouse) {
        WarehouseModel r = new WarehouseModel();
        r.setId(warehouse.getId());
        r.setAddress(getAddress(warehouse.getAddress()));
        r.setName(warehouse.getName());
        r.setIsActive(warehouse.getIsActive());
        return r;
    }
}
