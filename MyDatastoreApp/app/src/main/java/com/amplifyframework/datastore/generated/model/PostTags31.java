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

/** This is an auto generated class representing the PostTags31 type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "PostTags31s", type = Model.Type.USER, version = 1)
@Index(name = "byPost31", fields = {"post31ID"})
@Index(name = "byTag31", fields = {"tag31ID","tag31label"})
public final class PostTags31 implements Model {
  public static final QueryField ID = field("PostTags31", "id");
  public static final QueryField POST31 = field("PostTags31", "post31ID");
  public static final QueryField TAG31 = field("PostTags31", "tag31ID");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="Post31", isRequired = true) @BelongsTo(targetName = "post31ID", targetNames = {"post31ID"}, type = Post31.class) Post31 post31;
  private final @ModelField(targetType="Tag31", isRequired = true) @BelongsTo(targetName = "tag31ID", targetNames = {"tag31ID", "tag31label"}, type = Tag31.class) Tag31 tag31;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String resolveIdentifier() {
    return id;
  }
  
  public String getId() {
      return id;
  }
  
  public Post31 getPost31() {
      return post31;
  }
  
  public Tag31 getTag31() {
      return tag31;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private PostTags31(String id, Post31 post31, Tag31 tag31) {
    this.id = id;
    this.post31 = post31;
    this.tag31 = tag31;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      PostTags31 postTags31 = (PostTags31) obj;
      return ObjectsCompat.equals(getId(), postTags31.getId()) &&
              ObjectsCompat.equals(getPost31(), postTags31.getPost31()) &&
              ObjectsCompat.equals(getTag31(), postTags31.getTag31()) &&
              ObjectsCompat.equals(getCreatedAt(), postTags31.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), postTags31.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getPost31())
      .append(getTag31())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("PostTags31 {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("post31=" + String.valueOf(getPost31()) + ", ")
      .append("tag31=" + String.valueOf(getTag31()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static Post31Step builder() {
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
  public static PostTags31 justId(String id) {
    return new PostTags31(
      id,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      post31,
      tag31);
  }
  public interface Post31Step {
    Tag31Step post31(Post31 post31);
  }
  

  public interface Tag31Step {
    BuildStep tag31(Tag31 tag31);
  }
  

  public interface BuildStep {
    PostTags31 build();
    BuildStep id(String id);
  }
  

  public static class Builder implements Post31Step, Tag31Step, BuildStep {
    private String id;
    private Post31 post31;
    private Tag31 tag31;
    @Override
     public PostTags31 build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new PostTags31(
          id,
          post31,
          tag31);
    }
    
    @Override
     public Tag31Step post31(Post31 post31) {
        Objects.requireNonNull(post31);
        this.post31 = post31;
        return this;
    }
    
    @Override
     public BuildStep tag31(Tag31 tag31) {
        Objects.requireNonNull(tag31);
        this.tag31 = tag31;
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
    private CopyOfBuilder(String id, Post31 post31, Tag31 tag31) {
      super.id(id);
      super.post31(post31)
        .tag31(tag31);
    }
    
    @Override
     public CopyOfBuilder post31(Post31 post31) {
      return (CopyOfBuilder) super.post31(post31);
    }
    
    @Override
     public CopyOfBuilder tag31(Tag31 tag31) {
      return (CopyOfBuilder) super.tag31(tag31);
    }
  }
  
}
