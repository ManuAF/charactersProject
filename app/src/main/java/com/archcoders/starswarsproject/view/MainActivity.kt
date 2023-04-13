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

class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bindViews()
    }

    private fun bindViews() {
        supportFragmentManager.beginTransaction().add(
            binding.flCharacters.id,
            ListCharactersFragment()
        ).commitAllowingStateLoss()
    }

    fun goToCharacter(character: CharacterEntity){
        binding.flCharacters.removeAllViews()
        supportFragmentManager.beginTransaction().add(
            binding.flCharacters.id,
            DetailFragment(character)
        ).commitAllowingStateLoss()
    }

}