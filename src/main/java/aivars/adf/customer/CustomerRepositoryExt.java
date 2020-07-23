package aivars.adf.customer;

import java.util.List;

public interface CustomerRepositoryExt {

    List<Customer> searchCustomers(String query);

}
