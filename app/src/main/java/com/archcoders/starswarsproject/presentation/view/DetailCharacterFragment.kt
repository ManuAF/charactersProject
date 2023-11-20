package com.archcoders.starswarsproject.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.archcoders.starswarsproject.R
import com.archcoders.starswarsproject.databinding.FragmentDetailBinding
import com.archcoders.starswarsproject.presentation.viewmodel.DetailCharacterViewModel
import com.archcoders.starswarsproject.utils.Constants.CHARACTER_ID
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailCharacterFragment : Fragment(R.layout.fragment_detail) {

    private lateinit var applicationContext: Context

    private val viewModel: DetailCharacterViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.let {
            applicationContext = it
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val characterId: String = arguments?.getSerializable(CHARACTER_ID) as String
        val id = Integer.parseInt(characterId)
        viewModel.initializeCharacter(id)
        viewModel.getCharacterDO()
        val binding = FragmentDetailBinding.bind(view)

        binding.characterDetailToolbar.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.buttonFavourite.setOnClickListener {
            viewModel.onFavouriteClicked()
            binding.checkFavouriteButton()
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { binding.updateUI(it) }
            }
        }
    }

    private fun FragmentDetailBinding.checkFavouriteButton() {
        if (viewModel.state.value.characterDO?.favourite == true) {
            this.buttonFavourite.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.ic_favourite_on))
        } else {
            this.buttonFavourite.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.ic_favourite_off))

        }
    }

    private fun FragmentDetailBinding.updateUI(state: DetailCharacterViewModel.UiState) {
        val character = state.characterDO
        val url = character?.resourceURI
        Glide.with(applicationContext).load(url).into(detailImageCharacter)
        var description = ""
        if (character?.description != null) {
            description = character.description ?: ""
        }
        detailNameCharacter.text = character?.name

        val spannedText = buildSpannedString {
            bold { append("Description:\n") }
            this.append(description)
        }

        checkFavouriteButton()

        detailDescriptionCharacter.text = spannedText
    }
}