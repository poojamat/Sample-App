package com.example.mydatastoreapp

import android.util.Log
import com.amplifyframework.core.Amplify
import com.amplifyframework.core.model.query.Where
import com.amplifyframework.core.model.temporal.Temporal
import com.amplifyframework.datastore.generated.model.*
import java.util.*

class Mutation {

    var postId: String = "3fd98b51-203a-4888-bbae-a4a249764f1f"

    fun save(title: String, callback: (String)->Unit){
//
//        val blogOwner = BlogOwnerWithCustomPKS.builder()
//                .name(title +" Test Blog Owner")
//                .wea("Test wea").build()

//        Amplify.DataStore.save(blogOwner,{
        val blog = Blog.builder()
                .name(title + " blog")
                .build()

        Amplify.DataStore.save(blog,
                {
                    Log.i("MyAmplifyApp", "Saved a Blog")
                    val post = Post.builder()
                            .id(title + Math.random() + "blogId")
                            .title(title+ " post")
                            .blog(blog)
                            .build()
                    Amplify.DataStore.save(post,
                            {
                                callback(it.item().id)
                                Log.i("MyAmplifyApp", "Saved a post")
//                                val comment = Comment.builder()
//                                        .title("$title comment")
//                                        .content("new content$title")
//                                        .likes(10)
//                                        .description("yeh fun comment")
//                                        .post(post)
//                                        .build()
//                                Amplify.DataStore.save(comment,
//                                        {
//                                            Log.i("MyAmplifyApp", "Saved a comment")
//                                        },
//                                        { Log.e("MyAmplifyApp", "Save comment failed", it) }
//                                )
//                            },
//                            { Log.e("MyAmplifyApp", "Save post failed", it) }
//                    )
                },
                { Log.e("MyAmplifyApp", "Save blog failed", it) }
        )
    },{Log.e("MyAmplifyApp", "Save blog owner failed", it)})
    }


    fun update(title: String, id: String){
        Amplify.DataStore.query(Blog::class.java, Where.id( id),
                { matches ->
                    if (matches.hasNext()) {
                        val original = matches.next()
                        val edited = original.copyOfBuilder()
                                .name(title)
                                .build()
                        Amplify.DataStore.save(edited,
                                {
                                    Log.i("MyAmplifyApp", "Updated a blog")
                                },
                                { Log.e("MyAmplifyApp", "Update failed", it) }
                        )
                    }
                },
                {
                    Log.e("MyAmplifyApp", "Query failed", it)
                }
        )
    }

    fun delete(id: String){
        Amplify.DataStore.query(Post21::class.java,
                { matches ->
                    if (matches.hasNext()) {
                        val original = matches.next()
                        Amplify.DataStore.delete(original,
                                {
                                    Log.i("MyAmplifyApp", "Deleted a Post")
                                },
                                { Log.e("MyAmplifyApp", "Delete Blog failed", it) }
                        )
                    }
                },
                {
                    Log.e("MyAmplifyApp", "Query failed", it)
                }
        )
        Amplify.DataStore.query(Comment::class.java,
                { matches ->
                    if (matches.hasNext()) {
                        val original = matches.next()
                        Amplify.DataStore.delete(original,
                                {
                                    Log.i("MyAmplifyApp", "Deleted a Comment")
                                },
                                { Log.e("MyAmplifyApp", "Delete Blog failed", it) }
                        )
                    }
                },
                {
                    Log.e("MyAmplifyApp", "Query failed", it)
                }
        )

        Amplify.DataStore.query(Blog::class.java,
            { matches ->
                if (matches.hasNext()) {
                    val original = matches.next()
                    Amplify.DataStore.delete(original,
                        {
                            Log.i("MyAmplifyApp", "Deleted a Blog")
                        },
                        { Log.e("MyAmplifyApp", "Delete Blog failed", it) }
                    )
                }
            },
            {
                Log.e("MyAmplifyApp", "Query failed", it)
            }
        )

    }


