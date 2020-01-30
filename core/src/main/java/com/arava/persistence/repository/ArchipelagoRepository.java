package com.arava.persistence.repository;

import com.arava.persistence.entity.Archipelago;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Nidhal Dogga
 * Date : 28/01/2020 19:17
 * All rights reserved.
 */

public interface ArchipelagoRepository extends JpaRepository<Archipelago, String> {
}
