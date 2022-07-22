package com.example.mydatastoreapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amplifyframework.core.Action
import com.amplifyframework.core.Amplify
//import com.amplifyframework.kotlin.core.Amplify
import com.amplifyframework.core.Consumer
import com.amplifyframework.core.async.Cancelable
import com.amplifyframework.core.model.query.ObserveQueryOptions
import com.amplifyframework.core.model.query.QuerySortBy
import com.amplifyframework.core.model.query.QuerySortOrder
import com.amplifyframework.core.model.query.predicate.QueryPredicate
import com.amplifyframework.datastore.DataStoreException
import com.amplifyframework.datastore.DataStoreQuerySnapshot
import com.amplifyframework.datastore.generated.model.Post
import com.example.mydatastoreapp.databinding.ActivityMainBinding
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

class MainActivity : AppCompatActivity() {
    // This property is only valid between onCreateView and
    // onDestroyView.
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
//        binding.textPostId.text = Editable()
        val mutation = Mutation()
        binding.query.setOnClickListener {
            Query().query()
        }
        binding.clear.setOnClickListener {
            Amplify.DataStore.clear({},{})
        }
        binding.save.setOnClickListener {
            mutation.save(binding.editTextPosTitle.text.toString(), this::getPostId)
        }
        binding.update.setOnClickListener {
            mutation.update(binding.editTextUpdate.text.toString(), binding.textPostId.text.toString())
        }
        binding.delete.setOnClickListener {
            mutation.delete(binding.editTextDelete.text.toString())
        }
        binding.hasOneUni.setOnClickListener {
            val intent = Intent(this, HasOneUniActivity::class.java)
            startActivity(intent)
        }

        binding.hasOneBi.setOnClickListener {
            val intent = Intent(this, HasOneBiActivity::class.java)
            startActivity(intent)
        }

        binding.hasManyBi.setOnClickListener {
            val intent = Intent(this, HasManyBiActivity::class.java)
            startActivity(intent)
        }
        Observe().observe(this::getPostId)
        ObserveQuery().oqEqCaseSensitive()
    }

    private fun getPostId(id: String){
        //binding.textPostId.text= id
    }
}