    //Has One Uni
    fun saveHasOneUni(title: String){
        val team11 = Team11.builder()
            .id(title + Math.random() + "team11id")
            .name("$title team11")
            .build()
        Amplify.DataStore.save(team11,
            {
                Log.i("MyAmplifyApp", "Saved a team 11")

                val project11 = Project11.builder()
                    .id(title + Math.random() + "project11id")
                    .name("$title project11")
                        .project11TeamId(team11.id)
                        .project11TeamName(team11.name)
                    .build()

                Amplify.DataStore.save(project11,
                        {
                            Log.i("MyAmplifyApp", "Saved a project 11")
                        },
                        { Log.e("MyAmplifyApp", "Save project 11 failed", it) })
                },
                { Log.e("MyAmplifyApp", "Save team 11 failed", it) }
        )
    }

    fun updateHasOneUni(title: String, id: String){
        Amplify.DataStore.query(Project11::class.java, Where.identifier( Project11::class.java,
                Project11.Project11Identifier(id, title)),
                { matches ->
                    if (matches.hasNext()) {
                        val original = matches.next()
                        val edited = original.copyOfBuilder()
                                .name(title + " Updated" + Date(System.currentTimeMillis()))
                                .build()
                        Amplify.DataStore.save(edited,
                                {
                                    Log.i("MyAmplifyApp", "Updated a project 11")
                                },
                                { Log.e("MyAmplifyApp", "Update failed for project 11", it) }
                        )
                    }
                },
                {
                    Log.e("MyAmplifyApp", "Query failed for project 11", it)
                }
        )
    }

    fun deleteHasOneUni(id: String, title: String){
        Amplify.DataStore.query(Project11::class.java,
                Where.identifier<Project11>(Project11::class.java, Project11.Project11Identifier(id, title)),

                {
                    matches ->
                    if (matches.hasNext()) {
                        val original = matches.next()
                        Amplify.DataStore.delete(original,
                                {
                                    Log.i("MyAmplifyApp", "Deleted a Blog")
                                },
                                { Log.e("MyAmplifyApp", "Delete Blog failed", it) }
                        )
                    }
                },
                {
                    Log.e("MyAmplifyApp", "Query failed", it)
                }
        )
    }

    //Has One Bi
    fun saveHasOneBi(title: String){

        val project12 = Project12.builder()
                .id(title + Math.random() + "project12id")
                .name("$title project12")
                .build()

        Amplify.DataStore.save(project12,
                    {
                        Log.i("MyAmplifyApp", "Saved a project 12")
                    val team12 = Team12.builder()
                            .id(title + Math.random() + "team11id")
                            .name("$title team11")
                            .project(project12)
                            .build()
                    Amplify.DataStore.save(team12,
                            {
                                Log.i("MyAmplifyApp", "Saved a team 12")
                            },
                            { Log.e("MyAmplifyApp", "Save team 12 failed", it) })},

                { Log.e("MyAmplifyApp", "Save project 12 failed", it) }
        )
    }

    fun updateHasOneBi (title: String, id: String){
        Amplify.DataStore.query(Project12::class.java, Where.identifier( Project12::class.java,
                Project12.Project12Identifier(id, title)),
                { matches ->
                    if (matches.hasNext()) {
                        val original = matches.next()
                        val edited = original.copyOfBuilder()
                                .name(title + " Updated" + Date(System.currentTimeMillis()))
                                .build()
                        Amplify.DataStore.save(edited,
                                {
                                    Log.i("MyAmplifyApp", "Updated a project 12")
                                },
                                { Log.e("MyAmplifyApp", "Update failed for project 12", it) }
                        )
                    }
                },
                {
                    Log.e("MyAmplifyApp", "Query failed for project 12", it)
                }
        )
    }

