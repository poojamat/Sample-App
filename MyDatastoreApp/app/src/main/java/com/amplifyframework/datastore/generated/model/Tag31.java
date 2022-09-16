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

/** This is an auto generated class representing the Tag31 type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Tag31s", type = Model.Type.USER, version = 1)
@Index(name = "undefined", fields = {"id","label"})
public final class Tag31 implements Model {
  public static final QueryField ID = field("Tag31", "id");
  public static final QueryField LABEL = field("Tag31", "label");
  public static final QueryField DESC = field("Tag31", "desc");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String label;
  private final @ModelField(targetType="PostTags31") @HasMany(associatedWith = "tag31", type = PostTags31.class) List<PostTags31> posts = null;
  private final @ModelField(targetType="String") String desc;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  private Tag31Identifier tag31Identifier;
  public Tag31Identifier resolveIdentifier() {
    if (tag31Identifier == null) {
      this.tag31Identifier = new Tag31Identifier(id, label);
    }
    return tag31Identifier;
  }
  
  public String getId() {
      return id;
  }
  
  public String getLabel() {
      return label;
  }
  
  public List<PostTags31> getPosts() {
      return posts;
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
  
  private Tag31(String id, String label, String desc) {
    this.id = id;
    this.label = label;
    this.desc = desc;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Tag31 tag31 = (Tag31) obj;
      return ObjectsCompat.equals(getId(), tag31.getId()) &&
              ObjectsCompat.equals(getLabel(), tag31.getLabel()) &&
              ObjectsCompat.equals(getDesc(), tag31.getDesc()) &&
              ObjectsCompat.equals(getCreatedAt(), tag31.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), tag31.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getLabel())
      .append(getDesc())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Tag31 {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("label=" + String.valueOf(getLabel()) + ", ")
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
      label,
      desc);
  }
  public interface IdStep {
    LabelStep id(String id);
  }
  

  public interface LabelStep {
    BuildStep label(String label);
  }
  

  public interface BuildStep {
    Tag31 build();
    BuildStep desc(String desc);
  }
  

  public static class Builder implements IdStep, LabelStep, BuildStep {
    private String id;
    private String label;
    private String desc;
    @Override
     public Tag31 build() {
        
        return new Tag31(
          id,
          label,
          desc);
    }
    
    @Override
     public LabelStep id(String id) {
        Objects.requireNonNull(id);
        this.id = id;
        return this;
    }
    
    @Override
     public BuildStep label(String label) {
        Objects.requireNonNull(label);
        this.label = label;
        return this;
    }
    
    @Override
     public BuildStep desc(String desc) {
        this.desc = desc;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String label, String desc) {
      super.id(id)
        .label(label)
        .desc(desc);
    }
    
    @Override
     public CopyOfBuilder id(String id) {
      return (CopyOfBuilder) super.id(id);
    }
    
    @Override
     public CopyOfBuilder label(String label) {
      return (CopyOfBuilder) super.label(label);
    }
    
    @Override
     public CopyOfBuilder desc(String desc) {
      return (CopyOfBuilder) super.desc(desc);
    }
  }
  

  public static class Tag31Identifier extends ModelIdentifier<Tag31> {
    private static final long serialVersionUID = 1L;
    public Tag31Identifier(String id, String label) {
      super(id, label);
    }
  }
  
}
