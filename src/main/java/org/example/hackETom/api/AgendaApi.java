package org.example.hackETom.api;

import lombok.AllArgsConstructor;
import org.example.hackETom.model.Agenda;
import org.example.hackETom.service.AgendaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/agenda")
public class AgendaApi {

    private final AgendaService agendaService;

    @GetMapping
    public ResponseEntity<List<Agenda>> listarTodos() {
        return ResponseEntity.ok(agendaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agenda> listarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(agendaService.buscarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Agenda> salvar(@RequestBody Agenda agenda) {
        agendaService.salvar(agenda);
        return ResponseEntity.ok(agenda);
    }

    @PutMapping
    public ResponseEntity<Void> alterar(@RequestBody Agenda agenda) {
        if (agenda.getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        agendaService.salvar(agenda);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            agendaService.deletarPorId(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
