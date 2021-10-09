package tcc.poc.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Builder
public class Merchandise implements Serializable {
    private Integer id;
    private Double value;
    private String customerCpf;
    private String description;
    private Date arrivalForecast;
    private Supplier supplier;
    private Boolean isDelivered;
}
