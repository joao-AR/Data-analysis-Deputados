package com.example.dataAnalysisDeputados.Interface;

import com.example.dataAnalysisDeputados.entity.Deputados;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeputadoRepository extends JpaRepository<Deputados,Integer> {
}
