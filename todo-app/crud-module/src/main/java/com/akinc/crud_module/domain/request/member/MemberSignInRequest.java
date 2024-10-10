package com.akinc.crud_module.domain.request.member;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberSignInRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
