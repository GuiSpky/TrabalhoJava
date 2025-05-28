package org.example.hackETom.controller;

import org.example.hackETom.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller// Define que essa classe trata requisições web
@RequestMapping("medico")// Define o caminho base das rotas
public class MedicoController {

    @Autowired//Injeta automaticamente uma instância de MedicoService
    private MedicoService service;

    @GetMapping("listar")// Mapeia a URL "/medico/listar" via GET
    public String listar(Model model) {
        model.addAttribute("medicos", service.listarTodos());
        return "medico/lista";
        // Lista todos os médicos
    }

    @GetMapping("editar/{id}")// Mapeia a URL "/medico/editar/{id}"
    public String alterar(@PathVariable Long id, Model model) {
        model.addAttribute("medico", service.buscarPorId(id));
        return "medico/formulario";
        // Retorna a view "medico/formulario.html", preenchida com os dados do médico para edição
    }

    @GetMapping("remover/{id}")//Mapeia a URL "/medico/remover/{id}"
    public String remover(@PathVariable Long id, Model model) {
        service.deletarPorId(id);
        return "redirect:/medico/listar";
        // Após a remoção, redireciona para a lista de médicos
    }
}
