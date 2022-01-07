package tcc.poc.models.vo;

import gen.models.MerchandiseRequestModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class MechandiseQueueVO implements Serializable {
    private MerchandiseRequestModel merchandiseRequest;
    private String cnpj;
    private String idMerchandise;
    private Boolean delivered;
}