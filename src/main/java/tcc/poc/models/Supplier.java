package tcc.poc.models;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Supplier implements Serializable {
    private Integer id;
    private String cnpj;
    private Address address;
    private String name;
    private String email;
    private String telephone;
}
