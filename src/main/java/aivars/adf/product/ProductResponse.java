package aivars.adf.product;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ProductResponse {

    private Long id;
    private String brand;
    private String category;
    private String name;
    private BigDecimal unitPrice;

}
