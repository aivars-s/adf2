package aivars.adf.bootstrap;

import lombok.SneakyThrows;
import org.hibernate.search.jpa.Search;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
public class InitHibernateSearch implements ApplicationListener<ApplicationReadyEvent> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    @SneakyThrows
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Search.getFullTextEntityManager(entityManager).createIndexer().startAndWait();
    }

}
