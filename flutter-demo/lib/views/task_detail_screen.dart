import 'package:flutter/material.dart';
import 'package:get/get.dart';
import '../controllers/task_controller.dart';
import '../models/task.dart';

class TaskDetailScreen extends StatefulWidget {
  final int? index;
  final Task? task;

  TaskDetailScreen({this.index, this.task});

  @override
  _TaskDetailScreenState createState() => _TaskDetailScreenState();
}

class _TaskDetailScreenState extends State<TaskDetailScreen> {
  final TaskController taskController = Get.find();
  final GlobalKey<FormState> _formKey = GlobalKey<FormState>();
  final TextEditingController titleController = TextEditingController();
  final TextEditingController descriptionController = TextEditingController();
  final TextEditingController tagController = TextEditingController();
  Color? selectedColor;

  @override
  void initState() {
    super.initState();
    if (widget.task != null) {
      titleController.text = widget.task!.title;
      descriptionController.text = widget.task!.description;
      tagController.text = widget.task!.tag ?? '';
      selectedColor = widget.task!.color;
    }
  }

  void saveTask() {
    if (_formKey.currentState!.validate()) {
      final newTask = Task(
        title: titleController.text,
        description: descriptionController.text,
        tag: tagController.text.isNotEmpty ? tagController.text : null,
        color: selectedColor,
      );

      if (widget.index != null) {
        taskController.updateTask(widget.index!, newTask);
      } else {
        taskController.addTask(newTask);
      }
      Get.back(); // 关闭页面
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.index != null ? 'Edit Task' : 'Add Task'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Form(
          key: _formKey,
          child: Column(
            children: [
              // 标题输入框
              TextFormField(
                controller: titleController,
                decoration: InputDecoration(
                  labelText: 'Title',
                  border: OutlineInputBorder(),
                ),
                validator: (value) {
                  if (value == null || value.isEmpty) {
                    return 'Please enter a title';
                  }
                  return null;
                },
              ),
              SizedBox(height: 10),
              // 描述输入框
              TextFormField(
                controller: descriptionController,
                decoration: InputDecoration(
                  labelText: 'Description',
                  border: OutlineInputBorder(),
                ),
                validator: (value) {
                  if (value == null || value.isEmpty) {
                    return 'Please enter a description';
                  }
                  return null;
                },
              ),
              SizedBox(height: 10),
              // 标签输入框
              TextFormField(
                controller: tagController,
                decoration: InputDecoration(
                  labelText: 'Tag',
                  border: OutlineInputBorder(),
                ),
              ),
              SizedBox(height: 20),
              // 颜色选择器
              Expanded(
                child: GridView.count(
                  crossAxisCount: 7, // 每行显示5个颜色选项
                  crossAxisSpacing: 10.0, // 颜色项之间的水平间距
                  mainAxisSpacing: 10.0, // 颜色项之间的垂直间距
                  children: Colors.primaries.map((color) {
                    return GestureDetector(
                      onTap: () {
                        // 设置选中的颜色
                        setState(() {
                          selectedColor = color;
                        });
                      },
                      child: Transform.scale(
                        scale: selectedColor == color ? 1.1 : 1.0, // 选中时微微放大
                        child: CircleAvatar(
                          backgroundColor: color,
                          radius: 14, // 缩小圆的半径
                          child: selectedColor == color
                              ? Icon(
                                  Icons.done,
                                  color: Colors.white,
                                  size: 20,
                                )
                              : null,
                        ),
                      ),
                    );
                  }).toList(),
                ),
              ),
              // 保存按钮
              ElevatedButton(
                onPressed: saveTask,
                child: Text(widget.index != null ? 'Update Task' : 'Add Task'),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
