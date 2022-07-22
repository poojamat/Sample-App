package com.example.mydatastoreapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.amplifyframework.core.Amplify
import com.example.mydatastoreapp.databinding.HasOneUniBinding

class HasOneBiActivity : AppCompatActivity() {
    // This property is only valid between onCreateView and
    // onDestroyView.
    private lateinit var binding: HasOneUniBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HasOneUniBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.label.text = getString(R.string.HasOneBiLabel)
        val mutation = Mutation()
        binding.query.setOnClickListener {
            Query().queryHasOneBi()
        }
        binding.clear.setOnClickListener {
            Amplify.DataStore.clear({},{})
        }
        binding.save.setOnClickListener {
            mutation.saveHasOneBi(binding.editTextPosTitle.text.toString())
        }
        binding.update.setOnClickListener {
            mutation.updateHasOneBi(binding.editTextUpdate.text.toString(), binding.textPostId.text.toString())
        }
        binding.delete.setOnClickListener {
            mutation.deleteHasOneBi(binding.editTextDelete.text.toString(), binding.editTextDeleteTitle.toString())
        }
        //Observe().observe(this::getPostId)
        //ObserveQuery().oqEqCaseSensitive()
    }

}