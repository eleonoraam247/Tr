# LevelUp
A gamified productivity application designed to help users manage tasks and track personal progress in an engaging way.


📄 📌 PROJECT PROPOSAL
👥 Team Members:
Elya Asanova
Bermet Arstanalieva
🎮 Project Title:

Life RPG – Gamified Productivity App

💡 Short Description:

Life RPG is a gamified productivity Android application that transforms daily tasks and habits into a role-playing game experience. Users complete tasks to gain experience points (XP), level up their character, and track personal progress in a visually engaging and motivational interface.

The goal of the application is to increase productivity through game mechanics such as leveling systems, achievements, and streak tracking.

🚀 Planned Main Features:
📋 Task management (add, edit, delete tasks)
⭐ XP system (each task gives experience points)
🧑 User level system (Level progression based on XP)
🏆 Achievements and streak tracking
📊 Statistics screen (daily/weekly progress)
🔔 Daily reminders for tasks
🎮 Gamified UI (RPG-style dashboard)
🌙 Dark mode support
🧠 Planned Technologies / Frameworks:
Kotlin
Jetpack Compose (UI)
ViewModel (MVVM architecture)
Navigation Component
Repository Pattern
Hilt (Dependency Injection)
Data & Storage:
Room Database (tasks, XP, progress)
DataStore (user preferences, settings)
Background & Network:
WorkManager (task reminders / notifications)
Retrofit (motivational quotes API / external data)
🏗 Architecture Overview:

The application follows MVVM + Clean Architecture principles:

UI (Compose)
↓
ViewModel
↓
Repository
↓
Room / API / DataStore

This ensures separation of concerns, scalability, and testability.



======================================================================
📌 Project Overview

LevelUp is a gamified productivity Android application built with Kotlin and Jetpack Compose.
The app helps users manage daily tasks while rewarding progress through an RPG-style leveling and XP system.

The project follows modern Android development practices including MVVM architecture, Repository pattern, Dependency Injection, and local data persistence.

🧠 Main Features
✅ Task Management
Add tasks
Edit tasks
Delete tasks
Mark tasks as completed
⭐ XP & Level System
Users earn XP after completing tasks
XP increases user level
Progress bar shows current level progress
📊 Statistics
Completed tasks count
Daily XP tracking
Productivity overview
🔔 Notifications & Reminders
Daily reminders using WorkManager
Notifications for unfinished tasks
🌙 Settings
Dark mode support
Username/preferences storage using DataStore
🏗 Architecture

The application follows:

MVVM Architecture
Repository Pattern
Separation of Concerns
Dependency Injection with Hilt
📂 Architecture Flow
UI (Compose Screens)
↓
ViewModel
↓
Repository
↓
Room Database / Retrofit API / DataStore
🛠 Technologies Used
Core
Kotlin
Jetpack Compose
ViewModel
Navigation Component
Data & Storage
Room Database
DataStore
Background & Networking
WorkManager
Retrofit
Architecture
Hilt Dependency Injection
Repository Pattern
📁 Project Structure
com.levelup.app
│
├── data
│   ├── local
│   │   ├── dao
│   │   ├── entity
│   │   └── database
│   │
│   ├── remote
│   │   ├── api
│   │   └── dto
│   │
│   ├── repository
│   └── datastore
│
├── domain
│   ├── model
│   └── repository
│
├── di
│
├── ui
│   ├── navigation
│   ├── screens
│   ├── components
│   └── theme
│
└── MainActivity.kt

👥 Team Responsibilities
🧑‍💻 Team Member 1 — UI / Frontend
Responsibilities
Jetpack Compose UI
Navigation setup
Screen design
Reusable UI components
Theme & styling
UI state integration with ViewModels
Files
UI Screens
HomeScreen.kt
TasksScreen.kt
AddTaskScreen.kt
StatsScreen.kt
ProfileScreen.kt
Navigation
NavGraph.kt
Components
TaskItem.kt
XpProgressBar.kt
CustomButton.kt
Theme
Colors
Typography
Shapes
🧑‍💻 Team Member 2 — Data / Backend Logic
Responsibilities
Room Database
DAO implementation
Repository layer
DataStore
WorkManager
Retrofit API integration
Business logic
Files
Room Database
TaskEntity.kt
TaskDao.kt
AppDatabase.kt
Repository
TaskRepository.kt
TaskRepositoryImpl.kt
DataStore
PreferencesManager.kt
API
ApiService.kt
QuoteDto.kt
Background Tasks
ReminderWorker.kt
🤝 Shared Responsibilities

Both team members will work together on:

MVVM architecture integration
Hilt Dependency Injection
ViewModel connection
Testing & debugging
GitHub management
Final UI polish
Presentation/demo preparation
🔄 Development Plan
Phase 1 — Project Setup
Create project
Configure dependencies
Setup Hilt & Navigation
Phase 2 — Core Features
Room database
Task screens
XP system
Phase 3 — Advanced Features
Statistics
Notifications
DataStore settings
API integration
Phase 4 — Finalization
UI polishing
Testing
Bug fixing
README & presentation
