package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.HasMany;
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

/** This is an auto generated class representing the Post31 type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Post31s", type = Model.Type.USER, version = 1)
public final class Post31 implements Model {
  public static final QueryField ID = field("Post31", "id");
  public static final QueryField TITLE = field("Post31", "title");
  public static final QueryField CONTENT = field("Post31", "content");
  public static final QueryField DESC = field("Post31", "desc");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String title;
  private final @ModelField(targetType="String") String content;
  private final @ModelField(targetType="PostTags31") @HasMany(associatedWith = "post31", type = PostTags31.class) List<PostTags31> tags = null;
  private final @ModelField(targetType="String") String desc;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String resolveIdentifier() {
    return id;
  }
  
  public String getId() {
      return id;
  }
  
  public String getTitle() {
      return title;
  }
  
  public String getContent() {
      return content;
  }
  
  public List<PostTags31> getTags() {
      return tags;
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
  
  private Post31(String id, String title, String content, String desc) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.desc = desc;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Post31 post31 = (Post31) obj;
      return ObjectsCompat.equals(getId(), post31.getId()) &&
              ObjectsCompat.equals(getTitle(), post31.getTitle()) &&
              ObjectsCompat.equals(getContent(), post31.getContent()) &&
              ObjectsCompat.equals(getDesc(), post31.getDesc()) &&
              ObjectsCompat.equals(getCreatedAt(), post31.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), post31.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getTitle())
      .append(getContent())
      .append(getDesc())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Post31 {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("title=" + String.valueOf(getTitle()) + ", ")
      .append("content=" + String.valueOf(getContent()) + ", ")
      .append("desc=" + String.valueOf(getDesc()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static TitleStep builder() {
      return new Builder();
  }
  
  /**
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   */
  public static Post31 justId(String id) {
    return new Post31(
      id,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      title,
      content,
      desc);
  }
  public interface TitleStep {
    BuildStep title(String title);
  }
  

  public interface BuildStep {
    Post31 build();
    BuildStep id(String id);
    BuildStep content(String content);
    BuildStep desc(String desc);
  }
  

  public static class Builder implements TitleStep, BuildStep {
    private String id;
    private String title;
    private String content;
    private String desc;
    @Override
     public Post31 build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Post31(
          id,
          title,
          content,
          desc);
    }
    
    @Override
     public BuildStep title(String title) {
        Objects.requireNonNull(title);
        this.title = title;
        return this;
    }
    
    @Override
     public BuildStep content(String content) {
        this.content = content;
        return this;
    }
    
    @Override
     public BuildStep desc(String desc) {
        this.desc = desc;
        return this;
    }
    
    /**
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     */
    public BuildStep id(String id) {
        this.id = id;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String title, String content, String desc) {
      super.id(id);
      super.title(title)
        .content(content)
        .desc(desc);
    }
    
    @Override
     public CopyOfBuilder title(String title) {
      return (CopyOfBuilder) super.title(title);
    }
    
    @Override
     public CopyOfBuilder content(String content) {
      return (CopyOfBuilder) super.content(content);
    }
    
    @Override
     public CopyOfBuilder desc(String desc) {
      return (CopyOfBuilder) super.desc(desc);
    }
  }
  
}
