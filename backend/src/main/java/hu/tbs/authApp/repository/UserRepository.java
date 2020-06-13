package hu.tbs.authApp.repository;

import hu.tbs.authApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
