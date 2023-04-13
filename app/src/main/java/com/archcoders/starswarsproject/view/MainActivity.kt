package com.archcoders.starswarsproject.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.archcoders.starswarsproject.view.adapters.CharacterAdapter
import com.archcoders.starswarsproject.entities.CharacterEntity
import com.archcoders.starswarsproject.services.PeopleService
import com.archcoders.starswarsproject.repository.RetrofitManager
import com.archcoders.starswarsproject.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var characters = mutableListOf<CharacterEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createUserList()
    }

    private fun createUserList() {
        lifecycleScope.launch(Dispatchers.IO) {
            RetrofitManager.retrofit.create(PeopleService::class.java).getPeopleList()
                .body()?.apply {
                    this.data?.let {
                        it.results?.let {
                            characters = it as MutableList<CharacterEntity>
                            createCharactersView()
                        }
                    }
                }
        }
    }

    private fun createCharactersView() {
        runOnUiThread {
            binding.characterList.apply {
                this.adapter = CharacterAdapter(applicationContext, layoutInflater, characters)
                this.layoutManager = GridLayoutManager(applicationContext, 3)
                this.setHasFixedSize(true)
            }
        }
    }
}