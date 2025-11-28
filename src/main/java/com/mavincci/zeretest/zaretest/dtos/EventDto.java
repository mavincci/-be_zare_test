package com.mavincci.zeretest.zaretest.dtos;

import com.mavincci.zeretest.zaretest.entities.Event;
import com.mavincci.zeretest.zaretest.entities.EventType;

import java.time.LocalDateTime;

public record EventDto(
      Long id,
      Long userId,
      EventType eventType,
      Long productId,
      String category,
      LocalDateTime timestamp
) {
   public static EventDto fromEntity(Event entity) {
      return new EventDto(
            entity.id,
            entity.userId,
            entity.eventType,
            entity.productId,
            entity.category,
            entity.timestamp
      );
   }
}
