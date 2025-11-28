package com.mavincci.zeretest.zaretest.services;

import com.mavincci.zeretest.zaretest.entities.Event;
import com.mavincci.zeretest.zaretest.repositories.EventsRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class EventCache {
   static private Long initCounter = 0L;

   private static Long maxEvents = 500L;

   private final ConcurrentHashMap<Long, Event> eventCache = new ConcurrentHashMap<>();
   private final ConcurrentHashMap<Long, Long> eventIdCache = new ConcurrentHashMap<>();
   private final ConcurrentHashMap<LocalDateTime, Long> eventAccessCache = new ConcurrentHashMap<>();

   public EventCache() {
      initCounter ++;
      System.out.println("EventCache init counter: " + initCounter);
   }

   public void addEvents(List<Event> events) {
      System.out.println("EventCache adding Events");
      System.out.println("Length: " + events.size());

      final var currTime = LocalDateTime.now();

      for(Event event : events) {
         if(eventCache.size() >= maxEvents) {
            popEvent();
         }

         eventCache.put(event.id, event);
         eventIdCache.put(event.id, 1L);
         eventAccessCache.put(LocalDateTime.now(), event.id);
      }
   }

   public List<Event> fetch(int i) {
      return List.of();
   }

   public void popEvent() {
   }
}
