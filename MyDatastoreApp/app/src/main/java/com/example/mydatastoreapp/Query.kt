package com.example.mydatastoreapp

import android.util.Log
import com.amplifyframework.core.Amplify
import com.amplifyframework.core.model.query.Where
import com.amplifyframework.datastore.DataStoreException
import com.amplifyframework.datastore.generated.model.*

class Query {
    public fun query(){
//        Amplify.DataStore.query(Blog::class.java,
//                { allBlogs: Iterator<Blog> ->
//                    while (allBlogs.hasNext()) {
//                        val blog = allBlogs.next()
//                        Log.i("MyAmplifyApp", " query result: " + blog.name + " & " + blog.name
//                        + "id: " + blog.id)
//                    }
//                }
//        ) { failure: DataStoreException? -> Log.e("MyAmplifyApp", "Query failed.", failure) }
        Amplify.DataStore.query(Comment::class.java,
                Where.identifier(Comment::class.java, Comment.CommentIdentifier("Comment1", "My content", 10)),
                { allComment: Iterator<Comment> ->
                    while (allComment.hasNext()) {
                        val comment = allComment.next()
                        Log.i("MyAmplifyApp", " I am eq query my comment & Title: " + comment.title)
                    }
                }
        ) { failure: DataStoreException? -> Log.e("MyAmplifyApp", "Query failed.", failure) }
        Amplify.DataStore.query(Comment::class.java,
                Where.identifier(Comment::class.java, Comment.CommentIdentifier("April 14 comment", "new contentApril 14", 10)),
                { allComment: Iterator<Comment> ->
                    while (allComment.hasNext()) {
                        val comment = allComment.next()
                        Log.i("MyAmplifyApp", " I am eq query my comment & Title: " + comment.title)
                    }
                }
        ) { failure: DataStoreException? -> Log.e("MyAmplifyApp", "Query failed.", failure) }

        Amplify.DataStore.query(Comment::class.java,
                Where.identifier(Comment::class.java, Comment.CommentIdentifier("April 14 comment", "new contentApril 14", 10)),
                { allComment: Iterator<Comment> ->
                    while (allComment.hasNext()) {
                        val comment = allComment.next()
                        Log.i("MyAmplifyApp", " I am eq query my comment & Title: " + comment.title)
                    }
                }
        ) { failure: DataStoreException? -> Log.e("MyAmplifyApp", "Query failed.", failure) }

        Amplify.DataStore.query(Blog::class.java,
                { allBlogs: Iterator<Blog> ->
                    while (allBlogs.hasNext()) {
                        val blog = allBlogs.next()
                        Log.i("MyAmplifyApp", " I am eq query my blog & Title: " + blog.name)
                    }
                }
        ) { failure: DataStoreException? -> Log.e("MyAmplifyApp", "Query failed.", failure) }

        Amplify.DataStore.query(Blog::class.java,
                Where.id("aafc2807-5812-48e7-81e9-1d91a149320e"),
                { allBlogs: Iterator<Blog> ->
                    while (allBlogs.hasNext()) {
                        val blog = allBlogs.next()
                        Log.i("MyAmplifyApp", " I am eq query my blog & Title: " + blog.name)
                    }
                }
        ) { failure: DataStoreException? -> Log.e("MyAmplifyApp", "Query failed.", failure) }
        
    }

    fun queryHasOneUni(){
        Amplify.DataStore.query(Project11::class.java,
                { allProject11: Iterator<Project11> ->
                    while (allProject11.hasNext()) {
                        val project11 = allProject11.next()
                        Log.i("MyAmplifyApp", " I am  project 11 & name: " + project11.name + " & id: " +
                                project11.id + " & projectTeamd: " +
                                project11.project11TeamId + " & team: " +
                                project11.team.toString() )
                    }
                }
        ) { failure: DataStoreException? -> Log.e("MyAmplifyApp", "Query failed.", failure) }
    }

    fun queryHasOneBi(){
        Amplify.DataStore.query(Project12::class.java,
                { allProject12: Iterator<Project12> ->
                    while (allProject12.hasNext()) {
                        val project12 = allProject12.next()
                        Log.i("MyAmplifyApp", " I am  project 12 & name: " + project12.name + " & id: " +
                                project12.id + " & projectTeamd: " +
                                project12.project12TeamId + " & team: " +
                                project12.team.toString() )
                    }
                }
        ) { failure: DataStoreException? -> Log.e("MyAmplifyApp", "Query failed.", failure) }

        Amplify.DataStore.query(Team12::class.java,
                { allTeam12: Iterator<Team12> ->
                    while (allTeam12.hasNext()) {
                        val team12 = allTeam12.next()
                        Log.i("MyAmplifyApp", " I am  team 12 & name: " + team12.name + " & id: " +
                                team12.id + " & project: " +
                                team12.project )
                    }
                }
        ) { failure: DataStoreException? -> Log.e("MyAmplifyApp", "Query failed.", failure) }
    }

