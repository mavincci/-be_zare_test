package com.mavincci.zeretest.zaretest.services;

import com.mavincci.zeretest.zaretest.dtos.AddEventDto;
import com.mavincci.zeretest.zaretest.dtos.EventDto;
import com.mavincci.zeretest.zaretest.entities.Event;
import com.mavincci.zeretest.zaretest.entities.EventType;
import com.mavincci.zeretest.zaretest.repositories.EventsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventsService {
   private final EventsRepository eventsRepository;

   public List<EventDto> addEvents(List<AddEventDto> req) {
      final var reqTime = LocalDateTime.now();

      final var tempEvents = req.stream().map((e) -> Event.builder()
            .userId(1L)
            .eventType(EventType.valueOf(e.eventType()))
            .productId(e.productId())
            .category(e.category())
            .timestamp(reqTime)
            .build()).toList();

      final var savedEvents = eventsRepository.saveAll(tempEvents);

      return savedEvents.stream().map(EventDto::fromEntity).toList();
   }

   public List<EventDto> getEvents() {
      final var tempEvents = eventsRepository.findAll();

      return tempEvents.stream().map(EventDto::fromEntity).toList();
   }
}
