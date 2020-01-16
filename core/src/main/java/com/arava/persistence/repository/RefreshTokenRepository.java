package com.arava.persistence.repository;

import com.arava.persistence.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Nidhal Dogga
 * Date : 16/01/2020 08:21
 * All rights reserved.
 */

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {

}
