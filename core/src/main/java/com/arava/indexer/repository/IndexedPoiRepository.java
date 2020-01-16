package com.arava.indexer.repository;

import com.arava.indexer.entity.IndexedPoi;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Nidhal Dogga
 * Date : 17/01/2020 08:16
 * All rights reserved.
 */

public interface IndexedPoiRepository extends MongoRepository<IndexedPoi, String> {
}
