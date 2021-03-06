package tcc.poc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tcc.poc.models.*;
import tcc.poc.models.enums.StatusMerchandise;
import tcc.poc.utils.MockUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  Esta classe seria responsável por realizar todas integrações com o EIS
 */
@Service
public class EisService {

    @Autowired
    private MockUtils mockUtils;

    public Merchandise findMerchandiseByIdAndCpf(Integer id, String xCpfCustomer) {
        for (Merchandise m : findMerchandises(xCpfCustomer)) {
            if(m.getId().equals(id)) {
                return m;
            }
        }
        return null;
    }

    public Merchandise findMerchandiseCpfCode(String cpf, String code) {
        List<Merchandise> merchandises = findMerchandises(cpf);
        for (Merchandise m : merchandises) {
            if(m.getCode().equals(code)) {
                return m;
            }
        }
        return null;
    }

    public List<Merchandise> findMerchandises(String xCpfCustomer) {
        return Arrays.asList(mockUtils.createMockMerchandise(xCpfCustomer),
                             mockUtils.createMockMerchandise2(xCpfCustomer),
                             mockUtils.createMockMerchandise3(xCpfCustomer),
                             mockUtils.createMockMerchandise4(xCpfCustomer));
    }

    public List<Deposit> findDepositByMerchandise(Integer codigoMerchandise) {
        return Arrays.asList(mockUtils.createMockDeposit(codigoMerchandise), mockUtils.createMockDeposit2(codigoMerchandise));
    }

    public Customer findCustomerByCpf(String cpf) {
        for (Customer c : findCustomers()) {
            if(removeAlpha(c.getCpf()).equals(removeAlpha(cpf))) {
                return c;
            }
        }
        return null;
    }

    public List<Customer> findCustomers() {
        return Arrays.asList(mockUtils.createMockCustomer(), mockUtils.createMockCustomer2(), mockUtils.createMockCustomer3(), mockUtils.createMockCustomer4());
    }

    public List<Warehouse> findWarehouseByIdMerchandise(Integer codigoMerchandise) {
        List<Deposit> deposits = findDepositByMerchandise(codigoMerchandise);
        if(deposits != null && !deposits.isEmpty()) {
            List<Warehouse> retorno = new ArrayList();
            deposits.forEach(d -> retorno.add(d.getWarehouse()));
            return retorno;
        }
        return null;
    }

    public List<Warehouse> findWarehouses() {
        return Arrays.asList(mockUtils.createMockWarehouse(),
                             mockUtils.createMockWarehouse2(),
                             mockUtils.createMockWarehouse3(),
                             mockUtils.createMockWarehouse4(),
                             mockUtils.createMockWarehouse5());
    }

    public Warehouse findWarehouse(Integer id) {
        for (Warehouse w : findWarehouses()) {
            if(w.getId().equals(id)) {
                return w;
            }
        }
        return null;
    }


    public List<Supplier> findSuppliers() {
        return Arrays.asList(mockUtils.createMockSupplier(), mockUtils.createMockSupplier1(), mockUtils.createMockSupplier2());
    }

    public Supplier findSupplierByCnpj(String cnpj) {
        for (Supplier s : findSuppliers()) {
            if(removeAlpha(s.getCnpj()).equals(removeAlpha(cnpj))) {
                return s;
            }
        }
        return null;
    }

    public String removeAlpha(String string) {
        return string.replaceAll("[^\\d]", "");
    }
}
