package aivars.adf.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.min;

@Getter
@Setter
public class OrderCreateRequest {

    @NotNull
    private Long customer;

    @NotEmpty
    private List<@NotNull Long> product;

    @NotEmpty
    private List<@NotNull @Min(1) Integer> amount;

    public List<CreateOrderItemRequest> getCreateOrderItemRequests() {
        Map<Long, Integer> uniqueAmounts = new HashMap<>();

        IntStream.range(0, min(product.size(), amount.size()))
            .forEach(i -> {
                long productId = product.get(i);
                if (uniqueAmounts.containsKey(productId)) {
                    uniqueAmounts.compute(productId, (key, value) -> value + amount.get(i));
                } else {
                    uniqueAmounts.put(productId, amount.get(i));
                }
            });

        return uniqueAmounts.keySet().stream()
                .map(key -> new CreateOrderItemRequest(key, uniqueAmounts.get(key)))
                .collect(Collectors.toList());
    }

    @AllArgsConstructor
    @Getter
    public static class CreateOrderItemRequest {

        private final long product;
        private final int amount;

    }
}
