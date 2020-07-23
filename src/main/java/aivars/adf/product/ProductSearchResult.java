package aivars.adf.product;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductSearchResult {

    private Long id;
    private String text;
    private BigDecimal unitPrice;

}
