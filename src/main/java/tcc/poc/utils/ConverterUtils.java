package tcc.poc.utils;

import gen.models.WarehouseModel;
import gen.models.MerchandiseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import tcc.poc.models.Deposit;
import tcc.poc.models.Merchandise;

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
        return r;
    }

    public List<WarehouseModel> getDepositModel(List<Deposit> deposits) {
        List<WarehouseModel> r = new ArrayList<>();
        if(CollectionUtils.isEmpty(deposits)) {
            deposits.forEach(deposit -> {
                WarehouseModel warehouseModel = new WarehouseModel();
                warehouseModel.setDateHour(dateUtils.getDate(deposit.getDateDeposit()));
                warehouseModel.setState(deposit.getWarehouse().getAddress().getState());
                warehouseModel.setCity(deposit.getWarehouse().getAddress().getCity());
                warehouseModel.setId(deposit.getId());
                r.add(warehouseModel);
            });
        }
        return r;
    }
}
