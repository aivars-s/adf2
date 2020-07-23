package aivars.adf.product;

import aivars.adf.common.LongIdEntity;
import aivars.adf.order.OrderItem;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
@Indexed
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@ToString(callSuper = true, exclude = "orderItems")
public class Product extends LongIdEntity {

    @Column(name = "brand", nullable = false)
    @Field(analyzer = @Analyzer(definition = "nonLocalized"))
    private String brand;

    @Column(name = "category", nullable = false)
    @Field(analyzer = @Analyzer(definition = "localized"))
    private String category;

    @Column(name = "name", nullable = false)
    @Field(analyzer = @Analyzer(definition = "nonLocalized"))
    private String name;

    @Column(name = "unit_price", nullable = false)
    private BigDecimal unitPrice;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private Set<OrderItem> orderItems = new HashSet<>();

}
