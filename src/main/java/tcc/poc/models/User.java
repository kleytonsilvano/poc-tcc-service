package tcc.poc.models;

import lombok.Data;
import tcc.poc.models.enums.UserType;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private Integer id;
    private UserType userType;
    private String cpfCnpj;
    private String password;
}
