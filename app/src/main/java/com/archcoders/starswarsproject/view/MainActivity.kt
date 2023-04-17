package com.archcoders.starswarsproject.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.archcoders.starswarsproject.entities.CharacterEntity
import com.archcoders.starswarsproject.databinding.ActivityMainBinding

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
            DetailCharacterFragment(character)
        ).commitAllowingStateLoss()
    }

}