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

/** This is an auto generated class representing the Team11 type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Team11s", type = Model.Type.USER, version = 1)
@Index(name = "undefined", fields = {"id","name"})
public final class Team11 implements Model {
  public static final QueryField ID = field("Team11", "id");
  public static final QueryField NAME = field("Team11", "name");
  public static final QueryField DESC = field("Team11", "desc");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String name;
  private final @ModelField(targetType="String") String desc;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  private Team11Identifier team11Identifier;
  public Team11Identifier resolveIdentifier() {
    if (team11Identifier == null) {
      this.team11Identifier = new Team11Identifier(id, name);
    }
    return team11Identifier;
  }
  
  public String getId() {
      return id;
  }
  
  public String getName() {
      return name;
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
  
  private Team11(String id, String name, String desc) {
    this.id = id;
    this.name = name;
    this.desc = desc;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Team11 team11 = (Team11) obj;
      return ObjectsCompat.equals(getId(), team11.getId()) &&
              ObjectsCompat.equals(getName(), team11.getName()) &&
              ObjectsCompat.equals(getDesc(), team11.getDesc()) &&
              ObjectsCompat.equals(getCreatedAt(), team11.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), team11.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getName())
      .append(getDesc())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Team11 {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("name=" + String.valueOf(getName()) + ", ")
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
      name,
      desc);
  }
  public interface IdStep {
    NameStep id(String id);
  }
  

  public interface NameStep {
    BuildStep name(String name);
  }
  

  public interface BuildStep {
    Team11 build();
    BuildStep desc(String desc);
  }
  

  public static class Builder implements IdStep, NameStep, BuildStep {
    private String id;
    private String name;
    private String desc;
    @Override
     public Team11 build() {
        
        return new Team11(
          id,
          name,
          desc);
    }
    
    @Override
     public NameStep id(String id) {
        Objects.requireNonNull(id);
        this.id = id;
        return this;
    }
    
    @Override
     public BuildStep name(String name) {
        Objects.requireNonNull(name);
        this.name = name;
        return this;
    }
    
    @Override
     public BuildStep desc(String desc) {
        this.desc = desc;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String name, String desc) {
      super.id(id)
        .name(name)
        .desc(desc);
    }
    
    @Override
     public CopyOfBuilder id(String id) {
      return (CopyOfBuilder) super.id(id);
    }
    
    @Override
     public CopyOfBuilder name(String name) {
      return (CopyOfBuilder) super.name(name);
    }
    
    @Override
     public CopyOfBuilder desc(String desc) {
      return (CopyOfBuilder) super.desc(desc);
    }
  }
  

  public static class Team11Identifier extends ModelIdentifier<Team11> {
    private static final long serialVersionUID = 1L;
    public Team11Identifier(String id, String name) {
      super(id, name);
    }
  }
  
}
