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

/** This is an auto generated class representing the Post35 type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Post35s", type = Model.Type.USER, version = 1)
@Index(name = "undefined", fields = {"postId","title"})
public final class Post35 implements Model {
  public static final QueryField POST_ID = field("Post35", "postId");
  public static final QueryField TITLE = field("Post35", "title");
  public static final QueryField DESC = field("Post35", "desc");
  private final @ModelField(targetType="ID", isRequired = true) String postId;
  private final @ModelField(targetType="String", isRequired = true) String title;
  private final @ModelField(targetType="Comment35") @HasMany(associatedWith = "post", type = Comment35.class) List<Comment35> comments = null;
  private final @ModelField(targetType="String") String desc;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  private Post35Identifier post35Identifier;
  public Post35Identifier resolveIdentifier() {
    if (post35Identifier == null) {
      this.post35Identifier = new Post35Identifier(postId, title);
    }
    return post35Identifier;
  }
  
  public String getPostId() {
      return postId;
  }
  
  public String getTitle() {
      return title;
  }
  
  public List<Comment35> getComments() {
      return comments;
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
  
  private Post35(String postId, String title, String desc) {
    this.postId = postId;
    this.title = title;
    this.desc = desc;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Post35 post35 = (Post35) obj;
      return ObjectsCompat.equals(getPostId(), post35.getPostId()) &&
              ObjectsCompat.equals(getTitle(), post35.getTitle()) &&
              ObjectsCompat.equals(getDesc(), post35.getDesc()) &&
              ObjectsCompat.equals(getCreatedAt(), post35.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), post35.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getPostId())
      .append(getTitle())
      .append(getDesc())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Post35 {")
      .append("postId=" + String.valueOf(getPostId()) + ", ")
      .append("title=" + String.valueOf(getTitle()) + ", ")
      .append("desc=" + String.valueOf(getDesc()) + ", ")
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
      title,
      desc);
  }
  public interface PostIdStep {
    TitleStep postId(String postId);
  }
  

  public interface TitleStep {
    BuildStep title(String title);
  }
  

  public interface BuildStep {
    Post35 build();
    BuildStep desc(String desc);
  }
  

  public static class Builder implements PostIdStep, TitleStep, BuildStep {
    private String postId;
    private String title;
    private String desc;
    @Override
     public Post35 build() {
        
        return new Post35(
          postId,
          title,
          desc);
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
    
    @Override
     public BuildStep desc(String desc) {
        this.desc = desc;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String postId, String title, String desc) {
      super.postId(postId)
        .title(title)
        .desc(desc);
    }
    
    @Override
     public CopyOfBuilder postId(String postId) {
      return (CopyOfBuilder) super.postId(postId);
    }
    
    @Override
     public CopyOfBuilder title(String title) {
      return (CopyOfBuilder) super.title(title);
    }
    
    @Override
     public CopyOfBuilder desc(String desc) {
      return (CopyOfBuilder) super.desc(desc);
    }
  }
  

  public static class Post35Identifier extends ModelIdentifier<Post35> {
    private static final long serialVersionUID = 1L;
    public Post35Identifier(String postId, String title) {
      super(postId, title);
    }
  }
  
}
