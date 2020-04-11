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
            .draft(pick(src.isDraft(), dest.isDraft()))
            .featured(pick(src.isFeatured(), dest.isFeatured()))
            .theme(pick(src.getTheme(), dest.getTheme()))
            .details(pick(src.getDetails(), dest.getDetails()))
            .medias(pick(src.getMedias(), dest.getMedias()))
            .sponsored(pick(src.isSponsored(), dest.isSponsored()))
            .activity(pick(src.isActivity(), dest.isActivity()))
            .premium(pick(src.isPremium(), dest.isPremium()))
            .longitude(pick(src.getLongitude(), dest.getLongitude()))
            .latitude(pick(src.getLatitude(), dest.getLatitude()))
            .ratings(pick(src.getRatings(), dest.getRatings()))
            .created(pick(src.getCreated(), dest.getCreated()))
            .description(pick(src.getDescription(), dest.getDescription()))
            .disabled(pick(src.isDisabled(), dest.isDisabled()))
            .island(pick(src.getIsland(), dest.getIsland()))
            .ratings(pick(src.getRatings(), dest.getRatings()))
            .updated(pick(src.getUpdated(), dest.getUpdated()))
            .build();
  }

}
