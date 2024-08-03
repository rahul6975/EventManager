package com.rahul.eventmanager.views

import com.rahul.eventmanager.databinding.EventDetailFragmentBinding
import com.rahul.eventmanager.databinding.EventListFragmentBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rahul.eventmanager.model.room.entity.Event

class EventDetailFragment : Fragment() {

    private var _binding: EventDetailFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var event: Event

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = EventDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        event = EventDetailFragmentArgs.fromBundle(requireArguments()).event
        displayEventDetails()
    }

    private fun displayEventDetails() {
        binding.tvEventName.setText(event.name)
        binding.tvEventDateTime.setText(event.dateTime)
        binding.tvEventLocation.setText(event.location)
        binding.tvEventDescription.setText(event.description)
        binding.tvEventParticipants.setText(event.participants.joinToString(", "))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
