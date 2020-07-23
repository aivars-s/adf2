package aivars.adf.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductMapper mapper;
    private final ProductRepository repository;

    @GetMapping("search")
    @ResponseBody
    public List<ProductSearchResult> searchProducts(@RequestParam String query) {
        return mapper.toProductSearchResult(repository.searchProducts(query));
    }

}
