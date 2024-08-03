package com.rahul.eventmanager.views

import com.rahul.eventmanager.databinding.EventListFragmentBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rahul.eventmanager.R
import com.rahul.eventmanager.respository.EventViewModel

class EventListFragment : Fragment() {

    private var _binding: EventListFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var eventViewModel: EventViewModel
    private lateinit var eventAdapter: EventAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = EventListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        eventViewModel = ViewModelProvider(this)[EventViewModel::class.java]
        eventAdapter = EventAdapter(onClick = { event ->
            val action =
                EventListFragmentDirections.actionEventListFragmentToEventDetailFragment(event)
            findNavController().navigate(action)
        }, onEditClick = { editEvent ->
            val action =
                EventListFragmentDirections.actionEventListFragmentToCreateEventFragment(
                    editEvent
                )
            findNavController().navigate(action)
        },
            onDeleteClick = { delete ->
                eventViewModel.delete(delete)
            }
        )

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = eventAdapter
        }

        eventViewModel.getAllEvents().observe(viewLifecycleOwner) { events ->
            eventAdapter.submitList(events)
            if (events.isEmpty()) {
                binding.noDataLayout.root.visibility = View.VISIBLE
            } else {
                binding.noDataLayout.root.visibility = View.GONE
            }
        }

        binding.fabAddEvent.setOnClickListener {
            findNavController().navigate(R.id.action_eventListFragment_to_createEventFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
