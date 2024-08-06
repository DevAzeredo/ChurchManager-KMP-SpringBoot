package br.com.church.manager.server.server.grupo;


import br.com.church.manager.server.server.curso.Curso;
import br.com.church.manager.server.server.pessoa.Pessoa;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "grupo")
public class Grupo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column()
    private String descricao;

    @ManyToMany(mappedBy = "grupos")
    @JsonIgnore
    private Set<Pessoa> pessoas = new HashSet<>();

}