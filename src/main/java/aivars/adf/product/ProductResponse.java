package aivars.adf.product;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductResponse {

    private Long id;
    private String brand;
    private String category;
    private String name;
    private BigDecimal unitPrice;

}
