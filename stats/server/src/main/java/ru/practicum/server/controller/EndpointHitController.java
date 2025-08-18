package ru.practicum.server.controller;

import dto.EndpointHitDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.server.service.EndpointHitService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hit")
public class EndpointHitController {
    private final EndpointHitService endpointHitService;

    @PostMapping
    public EndpointHitDto saveEndpointHit(@RequestBody EndpointHitDto endpointHitDto) {
        return endpointHitService.save(endpointHitDto);
    }

}