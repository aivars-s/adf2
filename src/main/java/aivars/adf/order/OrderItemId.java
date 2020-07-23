package aivars.adf.order;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class OrderItemId implements Serializable {

    private Long orderId;
    private Long productId;

}
