package com.rahul.eventmanager.respository

import androidx.lifecycle.LiveData
import com.rahul.eventmanager.model.room.dao.EventDao
import com.rahul.eventmanager.model.room.entity.Event

class EventRepository(private val eventDao: EventDao) {

    fun getAllEvents(): LiveData<List<Event>> = eventDao.getAllEvents()

    suspend fun insert(event: Event) {
        eventDao.insert(event)
    }

    suspend fun update(event: Event) {
        eventDao.update(event)
    }

    suspend fun delete(event: Event) {
        eventDao.delete(event)
    }
}
