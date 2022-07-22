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

/** This is an auto generated class representing the Comment21 type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Comment21s", type = Model.Type.USER, version = 1)
@Index(name = "undefined", fields = {"id","content"})
public final class Comment21 implements Model {
  public static final QueryField ID = field("Comment21", "id");
  public static final QueryField CONTENT = field("Comment21", "content");
  public static final QueryField DESC = field("Comment21", "desc");
  public static final QueryField POST21_COMMENTS_ID = field("Comment21", "post21CommentsId");
  public static final QueryField POST21_COMMENTS_TITLE = field("Comment21", "post21CommentsTitle");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String content;
  private final @ModelField(targetType="String") String desc;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  private final @ModelField(targetType="ID") String post21CommentsId;
  private final @ModelField(targetType="ID") String post21CommentsTitle;
  private Comment21Identifier comment21Identifier;
  public Comment21Identifier resolveIdentifier() {
    if (comment21Identifier == null) {
      this.comment21Identifier = new Comment21Identifier(id, content);
    }
    return comment21Identifier;
  }
  
  public String getId() {
      return id;
  }
  
  public String getContent() {
      return content;
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
  
  public String getPost21CommentsId() {
      return post21CommentsId;
  }
  
  public String getPost21CommentsTitle() {
      return post21CommentsTitle;
  }
  
  private Comment21(String id, String content, String desc, String post21CommentsId, String post21CommentsTitle) {
    this.id = id;
    this.content = content;
    this.desc = desc;
    this.post21CommentsId = post21CommentsId;
    this.post21CommentsTitle = post21CommentsTitle;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Comment21 comment21 = (Comment21) obj;
      return ObjectsCompat.equals(getId(), comment21.getId()) &&
              ObjectsCompat.equals(getContent(), comment21.getContent()) &&
              ObjectsCompat.equals(getDesc(), comment21.getDesc()) &&
              ObjectsCompat.equals(getCreatedAt(), comment21.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), comment21.getUpdatedAt()) &&
              ObjectsCompat.equals(getPost21CommentsId(), comment21.getPost21CommentsId()) &&
              ObjectsCompat.equals(getPost21CommentsTitle(), comment21.getPost21CommentsTitle());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getContent())
      .append(getDesc())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .append(getPost21CommentsId())
      .append(getPost21CommentsTitle())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Comment21 {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("content=" + String.valueOf(getContent()) + ", ")
      .append("desc=" + String.valueOf(getDesc()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()) + ", ")
      .append("post21CommentsId=" + String.valueOf(getPost21CommentsId()) + ", ")
      .append("post21CommentsTitle=" + String.valueOf(getPost21CommentsTitle()))
      .append("}")
      .toString();
  }
  
  public static IdStep builder() {
      return new Builder();
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      content,
      desc,
      post21CommentsId,
      post21CommentsTitle);
  }
  public interface IdStep {
    ContentStep id(String id);
  }
  

  public interface ContentStep {
    BuildStep content(String content);
  }
  

  public interface BuildStep {
    Comment21 build();
    BuildStep desc(String desc);
    BuildStep post21CommentsId(String post21CommentsId);
    BuildStep post21CommentsTitle(String post21CommentsTitle);
  }
  

  public static class Builder implements IdStep, ContentStep, BuildStep {
    private String id;
    private String content;
    private String desc;
    private String post21CommentsId;
    private String post21CommentsTitle;
    @Override
     public Comment21 build() {
        
        return new Comment21(
          id,
          content,
          desc,
          post21CommentsId,
          post21CommentsTitle);
    }
    
    @Override
     public ContentStep id(String id) {
        Objects.requireNonNull(id);
        this.id = id;
        return this;
    }
    
    @Override
     public BuildStep content(String content) {
        Objects.requireNonNull(content);
        this.content = content;
        return this;
    }
    
    @Override
     public BuildStep desc(String desc) {
        this.desc = desc;
        return this;
    }
    
    @Override
     public BuildStep post21CommentsId(String post21CommentsId) {
        this.post21CommentsId = post21CommentsId;
        return this;
    }
    
    @Override
     public BuildStep post21CommentsTitle(String post21CommentsTitle) {
        this.post21CommentsTitle = post21CommentsTitle;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String content, String desc, String post21CommentsId, String post21CommentsTitle) {
      super.id(id)
        .content(content)
        .desc(desc)
        .post21CommentsId(post21CommentsId)
        .post21CommentsTitle(post21CommentsTitle);
    }
    
    @Override
     public CopyOfBuilder id(String id) {
      return (CopyOfBuilder) super.id(id);
    }
    
    @Override
     public CopyOfBuilder content(String content) {
      return (CopyOfBuilder) super.content(content);
    }
    
    @Override
     public CopyOfBuilder desc(String desc) {
      return (CopyOfBuilder) super.desc(desc);
    }
    
    @Override
     public CopyOfBuilder post21CommentsId(String post21CommentsId) {
      return (CopyOfBuilder) super.post21CommentsId(post21CommentsId);
    }
    
    @Override
     public CopyOfBuilder post21CommentsTitle(String post21CommentsTitle) {
      return (CopyOfBuilder) super.post21CommentsTitle(post21CommentsTitle);
    }
  }
  

  public static class Comment21Identifier extends ModelIdentifier<Comment21> {
    private static final long serialVersionUID = 1L;
    public Comment21Identifier(String id, String content) {
      super(id, content);
    }
  }
  
}
