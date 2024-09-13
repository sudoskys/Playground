// lib/views/home_screen.dart
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import '../controllers/task_controller.dart';
import 'task_detail_screen.dart';
import 'widgets/task_item.dart';

class HomeScreen extends StatefulWidget {
  @override
  _HomeScreenState createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  final TaskController taskController = Get.put(TaskController());
  final TextEditingController searchController = TextEditingController();
  bool isSearching = false; // 用于指示搜索栏是否可见

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        // 使用 Stack 布局
        title: Stack(
          children: [
            // AnimatedOpacity 用于动画过渡
            AnimatedOpacity(
              opacity: isSearching ? 0.0 : 1.0,
              duration: Duration(milliseconds: 300),
              child: AnimatedSwitcher(
                duration: Duration(milliseconds: 300),
                child: isSearching
                    ? SizedBox.shrink()
                    : Center(
                        child: GradientText(
                          'TODO List App',
                          style: TextStyle(
                            fontSize: 24,
                            fontWeight: FontWeight.bold,
                            fontFamily: 'Roboto',
                          ),
                          gradient: LinearGradient(
                            colors: <Color>[
                              Colors.blue,
                              Colors.purple,
                            ],
                          ),
                        ),
                      ),
              ),
            ),
            // 搜索栏和 X 按钮
            AnimatedOpacity(
              opacity: isSearching ? 1.0 : 0.0,
              duration: Duration(milliseconds: 300),
              child: AnimatedSwitcher(
                duration: Duration(milliseconds: 300),
                child: isSearching
                    ? Row(
                        children: [
                          Expanded(
                            child: TextField(
                              controller: searchController,
                              decoration: InputDecoration(
                                hintText: 'Search...',
                                border: InputBorder.none,
                                hintStyle: TextStyle(color: Colors.white70),
                              ),
                              style:
                                  TextStyle(color: Colors.white, fontSize: 18),
                              onChanged: (value) {
                                taskController.searchTasks(value);
                              },
                            ),
                          ),
                          IconButton(
                            icon: Icon(Icons.close),
                            onPressed: () {
                              setState(() {
                                isSearching = false;
                                searchController.clear();
                                taskController.searchTasks('');
                              });
                            },
                          ),
                        ],
                      )
                    : SizedBox.shrink(),
              ),
            ),
          ],
        ),
        actions: [
          if (!isSearching)
            IconButton(
              icon: Icon(Icons.search),
              onPressed: () {
                setState(() {
                  isSearching = true;
                });
              },
            ),
        ],
      ),
      // 同前面的代码
      drawer: Drawer(
        child: ListView(
          padding: EdgeInsets.zero,
          children: <Widget>[
            DrawerHeader(
              decoration: BoxDecoration(
                color: Colors.blue,
              ),
              child: Text(
                'Options',
                style: TextStyle(
                  color: Colors.white,
                  fontSize: 24,
                ),
              ),
            ),
            ListTile(
              leading: Icon(Icons.settings),
              title: Text('Settings'),
              onTap: () {
                // 实现导航到设置页面
              },
            ),
            ListTile(
              leading: Icon(Icons.info),
              title: Text('About'),
              onTap: () {
                // 实现导航到关于页面
              },
            ),
          ],
        ),
      ),
      body: Obx(
        () => ListView.builder(
          itemCount: taskController.filteredTasks.length,
          itemBuilder: (context, index) {
            final task = taskController.filteredTasks[index];
            return TaskItem(
              task: task,
              onDelete: () => taskController.removeTask(index),
              onEdit: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(
                    builder: (context) => TaskDetailScreen(
                      index: index,
                      task: task,
                    ),
                  ),
                );
              },
            );
          },
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          Navigator.push(
            context,
            MaterialPageRoute(
              builder: (context) => TaskDetailScreen(),
            ),
          );
        },
        child: Icon(Icons.add),
      ),
    );
  }
}

// 自定义的 GradientText 组件，用于实现渐变文字效果
class GradientText extends StatelessWidget {
  final String text;
  final TextStyle style;
  final Gradient gradient;

  GradientText(
    this.text, {
    required this.style,
    required this.gradient,
  });

  @override
  Widget build(BuildContext context) {
    return ShaderMask(
      shaderCallback: (bounds) {
        return gradient.createShader(Offset.zero & bounds.size);
      },
      child: Text(
        text,
        style: style.copyWith(color: Colors.white),
      ),
    );
  }
}
