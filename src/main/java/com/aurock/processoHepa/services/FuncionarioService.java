package com.aurock.processoHepa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurock.processoHepa.entites.Funcionario;
import com.aurock.processoHepa.repositories.FuncionarioRepository;
import com.aurock.processoHepa.repositories.SetorRepository;


/*
 * Camada de serviço, responsável por capturar, tratar requisições, repassa-las ao repository, 
 * receber a resposta da camada de repository, processar a resposta e encaminha-la para a camada
 * de recurso
 * */

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private SetorRepository setorRepository;
	
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
	
	/*
	 * Objetivo: Permite criar um objeto do tipo funcionario
	 * Retorno: Novo objeto do tipo funcioanrio
	 * */
	public Funcionario createFuncioanrio(Funcionario newFuncioanrio) {
		if(!setorRepository.findById(newFuncioanrio.getSetor().getId()).isEmpty()) {
			if(funcionarioRepository.findByEmail(newFuncioanrio.getEmail()) == null) {
				return funcionarioRepository.save(newFuncioanrio);
			}
			else {
				return null;
			}
		}
		else {
			return null;
		}
	}
	
	/*
	 * Objetivo: Permite deletar um objeto do tipo funcionario
	 * Retorno: Sem retorno
	 * */
	public void deletaFuncionario(Integer id) {
		funcionarioRepository.delete(findById(id));
	}
	
	/*
	 * Objetivo: Permite atualizar os dados de um objeto do tipo funcioanrio
	 * Retorno: O objeto do tipo funcionario com os dados atualizados 
	 * */
	public Funcionario updateFuncionario(Integer id, Funcionario funcionario) {
		Funcionario oldFuncionario = funcionarioRepository.getOne(id);
		atualizaDadosFuncionario(oldFuncionario,funcionario);
		return funcionarioRepository.save(oldFuncionario);
	}
	
	/*
	 * Objetivo: Realiza a atualização dos dados dos entre dos dois objetos do tipo funcionario informados
	 * Retorno: Sem retorno
	 * */
	private void atualizaDadosFuncionario(Funcionario oldDados, Funcionario newDados) {
		oldDados.setNome(newDados.getNome());
		oldDados.setSalario(newDados.getSalario());
		oldDados.setSetor(newDados.getSetor());
		oldDados.setIdade(newDados.getIdade());
		oldDados.setEmail(newDados.getEmail());
	}
}
