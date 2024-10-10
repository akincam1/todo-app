package com.akinc.crud_module.application.service.task;

import com.akinc.crud_module.domain.request.task.CreateTaskRequest;
import com.akinc.crud_module.domain.request.task.UpdateTaskRequest;
import com.akinc.crud_module.domain.response.task.GetTaskResponse;
import com.akinc.crud_module.infrastructure.persistence.entites.task.TaskEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskService {

    void createTask(CreateTaskRequest request, UUID memberId);

    void updateTask(Long id, UpdateTaskRequest request);

    void deleteTask(Long id);

    void completeTask(Long id);

    Optional<TaskEntity> getById(Long id);

    GetTaskResponse getDetailById(Long id);

    List<GetTaskResponse> getWithPagination(UUID memberId, Pageable pageable);
}
