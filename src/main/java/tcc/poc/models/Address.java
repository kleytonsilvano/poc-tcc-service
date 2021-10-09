package tcc.poc.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class Address implements Serializable {
    private Integer id;
    private String street;
    private String zipCode;
    private String district;
    private String city;
    private String state;
    private String number;
}
