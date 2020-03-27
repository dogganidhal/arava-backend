package com.arava.persistence.repository;

import com.arava.persistence.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Nidhal Dogga
 * Date : 17/03/2020 14:35
 * All rights reserved.
 */

public interface CommentRepository extends JpaRepository<Comment, String> {

  @Query("SELECT c FROM Comment c WHERE c.status = 'UNKNOWN' OR c.status IS NULL")
  List<Comment> findPendingComments();

}
