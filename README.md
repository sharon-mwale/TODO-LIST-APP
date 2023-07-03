# Android To-Do Application

This is a simple to-do application for Android that allows users to create, manage, and complete tasks.

## Features

- Create new tasks by entering a task name.
- Mark tasks as completed.
- Edit task names.
- Delete tasks.
- Display tasks sorted by completion status and creation timestamp.
- Persist task data and completion status using SharedPreferences.

## Technologies Used

- Kotlin programming language
- Android Studio (IDE)
- SQLite database (via Room Persistence Library)
- SharedPreferences for data persistence

## Getting Started

### Prerequisites

- Android Studio installed on your machine.
- An Android device or emulator to run the application.

### Installation

1. Clone this repository to your local machine or download the source code as a ZIP file.
2. Open Android Studio and select "Open an existing project".
3. Navigate to the cloned/downloaded project directory and select it.
4. Let Android Studio import the project and download any necessary dependencies.

### Usage

1. Launch the application on your Android device or emulator.
2. The main screen will display a list of tasks.
3. To create a new task, enter a task name in the text field and tap the "Add" button.
4. Tasks will be displayed in the order of completion status and creation timestamp.
5. To mark a task as completed, tap on the task name. The task will be greyed out and disabled.
6. To edit a task, tap on the completed task and a dialog will appear. Enter the new task name and tap "Save".
7. To delete a task, tap on the trash can icon next to the task.
8. The tasks and their completion status will be persisted and will be available on subsequent launches of the application.

## License

This project is licensed under the [MIT License](LICENSE).

## Acknowledgements

- This application was developed as a sample project.
- Parts of the code and structure were inspired by various Android development tutorials and resources.
## Folder Structure

The project follows a standard Android project structure:

```
├── app
│   ├── src
│   │   ├── main
│   │   │   ├── java/com/example/todoapp
│   │   │   │   ├── data
│   │   │   │   │   ├── database
│   │   │   │   │   │   └── TaskDatabase.kt
│   │   │   │   │   ├── model
│   │   │   │   │   │   └── Task.kt
│   │   │   │   │   └── TaskDao.kt
│   │   │   │   ├── ui
│   │   │   │   │   └── MainActivity.kt
│   │   │   ├── res
│   │   │   │   ├── layout
│   │   │   │   │   └── activity_main.xml
│   │   │   │   │   └── task_item.xml
│   │   │   │   ├── values
│   │   │   │   │   └── strings.xml
│   │   ├── test
│   │   └── AndroidManifest.xml
├── build.gradle
└── README.md
```

## Future Enhancements

Here are some ideas for future enhancements of the to-do application:

- Implement task priority levels (e.g., high, medium, low).
- Add due dates and reminders for tasks.
- Implement task categories or tags for better organization.
- Add user authentication and cloud synchronization for multi-device support.
- Improve the user interface with themes, animations, and visual enhancements.

## Contributing

Contributions are welcome! If you have any suggestions, bug reports, or feature requests, please open an issue or submit a pull request.

## Credits

This application was developed by [Your Name]. Feel free to contact me with any questions or feedback.

## License

This project is licensed under the [MIT License](LICENSE).
