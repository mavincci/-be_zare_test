package com.mavincci.zeretest.zaretest.repositories;

import com.mavincci.zeretest.zaretest.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface EventsRepository extends JpaRepository<Event, Long> {
   List<Event> findAllByOrderByTimestampDesc(Pageable pageable);
}
