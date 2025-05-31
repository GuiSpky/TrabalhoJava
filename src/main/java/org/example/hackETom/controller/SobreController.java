package org.example.hackETom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("sobre")
public class SobreController {

    @GetMapping
    public String sobre(Model model) {
        model.addAttribute("titulo", "Sobre Nós - Clínica Médica Hack e Tom");
        return "sobre";
    }
}
