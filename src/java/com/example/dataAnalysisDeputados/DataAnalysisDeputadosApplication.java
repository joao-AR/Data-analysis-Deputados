package com.example.dataAnalysisDeputados;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class DataAnalysisDeputadosApplication {
	public static void main(String[] args) throws SQLException {
		SpringApplication.run(DataAnalysisDeputadosApplication.class, args);
	}

}
