package com.mavincci.zeretest.zaretest.repositories;

import com.mavincci.zeretest.zaretest.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventsRepository extends JpaRepository<Event, Long> {
}
