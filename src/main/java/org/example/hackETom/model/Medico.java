package org.example.hackETom.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity// Indica que essa classe é uma entidade JPA, faz com que seja mapeada para uma tabela do banco de dados
@Data// Gera métodos Getters, setters, toString(), equals, hashCode e outros utilitários automaticamente através do lombok
@AllArgsConstructor// Cria um construtor com todos os atributos da classe
@NoArgsConstructor// Cria um construtor vazio
public class Medico {

    @Id// Indica que o atributo id é a PK da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY)// Define que o valor id será gerado automaticamente pelo banco
    private Long id;
    private String nome;

    private String crm;
    private String especialidade;
}
