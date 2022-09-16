package com.example.mydatastoreapp

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.amplifyframework.AmplifyException
import com.amplifyframework.api.aws.AWSApiPlugin
import com.amplifyframework.api.graphql.model.ModelMutation

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
 * This tests does create, query, update on a delete on Post22 and Comment22 models which have customPrimaryKey
 * and are connected to each other with hasMany relationship. Post22 has many comment22 and comment22 belongsTo Post22.
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class HasManyImplicitBiDirectionalInstrumentedTest {
    val date = Date() // This object contains the current date value
    val formatter = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
    val currentDate = formatter.format(date)



//    @Before
//    fun setup(){
//        try {
//            Amplify.addPlugin(AWSDataStorePlugin())
//            Amplify.addPlugin(AWSApiPlugin())
//            Amplify.configure(InstrumentationRegistry.getInstrumentation().targetContext)
//            Log.i("MyAmplifyApp", "Initialized Amplify")
//        } catch (error: AmplifyException) {
//            Log.e("MyAmplifyApp", "Could not initialize Amplify", error)
//        }
//    }

    @Test
    fun hasManyBi() {
        val countDownLatch = CountDownLatch(10)
        val post22 = Post22.builder()
                .id(currentDate + Math.random() + "post22id")
                .title("$currentDate post22 title")
                .desc("post22 desc")
                .build()
        val comment22 = Comment22.builder()
                .id(currentDate + Math.random() + "comment22")
                .content("$currentDate comment22 content")
                .post(post22)
                .desc("comment22 desc")
                .build()
        //Start the datastore
        //Amplify.DataStore.start({}, {})

        //Wait for subscriptions to be established and sync for all the models to finish.
//        Amplify.Hub.subscribe(HubChannel.DATASTORE,
//                { it.name == DataStoreChannelEventName.READY.toString() },
//                {
                    Log.i("MyAmplifyApp", "Datastore ready.")
                    //Save a post22
                    Amplify.DataStore.save(post22,
                            {
                                Log.i("MyAmplifyApp", "Saved a post 22 with id: " + it.item().id)
                                //Save a comment22
                                Amplify.DataStore.save(comment22,
                                        {
                                            Log.i("MyAmplifyApp", "Saved a comment22"+ it.item().id)
                                        },
                                        { Log.e("MyAmplifyApp", "Save comment22 failed", it) })
                            },

                            { Log.e("MyAmplifyApp", "Save post22 failed", it) }
                    )
//                })
        //Subscribe to datastore hubevents make sure outbox mutation is enqued after save update and delete.
        Amplify.Hub.subscribe(HubChannel.DATASTORE,
                { it.name == DataStoreChannelEventName.OUTBOX_MUTATION_ENQUEUED.toString() }
        ) {
            val outboxMutationEvent = it.data as OutboxMutationEvent<*>
            val serializedModel = outboxMutationEvent.element.model as SerializedModel
            if (outboxMutationEvent.modelName == "Post22" &&
                post22.id == serializedModel.serializedData["id"]) {
                assertEquals(post22.modelName, serializedModel.modelSchema?.name)
                assertEquals(post22.id, serializedModel.serializedData["id"])
                assertEquals(post22.title, serializedModel.serializedData["title"])
                //assertEquals(post22.desc, serializedModel.serializedData["desc"])
                Log.i("MyAmplifyApp", "countdown latch")
                countDownLatch.countDown()
            } else if (outboxMutationEvent.modelName == "Comment22" &&
                    comment22.id == serializedModel.serializedData["id"]) {
                assertEquals(comment22.modelName, serializedModel.modelSchema?.name)
                assertEquals(comment22.id, serializedModel.serializedData["id"])
                assertEquals(comment22.content, serializedModel.serializedData["content"])
                //assertEquals(comment22.desc, serializedModel.serializedData["desc"])
                Log.i("MyAmplifyApp", "countdown latch")
                countDownLatch.countDown()
            }
        }
        var countPostProcessed = 0;
        var countCommentProcessed = 0;

        //Subscribe to datastore hubevents make sure outbox mutation is processed after save update and delete.
        Amplify.Hub.subscribe(HubChannel.DATASTORE,
                { it.name == DataStoreChannelEventName.OUTBOX_MUTATION_PROCESSED.toString() },
                {
                    val outboxMutationEvent = it.data as OutboxMutationEvent<*>
                    val processedModel = outboxMutationEvent.element.model
                    if (outboxMutationEvent.modelName == "Post22") {
                        val post22Processed = processedModel as Post22
                        if (post22Processed.id == post22.id && countPostProcessed < 1){
                            assertEquals(post22.title, post22Processed.title)
                            assertEquals(post22.id, post22Processed.id)
                            //assertEquals(post22.desc, post22Processed.desc)
                            assertNotNull(post22Processed.createdAt)
                            assertNotNull(post22Processed.updatedAt)
                            Log.i("MyAmplifyApp", "countdown latch")
                            Log.i("MyAmplifyApp", "post processed count " + post22Processed)
                            countPostProcessed += 1
                            countDownLatch.countDown()
                        }

                    } else if (outboxMutationEvent.modelName == "Comment22") {
                        val comment22Processed = processedModel as Comment22
                        if (comment22Processed.id == comment22.id && countCommentProcessed < 1){
                            assertEquals(comment22.id, comment22Processed.id)
                            assertEquals(comment22.content, comment22Processed.content)
                            //assertEquals(comment22.desc, comment22Processed.desc)
                            assertEquals(comment22.post.id, comment22Processed.post.id)
                            assertEquals(comment22.post.title, comment22Processed.post.title)
                            assertNotNull(comment22Processed.createdAt)
                            assertNotNull(comment22Processed.updatedAt)
                            queryHasManyBi(countDownLatch, post22, comment22)
                            Log.i("MyAmplifyApp", "countdown latch")
                            countCommentProcessed += 1
                            countDownLatch.countDown()
                        }
                    }
                }
        )
        assertTrue(countDownLatch.await(300, TimeUnit.SECONDS))
        //Amplify.DataStore.stop({}, {})
    }

    private fun queryHasManyBi(countDownLatch: CountDownLatch, expectedPost22: Post22, expectedComment22: Comment22) {

        Amplify.DataStore.query(Comment22::class.java,
                Post22.ID.eq(expectedPost22.id),
                { allComment22: Iterator<Comment22> ->
                    while (allComment22.hasNext()) {
                        val comment22 = allComment22.next()
                        Log.i("MyAmplifyApp", " I am  Comment22 with post predicate & content: " + comment22.content + " & id: " +
                                comment22.id)
                        if (comment22.content == expectedComment22.content){
                            assertEquals(expectedComment22.id, comment22.id)
                            assertEquals(expectedComment22.content, comment22.content)
                            assertEquals(expectedComment22.post, comment22.post)
                            assertNotNull(comment22.createdAt)
                            assertNotNull(comment22.updatedAt)
                        }
                    }
                    Log.i("MyAmplifyApp", " now updating comment22")
                    Log.i("MyAmplifyApp", "countdown latch")
                    countDownLatch.countDown()

                },
                { failure: DataStoreException? -> Log.e("MyAmplifyApp", "Query failed.", failure) })


        Amplify.DataStore.query(Comment22::class.java, Where.identifier(Comment22::class.java,
            Comment22.Comment22Identifier(expectedComment22.id, expectedComment22.content)),
            { allComment22: Iterator<Comment22> ->
                while (allComment22.hasNext()) {
                    val comment22 = allComment22.next()
                    Log.i("MyAmplifyApp", " I am  Comment22 & content: " + comment22.content + " & id: " +
                            comment22.id)
                    if (comment22.content == expectedComment22.content){
                        assertEquals(expectedComment22.id, comment22.id)
                        assertEquals(expectedComment22.content, comment22.content)
                        assertEquals(expectedComment22.post, comment22.post)
                        assertNotNull(comment22.createdAt)
                        assertNotNull(comment22.updatedAt)
                        updateComment(expectedComment22, countDownLatch)
                    }
                }
                Log.i("MyAmplifyApp", " now updating comment22")
                Log.i("MyAmplifyApp", "countdown latch")
                countDownLatch.countDown()

            },
            { failure: DataStoreException? -> Log.e("MyAmplifyApp", "Query failed.", failure) })

        //Query to get all the Post22
        Amplify.DataStore.query(Post22::class.java, Where.identifier(Post22::class.java,
            Post22.Post22Identifier(expectedPost22.id, expectedPost22.title)),
            { allPost22: Iterator<Post22> ->
                while (allPost22.hasNext()) {
                    val post22 = allPost22.next()
                    Log.i("MyAmplifyApp", " I am  Post22 & title: " + post22.title + " & id: " +
                            post22.id + " & comments: " +
                            post22.comments + " & team: ")
                    if (expectedPost22.id == post22.id){
                        assertEquals(post22.title, expectedPost22.title)
                        assertEquals(post22.id, expectedPost22.id)
                        assertNotNull(post22.createdAt)
                        assertNotNull(post22.updatedAt)
                        updatePost(expectedPost22, countDownLatch)
                    }

                }
                Log.i("MyAmplifyApp", "countdown latch")
                countDownLatch.countDown()
            },
            { failure: DataStoreException? -> Log.e("MyAmplifyApp", "Query failed.", failure) })
    }

    private fun updatePost(toBeUpdatedPost22: Post22, countDownLatch: CountDownLatch) {
        Amplify.DataStore.query(Post22::class.java, Where.identifier(Post22::class.java,
                Post22.Post22Identifier(toBeUpdatedPost22.id, toBeUpdatedPost22.title)),
                { matches ->
                    if (matches.hasNext()) {
                        val original = matches.next()
                        val updatedDesc = toBeUpdatedPost22.desc + " Updated" + Date(System.currentTimeMillis())
                        val edited = original.copyOfBuilder()
                                .desc(updatedDesc)
                                .build()
                        Amplify.DataStore.save(edited,
                                {
                                    Log.i("MyAmplifyApp", "Updated a Post22")
                                    assertEquals(updatedDesc, it.item().desc)
                                    Log.i("MyAmplifyApp", "countdown latch")
                                    countDownLatch.countDown()
                                    deletePosts(countDownLatch, edited)
                                },
                                { Log.e("MyAmplifyApp", "Update failed forPost22", it) }
                        )
                    }
                },
                {
                    Log.e("MyAmplifyApp", "Query failed for Post22", it)
                }
        )


    }

    private fun updateComment(toBeUpdatedComment22: Comment22, countDownLatch: CountDownLatch) {
        Amplify.DataStore.query(Comment22::class.java, Where.identifier(Comment22::class.java,
                Comment22.Comment22Identifier(toBeUpdatedComment22.id, toBeUpdatedComment22.content)),
                { matches ->
                    if (matches.hasNext()) {
                        val original = matches.next()
                        val updatedDesc = toBeUpdatedComment22.desc + " Updated" + Date(System.currentTimeMillis())
                        val edited = original.copyOfBuilder()
                                .desc(updatedDesc)
                                .build()
                        Amplify.DataStore.save(edited,
                                {
                                    Log.i("MyAmplifyApp", "Updated a Comment22")
                                    assertEquals(updatedDesc, it.item().desc)
                                    Log.i("MyAmplifyApp", "countdown latch")
                                    countDownLatch.countDown()
                                },
                                { Log.e("MyAmplifyApp", "Update failed forComment22", it) }
                        )
                    }
                },
                {
                    Log.e("MyAmplifyApp", "Query failed for Comment22", it)
                }
        )
    }

    private fun deletePosts(countDownLatch: CountDownLatch, toBeDeletedPost22: Post22) {
        Amplify.DataStore.query(Post22::class.java, Where.identifier(Post22::class.java,
                Post22.Post22Identifier(toBeDeletedPost22.id, toBeDeletedPost22.title)),
                { matches ->
                    if (matches.hasNext()) {
                        val original = matches.next()
                        Amplify.DataStore.delete(original,
                                {
                                    Log.i("MyAmplifyApp", "Deleted a Post22")
                                    countDownLatch.countDown()
                                },
                                { Log.e("MyAmplifyApp", "Delete failed forPost22", it) }
                        )
                    }
                },
                {
                    Log.e("MyAmplifyApp", "Query failed for Post22", it)
                }
        )
    }

    private fun deleteComments(countDownLatch: CountDownLatch, toBeDeletedComment22: Comment22) {
        Amplify.DataStore.query(Comment22::class.java, Where.identifier(Comment22::class.java,
                Comment22.Comment22Identifier(toBeDeletedComment22.id, toBeDeletedComment22.content)),
                { matches ->
                    if (matches.hasNext()) {
                        val original = matches.next()
                        Amplify.DataStore.delete(original,
                                {
                                    Log.i("MyAmplifyApp", "Deleted a Comment22")
                                    Log.i("MyAmplifyApp", "countdown latch")
                                    countDownLatch.countDown()
                                },
                                { Log.e("MyAmplifyApp", "Delete failed for Comment22", it) }
                        )
                    }
                },
                {
                    Log.e("MyAmplifyApp", "Query failed for Comment22", it)
                }
        )
    }

    @Ignore("Deletes all the records")
    @Test
    fun deleteAll() {
        val countDownLatch = CountDownLatch(2)
        Amplify.DataStore.query(Post22::class.java,
                { matches ->
                    while (matches.hasNext()) {
                        val original = matches.next()
                        Amplify.DataStore.delete(original,
                                {
                                    Log.i("MyAmplifyApp", "Deleted a Post22")
                                    Log.i("MyAmplifyApp", "countdown latch")
                                },
                                { Log.e("MyAmplifyApp", "Delete failed for Post22", it) }
                        )
                    }
                    countDownLatch.countDown()
                },
                {
                    Log.e("MyAmplifyApp", "Query failed for CPost22", it)
                }
        )

        countDownLatch.await(200, TimeUnit.SECONDS)
    }

    @Ignore("update")
    @Test
    fun testQueryWithNestedPredicate(){
        val countDownLatch = CountDownLatch(1)
        Amplify.DataStore.start({},{})
        Amplify.Hub.subscribe(HubChannel.DATASTORE,
                { it.name == DataStoreChannelEventName.READY.toString() },
                {
                    Amplify.DataStore.query(Comment22::class.java,
                            Post22.TITLE.eq("07-07-2022 22:11:06 post22 title"),
                            {
                                allComments: Iterator<Comment22> ->
                                while (allComments.hasNext()) {
                                    val comment = allComments.next()
                                    Log.i("MyAmplifyApp", " I am  post & content: " + comment.content + " & id: " +
                                            comment.id)
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


    @Test
    @Ignore("Interferes with Datastore")
    fun apiTest(){
        val countDownLatch = CountDownLatch(2)
        val post22 = Post22.builder()
            .id(currentDate + Math.random() + "post22id")
            .title("$currentDate post22 title")
            .desc("post22 desc")
            .build()
        val comment22 = Comment22.builder()
            .id(currentDate + Math.random() + "comment22")
            .content("$currentDate comment22 content")
            .post(post22)
            .desc("comment22 desc")
            .build()

        Amplify.API.mutate(
            ModelMutation.create(post22),
            {
                Log.i("MyAmplifyApp", "Post with id: ${it.data.id}")
                Amplify.API.mutate(ModelMutation.create(comment22),
                    {
                        Log.i("MyAmplifyApp", "Comment with id: ${it.data.id}")
                        countDownLatch.countDown()
                    },
                    { Log.e("MyAmplifyApp", "Create failed", it) }
                )
                countDownLatch.countDown()
            },
            { Log.e("MyAmplifyApp", "Create failed", it) }
        )
        assertTrue(countDownLatch.await(300, TimeUnit.SECONDS))
    }

}