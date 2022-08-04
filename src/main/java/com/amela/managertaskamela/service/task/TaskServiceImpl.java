package com.amela.managertaskamela.service.task;

import com.amela.managertaskamela.model.Account;
import com.amela.managertaskamela.model.Task;
import com.amela.managertaskamela.repository.TaskMapper;
import com.amela.managertaskamela.service.account.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final UserDetailService userDetailService;
    private final TaskMapper taskMapper;

    public TaskServiceImpl(TaskMapper taskMapper, UserDetailService userDetailService) {
        this.taskMapper = taskMapper;
        this.userDetailService = userDetailService;
    }

    @Override
    public List<Task> findAllByPage(String title, String status, Pageable pageable) {
        return taskMapper.findAllByPage(title, status, pageable);
    }

    @Override
    public int countTasks(String title, String status) {
        return taskMapper.countTasks(title, status);
    }

    @Override
    public void createTask(Task task) {
        Account account = userDetailService.getCurrentAccount();
        task.setAccount(account);
        taskMapper.createTask(task);
    }

    @Override
    public Task findById(int id) {
        return taskMapper.findById(id);
    }

    @Override
    public void deleteTaskById(int id) {
        taskMapper.deleteTaskById(id);
    }

    @Override
    public void updateTask(Task task) {
        taskMapper.updateTask(task);
    }

    @Override
    public List<Task> findAllTasks() {
        return taskMapper.findAllTasks();
    }
}

