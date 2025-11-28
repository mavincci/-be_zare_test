package com.mavincci.zeretest.zaretest.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event{
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE)
   public Long id;

   public Long userId;

   public EventType eventType;

   public Long productId;

   public String category;

   public LocalDateTime timestamp;
}