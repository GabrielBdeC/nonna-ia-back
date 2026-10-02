package br.com.nonna_ai.controller;
import br.com.nonna_ai.entity.Reserva;
import br.com.nonna_ai.dto.ReservaResponseDto;
import java.util.stream.Collectors;
import br.com.nonna_ai.dto.ReservaDto;
import br.com.nonna_ai.mapper.ReservaMapper;
import jakarta.validation.Valid;

import br.com.nonna_ai.service.ReservaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/reserva")
public class ReservaController {
    private final ReservaService service;
    private final ReservaMapper mapper;
    public ReservaController(ReservaService service, ReservaMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReservaResponseDto criar(@RequestBody @Valid ReservaDto dto) { return mapper.toResponseDto(service.criar(mapper.toEntity(dto))); }

    @PostMapping("/{id}/cancelar")
    public ReservaResponseDto cancelar(@PathVariable String id, @RequestBody Map<String, String> body) {
        return mapper.toResponseDto(service.cancelar(id, body.get("motivo")));
    }
}
