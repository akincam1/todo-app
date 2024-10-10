package com.akinc.crud_module.domain.projection;

import java.util.UUID;

public interface MemberProjection {

    UUID getId();

    String getFirstName();

    String getSurname();
}
