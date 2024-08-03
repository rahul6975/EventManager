package com.rahul.eventmanager.views

import com.rahul.eventmanager.databinding.ItemEventBinding
import com.rahul.eventmanager.model.room.entity.Event
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class EventAdapter(
    private val onClick: (Event) -> Unit,
    private val onEditClick: (Event) -> Unit,
    private val onDeleteClick: (Event) -> Unit
) :
    ListAdapter<Event, EventAdapter.EventViewHolder>(EventDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = ItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = getItem(position)
        holder.bind(event, onClick, onEditClick, onDeleteClick)
    }

    class EventViewHolder(private val binding: ItemEventBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            event: Event,
            onClick: (Event) -> Unit,
            onEditClick: (Event) -> Unit,
            onDeleteClick: (Event) -> Unit
        ) {
            binding.tvEventName.text = event.name
            binding.tvEventDateTime.text = event.dateTime
            binding.tvEventLocation.text = event.location
            binding.root.setOnClickListener { onClick(event) }
            binding.ivEdit.setOnClickListener { onEditClick(event) }
            binding.ivDelete.setOnClickListener { onDeleteClick(event) }
        }
    }

    class EventDiffCallback : DiffUtil.ItemCallback<Event>() {
        override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem == newItem
        }
    }
}
