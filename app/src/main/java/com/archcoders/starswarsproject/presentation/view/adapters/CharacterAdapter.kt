package com.archcoders.starswarsproject.presentation.view.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.archcoders.domain.model.CharacterDO
import com.archcoders.starswarsproject.R
import com.archcoders.starswarsproject.databinding.ItemPeopleBinding
import com.archcoders.starswarsproject.utils.basicDiffUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.archcoders.starswarsproject.data.database.CharacterDB

class CharacterAdapter(
    private val callBack: (CharacterDO) -> Unit
) : RecyclerView.Adapter<CharacterAdapter.CharacterHolder>() {

    private var characterDBS: List<CharacterDO> by basicDiffUtil(
        emptyList(),
        areItemsTheSame = { old, new -> old.id == new.id }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPeopleBinding.inflate(inflater)
        return CharacterHolder(binding, parent.context)
    }

    fun submitList(characterDBS: List<CharacterDO>?) {
        characterDBS?.let {
            this.characterDBS = it
            notifyItemChanged(it.size - 1)
        }
    }

    override fun getItemCount(): Int = characterDBS.size

    override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
        val character = characterDBS[position]
        holder.bind(character)
        holder.itemView.setOnClickListener { callBack(character) }
    }

    class CharacterHolder(
        private val binding: ItemPeopleBinding,
        private val context: Context
    ) : ViewHolder(binding.root) {
        fun bind(characterDO: CharacterDO) = with(binding) {
            try {
                val url = characterDO.resourceURI
                Glide.with(context).load(url).into(binding.imageCharacter)
            } catch (e: GlideException) {
                val placeHolder = ContextCompat.getDrawable(context, R.drawable.ic_placeholder)
                Glide.with(context).load(placeHolder).into(binding.imageCharacter)
            }
            nameCharacter.text = characterDO.name

        }
    }
}