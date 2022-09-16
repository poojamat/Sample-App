package com.example.mydatastoreapp;

import android.util.Log;

import com.amplifyframework.core.Action;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.Consumer;
import com.amplifyframework.core.async.Cancelable;
import com.amplifyframework.core.model.query.ObserveQueryOptions;
import com.amplifyframework.core.model.query.QuerySortBy;
import com.amplifyframework.core.model.query.QuerySortOrder;
import com.amplifyframework.core.model.query.predicate.QueryPredicate;
import com.amplifyframework.datastore.DataStoreException;
import com.amplifyframework.datastore.DataStoreQuerySnapshot;
import com.amplifyframework.datastore.generated.model.Blog;
import com.amplifyframework.datastore.generated.model.Post;
import com.amplifyframework.util.Time;

import java.util.ArrayList;
import java.util.List;

public class ObserveQuery {

    public void oq(){
        String tag = "ObserveQuery";
        Consumer<DataStoreQuerySnapshot<Blog>> onQuerySnapshot = value ->{
            Log.d(tag, "success on contains Blog snapshot");
            Log.d(tag, "number of records: " + value.getItems().size());
            Log.d(tag, "sync status: " + value.getIsSynced());
        };
        Consumer<Cancelable> observationStarted = value ->{
            Log.d(tag, "success on cancelable");
        };
        Consumer<DataStoreException> onObservationError = value ->{
            Log.d(tag, "error on snapshot$value");
        };
        Action onObservationComplete = () ->{
            Log.d(tag, "complete");
        };
        QueryPredicate predicate =
                Blog.NAME.contains("Blog");
        QuerySortBy querySortBy = new QuerySortBy("post", "rating", QuerySortOrder.ASCENDING);
        List<QuerySortBy> sortByList = new ArrayList<QuerySortBy>();
        sortByList.add(querySortBy);
        ObserveQueryOptions options = new ObserveQueryOptions(predicate, sortByList);
        Amplify.DataStore.<Blog>observeQuery(
                Blog.class,
                options,
                observationStarted,
                onQuerySnapshot,
                onObservationError,
                onObservationComplete
        );
    }

