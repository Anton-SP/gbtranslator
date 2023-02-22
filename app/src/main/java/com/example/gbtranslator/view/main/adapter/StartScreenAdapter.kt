package com.example.gbtranslator.view.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gbtranslator.data.Word
import com.example.gbtranslator.databinding.ItemRvStartScreenBinding

class StartScreenAdapter(
    private val onClick: (Word) -> Unit,
    private var data: List<Word>
) : RecyclerView.Adapter<StartScreenAdapter.RecyclerItemViewHolder>() {

    fun submitList(newList: List<Word>) {
        this.data = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StartScreenAdapter.RecyclerItemViewHolder {

        val binding = ItemRvStartScreenBinding.inflate(
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
        holder: StartScreenAdapter.RecyclerItemViewHolder,
        position: Int
    ) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(
        private val binding: ItemRvStartScreenBinding,
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