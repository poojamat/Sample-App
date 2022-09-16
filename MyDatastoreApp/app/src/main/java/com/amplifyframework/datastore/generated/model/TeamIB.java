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

/** This is an auto generated class representing the TeamIB type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "TeamIBS", type = Model.Type.USER, version = 1)
@Index(name = "undefined", fields = {"teamId","name"})
public final class TeamIB implements Model {
  public static final QueryField TEAM_ID = field("TeamIB", "teamId");
  public static final QueryField NAME = field("TeamIB", "name");
  public static final QueryField PROJECT = field("TeamIB", "teamIBProjectProjectId");
  private final @ModelField(targetType="ID", isRequired = true) String teamId;
  private final @ModelField(targetType="String", isRequired = true) String name;
  private final @ModelField(targetType="ProjectIB") @BelongsTo(targetName = "teamIBProjectProjectId", targetNames = {"teamIBProjectProjectId", "teamIBProjectName"}, type = ProjectIB.class) ProjectIB project;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  private TeamIBIdentifier teamIBIdentifier;
  public TeamIBIdentifier resolveIdentifier() {
    if (teamIBIdentifier == null) {
      this.teamIBIdentifier = new TeamIBIdentifier(teamId, name);
    }
    return teamIBIdentifier;
  }
  
  public String getTeamId() {
      return teamId;
  }
  
  public String getName() {
      return name;
  }
  
  public ProjectIB getProject() {
      return project;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private TeamIB(String teamId, String name, ProjectIB project) {
    this.teamId = teamId;
    this.name = name;
    this.project = project;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      TeamIB teamIb = (TeamIB) obj;
      return ObjectsCompat.equals(getTeamId(), teamIb.getTeamId()) &&
              ObjectsCompat.equals(getName(), teamIb.getName()) &&
              ObjectsCompat.equals(getProject(), teamIb.getProject()) &&
              ObjectsCompat.equals(getCreatedAt(), teamIb.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), teamIb.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getTeamId())
      .append(getName())
      .append(getProject())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("TeamIB {")
      .append("teamId=" + String.valueOf(getTeamId()) + ", ")
      .append("name=" + String.valueOf(getName()) + ", ")
      .append("project=" + String.valueOf(getProject()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static TeamIdStep builder() {
      return new Builder();
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(teamId,
      name,
      project);
  }
  public interface TeamIdStep {
    NameStep teamId(String teamId);
  }
  

  public interface NameStep {
    BuildStep name(String name);
  }
  

  public interface BuildStep {
    TeamIB build();
    BuildStep project(ProjectIB project);
  }
  

  public static class Builder implements TeamIdStep, NameStep, BuildStep {
    private String teamId;
    private String name;
    private ProjectIB project;
    @Override
     public TeamIB build() {
        
        return new TeamIB(
          teamId,
          name,
          project);
    }
    
    @Override
     public NameStep teamId(String teamId) {
        Objects.requireNonNull(teamId);
        this.teamId = teamId;
        return this;
    }
    
    @Override
     public BuildStep name(String name) {
        Objects.requireNonNull(name);
        this.name = name;
        return this;
    }
    
    @Override
     public BuildStep project(ProjectIB project) {
        this.project = project;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String teamId, String name, ProjectIB project) {
      super.teamId(teamId)
        .name(name)
        .project(project);
    }
    
    @Override
     public CopyOfBuilder teamId(String teamId) {
      return (CopyOfBuilder) super.teamId(teamId);
    }
    
    @Override
     public CopyOfBuilder name(String name) {
      return (CopyOfBuilder) super.name(name);
    }
    
    @Override
     public CopyOfBuilder project(ProjectIB project) {
      return (CopyOfBuilder) super.project(project);
    }
  }
  

  public static class TeamIBIdentifier extends ModelIdentifier<TeamIB> {
    private static final long serialVersionUID = 1L;
    public TeamIBIdentifier(String teamId, String name) {
      super(teamId, name);
    }
  }
  
}
