package com.archcoders.starswarsproject.view

import android.os.Bundle
import android.text.SpannableString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.core.text.inSpans
import androidx.fragment.app.Fragment
import com.archcoders.starswarsproject.databinding.FragmentDetailBinding
import com.archcoders.starswarsproject.entities.CharacterEntity

class DetailFragment(private val character: CharacterEntity) : Fragment() {

    lateinit var binding: FragmentDetailBinding
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
        bindViews()
    }

    private fun bindViews() {
        val description = SpannableString(character.description)
        binding.detailNameCharacter.text = character.name

        val spannedText = buildSpannedString {
            bold { append("Description") }
            this.append(description)
        }

        binding.detailDescriptionCharacter.text = spannedText
    }
}