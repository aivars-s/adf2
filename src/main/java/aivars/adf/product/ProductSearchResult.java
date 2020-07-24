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
public class ProductSearchResult {

    private Long id;
    private String text;
    private BigDecimal unitPrice;

}
