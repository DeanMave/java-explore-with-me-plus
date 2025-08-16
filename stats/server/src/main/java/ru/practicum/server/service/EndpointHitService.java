package ru.practicum.server.service;

import dto.EndpointHitDto;
import dto.ViewStatsDto;

import java.time.LocalDateTime;
import java.util.List;

public interface EndpointHitService {
    EndpointHitDto save(EndpointHitDto endpointHitDto);

    List<ViewStatsDto> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, boolean unique);
}
