package ru.practicum.main.service;

import org.springframework.data.domain.Pageable;
import ru.practicum.main.dto.request.event.SearchOfEventByPublicDto;
import ru.practicum.main.dto.response.event.EventFullDto;
import ru.practicum.main.dto.response.event.EventShortDto;
import ru.practicum.main.service.interfaces.EventPublicService;

import java.util.List;

public class EventPublicServiceImpl implements EventPublicService {
    @Override
    public List<EventShortDto> getEvents(SearchOfEventByPublicDto searchOfEventByPublicDto, Pageable pageable) {
        return List.of();
    }

    @Override
    public EventFullDto getEvent(Long id) {
        return null;
    }
}
