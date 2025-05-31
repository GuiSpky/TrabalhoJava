package org.example.hackETom.controller;

import org.example.hackETom.model.Medico;
import org.example.hackETom.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoService service;

    @GetMapping()
    public String listar(Model model) {
        model.addAttribute("medicos", service.listarTodos());
        return "medico/lista";
    }
    
    @GetMapping("/novo")
    public String iniciar(Medico medico, Model model) {
        model.addAttribute("medico", new Medico());
        return "medico/cadastro";
    }
    
    @PostMapping()
    public String salvar(Medico medico, Model model) {
        try {
            service.salvar(medico);
            return "redirect:/medicos";
        } catch (Exception e) {
            model.addAttribute(
                    "erro",
                    "Ocorreu um erro ao salvar o médico: " + e.getMessage());
            return "medico/cadastro";
        }
    }
    
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("medico", service.buscarPorId(id));
        return "medico/cadastro";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id, Model model) {
        service.deletarPorId(id);
        return "redirect:/medicos";
    }
}
