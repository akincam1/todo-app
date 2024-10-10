package com.akinc.crud_module.application.service.task;

import com.akinc.crud_module.domain.exception.NotFoundException;
import com.akinc.crud_module.domain.mapper.TaskMapper;
import com.akinc.crud_module.domain.request.task.CreateTaskRequest;
import com.akinc.crud_module.domain.request.task.UpdateTaskRequest;
import com.akinc.crud_module.domain.response.task.GetTaskResponse;
import com.akinc.crud_module.infrastructure.persistence.entites.member.MemberEntity;
import com.akinc.crud_module.infrastructure.persistence.entites.task.TaskEntity;
import com.akinc.crud_module.infrastructure.persistence.enums.TaskStatus;
import com.akinc.crud_module.infrastructure.persistence.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;

    private final TaskMapper mapper;

    @Override
    public void createTask(CreateTaskRequest request, UUID memberId) {

        var entity = new TaskEntity();
        entity.setTitle(request.getTitle());
        entity.setDescription(request.getDescription());
        entity.setStatus(TaskStatus.CREATED);

        var memberEntity = new MemberEntity();
        memberEntity.setId(memberId);
        entity.setMemberEntity(memberEntity);

        repository.save(entity);
    }

    @Override
    public void updateTask(Long id, UpdateTaskRequest request) {

        var entity = getEntity(id);

        entity.setTitle(request.getTitle());
        entity.setDescription(request.getDescription());
        entity.setStatus(TaskStatus.UPDATED);
        repository.save(entity);
    }

    @Override
    public void deleteTask(Long id) {

        var entity = getEntity(id);

        entity.setStatus(TaskStatus.CANCELLED);
        repository.save(entity);
    }

    @Override
    public void completeTask(Long id) {

        var entity = getEntity(id);

        entity.setStatus(TaskStatus.COMPLETED);
        repository.save(entity);
    }

    @Override
    public Optional<TaskEntity> getById(Long id) {

        return repository.findById(id);
    }

    @Override
    public GetTaskResponse getDetailById(Long id) {

        var entity = getEntity(id);
        return mapper.mapToResponse(entity);
    }

    @Override
    public List<GetTaskResponse> getWithPagination(UUID memberId, Pageable pageable) {

        var entityList = repository.findByMemberEntity_Id(memberId, pageable);
        return mapper.mapToResponseList(entityList.stream().toList());
    }

    private TaskEntity getEntity(Long id) {

        var optionalEntity = repository.findById(id);

        if (optionalEntity.isEmpty()) {
            throw new NotFoundException("Task not found. Id: " + id);
        }

        return optionalEntity.get();
    }
}
