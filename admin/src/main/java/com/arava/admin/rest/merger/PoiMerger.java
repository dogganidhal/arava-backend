package com.arava.admin.rest.merger;

import com.arava.persistence.entity.Poi;
import com.arava.persistence.merger.EntityMerger;
import org.springframework.stereotype.Component;

/**
 * Created by Nidhal Dogga
 * Date : 28/03/2020 16:32
 * All rights reserved.
 */

@Component
public class PoiMerger implements EntityMerger<Poi> {

  @Override
  public Poi merge(Poi src, Poi dest) {
    return Poi.builder()
            .id(pick(src.getId(), dest.getId()))
            .title(pick(src.getTitle(), dest.getTitle()))
            .owner(pick(src.getOwner(), dest.getOwner()))
            .mainImage(pick(src.getMainImage(), dest.getMainImage()))
            .draft(pick(src.getDraft(), dest.getDraft()))
            .featured(pick(src.getFeatured(), dest.getFeatured()))
            .theme(pick(src.getTheme(), dest.getTheme()))
            .details(pick(src.getDetails(), dest.getDetails()))
            .medias(pick(src.getMedias(), dest.getMedias()))
            .sponsored(pick(src.getSponsored(), dest.getSponsored()))
            .longitude(pick(src.getLongitude(), dest.getLongitude()))
            .latitude(pick(src.getLatitude(), dest.getLatitude()))
            .comments(pick(src.getComments(), dest.getComments()))
            .created(pick(src.getCreated(), dest.getCreated()))
            .description(pick(src.getDescription(), dest.getDescription()))
            .disabled(pick(src.getDisabled(), dest.getDisabled()))
            .island(pick(src.getIsland(), dest.getIsland()))
            .ratings(pick(src.getRatings(), dest.getRatings()))
            .updated(pick(src.getUpdated(), dest.getUpdated()))
            .build();
  }

}
