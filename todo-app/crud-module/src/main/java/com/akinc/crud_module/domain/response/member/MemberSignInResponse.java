package com.akinc.crud_module.domain.response.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberSignInResponse {

    private String firstName;

    private String surname;

    private UUID uuid;
}
