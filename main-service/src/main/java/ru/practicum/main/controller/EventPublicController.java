package ru.practicum.main.controller;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.main.dto.request.event.SearchOfEventByPublicDto;
import ru.practicum.main.dto.request.event.SortOfEvent;
import ru.practicum.main.dto.response.event.EventFullDto;
import ru.practicum.main.dto.response.event.EventShortDto;
import ru.practicum.main.service.interfaces.EventPublicService;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
@Validated
public class EventPublicController {

    private final EventPublicService eventPublicService;

    @GetMapping
    public List<EventShortDto> getEvents(@RequestParam(name = "text") String text,
                                         @RequestParam(name = "categories") List<Integer> categories,
                                         @RequestParam(name = "paid") Boolean paid,
                                         @RequestParam(name = "rangeStart") LocalDateTime rangeStart,
                                         @RequestParam(name = "rangeEnd") LocalDateTime rangeEnd,
                                         @RequestParam(name = "onlyAvailable", defaultValue = "false")
                                         Boolean onlyAvailable,
                                         @RequestParam(name = "sort") SortOfEvent sort,
                                         @PositiveOrZero @RequestParam(name = "from", defaultValue = "0") Integer from,
                                         @Positive @RequestParam(name = "size", defaultValue = "10") Integer size) {
        log.debug("Поступил публичный запрос на возврат списка всех событий, подходящие под запрашиваемые условия");
        SearchOfEventByPublicDto searchOfEventByPublicDto = SearchOfEventByPublicDto.builder()
                .text(text)
                .categories(categories)
                .paid(paid)
                .rangeStart(rangeStart)
                .rangeEnd(rangeEnd)
                .onlyAvailable(onlyAvailable)
                .sort(sort)
                .build();
        Pageable pageable = PageRequest.of(from, size);
        return eventPublicService.getEvents(searchOfEventByPublicDto, pageable);
    }

    @GetMapping("/{id}")
    public EventFullDto getEvent(@PathVariable @Positive Long id) {
        log.debug("Поступил публичный запрос на возврат события {}", id);
        return eventPublicService.getEvent(id);
    }
}
