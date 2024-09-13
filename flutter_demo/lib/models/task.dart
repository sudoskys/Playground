// lib/models/task.dart
import 'package:flutter/material.dart';

class Task {
  String title;
  String description;
  String? tag;
  Color? color;

  Task({
    required this.title,
    required this.description,
    this.tag,
    this.color,
  });
}