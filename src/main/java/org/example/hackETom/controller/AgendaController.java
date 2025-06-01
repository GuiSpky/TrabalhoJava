package org.example.hackETom.controller;

import org.example.hackETom.model.Agenda;
import org.example.hackETom.model.Medico;
import org.example.hackETom.model.Paciente;
import org.example.hackETom.service.AgendaService;
import org.example.hackETom.service.MedicoService;
import org.example.hackETom.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping("agenda")
public class AgendaController {

    @Autowired
    private AgendaService service;

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private PacienteService pacienteService;

    @GetMapping()
    public String listar(Model model) {
        model.addAttribute("agendamentos", service.listarTodos());
        return "agenda/lista";
    }

    @GetMapping("/novo")
    public String iniciar(Model model) {
        model.addAttribute("agendamento", new Agenda());
        model.addAttribute("medicos", medicoService.listarTodos());
        model.addAttribute("pacientes", pacienteService.listarTodos());
        return "agenda/cadastro";
    }

@PostMapping()
public String salvar(@ModelAttribute("agendamento") Agenda agenda, Model model) {
    try {
        if (agenda.getDataHora().isBefore(LocalDateTime.now())) {
            model.addAttribute("erro", "A data do agendamento não pode ser anterior ao momento atual.");
            model.addAttribute("medicos", medicoService.listarTodos());
            model.addAttribute("pacientes", pacienteService.listarTodos());
            return "agenda/cadastro";
        }

        service.salvar(agenda);
        return "redirect:/agenda";

    } catch (Exception e) {
        model.addAttribute("erro", "Ocorreu um erro ao salvar o agendamento: " + e.getMessage());
        model.addAttribute("medicos", medicoService.listarTodos());
        model.addAttribute("pacientes", pacienteService.listarTodos());
        return "agenda/cadastro";
    }
}



    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("agendamento", service.buscarPorId(id));
        model.addAttribute("medicos", medicoService.listarTodos());
        model.addAttribute("pacientes", pacienteService.listarTodos());
        return "agenda/cadastro";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        service.deletarPorId(id);
        return "redirect:/agenda";
    }
}
