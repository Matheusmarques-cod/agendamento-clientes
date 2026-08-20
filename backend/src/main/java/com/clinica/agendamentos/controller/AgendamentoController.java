package com.clinica.agendamentos.controller;

import com.clinica.agendamentos.model.Agendamento;
import com.clinica.agendamentos.service.AgendamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/agendamentos")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AgendamentoController {

    private final AgendamentoService service;

    @GetMapping
    public List<Agendamento> listar(
            @RequestParam(required = false) String busca,
            @RequestParam(required = false) String medico,
            @RequestParam(required = false) String exame,
            @RequestParam(required = false) String status) {
        return service.listar(busca, medico, exame, status);
    }

    @PostMapping
    public Agendamento criar(@RequestBody Agendamento agendamento) {
        return service.salvar(agendamento);
    }

    @PutMapping("/{id}")
    public Agendamento atualizar(@PathVariable Long id, @RequestBody Agendamento agendamento) {
        return service.atualizar(id, agendamento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}