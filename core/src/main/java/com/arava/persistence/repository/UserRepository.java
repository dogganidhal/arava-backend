package com.arava.persistence.repository;

import com.arava.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Nidhal Dogga
 * Date : 14/01/2020 08:04
 * All rights reserved.
 */

public interface UserRepository extends JpaRepository<User, String> {

  @Query("SELECT u FROM User u WHERE u.email = ?1")
  User findByEmail(String email);

  @Query("SELECT count(u) > 0 FROM User u WHERE u.email = ?1")
  Boolean existsByEmail(String email);

  @Query("SELECT u FROM User u " +
          "WHERE LOWER(u.firstName) LIKE %?1% " +
          "OR LOWER(u.lastName) LIKE %?1% " +
          "OR LOWER(u.email) LIKE %?1%"
  )
  List<User> queryUsers(String query);

}
