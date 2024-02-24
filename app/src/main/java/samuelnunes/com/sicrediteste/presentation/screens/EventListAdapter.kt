package samuelnunes.com.sicrediteste.presentation.screens

import android.view.View.GONE
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.samuelnunes.utility_tool_kit.extensions.inflater
import samuelnunes.com.sicrediteste.data.local.entitys.EventEntity
import samuelnunes.com.sicrediteste.databinding.EventItemListBinding

internal class EventListAdapter(private val onEventClick: (EventEntity) -> Unit) :
    ListAdapter<EventEntity, EventListAdapter.EventViewHolder>(BreedItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder =
        EventViewHolder(EventItemListBinding.inflate(parent.inflater, parent, false))

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class EventViewHolder(private val binding: EventItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: EventEntity) {
            binding.apply {
                root.setOnClickListener {
                    onEventClick(item)
                }
                ivEvent.load(item.image) {
                    listener(
                        onError = { _, _ -> ivEvent.visibility = GONE }
                    )
                }
                tvTitle.text = item.title
                tvDescription.text = item.description
            }
        }
    }

    internal object BreedItemCallback : DiffUtil.ItemCallback<EventEntity>() {
        override fun areItemsTheSame(first: EventEntity, second: EventEntity): Boolean =
            first.id == second.id

        override fun areContentsTheSame(first: EventEntity, second: EventEntity): Boolean =
            first == second
    }

}