    fun queryHasManyBi(){
        Amplify.DataStore.query(Post22::class.java,
                { allPost22: Iterator<Post22> ->
                    while (allPost22.hasNext()) {
                        val post22 = allPost22.next()
                        Log.i("MyAmplifyApp", " I am  Post22 & title: " + post22.title + " & id: " +
                                post22.id + " & comments: " +
                                post22.comments + " & team: ")
                    }
                }
        ) { failure: DataStoreException? -> Log.e("MyAmplifyApp", "Query failed.", failure) }

        Amplify.DataStore.query(Comment22::class.java,
                { allComment22: Iterator<Comment22> ->
                    while (allComment22.hasNext()) {
                        val comment22 = allComment22.next()
                        Log.i("MyAmplifyApp", " I am  comment22 12 & content: " + comment22.content + " & id: " +
                                comment22.id + " & post: " +
                                comment22.post )
                    }
                }
        ) { failure: DataStoreException? -> Log.e("MyAmplifyApp", "Query failed.", failure) }
    }

    private fun flavorsOfQuery() {
        Amplify.DataStore.query(Blog::class.java,
                Where.matches(Blog.NAME.contains("blog")),
                { allBlogs: Iterator<Blog> ->
                    while (allBlogs.hasNext()) {
                        val blog = allBlogs.next()
                        Log.i("MyAmplifyApp", " I am contains query blog & Title: " + blog.name)
                    }
                }
        ) { failure: DataStoreException? -> Log.e("MyAmplifyApp", "Query failed.", failure) }

        Amplify.DataStore.query(Blog::class.java,
                Where.matches(Blog.NAME.eq("my blog")),
                { allBlogs: Iterator<Blog> ->
                    while (allBlogs.hasNext()) {
                        val blog = allBlogs.next()
                        Log.i("MyAmplifyApp", " I am eq query my blog & Title: " + blog.name)
                    }
                }
        ) { failure: DataStoreException? -> Log.e("MyAmplifyApp", "Query failed.", failure) }


        Amplify.DataStore.query(Blog::class.java,
                Where.matches(Blog.NAME.beginsWith("my")),
                { allBlogs: Iterator<Blog> ->
                    while (allBlogs.hasNext()) {
                        val blog = allBlogs.next()
                        Log.i("MyAmplifyApp", " I am begins with query my & Title: " + blog.name)
                    }
                }
        ) { failure: DataStoreException? -> Log.e("MyAmplifyApp", "Query failed.", failure) }

        Amplify.DataStore.query(Blog::class.java,
                Where.matches(Blog.NAME.contains("Blog")),
                { allBlogs: Iterator<Blog> ->
                    while (allBlogs.hasNext()) {
                        val blog = allBlogs.next()
                        Log.i("MyAmplifyApp", " I am contains query Blog & Title: " + blog.name)
                    }
                }
        ) { failure: DataStoreException? -> Log.e("MyAmplifyApp", "Query failed.", failure) }

        Amplify.DataStore.query(Blog::class.java,
                Where.matches(Blog.NAME.eq("My Blog")),
                { allBlogs: Iterator<Blog> ->
                    while (allBlogs.hasNext()) {
                        val blog = allBlogs.next()
                        Log.i("MyAmplifyApp", " I am eq query My Blog & Title: " + blog.name)
                    }
                }
        ) { failure: DataStoreException? -> Log.e("MyAmplifyApp", "Query failed.", failure) }


        Amplify.DataStore.query(Blog::class.java,
                Where.matches(Blog.NAME.beginsWith("My")),
                { allBlogs: Iterator<Blog> ->
                    while (allBlogs.hasNext()) {
                        val blog = allBlogs.next()
                        Log.i("MyAmplifyApp", " I am begins with query My & Title: " + blog.name)
                    }
                }
        ) { failure: DataStoreException? -> Log.e("MyAmplifyApp", "Query failed.", failure) }
    }
}