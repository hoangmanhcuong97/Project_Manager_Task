package com.amela.managertaskamela.service.task;

import com.amela.managertaskamela.model.Task;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskService {

    List<Task> findAllByPage(String title, String status, Pageable pageable);

    int countTasks (String title, String status);

    void createTask(Task task);

    Task findById(int id);

    void deleteTaskById(int id);

    void updateTask(Task task);

    List<Task> findAllTasks();

    List<Task> findAll(String title, String status, Pageable pageable);
}
