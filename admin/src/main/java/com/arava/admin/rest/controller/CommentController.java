package com.arava.admin.rest.controller;

import com.arava.admin.rest.dto.CommentDto;
import com.arava.business.manager.ContentManager;
import com.arava.persistence.entity.Comment;
import com.arava.persistence.repository.CommentRepository;
import com.arava.server.annotation.Admin;
import com.arava.server.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Nidhal Dogga
 * Date : 27/03/2020 22:05
 * All rights reserved.
 */


@RestController
@RequestMapping("/comment")
public class CommentController {

  @Autowired
  private CommentRepository commentRepository;

  @Autowired
  private Mapper<Comment, CommentDto> commentMapper;

  @Autowired
  private ContentManager contentManager;

  @Admin
  @GetMapping("/pending")
  public List<CommentDto> listUnapprovedComments() {
    return commentRepository.findPendingComments().stream()
            .map(commentMapper::deepMap)
            .collect(Collectors.toList());
  }

  @Admin
  @GetMapping("/archive")
  public List<CommentDto> listArchiveComments() {
    return commentRepository.findArchiveComments().stream()
            .map(commentMapper::deepMap)
            .collect(Collectors.toList());
  }

  @Admin
  @DeleteMapping("/{commentId}")
  public void deleteComment(@PathVariable("commentId") String commentId) {
    contentManager.deleteComment(commentId);
  }

  @Admin
  @PutMapping("/{commentId}/approve")
  public void approveComment(@PathVariable("commentId") String commentId) {
    contentManager.approveComment(commentId);
  }

  @Admin
  @PutMapping("/{commentId}/decline")
  public void declineComment(@PathVariable("commentId") String commentId) {
    contentManager.declineComment(commentId);
  }

}
