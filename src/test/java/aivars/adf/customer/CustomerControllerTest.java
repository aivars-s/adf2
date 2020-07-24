package aivars.adf.customer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

    @InjectMocks
    private CustomerController controller;

    @Mock
    private CustomerMapper mapper;

    @Mock
    private CustomerRepository repository;

    @Test
    public void searchCustomers() {
        List<Customer> customers = singletonList(new Customer());

        List<CustomerSearchResult> results = singletonList(new CustomerSearchResult());

        when(mapper.toCustomerSearchResult(customers)).thenReturn(results);
        when(repository.searchCustomers(anyString())).thenReturn(customers);

        assertThat(controller.searchCustomers("some query"))
                .containsExactlyInAnyOrder(new CustomerSearchResult());
    }

}
