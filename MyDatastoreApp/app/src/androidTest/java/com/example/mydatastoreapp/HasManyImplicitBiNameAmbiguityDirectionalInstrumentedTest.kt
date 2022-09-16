//package com.example.mydatastoreapp
//
//import android.util.Log
//import androidx.test.ext.junit.runners.AndroidJUnit4
//import androidx.test.platform.app.InstrumentationRegistry
//import com.amplifyframework.AmplifyException
//import com.amplifyframework.api.aws.AWSApiPlugin
//import com.amplifyframework.api.graphql.model.ModelMutation
//
//import com.amplifyframework.core.Amplify
//import com.amplifyframework.core.model.SerializedModel
//import com.amplifyframework.core.model.query.Where
//import com.amplifyframework.datastore.*
//
//import com.amplifyframework.datastore.generated.model.*
//import com.amplifyframework.datastore.syncengine.OutboxMutationEvent
//import com.amplifyframework.hub.HubChannel
//
//import org.junit.Test
//import org.junit.runner.RunWith
//
//import org.junit.Assert.*
//import org.junit.Before
//import org.junit.Ignore
//import java.text.SimpleDateFormat
//import java.util.*
//import java.util.concurrent.CountDownLatch
//import java.util.concurrent.TimeUnit
//
//
///**
// * Instrumented test, which will execute on an Android device.
// * This tests does create, query, update on a delete on ProjectIB and TeamIB models which have customPrimaryKey
// * and are connected to each other with hasMany relationship. ProjectIB has many TeamIB and TeamIB belongsTo ProjectIB.
// * See [testing documentation](http://d.android.com/tools/testing).
// */
//@RunWith(AndroidJUnit4::class)
//class HasManyImplicitBiNameAmbiguityDirectionalInstrumentedTest {
//    val date = Date() // This object contains the current date value
//    val formatter = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
//    val currentDate = formatter.format(date)
//
////    @Before
////    fun setup(){
////        try {
////            Amplify.addPlugin(AWSDataStorePlugin())
////            Amplify.addPlugin(AWSApiPlugin())
////            Amplify.configure(InstrumentationRegistry.getInstrumentation().targetContext)
////            Log.i("MyAmplifyApp", "Initialized Amplify")
////        } catch (error: AmplifyException) {
////            Log.e("MyAmplifyApp", "Could not initialize Amplify", error)
////        }
////    }
//
//    @Test
//    fun hasManyBi() {
//        val countDownLatch = CountDownLatch(11)
//        val projectIB = ProjectIB.builder()
//                .projectId(currentDate + Math.random() + "ProjectIBid")
//                .name("$currentDate ProjectIB name")
//                //.desc("ProjectIB desc")
//                .build()
//        val teamIB = TeamIB.builder()
//                .teamId(currentDate + Math.random() + "TeamIB")
//                .name("$currentDate TeamIB name")
//                .project(projectIB)
////                .desc("TeamIB desc")
//                .build()
//        //Start the datastore
//        Amplify.DataStore.start({}, {})
//
//        //Wait for subscriptions to be established and sync for all the models to finish.
//        Amplify.Hub.subscribe(HubChannel.DATASTORE,
//                { it.name == DataStoreChannelEventName.READY.toString() },
//                {
//                    Log.i("MyAmplifyApp", "Datastore ready.")
//                    //Save a ProjectIB
//                    Amplify.DataStore.save(projectIB,
//                            {
//                                Log.i("MyAmplifyApp", "Saved a post 22")
//                                //Save a TeamIB
//                                Amplify.DataStore.save(teamIB,
//                                        {
//                                            Log.i("MyAmplifyApp", "Saved a TeamIB")
//                                        },
//                                        { Log.e("MyAmplifyApp", "Save TeamIB failed", it) })
//                            },
//
//                            { Log.e("MyAmplifyApp", "Save ProjectIB failed", it) }
//                    )
//                })
//        //Sunbscribe to datastore hubevents make sure outbox mutation is enqued after save update and delete.
//        Amplify.Hub.subscribe(HubChannel.DATASTORE,
//                { it.name == DataStoreChannelEventName.OUTBOX_MUTATION_ENQUEUED.toString() }
//        ) {
//            val outboxMutationEvent = it.data as OutboxMutationEvent<*>
//            val serializedModel = outboxMutationEvent.element.model as SerializedModel
//            if (outboxMutationEvent.modelName == "ProjectIB") {
//                assertEquals(projectIB.modelName, serializedModel.modelSchema?.name)
//                assertEquals(projectIB.projectId, serializedModel.serializedData["projectId"])
//                assertEquals(projectIB.name, serializedModel.serializedData["name"])
//                //assertEquals(ProjectIB.desc, serializedModel.serializedData["desc"])
//                Log.i("MyAmplifyApp", "countdown latch")
//                countDownLatch.countDown()
//            } else if (outboxMutationEvent.modelName == "TeamIB") {
//                assertEquals(teamIB.modelName, serializedModel.modelSchema?.name)
//                assertEquals(teamIB.teamId, serializedModel.serializedData["teamId"])
//                assertEquals(teamIB.name, serializedModel.serializedData["name"])
//                //assertEquals(TeamIB.desc, serializedModel.serializedData["desc"])
//                Log.i("MyAmplifyApp", "countdown latch")
//                countDownLatch.countDown()
//            }
//        }
//        var countPostProcessed = 0;
//        var countCommentProcessed = 0;
//
//        //Subscribe to datastore hubevents make sure outbox mutation is processed after save update and delete.
//        Amplify.Hub.subscribe(HubChannel.DATASTORE,
//                { it.name == DataStoreChannelEventName.OUTBOX_MUTATION_PROCESSED.toString() },
//                {
//                    val outboxMutationEvent = it.data as OutboxMutationEvent<*>
//                    val processedModel = outboxMutationEvent.element.model
//                    if (outboxMutationEvent.modelName == "ProjectIB") {
//                        val projectIBProcessed = processedModel as ProjectIB
//                        if (projectIBProcessed.projectId == projectIB.projectId && countPostProcessed < 1){
//                            assertEquals(projectIB.name, projectIBProcessed.name)
//                            //assertEquals(ProjectIB.desc, ProjectIBProcessed.desc)
//                            assertNotNull(projectIBProcessed.createdAt)
//                            assertNotNull(projectIBProcessed.updatedAt)
//                            Log.i("MyAmplifyApp", "countdown latch")
//                            Log.i("MyAmplifyApp", "post processed count " + projectIBProcessed)
//                            countPostProcessed += 1
//                            countDownLatch.countDown()
//                        }
//
//                    } else if (outboxMutationEvent.modelName == "TeamIB") {
//                        val teamIBProcessed = processedModel as TeamIB
//                        if (teamIBProcessed.teamId == teamIB.teamId && countCommentProcessed < 1){
//                            assertEquals(teamIB.teamId, teamIBProcessed.teamId)
//                            assertEquals(teamIB.name, teamIBProcessed.name)
//                            //assertEquals(TeamIB.desc, TeamIBProcessed.desc)
//                            assertEquals(teamIB.project.projectId, teamIBProcessed.project.projectId)
//                            assertEquals(teamIB.project.name, teamIBProcessed.project.name)
//                            assertNotNull(teamIBProcessed.createdAt)
//                            assertNotNull(teamIBProcessed.updatedAt)
//                            queryHasManyBi(countDownLatch, projectIB, teamIB)
//                            Log.i("MyAmplifyApp", "countdown latch")
//                            countCommentProcessed += 1
//                            countDownLatch.countDown()
//                        }
//                    }
//                }
//        )
//        assertTrue(countDownLatch.await(300, TimeUnit.SECONDS))
//        Amplify.DataStore.stop({}, {})
//    }
//
//    private fun queryHasManyBi(countDownLatch: CountDownLatch, expectedProjectIB: ProjectIB, expectedTeamIB: TeamIB) {
//        //Query to get all the ProjectIB
//        Amplify.DataStore.query(ProjectIB::class.java, Where.identifier(ProjectIB::class.java,
//                ProjectIB.ProjectIBIdentifier(expectedProjectIB.projectId, expectedProjectIB.projectId)),
//                { allProjectIB: Iterator<ProjectIB> ->
//                    while (allProjectIB.hasNext()) {
//                        val ProjectIB = allProjectIB.next()
//                        Log.i("MyAmplifyApp", " I am  ProjectIB & name: " + ProjectIB.name + " & id: " +
//                                ProjectIB.projectId + " & team: " +
//                                ProjectIB.team )
//                        if (expectedProjectIB.projectId == ProjectIB.projectId){
//                            assertEquals(ProjectIB.name, expectedProjectIB.name)
//                            assertNotNull(ProjectIB.createdAt)
//                            assertNotNull(ProjectIB.updatedAt)
//                            updatePost(expectedProjectIB, countDownLatch)
//                        }
//
//                    }
//                    Log.i("MyAmplifyApp", "countdown latch")
//                    countDownLatch.countDown()
//                },
//         { failure: DataStoreException? -> Log.e("MyAmplifyApp", "Query failed.", failure) })
//
//        //Query to get all the ProjectIB
//        Amplify.DataStore.query(TeamIB::class.java, TeamIB.TEAM_ID.eq(expectedTeamIB.teamId).and(TeamIB.NAME.eq(expectedTeamIB.name)),
//                { allProjectIB: Iterator<TeamIB> ->
//                    while (allProjectIB.hasNext()) {
//                        val teamIB = allProjectIB.next()
//                        Log.i("MyAmplifyApp", " I am  TeamIB & name: " + teamIB.name + " & id: " +
//                                teamIB.teamId + " & team: " +
//                                teamIB.project )
//
//
//                    }
//
//                },
//                { failure: DataStoreException? -> Log.e("MyAmplifyApp", "Query failed.", failure) })
//
////        Amplify.DataStore.query(TeamIB::class.java, Where.identifier(TeamIB::class.java,
////                TeamIB.TeamIBIdentifier(expectedTeamIB.teamId, expectedTeamIB.name)),
////                { allTeamIB: Iterator<TeamIB> ->
////                    while (allTeamIB.hasNext()) {
////                        val TeamIB = allTeamIB.next()
////                        Log.i("MyAmplifyApp", " I am  TeamIB & content: " + TeamIB.name + " & id: " +
////                                TeamIB.teamId)
////                        if (TeamIB.name == expectedTeamIB.name){
////                            assertEquals(expectedTeamIB.teamId, TeamIB.teamId)
////                            assertEquals(expectedTeamIB.project, TeamIB.project)
////                            assertNotNull(TeamIB.createdAt)
////                            assertNotNull(TeamIB.updatedAt)
////                            //updateComment(expectedTeamIB, countDownLatch)
////                        }
////                    }
////                    Log.i("MyAmplifyApp", " now updating TeamIB")
////                    Log.i("MyAmplifyApp", "countdown latch")
////                    countDownLatch.countDown()
////
////                },
////         { failure: DataStoreException? -> Log.e("MyAmplifyApp", "Query failed.", failure) })
//
////        Amplify.DataStore.query(TeamIB::class.java,
////                ProjectIB.ID.eq(expectedProjectIB.id),
////                { allTeamIB: Iterator<TeamIB> ->
////                    while (allTeamIB.hasNext()) {
////                        val TeamIB = allTeamIB.next()
////                        Log.i("MyAmplifyApp", " I am  TeamIB with post predicate & content: " + TeamIB.content + " & id: " +
////                                TeamIB.id)
////                        if (TeamIB.content == expectedTeamIB.content){
////                            assertEquals(expectedTeamIB.id, TeamIB.id)
////                            assertEquals(expectedTeamIB.content, TeamIB.content)
////                            assertEquals(expectedTeamIB.post, TeamIB.post)
////                            assertNotNull(TeamIB.createdAt)
////                            assertNotNull(TeamIB.updatedAt)
////                        }
////                    }
////                    Log.i("MyAmplifyApp", " now updating TeamIB")
////                    Log.i("MyAmplifyApp", "countdown latch")
////                    countDownLatch.countDown()
////
////                },
////                { failure: DataStoreException? -> Log.e("MyAmplifyApp", "Query failed.", failure) })
//    }
//
//    private fun updatePost(toBeUpdatedProjectIB: ProjectIB, countDownLatch: CountDownLatch) {
//        Amplify.DataStore.query(ProjectIB::class.java, Where.identifier(ProjectIB::class.java,
//                ProjectIB.ProjectIBIdentifier(toBeUpdatedProjectIB.projectId, toBeUpdatedProjectIB.name)),
//                { matches ->
//                    if (matches.hasNext()) {
//                        val original = matches.next()
//                        val updatedDesc = toBeUpdatedProjectIB. + " Updated" + Date(System.currentTimeMillis())
//                        val edited = original.copyOfBuilder()
//                                .desc(updatedDesc)
//                                .build()
//                        Amplify.DataStore.save(edited,
//                                {
//                                    Log.i("MyAmplifyApp", "Updated a ProjectIB")
//                                    assertEquals(updatedDesc, it.item().desc)
//                                    Log.i("MyAmplifyApp", "countdown latch")
//                                    countDownLatch.countDown()
//                                    //deletePosts(countDownLatch, edited)
//                                },
//                                { Log.e("MyAmplifyApp", "Update failed forProjectIB", it) }
//                        )
//                    }
//                },
//                {
//                    Log.e("MyAmplifyApp", "Query failed for ProjectIB", it)
//                }
//        )
//
//
//  //  }
//
////    private fun updateComment(toBeUpdatedTeamIB: TeamIB, countDownLatch: CountDownLatch) {
////        Amplify.DataStore.query(TeamIB::class.java, Where.identifier(TeamIB::class.java,
////                TeamIB.TeamIBIdentifier(toBeUpdatedTeamIB.id, toBeUpdatedTeamIB.content)),
////                { matches ->
////                    if (matches.hasNext()) {
////                        val original = matches.next()
////                        val updatedDesc = toBeUpdatedTeamIB.desc + " Updated" + Date(System.currentTimeMillis())
////                        val edited = original.copyOfBuilder()
////                                .desc(updatedDesc)
////                                .build()
////                        Amplify.DataStore.save(edited,
////                                {
////                                    Log.i("MyAmplifyApp", "Updated a TeamIB")
////                                    assertEquals(updatedDesc, it.item().desc)
////                                    Log.i("MyAmplifyApp", "countdown latch")
////                                    countDownLatch.countDown()
////                                },
////                                { Log.e("MyAmplifyApp", "Update failed forTeamIB", it) }
////                        )
////                    }
////                },
////                {
////                    Log.e("MyAmplifyApp", "Query failed for TeamIB", it)
////                }
////        )
////    }
////
////    private fun deletePosts(countDownLatch: CountDownLatch, toBeDeletedProjectIB: ProjectIB) {
////        Amplify.DataStore.query(ProjectIB::class.java, Where.identifier(ProjectIB::class.java,
////                ProjectIB.ProjectIBIdentifier(toBeDeletedProjectIB.id, toBeDeletedProjectIB.title)),
////                { matches ->
////                    if (matches.hasNext()) {
////                        val original = matches.next()
////                        Amplify.DataStore.delete(original,
////                                {
////                                    Log.i("MyAmplifyApp", "Deleted a ProjectIB")
////                                },
////                                { Log.e("MyAmplifyApp", "Delete failed forProjectIB", it) }
////                        )
////                    }
////                },
////                {
////                    Log.e("MyAmplifyApp", "Query failed for ProjectIB", it)
////                }
////        )
////    }
////
////    private fun deleteComments(countDownLatch: CountDownLatch, toBeDeletedTeamIB: TeamIB) {
////        Amplify.DataStore.query(TeamIB::class.java, Where.identifier(TeamIB::class.java,
////                TeamIB.TeamIBIdentifier(toBeDeletedTeamIB.id, toBeDeletedTeamIB.content)),
////                { matches ->
////                    if (matches.hasNext()) {
////                        val original = matches.next()
////                        Amplify.DataStore.delete(original,
////                                {
////                                    Log.i("MyAmplifyApp", "Deleted a TeamIB")
////                                    Log.i("MyAmplifyApp", "countdown latch")
////                                    countDownLatch.countDown()
////                                },
////                                { Log.e("MyAmplifyApp", "Delete failed for TeamIB", it) }
////                        )
////                    }
////                },
////                {
////                    Log.e("MyAmplifyApp", "Query failed for TeamIB", it)
////                }
////        )
////    }
//
//    @Ignore("Deletes all the records")
//    @Test
//    fun deleteAll() {
//        val countDownLatch = CountDownLatch(2)
//        Amplify.DataStore.query(ProjectIB::class.java,
//                { matches ->
//                    while (matches.hasNext()) {
//                        val original = matches.next()
//                        Amplify.DataStore.delete(original,
//                                {
//                                    Log.i("MyAmplifyApp", "Deleted a ProjectIB")
//                                    Log.i("MyAmplifyApp", "countdown latch")
//                                },
//                                { Log.e("MyAmplifyApp", "Delete failed for ProjectIB", it) }
//                        )
//                    }
//                    countDownLatch.countDown()
//                },
//                {
//                    Log.e("MyAmplifyApp", "Query failed for CProjectIB", it)
//                }
//        )
//
//        countDownLatch.await(200, TimeUnit.SECONDS)
//    }
////    @Test
////    fun testQueryWithNestedPredicate(){
////        val countDownLatch = CountDownLatch(1)
////        Amplify.DataStore.start({},{})
////        Amplify.Hub.subscribe(HubChannel.DATASTORE,
////                { it.name == DataStoreChannelEventName.READY.toString() },
////                {
////                    Amplify.DataStore.query(TeamIB::class.java,
////                            ProjectIB.TITLE.eq("07-07-2022 22:11:06 ProjectIB title"),
////                            {
////                                allComments: Iterator<TeamIB> ->
////                                while (allComments.hasNext()) {
////                                    val comment = allComments.next()
////                                    Log.i("MyAmplifyApp", " I am  post & content: " + comment.content + " & id: " +
////                                            comment.id)
////                                    Amplify.DataStore.delete(comment,
////                                            {
////                                                Log.i("MyAmplifyApp", "Deleted a Post")
////                                                countDownLatch.countDown()
////                                            },
////                                            { Log.e("MyAmplifyApp", "Delete failed forPost", it) }
////                                    )
////
////                                }
////                            }
////                            ,{ failure: DataStoreException? -> Log.e("MyAmplifyApp", "Query failed.", failure) }
////                    )
////                })
////
////        assertTrue(countDownLatch.await(300, TimeUnit.SECONDS))
////    }
//
//
////    @Test
////    fun apiTest(){
////        val countDownLatch = CountDownLatch(2)
////        val ProjectIB = ProjectIB.builder()
////            .id(currentDate + Math.random() + "ProjectIBid")
////            .title("$currentDate ProjectIB title")
////            .desc("ProjectIB desc")
////            .build()
////        val TeamIB = TeamIB.builder()
////            .id(currentDate + Math.random() + "TeamIB")
////            .content("$currentDate TeamIB content")
////            .post(ProjectIB)
////            .desc("TeamIB desc")
////            .build()
////
////        Amplify.API.mutate(
////            ModelMutation.create(ProjectIB),
////            {
////                Log.i("MyAmplifyApp", "Post with id: ${it.data.id}")
////                Amplify.API.mutate(ModelMutation.create(TeamIB),
////                    {
////                        Log.i("MyAmplifyApp", "Comment with id: ${it.data.id}")
////                        countDownLatch.countDown()
////                    },
////                    { Log.e("MyAmplifyApp", "Create failed", it) }
////                )
////                countDownLatch.countDown()
////            },
////            { Log.e("MyAmplifyApp", "Create failed", it) }
////        )
////        assertTrue(countDownLatch.await(300, TimeUnit.SECONDS))
////    }
//
//}