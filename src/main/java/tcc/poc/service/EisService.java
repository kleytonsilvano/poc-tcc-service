package tcc.poc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tcc.poc.models.*;
import tcc.poc.models.enums.StatusMerchandise;
import tcc.poc.utils.MockUtils;

import java.util.Arrays;
import java.util.List;

/**
 *  Esta classe seria responsável por realizar todas integrações com o EIS
 */
@Service
public class EisService {

    @Autowired
    private MockUtils mockUtils;

    public List<Merchandise> findMerchandises(String xCpfCustomer) {
        return Arrays.asList(mockUtils.createMockMerchandise(xCpfCustomer),
                             mockUtils.createMockMerchandise2(xCpfCustomer));
    }

    public List<Deposit> findDepositByMerchandise(Integer codigoMerchandise) {
        return Arrays.asList(mockUtils.createMockDeposit(codigoMerchandise), mockUtils.createMockDeposit2(codigoMerchandise));
    }


    public List<Customer> findCustomers() {
        return Arrays.asList(mockUtils.createMockCustomer(), mockUtils.createMockCustomer2(), mockUtils.createMockCustomer3());
    }

    public List<Warehouse> findWarehouseByIdMerchandise(String codigoMerchandise) {
        return Arrays.asList(mockUtils.createMockWarehouse(codigoMerchandise), mockUtils.createMockWarehouse2(codigoMerchandise));
    }

    public List<Supplier> findSuppliers() {
        return Arrays.asList(mockUtils.createMockSupplier(), mockUtils.createMockSupplier1());
    }
}
