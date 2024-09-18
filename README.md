# Paysky-Task

Simple app that demonstrates the usage of the [JSON Placeholder API](https://jsonplaceholder.typicode.com/) in CRUD operations (
Create,Read,Update,Delete) using Retrofit, Coroutines, Flow, Hilt, Navigation Component, ViewModel, and LiveData.

With offline first approach using room database. And Syncing mechanism using Coroutines and WorkManager to periodically fetch the latest data from the
Api and update the local DB.

## Table of Contents

- [Architecture](#architecture)
- [Features](#features)
- [Technologies Used](#built-with-)

## Architecture

Clean architecture based on MVVM (Model-View-ViewModel) with Kotlin Coroutines and Flow

The following diagram shows all the layers and how each layer interacts with each other.
This architecture uses a layered software architecture.
![MVVM](/docs/mvvm_architecture.png)
![Clean Architecture](/docs/clean_architecture.png)

## Features

- **Create**: Create new post.
- **Read**: Retrieves a list of posts.
- **Update**: Edit an existing post.
- **Delete**: delete an existing post.

## Built With 🛠

* [Kotlin](https://kotlinlang.org/) - official programming language for Android development.
* [Coroutines](https://developer.android.com/kotlin/coroutines) - for asynchronous or
  non-blocking programming.
* [Flow](https://developer.android.com/kotlin/flow) - a flow is a type that can emit multiple values sequentially, as opposed to suspend functions
  that return only a single value.
* [Android Architecture Components](https://developer.android.com/jetpack/guide) - Part of Jetpack it's a set of libraries that help you design
  robust, testable, and maintainable apps.
    - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - store and manage UI-related data in a lifecycle conscious
      way.
    - [Navigation component](https://developer.android.com/guide/navigation) - Navigation refers to the interactions that allow users to navigate
      across, into, and back out from the different pieces of content within your app.
* [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - Hilt is a dependency injection library for Android that reduces
  the boilerplate of doing manual dependency injection (Based on Dagger 2).
* [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android.
* [Gson](https://github.com/google/gson) A Java serialization/deserialization library to convert Java Objects into JSON and back.
* [Material Design](https://material.io/design/guidelines-overview) are interactive building blocks for creating a friendly user interface.
* [WorkManager](https://developer.android.com/topic/libraries/architecture/workmanager) - WorkManager is an API that makes it easy to schedule
  deferrable, asynchronous tasks that are expected to run even if the app exits or the device restarts.
* [Room](https://developer.android.com/jetpack/androidx/releases/room) - The Room persistence library provides an abstraction layer over SQLite to
  allow
  fluent database access while harnessing the full power of SQLite.