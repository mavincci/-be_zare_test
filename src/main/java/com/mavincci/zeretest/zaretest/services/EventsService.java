package com.mavincci.zeretest.zaretest.services;

import com.mavincci.zeretest.zaretest.dtos.AddEventDto;
import com.mavincci.zeretest.zaretest.dtos.EventDto;
import com.mavincci.zeretest.zaretest.entities.AuthUser;
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
   private final EventCache eventCache;

   public List<EventDto> addEvents(List<AddEventDto> req, AuthUser user) {
      final var reqTime = LocalDateTime.now();

      final var tempEvents = req.stream().map((e) -> Event.builder()
            .userId(user.getId())
            .eventType(EventType.valueOf(e.eventType()))
            .productId(e.productId())
            .category(e.category())
            .timestamp(reqTime)
            .build()).toList();

      final var savedEvents = eventsRepository.saveAll(tempEvents);

      eventCache.addEvents(savedEvents);

      return savedEvents.stream().map(EventDto::fromEntity).toList();
   }

   public List<EventDto> getEvents() {
      final var tempEvents = eventsRepository.findAll();

      return tempEvents.stream().map(EventDto::fromEntity).toList();
   }

   public List<Event> latest20Events() {
      final var cachedEvents = eventCache.fetch(20);

//      final var diff = 20 - cachedEvents.size();
//
//      if (diff == 0) return cachedEvents;
//
//      final var fromDb = eventsRepository.findAll(
//            PageRequest.of(0, diff, Sort.by("timestamp").descending()));

      return cachedEvents;
   }
}
