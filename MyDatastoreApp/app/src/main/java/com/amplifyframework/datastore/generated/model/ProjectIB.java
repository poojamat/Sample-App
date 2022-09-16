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

/** This is an auto generated class representing the ProjectIB type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "ProjectIBS", type = Model.Type.USER, version = 1)
@Index(name = "undefined", fields = {"projectId","name"})
public final class ProjectIB implements Model {
  public static final QueryField PROJECT_ID = field("ProjectIB", "projectId");
  public static final QueryField NAME = field("ProjectIB", "name");
  public static final QueryField PROJECT_IB_TEAM_TEAM_ID = field("ProjectIB", "projectIBTeamTeamId");
  public static final QueryField PROJECT_IB_TEAM_NAME = field("ProjectIB", "projectIBTeamName");
  private final @ModelField(targetType="ID", isRequired = true) String projectId;
  private final @ModelField(targetType="String", isRequired = true) String name;
  private final @ModelField(targetType="TeamIB") @HasOne(associatedWith = "project", type = TeamIB.class) TeamIB team = null;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  private final @ModelField(targetType="ID") String projectIBTeamTeamId;
  private final @ModelField(targetType="String") String projectIBTeamName;
  private ProjectIBIdentifier projectIBIdentifier;
  public ProjectIBIdentifier resolveIdentifier() {
    if (projectIBIdentifier == null) {
      this.projectIBIdentifier = new ProjectIBIdentifier(projectId, name);
    }
    return projectIBIdentifier;
  }
  
  public String getProjectId() {
      return projectId;
  }
  
  public String getName() {
      return name;
  }
  
  public TeamIB getTeam() {
      return team;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  public String getProjectIbTeamTeamId() {
      return projectIBTeamTeamId;
  }
  
  public String getProjectIbTeamName() {
      return projectIBTeamName;
  }
  
  private ProjectIB(String projectId, String name, String projectIBTeamTeamId, String projectIBTeamName) {
    this.projectId = projectId;
    this.name = name;
    this.projectIBTeamTeamId = projectIBTeamTeamId;
    this.projectIBTeamName = projectIBTeamName;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      ProjectIB projectIb = (ProjectIB) obj;
      return ObjectsCompat.equals(getProjectId(), projectIb.getProjectId()) &&
              ObjectsCompat.equals(getName(), projectIb.getName()) &&
              ObjectsCompat.equals(getCreatedAt(), projectIb.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), projectIb.getUpdatedAt()) &&
              ObjectsCompat.equals(getProjectIbTeamTeamId(), projectIb.getProjectIbTeamTeamId()) &&
              ObjectsCompat.equals(getProjectIbTeamName(), projectIb.getProjectIbTeamName());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getProjectId())
      .append(getName())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .append(getProjectIbTeamTeamId())
      .append(getProjectIbTeamName())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("ProjectIB {")
      .append("projectId=" + String.valueOf(getProjectId()) + ", ")
      .append("name=" + String.valueOf(getName()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()) + ", ")
      .append("projectIBTeamTeamId=" + String.valueOf(getProjectIbTeamTeamId()) + ", ")
      .append("projectIBTeamName=" + String.valueOf(getProjectIbTeamName()))
      .append("}")
      .toString();
  }
  
  public static ProjectIdStep builder() {
      return new Builder();
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(projectId,
      name,
      projectIBTeamTeamId,
      projectIBTeamName);
  }
  public interface ProjectIdStep {
    NameStep projectId(String projectId);
  }
  

  public interface NameStep {
    BuildStep name(String name);
  }
  

  public interface BuildStep {
    ProjectIB build();
    BuildStep projectIbTeamTeamId(String projectIbTeamTeamId);
    BuildStep projectIbTeamName(String projectIbTeamName);
  }
  

  public static class Builder implements ProjectIdStep, NameStep, BuildStep {
    private String projectId;
    private String name;
    private String projectIBTeamTeamId;
    private String projectIBTeamName;
    @Override
     public ProjectIB build() {
        
        return new ProjectIB(
          projectId,
          name,
          projectIBTeamTeamId,
          projectIBTeamName);
    }
    
    @Override
     public NameStep projectId(String projectId) {
        Objects.requireNonNull(projectId);
        this.projectId = projectId;
        return this;
    }
    
    @Override
     public BuildStep name(String name) {
        Objects.requireNonNull(name);
        this.name = name;
        return this;
    }
    
    @Override
     public BuildStep projectIbTeamTeamId(String projectIbTeamTeamId) {
        this.projectIBTeamTeamId = projectIbTeamTeamId;
        return this;
    }
    
    @Override
     public BuildStep projectIbTeamName(String projectIbTeamName) {
        this.projectIBTeamName = projectIbTeamName;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String projectId, String name, String projectIbTeamTeamId, String projectIbTeamName) {
      super.projectId(projectId)
        .name(name)
        .projectIbTeamTeamId(projectIbTeamTeamId)
        .projectIbTeamName(projectIbTeamName);
    }
    
    @Override
     public CopyOfBuilder projectId(String projectId) {
      return (CopyOfBuilder) super.projectId(projectId);
    }
    
    @Override
     public CopyOfBuilder name(String name) {
      return (CopyOfBuilder) super.name(name);
    }
    
    @Override
     public CopyOfBuilder projectIbTeamTeamId(String projectIbTeamTeamId) {
      return (CopyOfBuilder) super.projectIbTeamTeamId(projectIbTeamTeamId);
    }
    
    @Override
     public CopyOfBuilder projectIbTeamName(String projectIbTeamName) {
      return (CopyOfBuilder) super.projectIbTeamName(projectIbTeamName);
    }
  }
  

  public static class ProjectIBIdentifier extends ModelIdentifier<ProjectIB> {
    private static final long serialVersionUID = 1L;
    public ProjectIBIdentifier(String projectId, String name) {
      super(projectId, name);
    }
  }
  
}
