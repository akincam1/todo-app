package com.akinc.crud_module.application.service.member;

import com.akinc.crud_module.domain.request.member.CreateMemberRequest;
import com.akinc.crud_module.domain.response.member.CreateMemberResponse;
import com.akinc.crud_module.domain.response.member.MemberSignInResponse;

import java.util.UUID;

public interface MemberService {

    CreateMemberResponse create(CreateMemberRequest request);

    MemberSignInResponse signIn(String email, String password);
}
