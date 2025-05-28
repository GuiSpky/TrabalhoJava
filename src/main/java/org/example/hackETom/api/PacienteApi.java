package org.example.hackETom.api;

import lombok.AllArgsConstructor;
import org.example.hackETom.model.Paciente;
import org.example.hackETom.service.PacienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/paciente")
public class PacienteApi {


    private final PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<List<Paciente>> listarTodos(){
        return ResponseEntity.ok(pacienteService.listarTodos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> listarPorId(@PathVariable Long id){
        return ResponseEntity.ok(pacienteService.buscarPorId(id));
    }
    @PostMapping
    public ResponseEntity salvar(@RequestBody Paciente paciente){
        pacienteService.salvar(paciente);
        return ResponseEntity.ok().build();
    }
    @PutMapping
    public ResponseEntity alterar(@RequestBody Paciente paciente){
        if (paciente.getId() == null) ResponseEntity.badRequest().build();
        pacienteService.salvar(paciente);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id){
        pacienteService.deletarPorId(id);
        return ResponseEntity.ok().build();
    }
}
