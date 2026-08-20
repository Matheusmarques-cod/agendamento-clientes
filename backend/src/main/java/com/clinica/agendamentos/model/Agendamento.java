package com.clinica.agendamentos.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomePaciente;
    private String cpf;
    private String telefone;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private Medico medico;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private Exame exame;

    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Status status;

    public enum Status {
        AGENDADO, CONFIRMADO, CANCELADO, REALIZADO
    }
}