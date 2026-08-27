package com.clinica.agendamentos.service;

import com.clinica.agendamentos.model.Agendamento;
import com.clinica.agendamentos.model.Agendamento.Status;
import com.clinica.agendamentos.model.Exame;
import com.clinica.agendamentos.model.Medico;
import com.clinica.agendamentos.repository.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository repository;

    public List<Agendamento> listar(String busca, String medico, String exame, String status) {
        Medico medicoEnum   = (medico != null && !medico.isEmpty())  ? Medico.valueOf(medico) : null;
        Exame  exameEnum    = (exame  != null && !exame.isEmpty())   ? Exame.valueOf(exame)   : null;
        Status statusEnum   = (status != null && !status.isEmpty())  ? Status.valueOf(status) : null;
        String buscaFiltro  = (busca  != null && !busca.isEmpty())   ? busca                 : null;

        return repository.filtrar(buscaFiltro, medicoEnum, exameEnum, statusEnum);
    }

    public Agendamento salvar(Agendamento agendamento) {
        return repository.save(agendamento);
    }
    public Agendamento atualizar(Long id, Agendamento dados) {
        Agendamento ag = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Agendamento não encontrado: " + id));
        ag.setNomePaciente(dados.getNomePaciente());
        ag.setCpf(dados.getCpf());
        ag.setTelefone(dados.getTelefone());
        ag.setMedico(dados.getMedico());
        ag.setExame(dados.getExame());
        ag.setDataHora(dados.getDataHora());
        ag.setStatus(dados.getStatus());
        return repository.save(ag);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Agendamento não encontrado: " + id);
        }
        repository.deleteById(id);
    }
}