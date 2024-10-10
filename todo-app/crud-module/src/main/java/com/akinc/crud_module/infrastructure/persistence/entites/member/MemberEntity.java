package com.akinc.crud_module.infrastructure.persistence.entites.member;

import com.akinc.crud_module.infrastructure.persistence.entites.BaseEntity;
import com.akinc.crud_module.infrastructure.persistence.entites.task.TaskEntity;
import com.akinc.crud_module.infrastructure.persistence.enums.MemberStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "member")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "email", nullable = false, updatable = false)
    private String email;

    @Column(name = "first_name", nullable = false, updatable = false)
    private String firstName;

    @Column(name = "surname", nullable = false, updatable = false)
    private String surname;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "status", nullable = false)
    private MemberStatus status;

    @OneToMany(mappedBy = "memberEntity", fetch = FetchType.LAZY)
    private List<TaskEntity> tasks;
}
