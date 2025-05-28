package org.example.hackETom.service;

import org.example.hackETom.model.Paciente;
import org.example.hackETom.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {


    @Autowired
    private PacienteRepository repository;

    public void salvar(Paciente paciente) {
        repository.save(paciente);
    }

    public List<Paciente> listarTodos() {
        return repository.findAll();
    }

    public Paciente buscarPorId(Long id) {
        return repository.findById(id).get();
    }

    public void deletarPorId(Long id) {
        repository.deleteById(id);
    }
}
