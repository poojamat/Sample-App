//package com.example.mydatastoreapp
//
//import android.util.Log
//import com.amplifyframework.core.Amplify
//import com.amplifyframework.core.model.SerializedModel
//import com.amplifyframework.core.model.query.Where
//import com.amplifyframework.datastore.DataStoreChannelEventName
//import com.amplifyframework.datastore.DataStoreException
//import com.amplifyframework.datastore.generated.model.Post22
//import com.amplifyframework.datastore.generated.model.Post31
//import com.amplifyframework.datastore.generated.model.PostTags31
//import com.amplifyframework.datastore.generated.model.Tag31
//import com.amplifyframework.datastore.syncengine.OutboxMutationEvent
//import com.amplifyframework.hub.HubChannel
//import java.text.SimpleDateFormat
//import java.util.*
//import java.util.concurrent.CountDownLatch
//import java.util.concurrent.TimeUnit
//
//class ManyToMany {
//    fun testManyToManyBi() {
//        Amplify.DataStore.stop({},{})
//        val countDownLatch = CountDownLatch(1)
//        val date = Date() // This object contains the current date value
//        val formatter = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
//        val currentDate = formatter.format(date)
//        val post31 = Post31.builder()
//                .title("$currentDate post31")
//                .desc("post31")
//                .build()
//        val tags31 = Tag31.builder()
//                .id(currentDate + Math.random() + "tags31")
//                .label("$currentDate label tags31")
//                .desc("tags31")
//                .build()
//
//        val postTags31 = PostTags31.builder()
//                .post31(post31)
//                .tag31(tags31)
//                .build()
//
//        Amplify.Hub.subscribe(HubChannel.DATASTORE,
//                { it.name == DataStoreChannelEventName.READY.toString() },
//                {
//                    Log.i("MyAmplifyApp", "Datastore ready.")
//
//                    Amplify.DataStore.save(post31,
//                            {
//                                Log.i("MyAmplifyApp", "Saved a post31")
//                                Amplify.DataStore.save(tags31,
//                                        {
//                                            Log.i("MyAmplifyApp", "Saved a tags31")
//                                            Amplify.DataStore.save(postTags31,
//                                                    {
//                                                        Log.i("MyAmplifyApp", "Saved a postTags31")
//                                                    },
//                                                    { Log.e("MyAmplifyApp", "Save postTags31 failed", it) })
//                                        },
//                                        { Log.e("MyAmplifyApp", "Save tags31 failed", it) })},
//
//                            { Log.e("MyAmplifyApp", "Save post31 failed", it) }
//                    ) })
//        Amplify.DataStore.start({},{})
//        Amplify.Hub.subscribe(HubChannel.DATASTORE,
//                { it.name == DataStoreChannelEventName.OUTBOX_MUTATION_ENQUEUED.toString() }
//        ) {
//            val outboxMutationEvent = it.data as OutboxMutationEvent<*>
//            val serializedModel = outboxMutationEvent.element.model as SerializedModel
//            if (outboxMutationEvent.modelName == "Post31") {
//
//                countDownLatch.countDown()
//            } else if (outboxMutationEvent.modelName == "Tag31") {
//
//                countDownLatch.countDown()
//            }
//        }
//        Amplify.Hub.subscribe(HubChannel.DATASTORE,
//                { it.name == DataStoreChannelEventName.OUTBOX_MUTATION_PROCESSED.toString() },
//                {
//                    val outboxMutationEvent = it.data as OutboxMutationEvent<*>
//                    val processedModel = outboxMutationEvent.element.model
//                    if (outboxMutationEvent.modelName == "Post31"){
//                        val post31Processed = processedModel as Post31
//
//                        countDownLatch.countDown()
//                    } else if (outboxMutationEvent.modelName == "Tag31"){
//                        val tag31Processed = processedModel as Tag31
//
//                        queryHasManyBi(countDownLatch, post31, tags31)
//                        countDownLatch.countDown()
//                    }
//                }
//        )
//    }
//
//    private fun queryHasManyBi(countDownLatch: CountDownLatch, expectedPost31: Post31, expectedTag31: Tag31) {
//        Amplify.DataStore.query(Post22::class.java,
//                {
//                    allPost31: Iterator<Post22> ->
//                    while (allPost31.hasNext()) {
//                        val post31 = allPost31.next()
//                        Log.i("MyAmplifyApp", " I am  Post31 & title: " + post31.title + " & id: " +
//                                post31.id + " & comments: " +
//                                post31.comments + " & team: ")
//
//                    countDownLatch.countDown()
//                    updatePost(expectedPost31, countDownLatch)
//                },
//                    { failure: DataStoreException? -> Log.e("MyAmplifyApp", "Query failed.", failure) }
//        )
//
//        Amplify.DataStore.query(Tag31::class.java,
//                { allTags31: Iterator<Tag31> ->
//                    while (allTags31.hasNext()) {
//                        val tag31 = allTags31.next()
//                        Log.i("MyAmplifyApp", " I am  tag32 & label: " + tag31.label + " & id: " +
//                                tag31.id )
//
//                    countDownLatch.countDown()
//                    updateTag(expectedTag31, countDownLatch)
//                }
//         { failure: DataStoreException? -> Log.e("MyAmplifyApp", "Query failed.", failure) })
//    }
//
//    private fun updatePost(toBeUpdatedPost31: Post31, countDownLatch: CountDownLatch){
//        Amplify.DataStore.query(Post31::class.java, Where.identifier( Post31::class.java, toBeUpdatedPost31.id),
//                { matches ->
//                    if (matches.hasNext()) {
//                        val original = matches.next()
//                        val updatedDesc = toBeUpdatedPost31.title + " Updated" + Date(System.currentTimeMillis())
//                        val edited = original.copyOfBuilder()
//                                .title(updatedDesc)
//                                .desc("updated Post31")
//                                .build()
//                        Amplify.DataStore.save(edited,
//                                {
//                                    Log.i("MyAmplifyApp", "Updated a Post31")
//                                    deletePosts(countDownLatch, edited)
//                                    countDownLatch.countDown()
//                                },
//                                { Log.e("MyAmplifyApp", "Update failed forPost31", it) }
//                        )
//                    }
//                },
//                {
//                    Log.e("MyAmplifyApp", "Query failed for Post31", it)
//                }
//        )
//    }
//
//    private fun updateTag(toBeUpdatedTag31: Tag31, countDownLatch: CountDownLatch) {
//        Amplify.DataStore.query(Tag31::class.java, Where.identifier(Tag31::class.java,
//                Tag31.Tag31Identifier(toBeUpdatedTag31.id, toBeUpdatedTag31.label)),
//                { matches ->
//                    if (matches.hasNext()) {
//                        val original = matches.next()
//                        val updatedDesc = toBeUpdatedTag31.desc + " Updated" + Date(System.currentTimeMillis())
//                        val edited = original.copyOfBuilder()
//                                .desc(updatedDesc)
//                                .build()
//                        Amplify.DataStore.save(edited,
//                                {
//                                    Log.i("MyAmplifyApp", "Updated a Tag31")
//                                    assertEquals(updatedDesc, it.item().desc)
//                                    countDownLatch.countDown()
//                                    deleteComments(countDownLatch, edited)
//                                },
//                                { Log.e("MyAmplifyApp", "Update failed forComment22", it) }
//                        )
//                    }
//                },
//                {
//                    Log.e("MyAmplifyApp", "Query failed for Comment22", it)
//                }
//        )
//    }
//
//    private fun deletePosts(countDownLatch: CountDownLatch, toBeDeletedPost31: Post31) {
//        Amplify.DataStore.query(Post31::class.java, Where.identifier(Post31::class.java,
//                toBeDeletedPost31.id),
//                { matches ->
//                    if (matches.hasNext()) {
//                        val original = matches.next()
//                        Amplify.DataStore.delete(original,
//                                {
//                                    Log.i("MyAmplifyApp", "Deleted a Post31")
//                                    countDownLatch.countDown()
//                                },
//                                { Log.e("MyAmplifyApp", "Delete failed forPost31", it) }
//                        )
//                    }
//                },
//                {
//                    Log.e("MyAmplifyApp", "Query failed for Post31", it)
//                }
//        )
//    }
//
//    private fun deleteComments(countDownLatch: CountDownLatch, toBeDeletedTag31: Tag31) {
//        Amplify.DataStore.query(Tag31::class.java, Where.identifier(Tag31::class.java,
//                Tag31.Tag31Identifier(toBeDeletedTag31.id, toBeDeletedTag31.label)),
//                { matches ->
//                    if (matches.hasNext()) {
//                        val original = matches.next()
//                        Amplify.DataStore.delete(original,
//                                {
//                                    Log.i("MyAmplifyApp", "Deleted a Tag31")
//                                    countDownLatch.countDown()
//                                },
//                                { Log.e("MyAmplifyApp", "Delete failed for Tag31", it) }
//                        )
//                    }
//                },
//                {
//                    Log.e("MyAmplifyApp", "Query failed for Tag31", it)
//                }
//        )
//    }
//}