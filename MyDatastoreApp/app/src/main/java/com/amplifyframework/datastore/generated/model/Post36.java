package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.HasMany;
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

/** This is an auto generated class representing the Post36 type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Post36s", type = Model.Type.USER, version = 1)
@Index(name = "undefined", fields = {"postId","title"})
public final class Post36 implements Model {
  public static final QueryField POST_ID = field("Post36", "postId");
  public static final QueryField TITLE = field("Post36", "title");
  private final @ModelField(targetType="ID", isRequired = true) String postId;
  private final @ModelField(targetType="String", isRequired = true) String title;
  private final @ModelField(targetType="Comment36") @HasMany(associatedWith = "postId", type = Comment36.class) List<Comment36> comments = null;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  private Post36Identifier post36Identifier;
  public Post36Identifier resolveIdentifier() {
    if (post36Identifier == null) {
      this.post36Identifier = new Post36Identifier(postId, title);
    }
    return post36Identifier;
  }
  
  public String getPostId() {
      return postId;
  }
  
  public String getTitle() {
      return title;
  }
  
  public List<Comment36> getComments() {
      return comments;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Post36(String postId, String title) {
    this.postId = postId;
    this.title = title;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Post36 post36 = (Post36) obj;
      return ObjectsCompat.equals(getPostId(), post36.getPostId()) &&
              ObjectsCompat.equals(getTitle(), post36.getTitle()) &&
              ObjectsCompat.equals(getCreatedAt(), post36.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), post36.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getPostId())
      .append(getTitle())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Post36 {")
      .append("postId=" + String.valueOf(getPostId()) + ", ")
      .append("title=" + String.valueOf(getTitle()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static PostIdStep builder() {
      return new Builder();
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(postId,
      title);
  }
  public interface PostIdStep {
    TitleStep postId(String postId);
  }
  

  public interface TitleStep {
    BuildStep title(String title);
  }
  

  public interface BuildStep {
    Post36 build();
  }
  

  public static class Builder implements PostIdStep, TitleStep, BuildStep {
    private String postId;
    private String title;
    @Override
     public Post36 build() {
        
        return new Post36(
          postId,
          title);
    }
    
    @Override
     public TitleStep postId(String postId) {
        Objects.requireNonNull(postId);
        this.postId = postId;
        return this;
    }
    
    @Override
     public BuildStep title(String title) {
        Objects.requireNonNull(title);
        this.title = title;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String postId, String title) {
      super.postId(postId)
        .title(title);
    }
    
    @Override
     public CopyOfBuilder postId(String postId) {
      return (CopyOfBuilder) super.postId(postId);
    }
    
    @Override
     public CopyOfBuilder title(String title) {
      return (CopyOfBuilder) super.title(title);
    }
  }
  

  public static class Post36Identifier extends ModelIdentifier<Post36> {
    private static final long serialVersionUID = 1L;
    public Post36Identifier(String postId, String title) {
      super(postId, title);
    }
  }
  
}
