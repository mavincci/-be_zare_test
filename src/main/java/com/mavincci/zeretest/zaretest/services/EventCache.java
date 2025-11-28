package com.mavincci.zeretest.zaretest.services;

import com.mavincci.zeretest.zaretest.entities.Event;
import com.mavincci.zeretest.zaretest.repositories.EventsRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventCache {
   static private Long initCounter = 0L;

   private static Long maxEvents = 500L;

   public EventCache() {
      initCounter ++;
      System.out.println("EventCache init counter: " + initCounter);
   }

   public String accessRecent() {
      System.out.println("EventCache accessRecent");
      return "Recent";
   }

   public void addEvents(List<Event> events) {
      System.out.println("EventCache adding Events");
      System.out.println("Length: " + events.size());
   }
}
