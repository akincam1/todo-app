package com.akinc.crud_module.infrastructure.persistence.entites.task;

import com.akinc.crud_module.infrastructure.persistence.entites.BaseEntity;
import com.akinc.crud_module.infrastructure.persistence.entites.member.MemberEntity;
import com.akinc.crud_module.infrastructure.persistence.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "task")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "status", nullable = false)
    private TaskStatus status;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false, updatable = false)
    private MemberEntity memberEntity;
}
