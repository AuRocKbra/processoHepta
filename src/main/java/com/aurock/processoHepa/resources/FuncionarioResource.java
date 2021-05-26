package com.aurock.processoHepa.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.aurock.processoHepa.entites.Funcionario;
import com.aurock.processoHepa.services.FuncionarioService;

@RestController
@RequestMapping(value="/funcionarios")
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
	
	@GetMapping(value="/home")
	public ModelAndView getHome() {
		//List<Funcionario> funcionarios = funcionarioService.findAll();
		ModelAndView homePage = new ModelAndView("index");
		//homePage.addObject("funcionarios",funcionarios);
		return homePage;
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
	
	/* Metodo POST
	 * Objetivo: Permite cadastrar um objeto do tipo funcionario
	 * Retorno: Retorna respota 201 de criação com o caminho para o novo objeto
	 * */
	//@PostMapping(value="/funcionarios")
	@PostMapping
	public ResponseEntity<Funcionario> createFuncionario(@RequestBody Funcionario newFuncionario){
		Funcionario response = funcionarioService.createFuncioanrio(newFuncionario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.getId()).toUri();
		return ResponseEntity.created(uri).body(response);
	}
	
	/* Metodo DELETE
	 * Objetivo: Permite excluir um objeto do tipo funcionario
	 * Retorno: Retorna respota 204 
	 * */
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> deleteFuncionario(@PathVariable Integer id){
		funcionarioService.deletaFuncionario(id);
		return ResponseEntity.noContent().build();
	}
	
	/* Metodo PUT
	 * Objetivo: Permite atualizar dados de um funcionario
	 * Retorno: Retorna objeto do tipo funcionario com os dados atualizados	
	 * */
	@PutMapping(value = "/{id}")
	public ResponseEntity<Funcionario> updateFuncionario(@PathVariable Integer id, @RequestBody Funcionario newDados){
		Funcionario response = funcionarioService.updateFuncionario(id, newDados);
		return ResponseEntity.ok().body(response);
	}
}
