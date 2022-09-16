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

/** This is an auto generated class representing the Comment22 type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Comment22s", type = Model.Type.USER, version = 1)
@Index(name = "undefined", fields = {"id","content"})
public final class Comment22 implements Model {
  public static final QueryField ID = field("Comment22", "id");
  public static final QueryField CONTENT = field("Comment22", "content");
  public static final QueryField POST = field("Comment22", "post22CommentsId");
  public static final QueryField DESC = field("Comment22", "desc");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String content;
  private final @ModelField(targetType="Post22") @BelongsTo(targetName = "post22CommentsId", targetNames = {"post22CommentsId", "post22CommentsTitle"}, type = Post22.class) Post22 post;
  private final @ModelField(targetType="String") String desc;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  private Comment22Identifier comment22Identifier;
  public Comment22Identifier resolveIdentifier() {
    if (comment22Identifier == null) {
      this.comment22Identifier = new Comment22Identifier(id, content);
    }
    return comment22Identifier;
  }
  
  public String getId() {
      return id;
  }
  
  public String getContent() {
      return content;
  }
  
  public Post22 getPost() {
      return post;
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
  
  private Comment22(String id, String content, Post22 post, String desc) {
    this.id = id;
    this.content = content;
    this.post = post;
    this.desc = desc;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Comment22 comment22 = (Comment22) obj;
      return ObjectsCompat.equals(getId(), comment22.getId()) &&
              ObjectsCompat.equals(getContent(), comment22.getContent()) &&
              ObjectsCompat.equals(getPost(), comment22.getPost()) &&
              ObjectsCompat.equals(getDesc(), comment22.getDesc()) &&
              ObjectsCompat.equals(getCreatedAt(), comment22.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), comment22.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getContent())
      .append(getPost())
      .append(getDesc())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Comment22 {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("content=" + String.valueOf(getContent()) + ", ")
      .append("post=" + String.valueOf(getPost()) + ", ")
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
      content,
      post,
      desc);
  }
  public interface IdStep {
    ContentStep id(String id);
  }
  

  public interface ContentStep {
    BuildStep content(String content);
  }
  

  public interface BuildStep {
    Comment22 build();
    BuildStep post(Post22 post);
    BuildStep desc(String desc);
  }
  

  public static class Builder implements IdStep, ContentStep, BuildStep {
    private String id;
    private String content;
    private Post22 post;
    private String desc;
    @Override
     public Comment22 build() {
        
        return new Comment22(
          id,
          content,
          post,
          desc);
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
     public BuildStep post(Post22 post) {
        this.post = post;
        return this;
    }
    
    @Override
     public BuildStep desc(String desc) {
        this.desc = desc;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String content, Post22 post, String desc) {
      super.id(id)
        .content(content)
        .post(post)
        .desc(desc);
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
     public CopyOfBuilder post(Post22 post) {
      return (CopyOfBuilder) super.post(post);
    }
    
    @Override
     public CopyOfBuilder desc(String desc) {
      return (CopyOfBuilder) super.desc(desc);
    }
  }
  

  public static class Comment22Identifier extends ModelIdentifier<Comment22> {
    private static final long serialVersionUID = 1L;
    public Comment22Identifier(String id, String content) {
      super(id, content);
    }
  }
  
}
