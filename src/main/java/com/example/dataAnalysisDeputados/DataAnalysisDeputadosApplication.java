package com.example.dataAnalysisDeputados;

import DAO.Database;
import DAO.PartidoDAO;
import DAO.PartidoImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.SQLException;

@SpringBootApplication
public class DataAnalysisDeputadosApplication {
	public static void main(String[] args) throws SQLException {
		SpringApplication.run(DataAnalysisDeputadosApplication.class, args);
	}

}
