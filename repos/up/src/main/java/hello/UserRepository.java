package hello;

/**
 * Created by Meruyert on 29.04.17.
 */
        import org.springframework.data.repository.CrudRepository;

        import hello.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Long> {
        User findByName(String name);
}