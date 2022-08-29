package com.amela.managertaskamela.repository;

import com.amela.managertaskamela.model.Task;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskMapper {
    List<Task> findAllByPage (@Param("title") String title,
                              @Param("status") String status,
                              @Param("pageable") Pageable pageable);

    int countTasks (@Param("title") String title,
                    @Param("status") String status);


    void createTask (@Param("task") Task task);

    Task findById (@Param("id") int id);

    void deleteTaskById(@Param("id") int id);

    void updateTask(@Param("task") Task task);

    List<Task> findAllTasks();

    List<Task> findAll(@Param("title") String title,
                       @Param("status") String status,
                       @Param("pageable") Pageable pageable);

}
