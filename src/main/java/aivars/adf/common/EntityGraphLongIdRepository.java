package aivars.adf.common;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface EntityGraphLongIdRepository<T> extends EntityGraphJpaRepository<T, Long> {
}
