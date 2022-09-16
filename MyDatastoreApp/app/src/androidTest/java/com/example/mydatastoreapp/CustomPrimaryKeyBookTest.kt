package com.example.mydatastoreapp

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.amplifyframework.AmplifyException
import com.amplifyframework.api.aws.AWSApiPlugin
import com.amplifyframework.api.graphql.model.ModelSubscription

import com.amplifyframework.core.Amplify
import com.amplifyframework.core.model.SerializedModel
import com.amplifyframework.core.model.query.Where
import com.amplifyframework.datastore.*

import com.amplifyframework.datastore.generated.model.*
import com.amplifyframework.datastore.syncengine.OutboxMutationEvent
import com.amplifyframework.hub.HubChannel

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Ignore
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit


/**
 * Instrumented test, which will execute on an Android device.
 * This tests does create, query, update on a delete on Post35 and Comment35 models which have customPrimaryKey
 * and are connected to each other with hasMany relationship. Post35 has many Comment35 and Comment35 belongsTo Post35.
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class CustomPrimaryKeyBookTest {


   @Test
    fun hasManyBi() {
       datastoreStartAndSaveSuccessfully()
       //Amplify.DataStore.stop({}, {})
    }

    private fun datastoreStartAndSaveSuccessfully(): Post35 {
        val countDownLatch = CountDownLatch(10)
        val date = Date() // This object contains the current date value
        val formatter = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
        val currentDate = formatter.format(date)
        val post = Post35.builder()
            .postId(currentDate + Math.random() + "postid")
            .title("$currentDate post title")
            .desc("post desc")
            .build()
        val comment35 = Comment35.builder()
            .commentId(currentDate + Math.random() + "comment35")
            .content("$currentDate comment35 content")
            .post(post)
            .desc("comment35 desc")

            .build()
        //Start the datastore
        //Amplify.DataStore.start({}, {})

        //Wait for subscriptions to be established and sync for all the models to finish.
//        Amplify.Hub.subscribe(HubChannel.DATASTORE,
//            { it.name == DataStoreChannelEventName.READY.toString() },
//            {
//                Log.i("MyAmplifyApp", "Datastore ready.")
                //Save a post35
                Amplify.DataStore.save(post,
                    {
                        Log.i("MyAmplifyApp", "Saved a post 35")
                        //Save a comment35
                        Amplify.DataStore.save(comment35,
                            {
                                Log.i("MyAmplifyApp", "Saved a comment35")
                            },
                            { Log.e("MyAmplifyApp", "Save comment35 failed", it) })
                    },

                    { Log.e("MyAmplifyApp", "Save post35 failed", it) }
                )
         //   })
        //Sunbscribe to datastore hubevents make sure outbox mutation is enqued after save update and delete.
        Amplify.Hub.subscribe(HubChannel.DATASTORE,
            { it.name == DataStoreChannelEventName.OUTBOX_MUTATION_ENQUEUED.toString() },
            {
                val outboxMutationEvent = it.data as OutboxMutationEvent<*>
                val serializedModel = outboxMutationEvent.element.model as SerializedModel
                if (outboxMutationEvent.modelName == "Post35" && post.postId ==
                    serializedModel.serializedData["postId"]) {
                    assertEquals(post.modelName, serializedModel.modelSchema?.name)
                    assertEquals(post.postId, serializedModel.serializedData["postId"])
                    assertEquals(post.title, serializedModel.serializedData["title"])
                    //assertEquals(post35.desc, serializedModel.serializedData["desc"])
                    Log.i("MyAmplifyApp", "countdown latch")
                    countDownLatch.countDown()
                } else if (outboxMutationEvent.modelName == "Comment35" && comment35.commentId ==
                    serializedModel.serializedData["commentId"] ) {
                    assertEquals(comment35.modelName, serializedModel.modelSchema?.name)
                    assertEquals(comment35.commentId, serializedModel.serializedData["commentId"])
                    assertEquals(comment35.content, serializedModel.serializedData["content"])
                    //assertEquals(comment35.desc, serializedModel.serializedData["desc"])
                    Log.i("MyAmplifyApp", "countdown latch")
                    countDownLatch.countDown()
                }
            })
        var countPostProcessed = 0;
        var countCommentProcessed = 0;

        //Subscribe to datastore hubevents make sure outbox mutation is processed after save update and delete.
        Amplify.Hub.subscribe(HubChannel.DATASTORE,
            { it.name == DataStoreChannelEventName.OUTBOX_MUTATION_PROCESSED.toString() },
            {
                val outboxMutationEvent = it.data as OutboxMutationEvent<*>
                val processedModel = outboxMutationEvent.element.model
                if (outboxMutationEvent.modelName == "Post35") {
                    val post35Processed = processedModel as Post35
                    if (post35Processed.postId == post.postId && countPostProcessed < 1) {
                        assertEquals(post.title, post35Processed.title)
                        assertEquals(post.postId, post35Processed.postId)
                        //assertEquals(post35.desc, post35Processed.desc)
                        assertNotNull(post35Processed.createdAt)
                        assertNotNull(post35Processed.updatedAt)
                        Log.i("MyAmplifyApp", "countdown latch")
                        Log.i("MyAmplifyApp", "post processed count " + post35Processed)
                        countPostProcessed += 1
                        countDownLatch.countDown()
                    }

                } else if (outboxMutationEvent.modelName == "Comment35") {
                    val comment35Processed = processedModel as Comment35
                    if (comment35Processed.commentId == comment35.commentId && countCommentProcessed < 1) {
                        assertEquals(comment35.commentId, comment35Processed.commentId)
                        assertEquals(comment35.content, comment35Processed.content)
                        //assertEquals(comment35.desc, comment35Processed.desc)
                        assertEquals(comment35.post.postId, comment35Processed.post.postId)
                        assertEquals(comment35.post.title, comment35Processed.post.title)
                        assertNotNull(comment35Processed.createdAt)
                        assertNotNull(comment35Processed.updatedAt)
                        queryHasManyBi(countDownLatch, post, comment35)
                        Log.i("MyAmplifyApp", "countdown latch")
                        countCommentProcessed += 1
                        countDownLatch.countDown()
                    }
                }
            }
        )
        assertTrue(countDownLatch.await(300, TimeUnit.SECONDS))
        return post
    }

    private fun queryHasManyBi(countDownLatch: CountDownLatch, expectedPost35: Post35, expectedComment35: Comment35) {
        //Query to get all the Post35
        Amplify.DataStore.query(Post35::class.java, Where.identifier(Post35::class.java,
                Post35.Post35Identifier(expectedPost35.postId, expectedPost35.title)),
                { allPost35: Iterator<Post35> ->
                    while (allPost35.hasNext()) {
                        val post35 = allPost35.next()
                        Log.i("MyAmplifyApp", " I am  Post35 & title: " + post35.title + " & id: " +
                                post35.postId + " & comments: " +
                                post35.comments + " & team: ")
                        if (expectedPost35.postId == post35.postId){
                            assertEquals(post35.title, expectedPost35.title)
                            assertEquals(post35.postId, expectedPost35.postId)
                            assertNotNull(post35.createdAt)
                            assertNotNull(post35.updatedAt)
                            updatePost(expectedPost35, countDownLatch)
                        }

                    }
                    Log.i("MyAmplifyApp", "countdown latch")
                    countDownLatch.countDown()
                },
         { failure: DataStoreException? -> Log.e("MyAmplifyApp", "Query failed.", failure) })

        Amplify.DataStore.query(Comment35::class.java, Where.identifier(Comment35::class.java,
                Comment35.Comment35Identifier(expectedComment35.commentId, expectedComment35.content)),
                { allComment35: Iterator<Comment35> ->
                    while (allComment35.hasNext()) {
                        val comment35 = allComment35.next()
                        Log.i("MyAmplifyApp", " I am  Comment35 & content: " + comment35.content + " & id: " +
                                comment35.commentId)
                        if (comment35.content == expectedComment35.content){
                            assertEquals(expectedComment35.commentId, comment35.commentId)
                            assertEquals(expectedComment35.content, comment35.content)
                            assertEquals(expectedComment35.post, comment35.post)
                            assertNotNull(comment35.createdAt)
                            assertNotNull(comment35.updatedAt)
                            updateComment(expectedComment35, countDownLatch)
                        }
                    }
                    Log.i("MyAmplifyApp", " now updating comment35")
                    Log.i("MyAmplifyApp", "countdown latch")
                    countDownLatch.countDown()

                },
         { failure: DataStoreException? -> Log.e("MyAmplifyApp", "Query failed.", failure) })

        Amplify.DataStore.query(Comment35::class.java,
                Post35.POST_ID.eq(expectedPost35.postId),
                { allComment35: Iterator<Comment35> ->
                    while (allComment35.hasNext()) {
                        val comment35 = allComment35.next()
                        Log.i("MyAmplifyApp", " I am  Comment35 with post predicate & content: " + comment35.content + " & id: " +
                                comment35.commentId)
                        if (comment35.content == expectedComment35.content){
                            assertEquals(expectedComment35.commentId, comment35.commentId)
                            assertEquals(expectedComment35.content, comment35.content)
                            assertEquals(expectedComment35.post, comment35.post)
                            assertNotNull(comment35.createdAt)
                            assertNotNull(comment35.updatedAt)
                        }
                    }
                    Log.i("MyAmplifyApp", " now updating comment35")
                    Log.i("MyAmplifyApp", "countdown latch")
                    countDownLatch.countDown()

                },
                { failure: DataStoreException? -> Log.e("MyAmplifyApp", "Query failed.", failure) })
    }

    private fun updatePost(toBeUpdatedPost35: Post35, countDownLatch: CountDownLatch) {
        Amplify.DataStore.query(Post35::class.java, Where.identifier(Post35::class.java,
                Post35.Post35Identifier(toBeUpdatedPost35.postId, toBeUpdatedPost35.title)),
                { matches ->
                    if (matches.hasNext()) {
                        val original = matches.next()
                        val updatedDesc = toBeUpdatedPost35.desc + " Updated" + Date(System.currentTimeMillis())
                        val edited = original.copyOfBuilder()
                                .desc(updatedDesc)
                                .build()
                        Amplify.DataStore.save(edited,
                                {
                                    Log.i("MyAmplifyApp", "Updated a Post35")
                                    assertEquals(updatedDesc, it.item().desc)
                                    Log.i("MyAmplifyApp", "countdown latch")
                                    countDownLatch.countDown()
                                    deletePosts(countDownLatch, edited)
                                },
                                { Log.e("MyAmplifyApp", "Update failed forPost35", it) }
                        )
                    }
                },
                {
                    Log.e("MyAmplifyApp", "Query failed for Post35", it)
                }
        )


    }

    private fun updateComment(toBeUpdatedComment35: Comment35, countDownLatch: CountDownLatch) {
        Amplify.DataStore.query(Comment35::class.java, Where.identifier(Comment35::class.java,
                Comment35.Comment35Identifier(toBeUpdatedComment35.commentId, toBeUpdatedComment35.content)),
                { matches ->
                    if (matches.hasNext()) {
                        val original = matches.next()
                        val updatedDesc = toBeUpdatedComment35.desc + " Updated" + Date(System.currentTimeMillis())
                        val edited = original.copyOfBuilder()
                                .desc(updatedDesc)
                                .build()
                        Amplify.DataStore.save(edited,
                                {
                                    Log.i("MyAmplifyApp", "Updated a Comment35")
                                    assertEquals(updatedDesc, it.item().desc)
                                    Log.i("MyAmplifyApp", "countdown latch")
                                    countDownLatch.countDown()
                                },
                                { Log.e("MyAmplifyApp", "Update failed forComment35", it) }
                        )
                    }
                },
                {
                    Log.e("MyAmplifyApp", "Query failed for Comment35", it)
                }
        )
    }

    private fun deletePosts(countDownLatch: CountDownLatch, toBeDeletedPost35: Post35) {
        Amplify.DataStore.query(Post35::class.java, Where.identifier(Post35::class.java,
                Post35.Post35Identifier(toBeDeletedPost35.postId, toBeDeletedPost35.title)),
                { matches ->
                    if (matches.hasNext()) {
                        val original = matches.next()
                        Amplify.DataStore.delete(original,
                                {
                                    Log.i("MyAmplifyApp", "Deleted a Post35")
                                    countDownLatch.countDown()
                                },
                                { Log.e("MyAmplifyApp", "Delete failed forPost35", it) }
                        )
                    }
                },
                {
                    Log.e("MyAmplifyApp", "Query failed for Post35", it)
                }
        )
    }

//    private fun deleteComments(countDownLatch: CountDownLatch, toBeDeletedComment35: Comment35) {
//        Amplify.DataStore.query(Comment35::class.java, Where.identifier(Comment35::class.java,
//                Comment35.Comment35Identifier(toBeDeletedComment35.commentId, toBeDeletedComment35.content)),
//                { matches ->
//                    if (matches.hasNext()) {
//                        val original = matches.next()
//                        Amplify.DataStore.delete(original,
//                                {
//                                    Log.i("MyAmplifyApp", "Deleted a Comment35")
//                                    Log.i("MyAmplifyApp", "countdown latch")
//                                    countDownLatch.countDown()
//                                },
//                                { Log.e("MyAmplifyApp", "Delete failed for Comment35", it) }
//                        )
//                    }
//                },
//                {
//                    Log.e("MyAmplifyApp", "Query failed for Comment35", it)
//                }
//        )
//    }

    @Ignore("Deletes all the records")
    @Test
    fun deleteAll() {
        val countDownLatch = CountDownLatch(10)
        Amplify.DataStore.query(Post35::class.java,
                { matches ->
                    if (matches.hasNext()) {
                        val original = matches.next()
                        Amplify.DataStore.delete(original,
                                {
                                    Log.i("MyAmplifyApp", "Deleted a Post35")
                                    Log.i("MyAmplifyApp", "countdown latch")

                                },
                                { Log.e("MyAmplifyApp", "Delete failed for Post35", it) }
                        )
                    }
                    countDownLatch.countDown()
                },
                {
                    Log.e("MyAmplifyApp", "Query failed for CPost35", it)
                }
        )

        countDownLatch.await(2000, TimeUnit.SECONDS)
    }

    @Ignore("Id doesn't exist")
    @Test
    fun testQueryWithNestedPredicate(){
        val countDownLatch = CountDownLatch(1)
        Amplify.DataStore.start({},{})
        Amplify.Hub.subscribe(HubChannel.DATASTORE,
                { it.name == DataStoreChannelEventName.READY.toString() },
                {
                    Amplify.DataStore.query(Comment35::class.java,
                            Post35.TITLE.eq("07-07-2035 35:11:06 post35 title"),
                            {
                                allComments: Iterator<Comment35> ->
                                while (allComments.hasNext()) {
                                    val comment = allComments.next()
                                    Log.i("MyAmplifyApp", " I am  post & content: " + comment.content + " & id: " +
                                            comment.commentId)
                                    Amplify.DataStore.delete(comment,
                                            {
                                                Log.i("MyAmplifyApp", "Deleted a Post")
                                                countDownLatch.countDown()
                                            },
                                            { Log.e("MyAmplifyApp", "Delete failed forPost", it) }
                                    )

                                }
                            }
                            ,{ failure: DataStoreException? -> Log.e("MyAmplifyApp", "Query failed.", failure) }
                    )
                })

        assertTrue(countDownLatch.await(300, TimeUnit.SECONDS))
    }


    @Ignore("no active update")
    @Test
    fun testApiCreateSubscription() {
        val countDownLatch = CountDownLatch(1)
        val subscription = Amplify.API.subscribe(
        ModelSubscription.onCreate(Post35::class.java),
        {
            Log.i("ApiQuickStart", "Subscription established")
        },
        {
            Log.i("ApiQuickStart", "Post35 create subscription received: ${(it.data as Post35)}")
            countDownLatch.countDown()
        },
        { Log.e("ApiQuickStart", "Subscription failed", it) },
        { Log.i("ApiQuickStart", "Subscription completed") }
        )

        assertTrue(countDownLatch.await(300, TimeUnit.SECONDS))

        // Cancel the subscription listener when you're finished with it
        subscription?.cancel();
    }


//    @Test
//    fun testApiUpdateSubscription() {
//        val countDownLatch = CountDownLatch(1)
//        val subscription = Amplify.API.subscribe(
//            ModelSubscription.onUpdate(Post35::class.java),
//            {
//                Log.i("ApiQuickStart", "Subscription established")
//            },
//            {
//                Log.i("ApiQuickStart", "Post35 update subscription received: ${(it.data as Post35)}")
//            },
//            { Log.e("ApiQuickStart", "Subscription failed", it) },
//            { Log.i("ApiQuickStart", "Subscription completed") }
//        )
//
//        assertTrue(countDownLatch.await(300, TimeUnit.SECONDS))
//
//        // Cancel the subscription listener when you're finished with it
//        subscription?.cancel();
//    }

//    @Test
//    fun testApiUpdateBlogSubscription() {
//        val countDownLatch = CountDownLatch(1)
//        val subscription = Amplify.API.subscribe(
//            ModelSubscription.onUpdate(Blog::class.java),
//            {
//                Log.i("ApiQuickStart", "Subscription established")
//            },
//            {
//                Log.i("ApiQuickStart", "Blog update subscription received: ${(it.data as Blog)}")
//            },
//            { Log.e("ApiQuickStart", "Subscription failed", it) },
//            { Log.i("ApiQuickStart", "Subscription completed") }
//        )
//
//        assertTrue(countDownLatch.await(3000, TimeUnit.SECONDS))
//
//        // Cancel the subscription listener when you're finished with it
//        subscription?.cancel();
//    }

    @Test
    @Ignore("Update")
    public fun deletePostsWithPkPredicate () {
        val post = datastoreStartAndSaveSuccessfully()
        val countDownLatch = CountDownLatch(2)
        Amplify.DataStore.query(Post35::class.java, Where.identifier(Post35::class.java,
            Post35.Post35Identifier(post.postId, post.title)),
            { matches ->
                if (matches.hasNext()) {
                    val original = matches.next()
                    Amplify.DataStore.delete(original,
                        Post35.POST_ID.eq(post.postId),
                        {
                            Log.i("MyAmplifyApp", "Deleted a Post35")
                            countDownLatch.countDown()
                        },
                        { Log.e("MyAmplifyApp", "Delete failed forPost35", it) }
                    )
                }
            },
            {
                Log.e("MyAmplifyApp", "Query failed for Post35", it)
            }
        )
        assertTrue(countDownLatch.await(300, TimeUnit.SECONDS))
    }


}