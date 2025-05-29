package org.example.hackETom.api;

import lombok.AllArgsConstructor;
import org.example.hackETom.model.Medico;
import org.example.hackETom.service.MedicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/medico")
public class MedicoApi {

    private final MedicoService medicoService;

    @GetMapping
    public ResponseEntity<List<Medico>> listarTodos() {
        return ResponseEntity.ok(medicoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> listarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(medicoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity salvar(@RequestBody Medico medico) {
        medicoService.salvar(medico);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity alterar(@RequestBody Medico medico) {
        if (medico.getId() == null) ResponseEntity.badRequest().build();
        medicoService.salvar(medico);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        medicoService.deletarPorId(id);
        return ResponseEntity.ok().build();
    }
}
