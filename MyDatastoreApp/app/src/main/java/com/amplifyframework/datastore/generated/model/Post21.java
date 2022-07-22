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

/** This is an auto generated class representing the Post21 type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Post21s", type = Model.Type.USER, version = 1)
@Index(name = "undefined", fields = {"id","title"})
public final class Post21 implements Model {
  public static final QueryField ID = field("Post21", "id");
  public static final QueryField TITLE = field("Post21", "title");
  public static final QueryField DESC = field("Post21", "desc");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String title;
  private final @ModelField(targetType="Comment21") @HasMany(associatedWith = "post21CommentsId", type = Comment21.class) List<Comment21> comments = null;
  private final @ModelField(targetType="String") String desc;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  private Post21Identifier post21Identifier;
  public Post21Identifier resolveIdentifier() {
    if (post21Identifier == null) {
      this.post21Identifier = new Post21Identifier(id, title);
    }
    return post21Identifier;
  }
  
  public String getId() {
      return id;
  }
  
  public String getTitle() {
      return title;
  }
  
  public List<Comment21> getComments() {
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
  
  private Post21(String id, String title, String desc) {
    this.id = id;
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
      Post21 post21 = (Post21) obj;
      return ObjectsCompat.equals(getId(), post21.getId()) &&
              ObjectsCompat.equals(getTitle(), post21.getTitle()) &&
              ObjectsCompat.equals(getDesc(), post21.getDesc()) &&
              ObjectsCompat.equals(getCreatedAt(), post21.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), post21.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
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
      .append("Post21 {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("title=" + String.valueOf(getTitle()) + ", ")
      .append("desc=" + String.valueOf(getDesc()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static IdStep builder() {
      return new Builder();
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      title,
      desc);
  }
  public interface IdStep {
    TitleStep id(String id);
  }
  

  public interface TitleStep {
    BuildStep title(String title);
  }
  

  public interface BuildStep {
    Post21 build();
    BuildStep desc(String desc);
  }
  

  public static class Builder implements IdStep, TitleStep, BuildStep {
    private String id;
    private String title;
    private String desc;
    @Override
     public Post21 build() {
        
        return new Post21(
          id,
          title,
          desc);
    }
    
    @Override
     public TitleStep id(String id) {
        Objects.requireNonNull(id);
        this.id = id;
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
    private CopyOfBuilder(String id, String title, String desc) {
      super.id(id)
        .title(title)
        .desc(desc);
    }
    
    @Override
     public CopyOfBuilder id(String id) {
      return (CopyOfBuilder) super.id(id);
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
  

  public static class Post21Identifier extends ModelIdentifier<Post21> {
    private static final long serialVersionUID = 1L;
    public Post21Identifier(String id, String title) {
      super(id, title);
    }
  }
  
}
