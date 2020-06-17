package hu.tbs.authApp.repository;

import hu.tbs.authApp.model.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PageRepository extends JpaRepository<Page, Long> {
    Page findByName(String name);
}
