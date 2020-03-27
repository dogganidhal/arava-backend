package com.arava.persistence.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

/**
 * Created by Nidhal Dogga
 * Date : 14/01/2020 23:45
 * All rights reserved.
 */


@Data
@SuperBuilder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Comment extends AbstractEntity {

  @ManyToOne(cascade = CascadeType.ALL)
  private User author;

  @ManyToOne(cascade = CascadeType.ALL)
  private Poi poi;

  @Column(length = 16384)
  private String content;

  @Column
  @Builder.Default
  @Enumerated(EnumType.STRING)
  private CommentStatus status = CommentStatus.UNKNOWN;

}
