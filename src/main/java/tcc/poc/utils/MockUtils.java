package tcc.poc.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tcc.poc.models.*;

/**
 * Classe criada apenas para gerar Mocks para satisfazer a POC
 */
@Component
public class MockUtils {

    @Autowired
    private DateUtils dateUtils;

    public Merchandise createMockMerchandise(String xCpfCustomer) {
        return Merchandise.builder()
                .id(1)
                .arrivalForecast(dateUtils.getDate("2021-11-10T00:00:00"))
                .customerCpf(xCpfCustomer)
                .isDelivered(false)
                .description("Apple Watch")
                .supplier(createMockSupplier())
                .value(2000D).build();
    }

    public Merchandise createMockMerchandise2(String xCpfCustomer) {
        return Merchandise.builder()
                .id(2)
                .arrivalForecast(dateUtils.getDate("2021-10-01T00:00:00"))
                .customerCpf(xCpfCustomer)
                .isDelivered(true)
                .description("The Lord of the Rings: The Fellowship of the Ring")
                .supplier(createMockSupplier())
                .value(38.50D).build();
    }

    public Supplier createMockSupplier() {
        return Supplier.builder()
                .id(1)
                .address(createMockAddress())
                .cnpj("1000000000000")
                .name("Supplier")
                .email("mail@mail.com")
                .telephone("+5511910000000")
                .build();

    }

    public Address createMockAddress() {
        return Address.builder()
                .id(1)
                .city("Belo Horizonte")
                .state("MG")
                .district("Bairro 01")
                .number("22")
                .street("Rua teste")
                .zipCode("30000000").build();
    }

    public Address createMockAddress2() {
        return Address.builder()
                .id(1)
                .city("São Paulo")
                .state("SP")
                .district("Bairro SP")
                .number("11")
                .street("Rua SP")
                .zipCode("30000000").build();
    }

    public Deposit createMockDeposit(Integer codigoMerchandise) {
        return Deposit.builder()
                .id(1)
                .dateDeposit(dateUtils.getDate("2021-09-11T00:00:00"))
                .merchandise(Merchandise.builder().id(codigoMerchandise).build())
                .warehouse(createMockWarehouse()).build();
    }

    public Deposit createMockDeposit2(Integer codigoMerchandise) {
        return Deposit.builder()
                .id(2)
                .dateDeposit(dateUtils.getDate("2021-09-13T00:00:00"))
                .merchandise(Merchandise.builder().id(codigoMerchandise).build())
                .warehouse(createMockWarehouse2()).build();
    }

    private Warehouse createMockWarehouse() {
        return Warehouse.builder()
                .address(createMockAddress())
                .name("Depósito Norte")
                .isActive(true).build();
    }

    private Warehouse createMockWarehouse2() {
        return Warehouse.builder()
                .address(createMockAddress2())
                .name("Depósito Sul")
                .isActive(true).build();
    }

    public Customer createMockCustomer() {
        return Customer.builder()
                .id(1)
                .address(createMockAddress())
                .name("Customer 0101")
                .cpf("010.010.111-10")
                .birthDate(dateUtils.getDate("2000-10-10"))
                .telephone("(00)91111-1000")
                .build();
    }

    public Customer createMockCustomer2() {
        return Customer.builder()
                .id(1)
                .address(createMockAddress())
                .name("Customer 0220")
                .cpf("000.000.100-00")
                .birthDate(dateUtils.getDate("1950-11-06"))
                .telephone("(00)92211-1112")
                .build();
    }

    public Customer createMockCustomer3() {
        return Customer.builder()
                .id(1)
                .address(createMockAddress())
                .name("Customer 03330")
                .cpf("000.888.111-88")
                .birthDate(dateUtils.getDate("1985-05-07"))
                .telephone("(00)99911-1122")
                .build();
    }

    public Warehouse createMockWarehouse(String codigoMerchandise) {
        return Warehouse.builder()
                .id(1)
                .address(createMockAddress())
                .name("Warehouse 1")
                .isActive(true)
                .build();
    }

    public Warehouse createMockWarehouse2(String codigoMerchandise) {
        return Warehouse.builder()
                .id(2)
                .address(createMockAddress2())
                .name("Warehouse 2")
                .isActive(false)
                .build();
    }
}
