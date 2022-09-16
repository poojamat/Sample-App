package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.core.model.ModelIdentifier;

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

/** This is an auto generated class representing the Comment36 type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Comment36s", type = Model.Type.USER, version = 1)
@Index(name = "undefined", fields = {"commentId","content"})
@Index(name = "byPost", fields = {"postId","postTitle"})
public final class Comment36 implements Model {
  public static final QueryField COMMENT_ID = field("Comment36", "commentId");
  public static final QueryField CONTENT = field("Comment36", "content");
  public static final QueryField POST_ID = field("Comment36", "postId");
  public static final QueryField POST_TITLE = field("Comment36", "postTitle");
  private final @ModelField(targetType="ID", isRequired = true) String commentId;
  private final @ModelField(targetType="String", isRequired = true) String content;
  private final @ModelField(targetType="ID") String postId;
  private final @ModelField(targetType="String") String postTitle;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  private Comment36Identifier comment36Identifier;
  public Comment36Identifier resolveIdentifier() {
    if (comment36Identifier == null) {
      this.comment36Identifier = new Comment36Identifier(commentId, content);
    }
    return comment36Identifier;
  }
  
  public String getCommentId() {
      return commentId;
  }
  
  public String getContent() {
      return content;
  }
  
  public String getPostId() {
      return postId;
  }
  
  public String getPostTitle() {
      return postTitle;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Comment36(String commentId, String content, String postId, String postTitle) {
    this.commentId = commentId;
    this.content = content;
    this.postId = postId;
    this.postTitle = postTitle;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Comment36 comment36 = (Comment36) obj;
      return ObjectsCompat.equals(getCommentId(), comment36.getCommentId()) &&
              ObjectsCompat.equals(getContent(), comment36.getContent()) &&
              ObjectsCompat.equals(getPostId(), comment36.getPostId()) &&
              ObjectsCompat.equals(getPostTitle(), comment36.getPostTitle()) &&
              ObjectsCompat.equals(getCreatedAt(), comment36.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), comment36.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getCommentId())
      .append(getContent())
      .append(getPostId())
      .append(getPostTitle())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Comment36 {")
      .append("commentId=" + String.valueOf(getCommentId()) + ", ")
      .append("content=" + String.valueOf(getContent()) + ", ")
      .append("postId=" + String.valueOf(getPostId()) + ", ")
      .append("postTitle=" + String.valueOf(getPostTitle()) + ", ")
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
      content,
      postId,
      postTitle);
  }
  public interface CommentIdStep {
    ContentStep commentId(String commentId);
  }
  

  public interface ContentStep {
    BuildStep content(String content);
  }
  

  public interface BuildStep {
    Comment36 build();
    BuildStep postId(String postId);
    BuildStep postTitle(String postTitle);
  }
  

  public static class Builder implements CommentIdStep, ContentStep, BuildStep {
    private String commentId;
    private String content;
    private String postId;
    private String postTitle;
    @Override
     public Comment36 build() {
        
        return new Comment36(
          commentId,
          content,
          postId,
          postTitle);
    }
    
    @Override
     public ContentStep commentId(String commentId) {
        Objects.requireNonNull(commentId);
        this.commentId = commentId;
        return this;
    }
    
    @Override
     public BuildStep content(String content) {
        Objects.requireNonNull(content);
        this.content = content;
        return this;
    }
    
    @Override
     public BuildStep postId(String postId) {
        this.postId = postId;
        return this;
    }
    
    @Override
     public BuildStep postTitle(String postTitle) {
        this.postTitle = postTitle;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String commentId, String content, String postId, String postTitle) {
      super.commentId(commentId)
        .content(content)
        .postId(postId)
        .postTitle(postTitle);
    }
    
    @Override
     public CopyOfBuilder commentId(String commentId) {
      return (CopyOfBuilder) super.commentId(commentId);
    }
    
    @Override
     public CopyOfBuilder content(String content) {
      return (CopyOfBuilder) super.content(content);
    }
    
    @Override
     public CopyOfBuilder postId(String postId) {
      return (CopyOfBuilder) super.postId(postId);
    }
    
    @Override
     public CopyOfBuilder postTitle(String postTitle) {
      return (CopyOfBuilder) super.postTitle(postTitle);
    }
  }
  

  public static class Comment36Identifier extends ModelIdentifier<Comment36> {
    private static final long serialVersionUID = 1L;
    public Comment36Identifier(String commentId, String content) {
      super(commentId, content);
    }
  }
  
}
