package org.example.hackETom.controller;

import org.example.hackETom.model.Agenda;
import org.example.hackETom.service.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("agenda")
public class AgendaController {

    @Autowired
    private AgendaService service;

    @GetMapping()
    public String iniciar(Agenda agenda, Model model) {
        return "agenda/formulario";
    }
    @PostMapping("salvar")
    public String salvar(Agenda agenda,
                         Model model) {
        try {
            service.salvar(agenda);
            return "redirect:/agenda/listar";
        } catch (Exception e) {
            model.addAttribute(
                    "erro",
                    "Alguma coisa de errada não está certa!"
                            + e.getMessage());
            return iniciar(agenda, model);
        }
    }
    @GetMapping("listar")
    public String listar(Model model) {
        model.addAttribute("agendas", service.listarTodos());
        return "agenda/lista";
    }
    @GetMapping("editar/{id}")
    public String alterar(@PathVariable Long id, Model model) {
        model.addAttribute("agenda", service.buscarPorId(id));
        return "agenda/formulario";
    }

    @GetMapping("remover/{id}")
    public String remover(@PathVariable Long id, Model model) {
        service.deletarPorId(id);
        return "redirect:/agenda/listar";
    }
}



