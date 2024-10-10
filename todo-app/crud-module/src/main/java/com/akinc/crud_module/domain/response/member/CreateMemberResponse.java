package com.akinc.crud_module.domain.response.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateMemberResponse {

    private String firstName;

    private String surname;

    private String email;
}
