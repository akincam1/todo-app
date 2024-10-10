package com.akinc.crud_module.infrastructure.persistence.repositories;

import com.akinc.crud_module.infrastructure.persistence.entites.task.TaskEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    boolean existsByIdAndMemberEntity_Id(Long id, UUID memberId);

    Page<TaskEntity> findByMemberEntity_Id(UUID memberId, Pageable pageable);
}
