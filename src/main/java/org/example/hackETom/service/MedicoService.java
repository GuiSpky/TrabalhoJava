package org.example.hackETom.service;

import org.example.hackETom.model.Medico;
import org.example.hackETom.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service// Indica que a classe é um Service do Spring. Permite a injeção de dependência em outras partes do sistema
public class MedicoService {

    @Autowired// Injeta dependência automaticamente
    private MedicoRepository repository;

    // Salvar o médico
    public void salvar(Medico medico) {
        repository.save(medico);
    }

    // Listar todos os médicos
    public List<Medico> listarTodos() {
        return repository.findAll();
    }

    // Busca o cadastro do médico através do id
    public Medico buscarPorId(Long id) {
        return repository.findById(id).get();
    }

    // Deletar o médico
    public void deletarPorId(Long id) {
        repository.deleteById(id);
    }
}
