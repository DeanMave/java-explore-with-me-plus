package ru.practicum.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.main.model.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
}
