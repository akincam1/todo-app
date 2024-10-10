package com.akinc.crud_module.api.member;

import com.akinc.crud_module.application.service.member.MemberService;
import com.akinc.crud_module.domain.request.member.CreateMemberRequest;
import com.akinc.crud_module.domain.request.member.MemberSignInRequest;
import com.akinc.crud_module.domain.response.member.CreateMemberResponse;
import com.akinc.crud_module.domain.response.member.MemberSignInResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService service;

    @PostMapping("/create")
    public ResponseEntity<CreateMemberResponse> create(@Valid @RequestBody CreateMemberRequest request) {

        var response = service.create(request);
        return ResponseEntity.ok(response);
    }
}
