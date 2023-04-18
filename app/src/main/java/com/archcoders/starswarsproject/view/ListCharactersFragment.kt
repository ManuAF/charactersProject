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
import com.archcoders.starswarsproject.R
import com.archcoders.starswarsproject.viewmodel.ListCharacterViewModel
import com.archcoders.starswarsproject.databinding.FragmentListCharactersBinding
import com.archcoders.starswarsproject.entities.CharacterEntity
import com.archcoders.starswarsproject.model.UiState
import com.archcoders.starswarsproject.usecase.impl.GetCharactersListUCImpl
import com.archcoders.starswarsproject.utils.visible
import com.archcoders.starswarsproject.view.adapters.CharacterAdapter
import kotlinx.coroutines.launch

class ListCharactersFragment : Fragment(R.layout.fragment_list_characters) {
    private lateinit var binding: FragmentListCharactersBinding
    private lateinit var viewModel: ListCharacterViewModel
    private lateinit var adapter: CharacterAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentListCharactersBinding.bind(view).apply {
            characterList.adapter = adapter
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {
                    binding.updateUI(it)
                }
            }
        }
    }

    private fun FragmentListCharactersBinding.updateUI(state: UiState) {
        progress.visible = state.loading
        state.characters?.let(adapter::submitList)
        state.navigateTo?.let(::navigateTo)
    }

    private fun navigateTo(characterEntity: CharacterEntity) {
        (activity as MainActivity).goToCharacter(characterEntity)
    }

}