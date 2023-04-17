package com.archcoders.starswarsproject.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.SpannableString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.archcoders.starswarsproject.databinding.FragmentDetailBinding
import com.archcoders.starswarsproject.entities.CharacterEntity
import com.archcoders.starswarsproject.viewmodel.DetailCharacterViewModel
import com.archcoders.starswarsproject.viewmodel.DetailCharacterViewModelFactory
import com.bumptech.glide.Glide
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailCharacterFragment(private val character: CharacterEntity) : Fragment() {

    companion object{
        const val CHARACTER = "DetailCharacterFragment:character"
    }
    lateinit var binding: FragmentDetailBinding
    private lateinit var applicationContext: Context

    private val viewModel: DetailCharacterViewModel by viewModels {
        DetailCharacterViewModelFactory(requireNotNull(activity?.intent?.getParcelableExtra(CHARACTER)))
    }
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
        binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       checkScreenToUpdate()
    }

    private fun checkScreenToUpdate() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.state.collect{updateUI(it.character)}
            }
        }
    }

    private fun updateUI(character: CharacterEntity) {
        val url = character.thumbnail?.path + "." + character.thumbnail?.extension
        Glide.with(applicationContext).load(url).into(binding.detailImageCharacter)
        val description = SpannableString(character.description)
        binding.detailNameCharacter.text = character.name

        val spannedText = buildSpannedString {
            bold { append("Description\n") }
            this.append(description)
        }

        binding.detailDescriptionCharacter.text = spannedText
    }
}