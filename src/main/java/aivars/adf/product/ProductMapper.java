package aivars.adf.product;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductResponse toProductResponse(Product source);

    @Mapping(target = "text", expression = "java(String.format(\"%s, %s %s (%s)\"," +
                    "source.getCategory(), source.getBrand(), source.getName(), source.getUnitPrice()))")
    ProductSearchResult toProductSearchResult(Product source);
    List<ProductSearchResult> toProductSearchResult(Iterable<Product> source);

}
