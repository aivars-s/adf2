package aivars.adf.product;

import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class ProductRepositoryExtImpl implements ProductRepositoryExt {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> searchProducts(String query) {
        return Search.getFullTextEntityManager(entityManager).createFullTextQuery(
                getQueryBuilder()
                        .simpleQueryString()
                        .onFields("brand", "category", "name")
                        .matching(query)
                        .createQuery(),
                Product.class
        ).setMaxResults(50).getResultList();
    }

    private QueryBuilder getQueryBuilder() {
        return Search
                .getFullTextEntityManager(entityManager)
                .getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Product.class)
                .get();
    }

}
