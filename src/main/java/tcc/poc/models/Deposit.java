package tcc.poc.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Builder
public class Deposit implements Serializable {
    private Integer id;
    private Merchandise merchandise;
    private Date dateDeposit;
    private Warehouse warehouse;
}