    public void oqEqCaseSensitive(){
        String tag = "ObserveQuery";
        Consumer<DataStoreQuerySnapshot<Blog>> onQuerySnapshot = value ->{
            String title = "& Title: ";
            if (value.getItems().size()>0){
                title = title + value.getItems().get(0).getName();
            }
            Log.d(tag, "success on eq My Blog snapshot" + title);
            Log.d(tag, "on eq My Blog snapshot number of records: " + value.getItems().size());
            Log.d(tag, "sync status: " + value.getIsSynced());
        };
        Consumer<Cancelable> observationStarted = value ->{
            Log.d(tag, "success on cancelable");
        };
        Consumer<DataStoreException> onObservationError = value ->{
            Log.d(tag, "error on snapshot$value");
        };
        Action onObservationComplete = () ->{
            Log.d(tag, "complete");
        };
        QueryPredicate predicate =
                Blog.NAME.eq("My Blog");
        QuerySortBy querySortBy = new QuerySortBy("blog", "name", QuerySortOrder.ASCENDING);
        List<QuerySortBy> sortByList = new ArrayList<QuerySortBy>();
        sortByList.add(querySortBy);
        ObserveQueryOptions options = new ObserveQueryOptions(predicate, sortByList);
        Amplify.DataStore.<Blog>observeQuery(
                Blog.class,
                options,
                observationStarted,
                onQuerySnapshot,
                onObservationError,
                onObservationComplete
        );

        Consumer<DataStoreQuerySnapshot<Blog>> onQuerySnapshotContains = value ->{
            Log.d(tag, "on contains observe query blog number of records: " + value.getItems().size());
            Log.d(tag, "sync status: " + value.getIsSynced());
            String title = "& Title: ";
            if (value.getItems().size()>0){
                title = title + value.getItems().get(0).getName();
            }
            Log.d(tag, "success on contains observe query blog " + title);

        };
        QueryPredicate predicateContains =
                Blog.NAME.contains("blog");
        ObserveQueryOptions optionsContains = new ObserveQueryOptions(predicateContains, sortByList);
        Amplify.DataStore.<Blog>observeQuery(
                Blog.class,
                optionsContains,
                observationStarted,
                onQuerySnapshotContains,
                onObservationError,
                onObservationComplete
        );


        Consumer<DataStoreQuerySnapshot<Blog>> onQuerySnapshoteq = value ->{
            Log.d(tag, "on eq observe query my blog number of records: " + value.getItems().size());
            Log.d(tag, "sync status: " + value.getIsSynced());
            String title = "& Title: ";
            if (value.getItems().size()>0){
                title = title + value.getItems().get(0).getName();
            }
            Log.d(tag, "success on eq observe query my blog " + title);
        };
        QueryPredicate predicateeq =
                Blog.NAME.eq("my blog");
        ObserveQueryOptions optionseq = new ObserveQueryOptions(predicateeq, sortByList);
        Amplify.DataStore.<Blog>observeQuery(
                Blog.class,
                optionseq,
                observationStarted,
                onQuerySnapshoteq,
                onObservationError,
                onObservationComplete
        );


        Consumer<DataStoreQuerySnapshot<Blog>> onQuerySnapshotBeginsWith = value ->{
            Log.d(tag, "success on begins with query my number of records: " + value.getItems().size());
            Log.d(tag, "sync status: " + value.getIsSynced());
            String title = "& Title: ";
            if (value.getItems().size()>0){
                title = title + value.getItems().get(0).getName();
            }
            Log.d(tag, "success on begins with query my " + title);
        };
        QueryPredicate predicateBeginsWith =
                Blog.NAME.beginsWith("my");
        ObserveQueryOptions optionsBeginsWith = new ObserveQueryOptions(predicateBeginsWith, sortByList);
        Amplify.DataStore.<Blog>observeQuery(
                Blog.class,
                optionsBeginsWith,
                observationStarted,
                onQuerySnapshotBeginsWith,
                onObservationError,
                onObservationComplete
        );



        Consumer<DataStoreQuerySnapshot<Blog>> onQuerySnapshotContainsCS = value ->{
            Log.d(tag, "on contains query Blog number of records: " + value.getItems().size());
            Log.d(tag, "sync status: " + value.getIsSynced());
            String title = "& Title: ";
            if (value.getItems().size()>0){
                title = title + value.getItems().get(0).getName();
            }
            Log.d(tag, "success on contains query Blog " + title);
        };
        QueryPredicate predicateContainsCS =
                Blog.NAME.contains("Blog");
        ObserveQueryOptions optionsContainsCS = new ObserveQueryOptions(predicateContainsCS, sortByList);
        Amplify.DataStore.<Blog>observeQuery(
                Blog.class,
                optionsContainsCS,
                observationStarted,
                onQuerySnapshotContainsCS,
                onObservationError,
                onObservationComplete
        );


        Consumer<DataStoreQuerySnapshot<Blog>> onQuerySnapshotEqCS = value ->{
            Log.d(tag, "on eq query My Blog number of records: " + value.getItems().size());
            Log.d(tag, "sync status: " + value.getIsSynced());
            String title = "& Title: ";
            if (value.getItems().size()>0){
                title = title + value.getItems().get(0).getName();
            }
            Log.d(tag, "success on eq query My Blog " + title);
        };
        QueryPredicate predicateEqCS =
                Blog.NAME.eq("My Blog");
        ObserveQueryOptions optionsEqCS = new ObserveQueryOptions(predicateEqCS, sortByList);
        Amplify.DataStore.<Blog>observeQuery(
                Blog.class,
                optionsEqCS,
                observationStarted,
                onQuerySnapshotEqCS,
                onObservationError,
                onObservationComplete
        );


        Consumer<DataStoreQuerySnapshot<Blog>> onQuerySnapshotBeginsWithCS = value ->{
            Log.d(tag, "on begins with query My number of records: " + value.getItems().size());
            Log.d(tag, "sync status: " + value.getIsSynced());
            String title = "& Title: ";
            if (value.getItems().size()>0){
                title = title + value.getItems().get(0).getName();
            }
            Log.d(tag, "success on begins with query My " + title);
        };
        QueryPredicate predicateBeginsWithCS =
                Blog.NAME.beginsWith("My");
        ObserveQueryOptions optionsBeginsWithCS = new ObserveQueryOptions(predicateBeginsWithCS, sortByList);
        Amplify.DataStore.<Blog>observeQuery(
                Blog.class,
                optionsBeginsWithCS,
                observationStarted,
                onQuerySnapshotBeginsWithCS,
                onObservationError,
                onObservationComplete
        );
    }
}
