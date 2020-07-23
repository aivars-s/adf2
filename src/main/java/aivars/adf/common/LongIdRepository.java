package aivars.adf.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface LongIdRepository<T> extends JpaRepository<T, Long> {
}
