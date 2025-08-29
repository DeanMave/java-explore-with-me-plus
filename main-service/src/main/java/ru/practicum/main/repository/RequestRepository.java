package ru.practicum.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.practicum.main.model.Request;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {

    @Query("""
            SELECT r FROM Request r
            JOIN FETCH r.event
            WHERE r.requester.id = :requesterId
            """)
    List<Request> findAllByRequesterId(@Param("requesterId") Long requesterId);

    @Query("""
            SELECT COUNT(r) > 0 FROM Request r
            WHERE r.event.id = :eventId AND r.requester.id = :requesterId
            """)
    boolean existsByRequesterIdAndEventId(@Param("requesterId") Long requesterId, @Param("eventId") Long eventId);

    int countByEventId(Long eventId);

}
