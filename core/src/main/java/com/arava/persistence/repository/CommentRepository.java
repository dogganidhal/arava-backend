package com.arava.persistence.repository;

import com.arava.persistence.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Nidhal Dogga
 * Date : 17/03/2020 14:35
 * All rights reserved.
 */

public interface CommentRepository extends JpaRepository<Comment, String> {

}
