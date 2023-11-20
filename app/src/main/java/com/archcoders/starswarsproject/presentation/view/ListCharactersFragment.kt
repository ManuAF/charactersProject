package com.archcoders.starswarsproject.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.archcoders.starswarsproject.R
import com.archcoders.starswarsproject.databinding.FragmentListCharactersBinding
import com.archcoders.starswarsproject.presentation.view.adapters.CharacterAdapter
import com.archcoders.starswarsproject.presentation.viewmodel.ListCharacterViewModel
import com.archcoders.starswarsproject.utils.launchAndCollect
import com.archcoders.starswarsproject.utils.visible
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListCharactersFragment : Fragment(R.layout.fragment_list_characters) {

    val viewModel by viewModel<ListCharacterViewModel>()

    private lateinit var applicationContext: Context
    private val adapter = CharacterAdapter { mainState.onCharacterClicked(it) }

    private lateinit var mainState: MainState

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.applicationContext?.let {
            applicationContext = it
        }
        mainState = buildState(applicationContext)

        val binding = FragmentListCharactersBinding.bind(view).apply {
            characterList.adapter = adapter
        }

        binding.textError.text = ""

        with(viewModel.state) {
            diff({ it.characterDBS }, adapter::submitList)
            diff({ it.loading }) { binding.progress.visible = it }
        }

        mainState.requestLocationPermission {
            viewModel.onUiReady()
        }
    }


    private fun <T, U> Flow<T>.diff(mapf: (T) -> U, body: (U) -> Unit) {
        viewLifecycleOwner.launchAndCollect(
            flow = this.map(mapf).distinctUntilChanged(),
            body = body
        )
    }

}