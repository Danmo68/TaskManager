import java.io.File


// Function to load tasks from a file
fun loadTasks(fileName: String): MutableList<String> {
    val taskList = mutableListOf<String>()
    val file = File(fileName)

    // Check if the file exists
    if (file.exists()) {
        // Read each line in the file as a task and add it to the list
        file.forEachLine { line ->
            taskList.add(line)
        }
        println("Tasks loaded from $fileName.")
    } else {
        println("No existing tasks found. Starting with an empty list.")
    }

    return taskList
}

// Function to save tasks to a file
fun saveTasks(tasks: List<String>, fileName: String) {
    File(fileName).printWriter().use { out ->
        tasks.forEach { task ->
            out.println(task)
        }
    }
    println("Tasks saved to $fileName.")
}

// Function to view all tasks
fun viewTasks(tasks: List<String>) {
    if (tasks.isEmpty()) {
        println("Your task list is empty!")
    } else {
        println("Here are your current tasks:")
        tasks.forEachIndexed { index, task ->
            println("${index + 1}. $task")
        }
    }
}

// Function to add a new task
fun addTask(tasks: MutableList<String>) {
    println("Enter the new task:")
    val newTask = readln()
    tasks.add(newTask)
    println("Task added: \"$newTask\"")
}

// Function to delete a task
fun deleteTask(tasks: MutableList<String>) {
    if (tasks.isEmpty()) {
        println("Your task list is empty! No task to delete.")
        return
    }

    viewTasks(tasks)
    println("Enter the number of the task you want to delete:")
    val taskNumber = readln().toIntOrNull()

    if (taskNumber == null || taskNumber < 1 || taskNumber > tasks.size) {
        println("Invalid task number.")
    } else {
        val removedTask = tasks.removeAt(taskNumber - 1)
        println("Task removed: \"$removedTask\"")
    }
}

// Main function to run the To-Do List Manager
fun main() {
    val fileName = "tasks.txt"
    val tasks = loadTasks(fileName) // Load tasks at program start

    while (true) {
        println("\nTo-Do List Manager")
        println("1. View Tasks")
        println("2. Add Task")
        println("3. Delete Task")
        println("4. Save and Exit")
        print("Choose an option: ")

        when (readln()) {
            "1" -> viewTasks(tasks)
            "2" -> addTask(tasks)
            "3" -> deleteTask(tasks)
            "4" -> {
                saveTasks(tasks, fileName)
                println("Goodbye!")
                return
            }
            else -> println("Invalid option. Please try again.")
        }
    }
}