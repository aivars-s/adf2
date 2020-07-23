package aivars.adf.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerMapper mapper;
    private final CustomerRepository repository;

    @GetMapping("search")
    @ResponseBody
    public List<CustomerSearchResult> searchCustomers(@RequestParam String query) {
        return mapper.toCustomerSearchResult(repository.searchCustomers(query));
    }

}
