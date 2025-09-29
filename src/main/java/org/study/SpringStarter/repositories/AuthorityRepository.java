package org.study.SpringStarter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.study.SpringStarter.models.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {

}
