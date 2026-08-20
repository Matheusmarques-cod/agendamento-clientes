package com.clinica.agendamentos.repository;

import com.clinica.agendamentos.model.Agendamento;
import com.clinica.agendamentos.model.Agendamento.Status;
import com.clinica.agendamentos.model.Exame;
import com.clinica.agendamentos.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    @Query("""
        SELECT a FROM Agendamento a
        WHERE (:busca IS NULL OR LOWER(a.nomePaciente) LIKE LOWER(CONCAT('%', :busca, '%'))
               OR LOWER(a.cpf) LIKE LOWER(CONCAT('%', :busca, '%')))
        AND (:medico IS NULL OR a.medico = :medico)
        AND (:exame IS NULL OR a.exame = :exame)
        AND (:status IS NULL OR a.status = :status)
        ORDER BY a.dataHora DESC
    """)
    List<Agendamento> filtrar(
        @Param("busca") String busca,
        @Param("medico") Medico medico,
        @Param("exame") Exame exame,
        @Param("status") Status status
    );
}