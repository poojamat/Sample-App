package com.example.mydatastoreapp

import android.app.Application
import android.util.Log
import com.amplifyframework.AmplifyException
import com.amplifyframework.api.aws.AWSApiPlugin
import com.amplifyframework.core.Amplify
import com.amplifyframework.datastore.AWSDataStorePlugin
import com.amplifyframework.datastore.DataStoreConfiguration
import com.amplifyframework.datastore.DataStoreConflictHandler
import com.amplifyframework.logging.AndroidLoggingPlugin
import com.amplifyframework.logging.LogLevel

class MyDataStoreApp: Application() {

    override fun onCreate() {
        super.onCreate()

        try {
            val config = DataStoreConfiguration.builder()
                    .conflictHandler (DataStoreConflictHandler.alwaysRetryLocal())
                    .build()
            Amplify.addPlugin(AWSDataStorePlugin.builder().dataStoreConfiguration(config).build())
            Amplify.addPlugin(AWSApiPlugin())
            Amplify.addPlugin(AndroidLoggingPlugin(LogLevel.DEBUG))
            Amplify.configure(applicationContext)
            Log.i("MyAmplifyApp", "Initialized Amplify")
        } catch (error: AmplifyException) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error)
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        Amplify.DataStore.stop({},{})
    }
}