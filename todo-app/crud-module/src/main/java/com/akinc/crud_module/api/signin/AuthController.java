package com.akinc.crud_module.api.signin;

import com.akinc.crud_module.application.service.member.MemberService;
import com.akinc.crud_module.domain.request.member.MemberSignInRequest;
import com.akinc.crud_module.domain.response.member.MemberSignInResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final MemberService service;

    @PostMapping("/sign-in")
    public ResponseEntity<MemberSignInResponse> signIn(@Valid @RequestBody MemberSignInRequest request) {

        var response = service.signIn(request.getEmail(), request.getPassword());

        HttpHeaders headers = new HttpHeaders();

        return ResponseEntity.ok().headers(headers).body(response);
    }

    @PostMapping("/generate")
    public ResponseEntity<Void> signIn(@RequestHeader(value = "X-Refresh-Token") String token) {

        HttpHeaders headers = new HttpHeaders();

        return ResponseEntity.ok().headers(headers).build();
    }
}
