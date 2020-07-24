package aivars.adf.customer;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CustomerResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;

}
