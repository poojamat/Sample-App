package com.example.mydatastoreapp

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.amplifyframework.AmplifyException
import com.amplifyframework.api.aws.AWSApiPlugin
import com.amplifyframework.api.graphql.model.ModelQuery
import com.amplifyframework.core.Amplify
import com.amplifyframework.core.model.SerializedModel
import com.amplifyframework.core.model.query.Where
import com.amplifyframework.datastore.*
import com.amplifyframework.datastore.generated.model.Comment21
import com.amplifyframework.datastore.generated.model.Post21
import com.amplifyframework.datastore.syncengine.OutboxMutationEvent
import com.amplifyframework.hub.HubChannel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

/**
 * Instrumented test, which will execute on an Android device.
 * This tests does create, query, update on a delete on Post21 and Comment21 models which have customPrimaryKey
 * and are connected to each other with hasMany relationship. Post21 has many comment21.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
public class HasManyExplicitUniDirectionalInstrumentedTest {

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

    /**
     * This test saves, updates, deletes and query records for two models associated with each other in a @hasmany relation
     * bidirectionally.
     */
    @Test
    fun hasManyUniTestSaveUpdateQueryDelete() {
        datastoreStartAndSaveSuccessfully()
        //Amplify.DataStore.stop({}, {})
    }

    private fun datastoreStartAndSaveSuccessfully(): Post21 {
        val countDownLatch = CountDownLatch(10)
        val date = Date()

        val formatter = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
        val currentDate = formatter.format(date)
        val post21 = Post21.builder()
            .id(currentDate + Math.random() + "post21id")
            .title("$currentDate post21")
            .build()
        val comment21 = Comment21.builder()
            .id(currentDate + Math.random() + "comment21")
            .content("$currentDate comment21")
            .post21CommentsId(post21.id)
            .post21CommentsTitle(post21.title)
            .build()

//        Amplify.DataStore.clear({
//            Amplify.DataStore
//            Amplify.DataStore.start({}, {})
//        }, {})


//        Amplify.Hub.subscribe(HubChannel.DATASTORE,
//            { it.name == DataStoreChannelEventName.READY.toString() },
//            {
                Amplify.DataStore.save(post21,
                    {
                        Log.i("MyAmplifyApp", "Saved a post 21")
                        Amplify.DataStore.save(comment21,
                            {
                                Log.i("MyAmplifyApp", "Saved a comment21")
                            },
                            { Log.e("MyAmplifyApp", "Save comment21 failed", it) })
                    },

                    { Log.e("MyAmplifyApp", "Save post21 failed", it) })
//            })

        Amplify.Hub.subscribe(HubChannel.DATASTORE,
            { it.name == DataStoreChannelEventName.OUTBOX_MUTATION_ENQUEUED.toString() },
            {
                val outboxMutationEvent = it.data as OutboxMutationEvent<*>
                val serializedModel = outboxMutationEvent.element.model as SerializedModel
                if (outboxMutationEvent.modelName == "Post21" &&
                    serializedModel.serializedData["id"] == post21.id ) {
                    assertEquals(post21.modelName, serializedModel.modelSchema?.name)
                    assertEquals(post21.id, serializedModel.serializedData["id"])
                    assertEquals(post21.title, serializedModel.serializedData["title"])
                    countDownLatch.countDown()
                } else if (outboxMutationEvent.modelName == "Comment21" &&
                        comment21.id == serializedModel.serializedData["id"]) {
                    assertEquals(comment21.modelName, serializedModel.modelSchema?.name)
                    assertEquals(comment21.id, serializedModel.serializedData["id"])
                    assertEquals(comment21.content, serializedModel.serializedData["content"])
                    countDownLatch.countDown()
                }
            })
        Amplify.Hub.subscribe(HubChannel.DATASTORE,
            { it.name.equals(DataStoreChannelEventName.OUTBOX_MUTATION_PROCESSED.toString()) },
            {
                val outboxMutationEvent = it.data as OutboxMutationEvent<*>
                val processedModel = outboxMutationEvent.element.model
                if (outboxMutationEvent.modelName == "Post21") {
                    val post21Processed = processedModel as Post21
                    if(post21.id == post21Processed.id){
                        assertEquals(post21.title, post21Processed.title)
                        assertEquals(post21.id, post21Processed.id)
                        assertNotNull(post21Processed.createdAt)
                        assertNotNull(post21Processed.updatedAt)
                        countDownLatch.countDown()
                    }
                } else if (outboxMutationEvent.modelName == "Comment21") {
                    val comment21Processed = processedModel as Comment21
                    if (comment21Processed.id ==comment21.id){
                        assertEquals(comment21.id, comment21Processed.id)
                        assertEquals(comment21.content, comment21Processed.content)
                        assertEquals(comment21.post21CommentsId, comment21Processed.post21CommentsId)
                        assertEquals(
                            comment21.post21CommentsTitle,
                            comment21Processed.post21CommentsTitle
                        )
                        assertNotNull(comment21Processed.createdAt)
                        assertNotNull(comment21Processed.updatedAt)
                        //listPosts()
                        countDownLatch.countDown()
                        queryHasManyUni(countDownLatch, post21, comment21)
                    }
                }
            })


        assertTrue(countDownLatch.await(300, TimeUnit.SECONDS))
        return post21
    }


    private fun listPosts() {
        Amplify.API.query(ModelQuery.list(Post21::class.java),
                {
                    Log.i("MyAmplifyApp", "Query results = ${it.data }")
                },
                { Log.e("MyAmplifyApp", "Query failed", it) }
        );
    }

    private fun queryHasManyUni(countDownLatch: CountDownLatch, expectedPost21: Post21, expectedComment21: Comment21) {

        Amplify.DataStore.query(Post21::class.java, Where.identifier(Post21::class.java,
                Post21.Post21Identifier(expectedPost21.id, expectedPost21.title)),
                { allPost21: Iterator<Post21> ->
                    while (allPost21.hasNext()) {
                        val post21 = allPost21.next()
                        Log.i("MyAmplifyApp", " I am  Post21 & title: " + post21.title + " & id: " +
                                post21.id + " & comments: " + post21.comments + " & team: ")
                        assertEquals(post21.title, expectedPost21.title)
                        assertEquals(post21.id, expectedPost21.id)
                        assertNotNull(expectedPost21.createdAt)
                        assertNotNull(expectedPost21.updatedAt)
                    }
                    Log.i("MyAmplifyApp", " now updating post")
                    countDownLatch.countDown()
                    updatePost(expectedPost21, countDownLatch)
                }
        ) { failure: DataStoreException? -> Log.e("MyAmplifyApp", "Query failed.", failure) }

        Amplify.DataStore.query(Comment21::class.java,
                Where.identifier(Comment21::class.java, Comment21.Comment21Identifier(expectedComment21.id,
                expectedComment21.content)),
                { allComment21: Iterator<Comment21> ->
                    while (allComment21.hasNext()) {
                        val comment21 = allComment21.next()
                        Log.i("MyAmplifyApp", " I am  comment22 12 & content: " + comment21.content + " & id: " +
                                comment21.id)
                        assertEquals(comment21.id, expectedComment21.id)
                        assertEquals(comment21.content, expectedComment21.content)
                        assertEquals(comment21.post21CommentsId, expectedComment21.post21CommentsId)
                        assertEquals(comment21.post21CommentsTitle, expectedComment21.post21CommentsTitle)
                        assertNotNull(comment21.createdAt)
                        assertNotNull(comment21.updatedAt)
                    }
                    countDownLatch.countDown()
                    updateComment(expectedComment21, countDownLatch)
                }
        ) { failure: DataStoreException? -> Log.e("MyAmplifyApp", "Query failed.", failure) }

    }

    private fun updatePost(toBeUpdatedPost21: Post21, countDownLatch: CountDownLatch) {
        Amplify.DataStore.query(Post21::class.java, Where.identifier(Post21::class.java,
                Post21.Post21Identifier(toBeUpdatedPost21.id, toBeUpdatedPost21.title)),
                { matches ->
                    if (matches.hasNext()) {
                        val original = matches.next()
                        val updatedDesc = toBeUpdatedPost21.desc + " Updated" + Date(System.currentTimeMillis())
                        val edited = original.copyOfBuilder()
                                .desc(updatedDesc)
                                .build()
                        Amplify.DataStore.save(edited,
                                {
                                    Log.i("MyAmplifyApp", "Updated a Post21")
                                    assertEquals(updatedDesc, it.item().desc)
                                    deletePosts(countDownLatch, edited)
                                    countDownLatch.countDown()
                                },
                                { Log.e("MyAmplifyApp", "Update failed forPost21", it) }
                        )
                    }
                },
                {
                    Log.e("MyAmplifyApp", "Query failed for Post21", it)
                }
        )
    }

    private fun updateComment(toBeUpdatedComment21: Comment21, countDownLatch: CountDownLatch) {
        Amplify.DataStore.query(Comment21::class.java, Where.identifier(Comment21::class.java,
                Comment21.Comment21Identifier(toBeUpdatedComment21.id, toBeUpdatedComment21.content)),
                { matches ->
                    if (matches.hasNext()) {
                        val original = matches.next()
                        val updatedDesc = toBeUpdatedComment21.desc + " Updated" + Date(System.currentTimeMillis())
                        val edited = original.copyOfBuilder()
                                .desc(updatedDesc)
                                .build()
                        Amplify.DataStore.save(edited,
                                {
                                    Log.i("MyAmplifyApp", "Updated a Comment21")
                                    assertEquals(updatedDesc, it.item().desc)
                                    countDownLatch.countDown()
                                    deleteComments(countDownLatch, edited)
                                },
                                { Log.e("MyAmplifyApp", "Update failed forComment21", it) }
                        )
                    }
                },
                {
                    Log.e("MyAmplifyApp", "Query failed for Comment21", it)
                }
        )
    }

    private fun deletePosts(countDownLatch: CountDownLatch, toBeDeletedPost21: Post21) {
        Amplify.DataStore.query(Post21::class.java, Where.identifier(Post21::class.java,
                Post21.Post21Identifier(toBeDeletedPost21.id, toBeDeletedPost21.title)),
                { matches ->
                    if (matches.hasNext()) {
                        val original = matches.next()
                        Amplify.DataStore.delete(original,
                                {
                                    Log.i("MyAmplifyApp", "Deleted a Post21")
                                    countDownLatch.countDown()
                                },
                                { Log.e("MyAmplifyApp", "Delete failed forPost21", it) }
                        )
                    }
                },
                {
                    Log.e("MyAmplifyApp", "Query failed for Post21", it)
                }
        )
    }

    @Test
    @Ignore("update")
    public fun deletePostsWithPkPredicate () {
        val post = datastoreStartAndSaveSuccessfully()
        val countDownLatch = CountDownLatch(2)
        Amplify.DataStore.query(Post21::class.java, Where.identifier(Post21::class.java,
            Post21.Post21Identifier(post.id, post.title)),
            { matches ->
                if (matches.hasNext()) {
                    val original = matches.next()
                    Amplify.DataStore.delete(original,
                        Post21.ID.eq(post.id),
                        {
                            Log.i("MyAmplifyApp", "Deleted a Post21")
                            countDownLatch.countDown()
                        },
                        { Log.e("MyAmplifyApp", "Delete failed forPost21", it) }
                    )
                }
            },
            {
                Log.e("MyAmplifyApp", "Query failed for Post21", it)
            }
        )
        assertTrue(countDownLatch.await(300, TimeUnit.SECONDS))
    }

    private fun deleteComments(countDownLatch: CountDownLatch, toBeDeletedComment21: Comment21) {
        Amplify.DataStore.query(Comment21::class.java, Where.identifier(Comment21::class.java,
                Comment21.Comment21Identifier(toBeDeletedComment21.id, toBeDeletedComment21.content)),
                { matches ->
                    if (matches.hasNext()) {
                        val original = matches.next()
                        Amplify.DataStore.delete(original,
                                {
                                    Log.i("MyAmplifyApp", "Deleted a Comment21")
                                    countDownLatch.countDown()
                                },
                                { Log.e("MyAmplifyApp", "Delete failed for Comment21", it) }
                        )
                    }
                },
                {
                    Log.e("MyAmplifyApp", "Query failed for Comment21", it)
                }
        )
    }
}