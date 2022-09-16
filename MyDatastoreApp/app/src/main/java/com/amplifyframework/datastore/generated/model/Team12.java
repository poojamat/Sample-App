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

/** This is an auto generated class representing the Team12 type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Team12s", type = Model.Type.USER, version = 1)
@Index(name = "undefined", fields = {"id","name"})
public final class Team12 implements Model {
  public static final QueryField ID = field("Team12", "id");
  public static final QueryField NAME = field("Team12", "name");
  public static final QueryField PROJECT = field("Team12", "team12ProjectId");
  public static final QueryField DESC = field("Team12", "desc");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String name;
  private final @ModelField(targetType="Project12") @BelongsTo(targetName = "team12ProjectId", targetNames = {"team12ProjectId", "team12ProjectName"}, type = Project12.class) Project12 project;
  private final @ModelField(targetType="String") String desc;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  private Team12Identifier team12Identifier;
  public Team12Identifier resolveIdentifier() {
    if (team12Identifier == null) {
      this.team12Identifier = new Team12Identifier(id, name);
    }
    return team12Identifier;
  }
  
  public String getId() {
      return id;
  }
  
  public String getName() {
      return name;
  }
  
  public Project12 getProject() {
      return project;
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
  
  private Team12(String id, String name, Project12 project, String desc) {
    this.id = id;
    this.name = name;
    this.project = project;
    this.desc = desc;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Team12 team12 = (Team12) obj;
      return ObjectsCompat.equals(getId(), team12.getId()) &&
              ObjectsCompat.equals(getName(), team12.getName()) &&
              ObjectsCompat.equals(getProject(), team12.getProject()) &&
              ObjectsCompat.equals(getDesc(), team12.getDesc()) &&
              ObjectsCompat.equals(getCreatedAt(), team12.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), team12.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getName())
      .append(getProject())
      .append(getDesc())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Team12 {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("name=" + String.valueOf(getName()) + ", ")
      .append("project=" + String.valueOf(getProject()) + ", ")
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
      project,
      desc);
  }
  public interface IdStep {
    NameStep id(String id);
  }
  

  public interface NameStep {
    BuildStep name(String name);
  }
  

  public interface BuildStep {
    Team12 build();
    BuildStep project(Project12 project);
    BuildStep desc(String desc);
  }
  

  public static class Builder implements IdStep, NameStep, BuildStep {
    private String id;
    private String name;
    private Project12 project;
    private String desc;
    @Override
     public Team12 build() {
        
        return new Team12(
          id,
          name,
          project,
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
     public BuildStep project(Project12 project) {
        this.project = project;
        return this;
    }
    
    @Override
     public BuildStep desc(String desc) {
        this.desc = desc;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String name, Project12 project, String desc) {
      super.id(id)
        .name(name)
        .project(project)
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
     public CopyOfBuilder project(Project12 project) {
      return (CopyOfBuilder) super.project(project);
    }
    
    @Override
     public CopyOfBuilder desc(String desc) {
      return (CopyOfBuilder) super.desc(desc);
    }
  }
  

  public static class Team12Identifier extends ModelIdentifier<Team12> {
    private static final long serialVersionUID = 1L;
    public Team12Identifier(String id, String name) {
      super(id, name);
    }
  }
  
}