    fun deleteHasOneBi (id: String, title: String){
        Amplify.DataStore.delete(Team12::class.java, Where.matchesAll().queryPredicate,
                {
                    Log.i("MyAmplifyApp", "Project12 a comment")

                },
                { Log.e("MyAmplifyApp", "Delete Project11 failed", it) }

        )
        Amplify.DataStore.delete(Project12::class.java, Where.matchesAll().queryPredicate,
                {
                    Log.i("MyAmplifyApp", "Project12 a comment")

                },
                { Log.e("MyAmplifyApp", "Delete Project11 failed", it) }

        )


        Amplify.DataStore.query(Project12::class.java,
                Where.identifier<Project12>(Project12::class.java, Project12.Project12Identifier(id, title)),

                { matches ->
                    if (matches.hasNext()) {
                        val original = matches.next()
                        Amplify.DataStore.delete(original,
                                {
                                    Log.i("MyAmplifyApp", "Deleted a Blog")
                                },
                                { Log.e("MyAmplifyApp", "Delete Blog failed", it) }
                        )
                    }
                },
                {
                    Log.e("MyAmplifyApp", "Query failed", it)
                }
        )
    }

    //Has Many Bi
    fun saveHasManyBi(title: String){

        val post22 = Post22.builder()
                .id(title + Math.random() + "post22id")
                .title("$title post22")
                .build()

        Amplify.DataStore.save(post22,
                {
                    Log.i("MyAmplifyApp", "Saved a post22")
                    val comment22 = Comment22.builder()
                            .id(title + Math.random() + "comment22id")
                            .content("$title comment22")
                            .post(post22)
                            .build()
                    Amplify.DataStore.save(comment22,
                            {
                                Log.i("MyAmplifyApp", "Saved a comment22")
                            },
                            { Log.e("MyAmplifyApp", "Save comment22 failed", it) })},

                { Log.e("MyAmplifyApp", "Save post22 failed", it) }
        )
    }

    fun updateHasManyBi (title: String, id: String){
        Amplify.DataStore.query(Post22::class.java, Where.identifier( Post22::class.java,
                Post22.Post22Identifier(id, title)),
                { matches ->
                    if (matches.hasNext()) {
                        val original = matches.next()
                        val edited = original.copyOfBuilder()
                                .title(title + " Updated" + Date(System.currentTimeMillis()))
                                .build()
                        Amplify.DataStore.save(edited,
                                {
                                    Log.i("MyAmplifyApp", "Updated a project 12")
                                },
                                { Log.e("MyAmplifyApp", "Update failed for project 12", it) }
                        )
                    }
                },
                {
                    Log.e("MyAmplifyApp", "Query failed for project 12", it)
                }
        )
    }

    fun deleteHasManyBi (id: String, title: String){
        Amplify.DataStore.delete(Post22::class.java, Where.matchesAll().queryPredicate,
                {
                    Log.i("MyAmplifyApp", "Project12 a comment")

                },
                { Log.e("MyAmplifyApp", "Delete Project11 failed", it) }

        )
        Amplify.DataStore.delete(Comment22::class.java, Where.matchesAll().queryPredicate,
                {
                    Log.i("MyAmplifyApp", "Project12 a comment")

                },
                { Log.e("MyAmplifyApp", "Delete Project11 failed", it) }

        )


        Amplify.DataStore.query(Post22::class.java,
                Where.identifier<Post22>(Post22::class.java,  Post22.Post22Identifier(id, title)),
                { matches ->
                    if (matches.hasNext()) {
                        val original = matches.next()
                        Amplify.DataStore.delete(original,
                                {
                                    Log.i("MyAmplifyApp", "Deleted a Blog")
                                },
                                { Log.e("MyAmplifyApp", "Delete Blog failed", it) }
                        )
                    }
                },
                {
                    Log.e("MyAmplifyApp", "Query failed", it)
                }
        )
    }

    fun deleteAll(){
        Amplify.DataStore.delete(Project11::class.java, Where.matchesAll().queryPredicate,
                {
                    Log.i("MyAmplifyApp", "Project11 a comment")
                    Amplify.DataStore.delete(Team11::class.java, Where.matchesAll().queryPredicate,
                            {
                                Log.i("MyAmplifyApp", "Deleted a Team11")
                            },
                            { Log.e("MyAmplifyApp", "Delete Team11 failed", it) })
                },
                { Log.e("MyAmplifyApp", "Delete Project11 failed", it) })

                Amplify.DataStore.delete(Blog::class.java, Where.matchesAll().queryPredicate,
                {
                    Log.i("MyAmplifyApp", "Deleted a comment")
                },
                { Log.e("MyAmplifyApp", "Delete comment failed", it) }
        )
    }
}