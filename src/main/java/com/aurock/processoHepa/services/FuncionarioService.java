package com.aurock.processoHepa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurock.processoHepa.entites.Funcionario;
import com.aurock.processoHepa.repositories.FuncionarioRepository;


/*
 * Camada de serviço, responsável por capturar, tratar requisições, repassa-las ao repository, 
 * receber a resposta da camada de repository, processar a resposta e encaminha-la para a camada
 * de recurso
 * */

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	/*
	 * Objetivo: Permite obter lista completa de funcionarios cadastrados
	 * Retorno: Lista do tipo funcionario contendo todos os funcionarios cadastrados
	 * */
	public List<Funcionario> findAll(){
		return funcionarioRepository.findAll();
	}
	
	/*
	 * Objetivo: Permite obter registro de um funcionario em específico
	 * Retorno: O registro do funcioanrio desejado
	 * */
	public Funcionario findById(Integer id) {
		return funcionarioRepository.findById(id).get();
	}
}
