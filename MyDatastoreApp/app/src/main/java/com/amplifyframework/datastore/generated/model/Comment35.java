package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.BelongsTo;
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

/** This is an auto generated class representing the Comment35 type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Comment35s", type = Model.Type.USER, version = 1)
@Index(name = "undefined", fields = {"commentId","content"})
@Index(name = "byPost", fields = {"postId","postTitle"})
public final class Comment35 implements Model {
  public static final QueryField COMMENT_ID = field("Comment35", "commentId");
  public static final QueryField CONTENT = field("Comment35", "content");
  public static final QueryField POST = field("Comment35", "postId");
  public static final QueryField DESC = field("Comment35", "desc");
  private final @ModelField(targetType="ID", isRequired = true) String commentId;
  private final @ModelField(targetType="String", isRequired = true) String content;
  private final @ModelField(targetType="Post35") @BelongsTo(targetName = "postId", targetNames = {"postId", "postTitle"}, type = Post35.class) Post35 post;
  private final @ModelField(targetType="String") String desc;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  private Comment35Identifier comment35Identifier;
  public Comment35Identifier resolveIdentifier() {
    if (comment35Identifier == null) {
      this.comment35Identifier = new Comment35Identifier(commentId, content);
    }
    return comment35Identifier;
  }
  
  public String getCommentId() {
      return commentId;
  }
  
  public String getContent() {
      return content;
  }
  
  public Post35 getPost() {
      return post;
  }
  
  public String getDesc() {
      return desc;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Comment35(String commentId, String content, Post35 post, String desc) {
    this.commentId = commentId;
    this.content = content;
    this.post = post;
    this.desc = desc;
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
              ObjectsCompat.equals(getContent(), comment35.getContent()) &&
              ObjectsCompat.equals(getPost(), comment35.getPost()) &&
              ObjectsCompat.equals(getDesc(), comment35.getDesc()) &&
              ObjectsCompat.equals(getCreatedAt(), comment35.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), comment35.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getCommentId())
      .append(getContent())
      .append(getPost())
      .append(getDesc())
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
      .append("content=" + String.valueOf(getContent()) + ", ")
      .append("post=" + String.valueOf(getPost()) + ", ")
      .append("desc=" + String.valueOf(getDesc()) + ", ")
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
      post,
      desc);
  }
  public interface CommentIdStep {
    ContentStep commentId(String commentId);
  }
  

  public interface ContentStep {
    BuildStep content(String content);
  }
  

  public interface BuildStep {
    Comment35 build();
    BuildStep post(Post35 post);
    BuildStep desc(String desc);
  }
  

  public static class Builder implements CommentIdStep, ContentStep, BuildStep {
    private String commentId;
    private String content;
    private Post35 post;
    private String desc;
    @Override
     public Comment35 build() {
        
        return new Comment35(
          commentId,
          content,
          post,
          desc);
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
     public BuildStep post(Post35 post) {
        this.post = post;
        return this;
    }
    
    @Override
     public BuildStep desc(String desc) {
        this.desc = desc;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String commentId, String content, Post35 post, String desc) {
      super.commentId(commentId)
        .content(content)
        .post(post)
        .desc(desc);
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
     public CopyOfBuilder post(Post35 post) {
      return (CopyOfBuilder) super.post(post);
    }
    
    @Override
     public CopyOfBuilder desc(String desc) {
      return (CopyOfBuilder) super.desc(desc);
    }
  }
  

  public static class Comment35Identifier extends ModelIdentifier<Comment35> {
    private static final long serialVersionUID = 1L;
    public Comment35Identifier(String commentId, String content) {
      super(commentId, content);
    }
  }
  
}
