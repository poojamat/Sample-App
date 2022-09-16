package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.HasOne;
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

/** This is an auto generated class representing the Project12 type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Project12s", type = Model.Type.USER, version = 1)
@Index(name = "undefined", fields = {"id","name"})
public final class Project12 implements Model {
  public static final QueryField ID = field("Project12", "id");
  public static final QueryField NAME = field("Project12", "name");
  public static final QueryField DESC = field("Project12", "desc");
  public static final QueryField PROJECT12_TEAM_ID = field("Project12", "project12TeamId");
  public static final QueryField PROJECT12_TEAM_NAME = field("Project12", "project12TeamName");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String name;
  private final @ModelField(targetType="Team12") @HasOne(associatedWith = "project", type = Team12.class) Team12 team = null;
  private final @ModelField(targetType="String") String desc;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  private final @ModelField(targetType="ID") String project12TeamId;
  private final @ModelField(targetType="String") String project12TeamName;
  private Project12Identifier project12Identifier;
  public Project12Identifier resolveIdentifier() {
    if (project12Identifier == null) {
      this.project12Identifier = new Project12Identifier(id, name);
    }
    return project12Identifier;
  }
  
  public String getId() {
      return id;
  }
  
  public String getName() {
      return name;
  }
  
  public Team12 getTeam() {
      return team;
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
  
  public String getProject12TeamId() {
      return project12TeamId;
  }
  
  public String getProject12TeamName() {
      return project12TeamName;
  }
  
  private Project12(String id, String name, String desc, String project12TeamId, String project12TeamName) {
    this.id = id;
    this.name = name;
    this.desc = desc;
    this.project12TeamId = project12TeamId;
    this.project12TeamName = project12TeamName;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Project12 project12 = (Project12) obj;
      return ObjectsCompat.equals(getId(), project12.getId()) &&
              ObjectsCompat.equals(getName(), project12.getName()) &&
              ObjectsCompat.equals(getDesc(), project12.getDesc()) &&
              ObjectsCompat.equals(getCreatedAt(), project12.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), project12.getUpdatedAt()) &&
              ObjectsCompat.equals(getProject12TeamId(), project12.getProject12TeamId()) &&
              ObjectsCompat.equals(getProject12TeamName(), project12.getProject12TeamName());
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
      .append(getProject12TeamId())
      .append(getProject12TeamName())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Project12 {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("name=" + String.valueOf(getName()) + ", ")
      .append("desc=" + String.valueOf(getDesc()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()) + ", ")
      .append("project12TeamId=" + String.valueOf(getProject12TeamId()) + ", ")
      .append("project12TeamName=" + String.valueOf(getProject12TeamName()))
      .append("}")
      .toString();
  }
  
  public static IdStep builder() {
      return new Builder();
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      name,
      desc,
      project12TeamId,
      project12TeamName);
  }
  public interface IdStep {
    NameStep id(String id);
  }
  

  public interface NameStep {
    BuildStep name(String name);
  }
  

  public interface BuildStep {
    Project12 build();
    BuildStep desc(String desc);
    BuildStep project12TeamId(String project12TeamId);
    BuildStep project12TeamName(String project12TeamName);
  }
  

  public static class Builder implements IdStep, NameStep, BuildStep {
    private String id;
    private String name;
    private String desc;
    private String project12TeamId;
    private String project12TeamName;
    @Override
     public Project12 build() {
        
        return new Project12(
          id,
          name,
          desc,
          project12TeamId,
          project12TeamName);
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
    
    @Override
     public BuildStep project12TeamId(String project12TeamId) {
        this.project12TeamId = project12TeamId;
        return this;
    }
    
    @Override
     public BuildStep project12TeamName(String project12TeamName) {
        this.project12TeamName = project12TeamName;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String name, String desc, String project12TeamId, String project12TeamName) {
      super.id(id)
        .name(name)
        .desc(desc)
        .project12TeamId(project12TeamId)
        .project12TeamName(project12TeamName);
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
    
    @Override
     public CopyOfBuilder project12TeamId(String project12TeamId) {
      return (CopyOfBuilder) super.project12TeamId(project12TeamId);
    }
    
    @Override
     public CopyOfBuilder project12TeamName(String project12TeamName) {
      return (CopyOfBuilder) super.project12TeamName(project12TeamName);
    }
  }
  

  public static class Project12Identifier extends ModelIdentifier<Project12> {
    private static final long serialVersionUID = 1L;
    public Project12Identifier(String id, String name) {
      super(id, name);
    }
  }
  
}
