package aivars.adf.customer;

import aivars.adf.common.LongIdEntity;
import aivars.adf.order.Order;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customers")
@Indexed
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@ToString(callSuper = true, exclude = "orders")
public class Customer extends LongIdEntity {

    @Column(name = "first_name", nullable = false)
    @Field(analyzer = @Analyzer(definition = "localized"))
    private String firstName;

    @Field(analyzer = @Analyzer(definition = "localized"))
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Field(analyzer = @Analyzer(definition = "nonLocalized"))
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private Set<Order> orders = new HashSet<>();

}
