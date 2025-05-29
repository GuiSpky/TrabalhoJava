package org.example.hackETom;

import org.example.hackETom.model.Medico;
import org.example.hackETom.model.Paciente;
import org.example.hackETom.service.MedicoService;
import org.example.hackETom.service.PacienteService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Arrays;

@SpringBootApplication
public class TrabalhoJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrabalhoJavaApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(PacienteService pacienteService, MedicoService medicoService) {
        return args -> {
            var paciete = new Paciente(
                    null,
                    "Nome Teste",
                    "11122233345",
                    LocalDate.of(2001,10,04),
                    "44911112222"
            );
            var medico = new Medico(
                    null,
                    "Doctor Who",
                    "5646544444",
                    "Neurocirurgião"
            );
            medicoService.salvar(medico);
            pacienteService.salvar(paciete);
        };
    }

//    @Bean
//    public CommandLineRunner commandLineRunner(MedicoService medicoService) {
//        return args -> {
//            var medico = new Medico(
//                    null,
//                    "Doctor Who",
//                    "5646544444",
//                    "Neurocirurgião"
//            );
//            medicoService.salvar(medico);
//        };
//    }
}
