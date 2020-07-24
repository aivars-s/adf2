package aivars.adf.product;

import org.apache.lucene.search.Query;
import org.hibernate.search.SearchFactory;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.query.dsl.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryExtImplTest {

    @InjectMocks
    private ProductRepositoryExtImpl productRepositoryExt;

    @Mock
    private FullTextEntityManager entityManager;

    @Test
    public void searchProducts() {
        String queryString = "some query words";

        List<Product> expected = getExpectedProducts();

        SearchFactory searchFactory = mock(SearchFactory.class);
        QueryContextBuilder queryContextBuilder = mock(QueryContextBuilder.class);
        EntityContext entityContext = mock(EntityContext.class);
        QueryBuilder queryBuilder = mock(QueryBuilder.class);
        SimpleQueryStringContext simpleQueryStringContext = mock(SimpleQueryStringContext.class);
        SimpleQueryStringMatchingContext matchingContext = mock(SimpleQueryStringMatchingContext.class);
        SimpleQueryStringTermination stringTermination = mock(SimpleQueryStringTermination.class);
        Query query = mock(Query.class);
        FullTextQuery fullTextQuery = mock(FullTextQuery.class);

        when(entityManager.getSearchFactory()).thenReturn(searchFactory);
        when(searchFactory.buildQueryBuilder()).thenReturn(queryContextBuilder);
        when(queryContextBuilder.forEntity(Product.class)).thenReturn(entityContext);
        when(entityContext.get()).thenReturn(queryBuilder);
        when(queryBuilder.simpleQueryString()).thenReturn(simpleQueryStringContext);
        when(simpleQueryStringContext.onFields(anyString(), any())).thenReturn(matchingContext);
        when(matchingContext.matching(anyString())).thenReturn(stringTermination);
        when(stringTermination.createQuery()).thenReturn(query);

        when(entityManager.createFullTextQuery(query, Product.class)).thenReturn(fullTextQuery);
        when(fullTextQuery.setMaxResults(anyInt())).thenReturn(fullTextQuery);
        when(fullTextQuery.getResultList()).thenReturn(expected);

        assertEquals(expected, productRepositoryExt.searchProducts(queryString));
    }

    private List<Product> getExpectedProducts() {
        Product product = new Product();
        product.setId(1L);
        return singletonList(product);
    }

}
