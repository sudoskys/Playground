// lib/controllers/task_controller.dart
import 'package:get/get.dart';
import '../models/task.dart';

class TaskController extends GetxController {
  var tasks = <Task>[].obs;
  var filteredTasks = <Task>[].obs;

  // 添加任务
  void addTask(Task task) {
    tasks.add(task);
    updateFilteredTasks();
  }

  // 删除任务
  void removeTask(int index) {
    tasks.removeAt(index);
    updateFilteredTasks();
  }

  // 更新任务
  void updateTask(int index, Task task) {
    tasks[index] = task;
    updateFilteredTasks();
  }

  // 搜索任务
  void searchTasks(String query) {
    if (query.isEmpty) {
      filteredTasks.assignAll(tasks);
    } else {
      filteredTasks.assignAll(tasks.where((task) {
        return task.title.toLowerCase().contains(query.toLowerCase()) ||
            (task.tag?.toLowerCase().contains(query.toLowerCase()) ?? false);
      }).toList());
    }
  }

  // 更新过滤的任务列表
  void updateFilteredTasks() {
    filteredTasks.assignAll(tasks);
  }
}