package tcc.poc.models;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Warehouse implements Serializable {
    private Integer id;
    private Address address;
    private String name;
    private Boolean isActive;
}
