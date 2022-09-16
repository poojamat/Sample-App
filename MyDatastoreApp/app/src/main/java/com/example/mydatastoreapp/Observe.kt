package com.example.mydatastoreapp

import android.util.Log
import com.amplifyframework.core.Amplify
import com.amplifyframework.datastore.generated.model.Blog
import com.amplifyframework.datastore.generated.model.Post

class Observe {

    public fun observe(callback: (String)->Unit){
                Amplify.DataStore.clear({
            Amplify.DataStore.observe(
                    Blog::class.java,
                    {
                        Log.i("MyAmplifyApp", "Observation began")

                    },
                    { postChanged ->
                        val blog = postChanged.item()
                       // callback(blog.id)
                        Log.i("MyAmplifyApp", "Blog: $blog")
                    },
                    { Log.e("MyAmplifyApp", "Observation failed", it) },
                    { Log.i("MyAmplifyApp", "Observation complete") }
            )
        },{})
    }

}