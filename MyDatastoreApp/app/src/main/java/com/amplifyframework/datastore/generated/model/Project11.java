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

/** This is an auto generated class representing the Project11 type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Project11s", type = Model.Type.USER, version = 1)
@Index(name = "undefined", fields = {"id","name"})
public final class Project11 implements Model {
  public static final QueryField ID = field("Project11", "id");
  public static final QueryField NAME = field("Project11", "name");
  public static final QueryField DESC = field("Project11", "desc");
  public static final QueryField PROJECT11_TEAM_ID = field("Project11", "project11TeamId");
  public static final QueryField PROJECT11_TEAM_NAME = field("Project11", "project11TeamName");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String name;
  private final @ModelField(targetType="Team11") @HasOne(associatedWith = "id", type = Team11.class) Team11 team = null;
  private final @ModelField(targetType="String") String desc;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  private final @ModelField(targetType="ID") String project11TeamId;
  private final @ModelField(targetType="ID") String project11TeamName;
  private Project11Identifier project11Identifier;
  public Project11Identifier resolveIdentifier() {
    if (project11Identifier == null) {
      this.project11Identifier = new Project11Identifier(id, name);
    }
    return project11Identifier;
  }
  
  public String getId() {
      return id;
  }
  
  public String getName() {
      return name;
  }
  
  public Team11 getTeam() {
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
  
  public String getProject11TeamId() {
      return project11TeamId;
  }
  
  public String getProject11TeamName() {
      return project11TeamName;
  }
  
  private Project11(String id, String name, String desc, String project11TeamId, String project11TeamName) {
    this.id = id;
    this.name = name;
    this.desc = desc;
    this.project11TeamId = project11TeamId;
    this.project11TeamName = project11TeamName;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Project11 project11 = (Project11) obj;
      return ObjectsCompat.equals(getId(), project11.getId()) &&
              ObjectsCompat.equals(getName(), project11.getName()) &&
              ObjectsCompat.equals(getDesc(), project11.getDesc()) &&
              ObjectsCompat.equals(getCreatedAt(), project11.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), project11.getUpdatedAt()) &&
              ObjectsCompat.equals(getProject11TeamId(), project11.getProject11TeamId()) &&
              ObjectsCompat.equals(getProject11TeamName(), project11.getProject11TeamName());
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
      .append(getProject11TeamId())
      .append(getProject11TeamName())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Project11 {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("name=" + String.valueOf(getName()) + ", ")
      .append("desc=" + String.valueOf(getDesc()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()) + ", ")
      .append("project11TeamId=" + String.valueOf(getProject11TeamId()) + ", ")
      .append("project11TeamName=" + String.valueOf(getProject11TeamName()))
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
      project11TeamId,
      project11TeamName);
  }
  public interface IdStep {
    NameStep id(String id);
  }
  

  public interface NameStep {
    BuildStep name(String name);
  }
  

  public interface BuildStep {
    Project11 build();
    BuildStep desc(String desc);
    BuildStep project11TeamId(String project11TeamId);
    BuildStep project11TeamName(String project11TeamName);
  }
  

  public static class Builder implements IdStep, NameStep, BuildStep {
    private String id;
    private String name;
    private String desc;
    private String project11TeamId;
    private String project11TeamName;
    @Override
     public Project11 build() {
        
        return new Project11(
          id,
          name,
          desc,
          project11TeamId,
          project11TeamName);
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
     public BuildStep project11TeamId(String project11TeamId) {
        this.project11TeamId = project11TeamId;
        return this;
    }
    
    @Override
     public BuildStep project11TeamName(String project11TeamName) {
        this.project11TeamName = project11TeamName;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String name, String desc, String project11TeamId, String project11TeamName) {
      super.id(id)
        .name(name)
        .desc(desc)
        .project11TeamId(project11TeamId)
        .project11TeamName(project11TeamName);
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
     public CopyOfBuilder project11TeamId(String project11TeamId) {
      return (CopyOfBuilder) super.project11TeamId(project11TeamId);
    }
    
    @Override
     public CopyOfBuilder project11TeamName(String project11TeamName) {
      return (CopyOfBuilder) super.project11TeamName(project11TeamName);
    }
  }
  

  public static class Project11Identifier extends ModelIdentifier<Project11> {
    private static final long serialVersionUID = 1L;
    public Project11Identifier(String id, String name) {
      super(id, name);
    }
  }
  
}
