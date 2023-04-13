package com.archcoders.starswarsproject.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.archcoders.starswarsproject.ListCharacterViewModel
import com.archcoders.starswarsproject.view.adapters.CharacterAdapter
import com.archcoders.starswarsproject.entities.CharacterEntity
import com.archcoders.starswarsproject.services.PeopleService
import com.archcoders.starswarsproject.repository.RetrofitManager
import com.archcoders.starswarsproject.databinding.ActivityMainBinding
import com.archcoders.starswarsproject.view.interfaces.OnClickCharacter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), OnClickCharacter{

    private lateinit var binding: ActivityMainBinding
    private var characters = mutableListOf<CharacterEntity>()
    private var viewModel: ListCharacterViewModel = ListCharacterViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getCharacterList()
        setObservers()
    }

    private fun getCharacterList() {
        lifecycleScope.launch {
            viewModel.createUserList()
        }
    }

    private fun setObservers() {
        viewModel._characters.observe(this){
            characters = it
            createCharactersView()
        }
    }


    private fun createCharactersView() {
        val callBack: OnClickCharacter = this
        runOnUiThread {
            binding.characterList.apply {
                this.adapter = CharacterAdapter(applicationContext, layoutInflater, characters, callBack)
                this.layoutManager = GridLayoutManager(applicationContext, 3)
                this.setHasFixedSize(true)
            }
        }
    }

    override fun onClickCharacter(characterEntity: CharacterEntity) {
        //TODO
    }
}