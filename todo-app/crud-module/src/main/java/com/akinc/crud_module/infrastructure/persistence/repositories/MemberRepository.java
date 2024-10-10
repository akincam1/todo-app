package com.akinc.crud_module.infrastructure.persistence.repositories;

import com.akinc.crud_module.domain.projection.MemberProjection;
import com.akinc.crud_module.infrastructure.persistence.entites.member.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    boolean existsMemberEntityByEmail(String email);

    MemberProjection findByEmailAndPassword(String email, String password);
}
