package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;

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

/** This is an auto generated class representing the Book type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Books", type = Model.Type.USER, version = 1)
@Index(name = "undefined", fields = {"isbn"})
public final class Book implements Model {
  public static final QueryField ISBN = field("Book", "isbn");
  public static final QueryField NAME = field("Book", "name");
  public static final QueryField AUTHOR = field("Book", "author");
  private final @ModelField(targetType="ID", isRequired = true) String isbn;
  private final @ModelField(targetType="String", isRequired = true) String name;
  private final @ModelField(targetType="String") String author;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String resolveIdentifier() {
    return isbn;
  }
  
  public String getIsbn() {
      return isbn;
  }
  
  public String getName() {
      return name;
  }
  
  public String getAuthor() {
      return author;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Book(String isbn, String name, String author) {
    this.isbn = isbn;
    this.name = name;
    this.author = author;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Book book = (Book) obj;
      return ObjectsCompat.equals(getIsbn(), book.getIsbn()) &&
              ObjectsCompat.equals(getName(), book.getName()) &&
              ObjectsCompat.equals(getAuthor(), book.getAuthor()) &&
              ObjectsCompat.equals(getCreatedAt(), book.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), book.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getIsbn())
      .append(getName())
      .append(getAuthor())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Book {")
      .append("isbn=" + String.valueOf(getIsbn()) + ", ")
      .append("name=" + String.valueOf(getName()) + ", ")
      .append("author=" + String.valueOf(getAuthor()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static IsbnStep builder() {
      return new Builder();
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(isbn,
      name,
      author);
  }
  public interface IsbnStep {
    NameStep isbn(String isbn);
  }
  

  public interface NameStep {
    BuildStep name(String name);
  }
  

  public interface BuildStep {
    Book build();
    BuildStep author(String author);
  }
  

  public static class Builder implements IsbnStep, NameStep, BuildStep {
    private String isbn;
    private String name;
    private String author;
    @Override
     public Book build() {
        
        return new Book(
          isbn,
          name,
          author);
    }
    
    @Override
     public NameStep isbn(String isbn) {
        Objects.requireNonNull(isbn);
        this.isbn = isbn;
        return this;
    }
    
    @Override
     public BuildStep name(String name) {
        Objects.requireNonNull(name);
        this.name = name;
        return this;
    }
    
    @Override
     public BuildStep author(String author) {
        this.author = author;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String isbn, String name, String author) {
      super.isbn(isbn)
        .name(name)
        .author(author);
    }
    
    @Override
     public CopyOfBuilder isbn(String isbn) {
      return (CopyOfBuilder) super.isbn(isbn);
    }
    
    @Override
     public CopyOfBuilder name(String name) {
      return (CopyOfBuilder) super.name(name);
    }
    
    @Override
     public CopyOfBuilder author(String author) {
      return (CopyOfBuilder) super.author(author);
    }
  }
  
}
