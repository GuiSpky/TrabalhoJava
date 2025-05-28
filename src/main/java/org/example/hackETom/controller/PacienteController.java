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
@RequestMapping("paciente")
public class PacienteController {

    @Autowired
    private PacienteService service;

    @GetMapping()
    public String iniciar(Paciente paciente, Model model) {
        return "paciente/formulario";
    }
    @PostMapping("salvar")
    public String salvar(Paciente paciente,
                         Model model) {
        try {
            service.salvar(paciente);
            return "redirect:/paciente/listar";
        } catch (Exception e) {
            model.addAttribute(
                    "erro",
                    "Alguma coisa de errada não está certa!"
                            + e.getMessage());
            return iniciar(paciente, model);
        }
    }
    @GetMapping("listar")
    public String listar(Model model) {
        model.addAttribute("pacientes", service.listarTodos());
        return "paciente/lista";
    }
    @GetMapping("editar/{id}")
    public String alterar(@PathVariable Long id, Model model) {
        model.addAttribute("paciente", service.buscarPorId(id));
        return "paciente/formulario";
    }

    @GetMapping("remover/{id}")
    public String remover(@PathVariable Long id, Model model) {
        service.deletarPorId(id);
        return "redirect:/paciente/listar";
    }
}
