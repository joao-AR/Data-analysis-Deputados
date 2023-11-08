package com.example.dataAnalysisDeputados.Interface;

import com.example.dataAnalysisDeputados.entity.Proposicao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProposicaoRepository extends JpaRepository<Proposicao,Integer> {
}
