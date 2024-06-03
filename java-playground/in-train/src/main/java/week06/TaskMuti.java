package week06;

import java.util.ArrayList;

// 创建一些作业,作业号，优先级，要求运行时间，状态
class PCB {
    String id;
    int priority;
    int arriveTime;
    int runTime;
    int requiredTime;
    boolean isFinish;

    public PCB(String id, int priority, int requiredTime, int arriveTime) {
        this.id = id;
        this.priority = priority;
        this.runTime = 0;
        this.arriveTime = arriveTime;
        this.requiredTime = requiredTime;
        this.isFinish = false;
    }

    public void printStatus() {
        System.out.println("Task ID: " + id);
        System.out.println("Priority: " + priority);
        System.out.println("Arrive Time: " + arriveTime);
        System.out.println("Run Time: " + runTime);
        System.out.println("Required Time: " + requiredTime);
        System.out.println("Is Finished: " + isFinish);
        System.out.println("------------------------");
    }

}

// 实现按照优先级调度算法
class TaskScheduler {
    ArrayList<PCB> tasks;
    int currentTime;

    public TaskScheduler(ArrayList<PCB> tasks) {
        this.tasks = tasks;
        this.currentTime = 0;
    }

    private void updatePriorities() {
        for (PCB task : tasks) {
            if (!task.isFinish) {
                task.priority++;
            }
        }
    }

    public void schedule() throws Exception {
        while (true) {
            // 选择优先级最高的作业
            PCB task = getHighestPriorityTask();
            if (task == null) {
                break;
            }
            // 运行作业
            runTask(task);
            // 更新当前时间
            currentTime++;
            // 更新优先级
            updatePriorities();
        }
    }

    private PCB getHighestPriorityTask() {
        PCB highestPriorityTask = null;
        for (PCB task : tasks) {
            if (!task.isFinish) {
                if (highestPriorityTask == null) {
                    highestPriorityTask = task;
                } else {
                    if (task.priority < highestPriorityTask.priority) {
                        highestPriorityTask = task;
                    }
                }
            }
        }
        return highestPriorityTask;
    }

    private void runTask(PCB task) {
        task.printStatus();
        System.out.println("运行:" + task.id + " --时间 " + currentTime);
        task.runTime++;
        if (task.runTime == task.requiredTime) {
            task.isFinish = true;
            System.out.println("Task " + task.id + "完成于" + currentTime);
        }
    }

}

// 实现时间片轮转调度算法
class TaskScheduler2 {
    ArrayList<PCB> tasks;
    int currentTime;
    int timeSlice;

    public TaskScheduler2(ArrayList<PCB> tasks, int timeSlice) {
        this.tasks = tasks;
        this.currentTime = 0;
        this.timeSlice = timeSlice;
    }

    public void schedule() throws Exception {
        while (true) {
            // 选择一个作业
            PCB task = getTask();
            if (task == null) {
                break;
            }
            // 运行作业
            runTask(task);
            // 更新当前时间
            currentTime++;
        }
    }

    private PCB getTask() {
        for (PCB task : tasks) {
            if (!task.isFinish) {
                return task;
            }
        }
        return null;
    }

    private void runTask(PCB task) {
        task.printStatus();
        System.out.println("运行:" + task.id + " --时间 " + currentTime);
        task.runTime++;
        if (task.runTime == task.requiredTime) {
            task.isFinish = true;
            System.out.println("Task " + task.id + "完成于" + currentTime);
        }
    }
}

public class TaskMuti {
    // 优先数调度算法实现处理器调度
    public static void main(String[] args) {
        //作业号，到达时间，运行时间
        // 创建一个可变数组
        ArrayList<PCB> tasks = new ArrayList<>();
        tasks.add(new PCB("A", 3, 3, 0));
        tasks.add(new PCB("B", 4, 6, 1));
        tasks.add(new PCB("C", 2, 4, 2));
        tasks.add(new PCB("D", 1, 5, 3));
        // 运行任务
        TaskScheduler scheduler = new TaskScheduler(tasks);
        try {
            scheduler.schedule();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("====================================");
        // 时间片轮转调度算法实现处理器调度
        ArrayList<PCB> tasks2 = new ArrayList<>();
        tasks2.add(new PCB("A", 0, 1, 0));
        tasks2.add(new PCB("B", 0, 4, 3));
        tasks2.add(new PCB("C", 0, 4, 1));
        tasks2.add(new PCB("D", 0, 5, 2));
        // 运行任务
        TaskScheduler2 scheduler2 = new TaskScheduler2(tasks2, 2);
        try {
            scheduler2.schedule();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


