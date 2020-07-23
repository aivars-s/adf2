package aivars.adf.customer;

import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class CustomerRepositoryExtImpl implements CustomerRepositoryExt {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public List<Customer> searchCustomers(String query) {
        return Search.getFullTextEntityManager(entityManager).createFullTextQuery(
                getQueryBuilder()
                        .simpleQueryString()
                        .onFields("first_name", "last_name", "email")
                        .matching(query)
                        .createQuery(),
                Customer.class
        ).setMaxResults(50).getResultList();
    }

    private QueryBuilder getQueryBuilder() {
        return Search
                .getFullTextEntityManager(entityManager)
                .getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Customer.class)
                .get();
    }

}
