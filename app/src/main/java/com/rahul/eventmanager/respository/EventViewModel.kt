package com.rahul.eventmanager.respository

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.rahul.eventmanager.model.room.database.EventDatabase
import com.rahul.eventmanager.model.room.entity.Event
import kotlinx.coroutines.launch

class EventViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: EventRepository

    init {
        val eventDao = EventDatabase.getDatabase(application).eventDao()
        repository = EventRepository(eventDao)
    }

    fun getAllEvents(): LiveData<List<Event>> = repository.getAllEvents()  //fetch all events

    fun insert(event: Event) = viewModelScope.launch {  //add new event
        repository.insert(event)
    }

    fun update(event: Event) = viewModelScope.launch {    // update existing event
        repository.update(event)
    }

    fun delete(event: Event) = viewModelScope.launch {    // delete existing event
        repository.delete(event)
    }
}
