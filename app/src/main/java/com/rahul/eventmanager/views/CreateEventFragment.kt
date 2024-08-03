package com.rahul.eventmanager.views

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.rahul.eventmanager.databinding.CreateEventFragmentBinding
import com.rahul.eventmanager.model.room.entity.Event
import com.rahul.eventmanager.respository.EventViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CreateEventFragment : Fragment() {

    private var _binding: CreateEventFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var eventViewModel: EventViewModel
    private lateinit var event: Event


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CreateEventFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        eventViewModel = ViewModelProvider(this).get(EventViewModel::class.java)
        try {
            event = CreateEventFragmentArgs.fromBundle(requireArguments()).event
        } catch (e: Exception) {
        }

        populateData()

        binding.btnSave.setOnClickListener {
            saveEvent()
        }
        binding.tilEventDateTime.setOnClickListener {
            showDatePicker()
        }
    }

    private fun populateData() {
        if (this::event.isInitialized) {
            var participants = ""
            event.participants.forEach {
                participants = "$participants,$it"
            }
            binding.etEventName.setText(event.name)
            binding.etEventLocation.setText(event.location)
            binding.etEventParticipants.setText(participants)
            binding.etEventDescription.setText(event.description)
            binding.etEventDateTime.setText(event.dateTime)
            binding.btnSave.text = "Update Event"
        }
    }

    private fun saveEvent() {
        if (validate()) {

            val name = binding.etEventName.text.toString().trim()
            val dateTime = binding.etEventDateTime.text.toString().trim()
            val location = binding.etEventLocation.text.toString().trim()
            val description = binding.etEventDescription.text.toString().trim()
            val participants = binding.etEventParticipants.text.toString().split(",")



            if (!this::event.isInitialized) {
                val event = Event(
                    name = name,
                    dateTime = dateTime,
                    location = location,
                    description = description,
                    participants = participants
                )
                eventViewModel.insert(event)
                findNavController().navigateUp()
            } else {
                val event = Event(
                    id = event.id,
                    name = name,
                    dateTime = dateTime,
                    location = location,
                    description = description,
                    participants = participants
                )
                eventViewModel.update(event)
                findNavController().navigateUp()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val today = calendar.timeInMillis

        val datePicker = DatePickerDialog(
            requireContext(),
            { _, year, month, dayOfMonth ->
                // Format and display the selected date
                val selectedDate = Calendar.getInstance().apply {
                    set(year, month, dayOfMonth)
                }.time

                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val formattedDate = dateFormat.format(selectedDate)

                // Save the selected date
                binding.etEventDateTime.setText(formattedDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        calendar.add(Calendar.YEAR, 1)
        val oneYearFromNow = calendar.timeInMillis

        // Set the minimum and maximum date for the date picker
        datePicker.datePicker.minDate = today
        datePicker.datePicker.maxDate = oneYearFromNow

        datePicker.show()
    }

    private fun validate(): Boolean {
        var valid = true
        if (binding.etEventName.text?.isEmpty() == true) {
            binding.etEventName.error = "Event name cannot be empty"
            valid = false
        } else {
            binding.etEventName.error = null
        }
        if (binding.etEventDateTime.text?.isEmpty() == true) {
            binding.etEventDateTime.error = "Event Date cannot be empty"
            valid = false
        } else {
            binding.etEventDateTime.error = null
        }
        if (binding.etEventDescription.text?.isEmpty() == true) {
            binding.etEventDescription.error = "Event Description cannot be empty"
            valid = false
        } else {
            binding.etEventDescription.error = null
        }
        if (binding.etEventParticipants.text?.isEmpty() == true) {
            binding.etEventParticipants.error = "Event Participants cannot be empty"
            valid = false
        } else {
            binding.etEventParticipants.error = null
        }
        if (binding.etEventLocation.text?.isEmpty() == true) {
            binding.etEventLocation.error = "Event Location cannot be empty"
            valid = false
        } else {
            binding.etEventLocation.error = null
        }
        return valid
    }
}
