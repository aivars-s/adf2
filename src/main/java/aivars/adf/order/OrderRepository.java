package aivars.adf.order;

import aivars.adf.common.EntityGraphLongIdRepository;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaSpecificationExecutor;

public interface OrderRepository extends EntityGraphLongIdRepository<Order>,
        EntityGraphJpaSpecificationExecutor<Order> {
}
