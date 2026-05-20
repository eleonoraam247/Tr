# LevelUp 🎮
Gamified Productivity Android App

A gamified productivity application designed to help users manage tasks and track personal progress in an engaging RPG style.

## 🚀 Текущий статус проекта (Current Status)

### ✅ Реализовано (Implemented):
*   **Task Management**: Полный цикл добавления, отображения и удаления квестов.
*   **XP & Level System**: Автоматический расчет уровня (1000 XP за уровень). Прогресс-бар в реальном времени.
*   **Notifications**: Напоминания через `WorkManager` (приходят через 10 сек после создания квеста для теста).
*   **DatePicker**: Полноценный календарь в окне "New Quest".
*   **Data Persistence**: Задачи хранятся в **Room**, прогресс пользователя — в **DataStore**.
*   **Full Reset**: Кнопка в настройках теперь полностью очищает и XP, и список задач.
*   **Character Customization**: Смена имени и выбор класса героя (Wizard, Warrior и др.).

### ⏳ В разработке (In Progress):
*   **Statistics Logic**: Подсчет серий (streaks) и выполненных задач за неделю.
*   **Achievements**: Система достижений и наград.
*   **Retrofit**: Интеграция API для получения мотивационных цитат.
*   **Dark Mode**: Привязка темы приложения к настройкам в DataStore.

---

## 🏗 Архитектура (Architecture)
Проект построен на принципах **MVVM + Clean Architecture**:

*   **UI (Jetpack Compose)**: `ui/screens` — визуальная часть.
*   **ViewModel**: `ui/viewmodel` — связь UI с данными.
*   **Domain**: `domain/model` и `domain/repository` — бизнес-логика.
*   **Data**: 
    *   **Room** (`data/local`) — база данных квестов.
    *   **DataStore** (`data/datastore`) — настройки и прогресс.
    *   **WorkManager** (`data/worker`) — фоновые уведомления.

---

## 📂 Структура файлов (Project Structure)
```
app/src/main/java/com/example/andproject/
│
├── data/
│   ├── local/ (Room DB: TaskDao, TaskEntity, AppDatabase)
│   ├── repository/ (TaskRepositoryImpl)
│   ├── datastore/ (PreferencesManager)
│   └── worker/ (ReminderWorker)
│
├── domain/
│   ├── model/ (Task)
│   └── repository/ (TaskRepository)
│
├── di/ (Hilt: AppModule)
│
├── ui/
│   ├── navigation/ (NavGraph, Screen)
│   ├── viewmodel/ (UserViewModel, TasksViewModel)
│   ├── screens/ (Quests, AddTask, Stats, Settings, etc.)
│   ├── components/ (UI elements)
│   └── theme/ (Styles, Colors)
│
├── MainActivity.kt
└── LevelUpApplication.kt
```

---

## 👥 Команда (Team)
*   **Elya Asanova**
*   **Bermet Arstanalieva**
