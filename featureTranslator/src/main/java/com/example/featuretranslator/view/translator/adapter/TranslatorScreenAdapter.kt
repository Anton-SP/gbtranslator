package com.example.featuretranslator.view.translator.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.featuretranslator.data.Word
import com.example.featuretranslator.databinding.ItemRvTranslatorScreenBinding

class TranslatorScreenAdapter(
    private val onClick: (Word) -> Unit,
    private var data: List<Word>
) : RecyclerView.Adapter<TranslatorScreenAdapter.RecyclerItemViewHolder>() {

    fun submitList(newList: List<Word>) {
        this.data = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TranslatorScreenAdapter.RecyclerItemViewHolder {

        val binding = ItemRvTranslatorScreenBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        val holder = RecyclerItemViewHolder(
            binding = binding,
            onClick = onClick
        )
        return holder
    }

    override fun onBindViewHolder(
        holder: TranslatorScreenAdapter.RecyclerItemViewHolder,
        position: Int
    ) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(
        private val binding: ItemRvTranslatorScreenBinding,
        private val onClick: (Word) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Word) {

            if (layoutPosition != RecyclerView.NO_POSITION) {
                binding.apply {
                    tvHeaderItem.text = data.text
                    tvDescriptionItem.text = data.meanings?.get(0)?.translation?.translation
                    cvItemContainer.setOnClickListener {
                        onClick.invoke(data)
                    }
                }
            }
        }
    }

}