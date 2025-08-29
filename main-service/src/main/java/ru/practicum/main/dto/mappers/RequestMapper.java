package ru.practicum.main.dto.mappers;

import ru.practicum.main.dto.response.request.ParticipationRequestDto;
import ru.practicum.main.model.Request;

import java.util.List;

public class RequestMapper {

    public static ParticipationRequestDto toParticipationRequestDto(Request request) {
        return ParticipationRequestDto
                .builder()
                .id(request.getId())
                .requester(request.getRequester().getId())
                .event(request.getEvent().getId())
                .created(request.getCreated())
                .status(request.getStatus().toString())
                .build();
    }

    public static List<ParticipationRequestDto> toDto(List<Request> requests) {
        return requests.stream().map(RequestMapper::toParticipationRequestDto).toList();
    }
}
