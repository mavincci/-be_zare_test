package com.mavincci.zeretest.zaretest.dtos;

public record AddEventDto(
      String eventType,
      Long productId,
      String category
) {
}
