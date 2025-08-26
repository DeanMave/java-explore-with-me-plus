package ru.practicum.main.service;

import org.springframework.data.domain.Pageable;
import ru.practicum.main.dto.request.event.SearchOfEventByAdminDto;
import ru.practicum.main.dto.request.event.UpdateEventAdminRequest;
import ru.practicum.main.dto.response.event.EventFullDto;
import ru.practicum.main.service.interfaces.EventAdminService;

import java.util.List;

public class EventAdminServiceImpl implements EventAdminService {
    @Override
    public List<EventFullDto> getEvents(SearchOfEventByAdminDto searchOfEventByAdminDto, Pageable pageable) {
        return List.of();
    }

    @Override
    public EventFullDto updateEvent(Long eventId, UpdateEventAdminRequest updateEventAdminRequest) {
        return null;
    }
}
