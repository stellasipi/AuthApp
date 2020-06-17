package hu.tbs.authApp.repository;

import hu.tbs.authApp.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session,Long> {
}
