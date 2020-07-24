package aivars.adf.order;

import aivars.adf.customer.Customer_;
import aivars.adf.product.Product_;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class OrderSearchRequest {

    private List<Long> customer = new ArrayList<>();
    private List<Long> product = new ArrayList<>();

    public Specification<Order> toSpecification() {
        return ((root, query, cb) -> {
            Predicate customerPredicate = null;
            Predicate productPredicate = null;

            if (customer != null && !customer.isEmpty()) {
                customerPredicate = root.join(Order_.customer).get(Customer_.id).in(customer);
            }

            if (product != null && !product.isEmpty()) {
                productPredicate = root.join(Order_.items).join(OrderItem_.product).get(Product_.id).in(product);
            }

            if (customerPredicate != null) {
                if (productPredicate != null) {
                    return cb.and(customerPredicate, productPredicate);
                }
                return customerPredicate;
            }
            if (productPredicate != null) {
                return productPredicate;
            }
            return cb.and();
        });
    }

}
