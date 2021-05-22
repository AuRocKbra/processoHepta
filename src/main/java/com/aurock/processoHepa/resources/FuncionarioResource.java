package com.aurock.processoHepa.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurock.processoHepa.entites.Funcionario;
import com.aurock.processoHepa.services.FuncionarioService;

@RestController
@RequestMapping(value="/funcionario")
/*
 * Camada de recursos que poderão ser acessadas pelo usuário
 * 
 * */
public class FuncionarioResource {
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	/*
	 * Metodo GET
	 * Objetivo: Permite obter uma lista de usuários cadastrados no banco de dados
	 * Retorno: Lista de funcioanrios cadastrados
	 * */
	@GetMapping
	public ResponseEntity<List<Funcionario>> findAll(){
		List<Funcionario> funcionarios = funcionarioService.findAll(); 
		return ResponseEntity.ok().body(funcionarios);
	}
	
	/* Metodo GET
	 * Objetivo: Permite obter um registro do tipo funcionario passando como parâmetro o id
	 * Retorno: Retorna o registro do funcionario encontrado no banco
	 * */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Funcionario> findById(@PathVariable Integer id){
		Funcionario funcionario = funcionarioService.findById(id);
		return ResponseEntity.ok().body(funcionario);
	}
}
