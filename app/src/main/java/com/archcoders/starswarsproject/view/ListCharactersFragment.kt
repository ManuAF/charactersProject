package com.archcoders.starswarsproject.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.archcoders.starswarsproject.viewmodel.ListCharacterViewModel
import com.archcoders.starswarsproject.databinding.FragmentListCharactersBinding
import com.archcoders.starswarsproject.entities.CharacterEntity
import com.archcoders.starswarsproject.usecase.impl.GetCharactersListUCImpl
import com.archcoders.starswarsproject.utils.visible
import com.archcoders.starswarsproject.view.adapters.CharacterAdapter
import kotlinx.coroutines.launch

class ListCharactersFragment : Fragment() {
    private lateinit var binding: FragmentListCharactersBinding
    private lateinit var applicationContext: Context

    private var characters = mutableListOf<CharacterEntity>()

    private lateinit var viewModel: ListCharacterViewModel
    private lateinit var adapter: CharacterAdapter
    private val getCharactersListUCImpl = GetCharactersListUCImpl()

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

    private fun bindViews() {
        viewModel= ListCharacterViewModel(getCharactersListUCImpl)
        adapter = CharacterAdapter(viewModel::onCharacterClick,layoutInflater)
        binding.characterList.adapter = adapter
        setObservers()
    }

    private fun setObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.state.collect(::updateUI)
            }
        }
    }

    private fun updateUI(state: ListCharacterViewModel.UiState) {
        binding.progress.visible = state.loading
        state.characters?.let{
            adapter.submitList(it)
            binding.characterList.apply {
                this.adapter = adapter
                this.layoutManager = GridLayoutManager(applicationContext,3)
                setHasFixedSize(true)
            }
        }
        state.navigateTo?.let(::navigateTo)
    }

    private fun navigateTo(characterEntity: CharacterEntity) {
        (activity as MainActivity).goToCharacter(characterEntity)
    }

}