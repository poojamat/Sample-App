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

/** This is an auto generated class representing the ModelCompositeMultiplePk type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "ModelCompositeMultiplePks", type = Model.Type.USER, version = 1)
@Index(name = "undefined", fields = {"id","location","name"})
public final class ModelCompositeMultiplePk implements Model {
  public static final QueryField ID = field("ModelCompositeMultiplePk", "id");
  public static final QueryField LOCATION = field("ModelCompositeMultiplePk", "location");
  public static final QueryField NAME = field("ModelCompositeMultiplePk", "name");
  public static final QueryField LAST_NAME = field("ModelCompositeMultiplePk", "lastName");
  public static final QueryField AGE = field("ModelCompositeMultiplePk", "age");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String location;
  private final @ModelField(targetType="String", isRequired = true) String name;
  private final @ModelField(targetType="String") String lastName;
  private final @ModelField(targetType="Int") Integer age;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  private ModelCompositeMultiplePkIdentifier modelCompositeMultiplePkIdentifier;
  public ModelCompositeMultiplePkIdentifier resolveIdentifier() {
    if (modelCompositeMultiplePkIdentifier == null) {
      this.modelCompositeMultiplePkIdentifier = new ModelCompositeMultiplePkIdentifier(id, location, name);
    }
    return modelCompositeMultiplePkIdentifier;
  }
  
  public String getId() {
      return id;
  }
  
  public String getLocation() {
      return location;
  }
  
  public String getName() {
      return name;
  }
  
  public String getLastName() {
      return lastName;
  }
  
  public Integer getAge() {
      return age;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private ModelCompositeMultiplePk(String id, String location, String name, String lastName, Integer age) {
    this.id = id;
    this.location = location;
    this.name = name;
    this.lastName = lastName;
    this.age = age;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      ModelCompositeMultiplePk modelCompositeMultiplePk = (ModelCompositeMultiplePk) obj;
      return ObjectsCompat.equals(getId(), modelCompositeMultiplePk.getId()) &&
              ObjectsCompat.equals(getLocation(), modelCompositeMultiplePk.getLocation()) &&
              ObjectsCompat.equals(getName(), modelCompositeMultiplePk.getName()) &&
              ObjectsCompat.equals(getLastName(), modelCompositeMultiplePk.getLastName()) &&
              ObjectsCompat.equals(getAge(), modelCompositeMultiplePk.getAge()) &&
              ObjectsCompat.equals(getCreatedAt(), modelCompositeMultiplePk.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), modelCompositeMultiplePk.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getLocation())
      .append(getName())
      .append(getLastName())
      .append(getAge())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("ModelCompositeMultiplePk {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("location=" + String.valueOf(getLocation()) + ", ")
      .append("name=" + String.valueOf(getName()) + ", ")
      .append("lastName=" + String.valueOf(getLastName()) + ", ")
      .append("age=" + String.valueOf(getAge()) + ", ")
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
      location,
      name,
      lastName,
      age);
  }
  public interface IdStep {
    LocationStep id(String id);
  }
  

  public interface LocationStep {
    NameStep location(String location);
  }
  

  public interface NameStep {
    BuildStep name(String name);
  }
  

  public interface BuildStep {
    ModelCompositeMultiplePk build();
    BuildStep lastName(String lastName);
    BuildStep age(Integer age);
  }
  

  public static class Builder implements IdStep, LocationStep, NameStep, BuildStep {
    private String id;
    private String location;
    private String name;
    private String lastName;
    private Integer age;
    @Override
     public ModelCompositeMultiplePk build() {
        
        return new ModelCompositeMultiplePk(
          id,
          location,
          name,
          lastName,
          age);
    }
    
    @Override
     public LocationStep id(String id) {
        Objects.requireNonNull(id);
        this.id = id;
        return this;
    }
    
    @Override
     public NameStep location(String location) {
        Objects.requireNonNull(location);
        this.location = location;
        return this;
    }
    
    @Override
     public BuildStep name(String name) {
        Objects.requireNonNull(name);
        this.name = name;
        return this;
    }
    
    @Override
     public BuildStep lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
    
    @Override
     public BuildStep age(Integer age) {
        this.age = age;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String location, String name, String lastName, Integer age) {
      super.id(id)
        .location(location)
        .name(name)
        .lastName(lastName)
        .age(age);
    }
    
    @Override
     public CopyOfBuilder id(String id) {
      return (CopyOfBuilder) super.id(id);
    }
    
    @Override
     public CopyOfBuilder location(String location) {
      return (CopyOfBuilder) super.location(location);
    }
    
    @Override
     public CopyOfBuilder name(String name) {
      return (CopyOfBuilder) super.name(name);
    }
    
    @Override
     public CopyOfBuilder lastName(String lastName) {
      return (CopyOfBuilder) super.lastName(lastName);
    }
    
    @Override
     public CopyOfBuilder age(Integer age) {
      return (CopyOfBuilder) super.age(age);
    }
  }
  

  public static class ModelCompositeMultiplePkIdentifier extends ModelIdentifier<ModelCompositeMultiplePk> {
    private static final long serialVersionUID = 1L;
    public ModelCompositeMultiplePkIdentifier(String id, String location, String name) {
      super(id, location, name);
    }
  }
  
}
