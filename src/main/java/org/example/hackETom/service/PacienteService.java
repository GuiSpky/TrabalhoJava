package org.example.hackETom.service;

import org.example.hackETom.model.Paciente;
import org.example.hackETom.repository.PacienteRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PacienteService {


    @Autowired
    private PacienteRepository repository;

    public void salvar(Paciente paciente) {
        try {
            if (paciente.getNome() == null || paciente.getCpf() == null || paciente.getNascimento() == null) {
                throw new RuntimeException("Todos os campos devem ser preenchidos.");
            } if (paciente.getNascimento().isAfter(LocalDate.now())) {
                throw new RuntimeException("Data de nascimento inválida.");
            }

            repository.save(paciente);

        } catch (ConstraintViolationException cve) {
            System.out.println("Erro de validação: " + cve.getMessage());
        } catch (RuntimeException re) {
            throw re;
        } catch (Exception e) {
            System.out.println("Erro ao salvar agenda: " + e.getMessage());
        }
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
