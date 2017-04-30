package hello;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by asxat on 2/18/17.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
}
