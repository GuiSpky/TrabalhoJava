package org.example.hackETom.service;

import org.example.hackETom.model.Agenda;
import org.example.hackETom.repository.AgendaRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AgendaService {
    @Autowired
    private AgendaRepository repository;

    @Transactional
    public void salvar(Agenda agenda) {
        try {
            if (agenda.getMedico() == null || agenda.getPaciente() == null || agenda.getDataHora() == null) {
                throw new RuntimeException("Todos os campos (médico, paciente e data/hora) devem ser preenchidos.");
            }
//            if (agenda.getDataHora().isBefore(LocalDateTime.now())) { Testar validação de data e hora
//                throw new RuntimeException("A data e hora do agendamento não pode ser anterior ao momento atual.");
//            }

            repository.save(agenda);

        } catch (ConstraintViolationException cve) {
            System.out.println("Erro de validação: " + cve.getMessage());
        } catch (RuntimeException re) {
            throw re;
        } catch (Exception e) {
            System.out.println("Erro ao salvar agenda: " + e.getMessage());
        }
    }

    public List<Agenda> listarTodos() {
        return repository.findAll();
    }

    public Agenda buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Agenda não encontrada com id: " + id));
    }

    public void deletarPorId(Long id) {
        repository.deleteById(id);
    }
}

