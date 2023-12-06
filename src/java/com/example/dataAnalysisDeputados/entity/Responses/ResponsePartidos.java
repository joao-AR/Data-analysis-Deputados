package com.example.dataAnalysisDeputados.entity.Responses;

import com.example.dataAnalysisDeputados.entity.Partidos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponsePartidos {
        private List<Partidos> dados;
}
