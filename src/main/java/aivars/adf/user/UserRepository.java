package aivars.adf.user;

import aivars.adf.common.LongIdRepository;

import java.util.Optional;

public interface UserRepository extends LongIdRepository<User> {

    Optional<User> findByUsername(String username);

}
