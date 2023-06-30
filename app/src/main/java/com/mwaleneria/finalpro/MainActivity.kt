package com.mwaleneria.finalpro

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var linearLayoutTasks: LinearLayout
    private lateinit var editTextTask: EditText
    private lateinit var taskDao: TaskDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        linearLayoutTasks = findViewById(R.id.linear_layout_tasks)
        editTextTask = findViewById(R.id.editTextTask)
        val buttonAdd = findViewById<Button>(R.id.buttonAdd)
        buttonAdd.setOnClickListener {
            addTask()
        }

        val taskDatabase = TaskDatabase.getDatabase(this)
        taskDao = taskDatabase.taskDao()

        loadTasks()
    }

    private fun loadTasks() {
        lifecycleScope.launch(Dispatchers.IO) {
            val tasks = taskDao.getAllTasks()
            withContext(context = Dispatchers.Main) {
                tasks.forEach { task ->
                    addTaskView(task)
                }
            }
        }
    }

    private fun addTask() {
        val taskText = editTextTask.text.toString().trim()
        if (taskText.isNotEmpty()) {
            val task = Task(name = taskText)
            lifecycleScope.launch(Dispatchers.IO) {
                val taskId = taskDao.insertTask(task)
                task.id = taskId
                withContext(context = Dispatchers.Main) {
                    addTaskView(task)
                }
            }
            editTextTask.text.clear()
        }
    }
    private fun addTaskView(task: Task) {
        val taskView = layoutInflater.inflate(R.layout.task_item, null)
        val taskName = taskView.findViewById<Button>(R.id.taskName)
        taskName.text = task.name
        taskName.setOnClickListener {
            markTaskAsCompleted(task, taskName)
        }
        val deleteButton = taskView.findViewById<Button>(R.id.deleteButton)
        deleteButton.setOnClickListener {
            deleteTask(task, taskView)
        }
        linearLayoutTasks.addView(taskView)
    }


    private fun markTaskAsCompleted(task: Task, taskView: View) {
        if (!task.completed) {
            task.completed = true
            taskDao.updateTask(task)
            taskView.isEnabled = false
            taskView.alpha = 0.5f
        } else {
            showEditTaskDialog(task)
        }
    }

    private fun showEditTaskDialog(task: Task) {
        val dialog = AlertDialog.Builder(this)
            .setTitle("Edit Task")
            .setView(R.layout.dialog_edit_task)
            .setPositiveButton("Save") { _, _ ->
                val dialog = null
                val editText = (dialog.findViewById<View>(R.id.editTextTaskName) as EditText)
                val newTaskName = editText.text.toString().trim()
                if (newTaskName.isNotEmpty()) {
                    task.name = newTaskName
                    lifecycleScope.launch(Dispatchers.IO) {
                        taskDao.updateTaskName(task.id, newTaskName)
                    }
                }
            }
            .setNegativeButton("Cancel", null)
            .create()

        dialog.setOnShowListener {
            val editText = (dialog.findViewById<View>(R.id.editTextTaskName) as EditText)
            editText.setText(task.name)
            editText.setSelection(task.name.length)
        }

        dialog.show()
    }

    private fun deleteTask(task: Task, taskView: View) {
        lifecycleScope.launch(Dispatchers.IO) {
            taskDao.deleteTask(task)
            withContext(Dispatchers.Main) {
                linearLayoutTasks.removeView(taskView)
            }
        }
    }
}
