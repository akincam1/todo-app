package com.akinc.crud_module.domain.mapper;

import com.akinc.crud_module.domain.response.task.GetTaskResponse;
import com.akinc.crud_module.infrastructure.persistence.entites.task.TaskEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    GetTaskResponse mapToResponse(TaskEntity entity);
    List<GetTaskResponse> mapToResponseList(List<TaskEntity> entities);
}