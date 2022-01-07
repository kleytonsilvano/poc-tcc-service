package tcc.poc.models.vo;

import gen.models.DepositWarehouseModel;
import gen.models.WarehouseModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class WarehouseQueueVO implements Serializable {

    private DepositWarehouseModel depositWarehouse;
    private WarehouseModel warehouse;

}
