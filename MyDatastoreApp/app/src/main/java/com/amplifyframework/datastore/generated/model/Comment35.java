package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.BelongsTo;
import com.amplifyframework.core.model.temporal.Temporal;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the Comment35 type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Comment35s", type = Model.Type.USER, version = 1)
@Index(name = "undefined", fields = {"commentId"})
@Index(name = "byPost", fields = {"postId"})
public final class Comment35 implements Model {
  public static final QueryField COMMENT_ID = field("Comment35", "commentId");
  public static final QueryField POST = field("Comment35", "postId");
  private final @ModelField(targetType="ID", isRequired = true) String commentId;
  private final @ModelField(targetType="Post35") @BelongsTo(targetName = "postId", targetNames = {"postId", "postTitle"}, type = Post35.class) Post35 post;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String resolveIdentifier() {
    return commentId;
  }
  
  public String getCommentId() {
      return commentId;
  }
  
  public Post35 getPost() {
      return post;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Comment35(String commentId, Post35 post) {
    this.commentId = commentId;
    this.post = post;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Comment35 comment35 = (Comment35) obj;
      return ObjectsCompat.equals(getCommentId(), comment35.getCommentId()) &&
              ObjectsCompat.equals(getPost(), comment35.getPost()) &&
              ObjectsCompat.equals(getCreatedAt(), comment35.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), comment35.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getCommentId())
      .append(getPost())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Comment35 {")
      .append("commentId=" + String.valueOf(getCommentId()) + ", ")
      .append("post=" + String.valueOf(getPost()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static CommentIdStep builder() {
      return new Builder();
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(commentId,
      post);
  }
  public interface CommentIdStep {
    BuildStep commentId(String commentId);
  }
  

  public interface BuildStep {
    Comment35 build();
    BuildStep post(Post35 post);
  }
  

  public static class Builder implements CommentIdStep, BuildStep {
    private String commentId;
    private Post35 post;
    @Override
     public Comment35 build() {
        
        return new Comment35(
          commentId,
          post);
    }
    
    @Override
     public BuildStep commentId(String commentId) {
        Objects.requireNonNull(commentId);
        this.commentId = commentId;
        return this;
    }
    
    @Override
     public BuildStep post(Post35 post) {
        this.post = post;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String commentId, Post35 post) {
      super.commentId(commentId)
        .post(post);
    }
    
    @Override
     public CopyOfBuilder commentId(String commentId) {
      return (CopyOfBuilder) super.commentId(commentId);
    }
    
    @Override
     public CopyOfBuilder post(Post35 post) {
      return (CopyOfBuilder) super.post(post);
    }
  }
  
}
