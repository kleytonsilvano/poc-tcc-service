package tcc.poc.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class Customer implements Serializable {
    private Integer id;
    private String cpf;
    private String name;
    private Address address;
    private String telephone;
    private String email;
    private Date birthDate;
}
