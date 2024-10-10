package com.akinc.crud_module.application.service.member;

import com.akinc.crud_module.domain.exception.BadRequestException;
import com.akinc.crud_module.domain.request.member.CreateMemberRequest;
import com.akinc.crud_module.domain.response.member.CreateMemberResponse;
import com.akinc.crud_module.domain.response.member.MemberSignInResponse;
import com.akinc.crud_module.infrastructure.persistence.entites.member.MemberEntity;
import com.akinc.crud_module.infrastructure.persistence.enums.MemberStatus;
import com.akinc.crud_module.infrastructure.persistence.repositories.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository repository;

    @Override
    public CreateMemberResponse create(CreateMemberRequest request) {

        var isEmailExists = repository.existsMemberEntityByEmail(request.getEmail());
        if (isEmailExists) {
            throw new BadRequestException("Email already exists!");
        }

        var entity = new MemberEntity();
        entity.setFirstName(request.getFirstName());
        entity.setSurname(request.getSurname());
        entity.setEmail(request.getEmail());
        entity.setPassword(request.getPassword());
        entity.setStatus(MemberStatus.ACTIVE);

        var savedEntity =  repository.save(entity);
        return new CreateMemberResponse(savedEntity.getFirstName(), savedEntity.getSurname(), savedEntity.getEmail());
    }

    @Override
    public MemberSignInResponse signIn(String email, String password) {

        var projection = repository.findByEmailAndPassword(email, password);

        if (projection == null) {
            throw new BadRequestException("Invalid email or password!");
        }

        var response = new MemberSignInResponse();
        response.setFirstName(projection.getFirstName());
        response.setSurname(projection.getSurname());
        response.setUuid(projection.getId());

        return response;
    }
}
