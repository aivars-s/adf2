package aivars.adf.product;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @InjectMocks
    private ProductController controller;

    @Mock
    private ProductMapper mapper;

    @Mock
    private ProductRepository repository;

    @Test
    public void searchProducts() {
        List<Product> products = singletonList(new Product());

        List<ProductSearchResult> results = singletonList(new ProductSearchResult());

        when(mapper.toProductSearchResult(products)).thenReturn(results);
        when(repository.searchProducts(anyString())).thenReturn(products);

        assertThat(controller.searchProducts("some query"))
                .containsExactlyInAnyOrder(new ProductSearchResult());
    }

}
