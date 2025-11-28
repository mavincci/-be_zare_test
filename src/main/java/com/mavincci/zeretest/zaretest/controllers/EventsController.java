package com.mavincci.zeretest.zaretest.controllers;

import com.mavincci.zeretest.zaretest.dtos.AddEventDto;
import com.mavincci.zeretest.zaretest.dtos.EventDto;
import com.mavincci.zeretest.zaretest.entities.AuthUser;
import com.mavincci.zeretest.zaretest.services.EventsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
class EventsController {
   private final EventsService eventsService;


   @GetMapping
   public List<EventDto> getEvents() {
      return eventsService.getEvents();
   }

   @PostMapping
   public List<EventDto> addEvents(@RequestBody List<AddEventDto> req, @AuthenticationPrincipal AuthUser user) {
      return eventsService.addEvents(req, user);
   }

   @GetMapping("/recent")
   public String recentEvents() {
      return "REcent events";
   }

   @GetMapping("/summary")
   public String eventsSummary() {
      return "Events summary";
   }
}