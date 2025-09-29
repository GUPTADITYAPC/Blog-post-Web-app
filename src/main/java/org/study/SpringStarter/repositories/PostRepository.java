package org.study.SpringStarter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.study.SpringStarter.models.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
