package com.geektech.kotlin1hw3.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geektech.kotlin1hw3.R
import com.geektech.kotlin1hw3.databinding.ItemRvBinding
import com.geektech.kotlin1hw3.extension.loadImage

class Adapter(
    private var list: ArrayList<String>,
    private var longListener: Listener
) :

    RecyclerView.Adapter<Adapter.ViewHolder>() {

    private var lastCheckedPos = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount() = list.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val ui = ItemRvBinding.bind(view)

        init {
            itemView.setOnLongClickListener {
                if (lastCheckedPos == adapterPosition) {
                    lastCheckedPos = -1
                } else {
                    lastCheckedPos = adapterPosition
                    longListener.onLongClick(adapterPosition)
                }
                notifyItemRangeChanged(adapterPosition, itemCount)
                true
            }
        }

        fun onBind(url: String) {
            ui.ivImage.loadImage(url)
            if (adapterPosition == lastCheckedPos) {
                ui.bgItem.visibility = View.VISIBLE
            } else ui.bgItem.visibility = View.GONE
        }

    }

    interface Listener {
        fun onLongClick(id: Int)
    }
}