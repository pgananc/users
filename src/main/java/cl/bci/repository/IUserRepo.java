package cl.bci.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cl.bci.model.User;

public interface IUserRepo extends JpaRepository<User, UUID> {
	User findOneByEmail(String email);

	@Query("SELECT u.name, u.password FROM User u WHERE u.name = :name")
	Object findOneByName(@Param("name") String name);
	
}
