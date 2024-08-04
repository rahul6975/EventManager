<h1 align="center">Event Manager App</h1>

<p align="center">
  <a href="https://android-arsenal.com/api?level=34"><img alt="API" src="https://img.shields.io/badge/API-34%2B-brightgreen.svg?style=flat"/></a>
</p>

<p align="center"> 
Event Manager Android application built using Kotlin, MVVM and Room 
</p>

## App Screenshot

<table>
  <tr>
    <td><img src="https://github.com/rahul6975/EventManager/blob/master/screenshots/Screenshot_2024-08-03-22-11-49-137_com.rahul.eventmanager.jpg" width="200"/></td>
    <td><img src="https://github.com/rahul6975/EventManager/blob/master/screenshots/Screenshot_2024-08-03-22-14-14-362_com.rahul.eventmanager.jpg" width="200"/></td>
    <td><img src="https://github.com/rahul6975/EventManager/blob/master/screenshots/Screenshot_2024-08-03-22-14-24-521_com.rahul.eventmanager.jpg" width="200"/></td>
  </tr>
  
  <tr>
  </tr>
 </table>

 <br>

## Model View ViewModel Architecture

![](screenshots/mvvm.png)

<br>

<br>

## Built With 🛠
- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - A coroutine is a concurrency design pattern that you can use on Android to simplify code that executes asynchronously.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Data objects that notify views when the underlying database changes.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes. 
  - [ViewBinding](https://developer.android.com/topic/libraries/view-binding) - Generates a binding class for each XML layout file present in that module and allows you to more easily write code that interacts with views.
  - [DataBinding](https://developer.android.com/topic/libraries/data-binding) - The Data Binding Library is a support library that allows you to bind UI components in your layouts to data sources in your app using a declarative format rather than programmatically.
  - [Room](https://developer.android.com/topic/libraries/architecture/room) - SQLite object mapping library.
- [Material Components for Android](https://github.com/material-components/material-components-android) - Modular and customizable Material Design UI components for Android.

<br>

<br>

## Features 👨🏼‍💻

- The User can create an Event by providing EventName, EventDate, EventLocation, EventDescription, EventParticipants.
- The user can modify (edit) an exisiting Event.
- The user can delete an exisiting Event.

  <br>

  <br>
  
  ## Project Structure and Files 📂

   - Model
      - Room
         - Event.kt
         - EventDao.kt
         - EventDatabase.kt
   - ViewModel
      - EventViewModel.kt
      - EventRepository.kt
   - Views
      - CreateEventFragment.kt
      - EventListFragment.kt
      - EventDetailFragment.kt
      - MainActivity.kt
  
  
