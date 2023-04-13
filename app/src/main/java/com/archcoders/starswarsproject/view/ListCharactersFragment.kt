package com.archcoders.starswarsproject.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.archcoders.starswarsproject.ListCharacterViewModel
import com.archcoders.starswarsproject.databinding.FragmentListCharactersBinding
import com.archcoders.starswarsproject.entities.CharacterEntity
import com.archcoders.starswarsproject.view.adapters.CharacterAdapter
import com.archcoders.starswarsproject.view.interfaces.OnClickCharacter
import kotlinx.coroutines.launch

class ListCharactersFragment: Fragment(), OnClickCharacter {

    private lateinit var binding: FragmentListCharactersBinding
    private var characters = mutableListOf<CharacterEntity>()
    private var viewModel: ListCharacterViewModel = ListCharacterViewModel()
    private lateinit var applicationContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       context?.let {
           applicationContext = it
       }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListCharactersBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews()
    }
    private fun bindViews(){
        getCharacterList()
        setObservers()
    }

    private fun getCharacterList() {
        lifecycleScope.launch {
            viewModel.createUserList()
        }
    }

    private fun setObservers() {
        viewModel._characters.observe(viewLifecycleOwner){
            characters = it
            createCharactersView()
        }
    }


    private fun createCharactersView() {
        val callBack: OnClickCharacter = this
        activity?.runOnUiThread {
            binding.characterList.apply {
                this.adapter = CharacterAdapter(applicationContext, layoutInflater, characters, callBack)
                this.layoutManager = GridLayoutManager(applicationContext, 3)
                this.setHasFixedSize(true)
            }
        }
    }

    override fun onClickCharacter(characterEntity: CharacterEntity) {
        (activity as MainActivity).goToCharacter(characterEntity)
    }
}