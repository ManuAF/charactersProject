package com.archcoders.starswarsproject.view.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.archcoders.starswarsproject.R
import com.archcoders.starswarsproject.databinding.ItemPeopleBinding
import com.archcoders.starswarsproject.entities.CharacterEntity
import com.archcoders.starswarsproject.utils.basicDiffUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException

class CharacterAdapter(
    private val callBack: (CharacterEntity) -> Unit,
    private val layoutInflater: LayoutInflater
) : RecyclerView.Adapter<CharacterAdapter.CharacterHolder>() {

    private var characters: List<CharacterEntity> by basicDiffUtil(
        emptyList(),
        areItemsTheSame = { old, new -> old.id == new.id }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterHolder {
        val binding = ItemPeopleBinding.inflate(layoutInflater)
        return CharacterHolder(binding, parent.context)
    }

    fun submitList(characters: List<CharacterEntity>) {
        this.characters = characters
        notifyItemChanged(characters.size - 1)
    }

    override fun getItemCount(): Int = characters.size

    override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
        val character = characters[position]
        holder.bind(character)
        holder.itemView.setOnClickListener { callBack(character) }
    }

    class CharacterHolder(
        private val binding: ItemPeopleBinding,
        private val context: Context
    ) : ViewHolder(binding.root) {
        fun bind(character: CharacterEntity) = with(binding) {
            try {
                val url = character.thumbnail?.path + "." + character.thumbnail?.extension
                Glide.with(context).load(url).into(binding.imageCharacter)
            } catch (e: GlideException) {
                val placeHolder = ContextCompat.getDrawable(context, R.drawable.ic_placeholder)
                Glide.with(context).load(placeHolder).into(binding.imageCharacter)
                Log.e("bind()", e.message ?: "Fail to get image from URL")
            }
            nameCharacter.text = character.name

        }
    }
}