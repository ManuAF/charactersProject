package com.archcoders.starswarsproject.view.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.archcoders.starswarsproject.R
import com.archcoders.starswarsproject.databinding.ItemPeopleBinding
import com.archcoders.starswarsproject.entities.CharacterEntity
import com.archcoders.starswarsproject.view.interfaces.OnClickCharacter
import com.bumptech.glide.Glide

class CharacterAdapter(
    val context: Context,
    private val layoutInflater: LayoutInflater,
    private val characters: MutableList<CharacterEntity>,
    private val callBack: OnClickCharacter
) : RecyclerView.Adapter<CharacterAdapter.CharacterHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterHolder {
        val itemPeopleBinding = ItemPeopleBinding.inflate(layoutInflater, parent, false)
        return CharacterHolder(itemPeopleBinding)
    }

    override fun getItemCount(): Int = characters.size

    override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
        val character = characters[position]
        holder.bind(character)
    }

    inner class CharacterHolder(private val binding: ItemPeopleBinding) : ViewHolder(binding.root) {
        fun bind(character: CharacterEntity) {
            try {
                val url = character.thumbnail?.path + "." + character.thumbnail?.extension
                Glide.with(context).load(url).into(binding.imageCharacter)
            } catch (e: Exception) {
                val placeHolder = ContextCompat.getDrawable(context, R.drawable.ic_placeholder)
                Glide.with(context).load(placeHolder).into(binding.imageCharacter)
                Log.e("bind()", e.message ?: "Fail to get image from URL")
            }
            binding.nameCharacter.text = character.name
            binding.root.setOnClickListener {
                callBack.onClickCharacter(character)
            }
        }
    }
}