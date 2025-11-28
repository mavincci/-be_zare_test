package com.mavincci.zeretest.zaretest.dtos;

import com.mavincci.zeretest.zaretest.entities.AuthUser;

public record AuthSessionDto(
      String token,
      AuthUser user
) {
}
