package aivars.adf.customer;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerResponse toCustomerResponse(Customer source);

    @Mapping(target = "text", expression = "java(String.format(\"%s %s <%s>\"," +
            "source.getFirstName(), source.getLastName(), source.getEmail()))")
    CustomerSearchResult toCustomerSearchResult(Customer source);
    List<CustomerSearchResult> toCustomerSearchResult(Iterable<Customer> source);

}
