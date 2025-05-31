package org.example.hackETom.controller;

import org.example.hackETom.model.Paciente;
import org.example.hackETom.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteService service;

    @GetMapping()
    public String listar(Model model) {
        model.addAttribute("pacientes", service.listarTodos());
        return "paciente/lista";
    }
    
    @GetMapping("/novo")
    public String iniciar(Paciente paciente, Model model) {
        model.addAttribute("paciente", new Paciente());
        return "paciente/cadastro";
    }
    
    @PostMapping()
    public String salvar(Paciente paciente, Model model) {
        try {
            service.salvar(paciente);
            return "redirect:/pacientes";
        } catch (Exception e) {
            model.addAttribute(
                    "erro",
                    "Ocorreu um erro ao salvar o paciente: " + e.getMessage());
            return "paciente/cadastro";
        }
    }
    
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("paciente", service.buscarPorId(id));
        return "paciente/cadastro";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id, Model model) {
        service.deletarPorId(id);
        return "redirect:/pacientes";
    }
}
