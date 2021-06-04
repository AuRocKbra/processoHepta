package com.aurock.processoHepa.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurock.processoHepa.entites.Funcionario;
import com.aurock.processoHepa.entites.Setor;
import com.aurock.processoHepa.entites.dto.FuncionarioDto;
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
	public List<FuncionarioDto> findAll(){
		List<Funcionario> funcionarios = funcionarioRepository.findAll();
		List<FuncionarioDto> funcionariosDto = new ArrayList<>();
		for (Funcionario funcionarioEncontrado: funcionarios) {
			FuncionarioDto funcionarioDto = new FuncionarioDto(funcionarioEncontrado.getId(),funcionarioEncontrado.getNome(), funcionarioEncontrado.getEmail(), funcionarioEncontrado.getSetor().getNome(), funcionarioEncontrado.getIdade(), funcionarioEncontrado.getSalario());
			funcionariosDto.add(funcionarioDto);
		}
		return funcionariosDto;
	}
	
	/*
	 * Objetivo: Permite obter registro de um funcionario em específico
	 * Retorno: O registro do funcioanrio desejado
	 * */
	public FuncionarioDto findById(Integer id) {
		Funcionario funcionario =  funcionarioRepository.findById(id).get();
		FuncionarioDto funcionarioDto = new FuncionarioDto(funcionario.getId(),funcionario.getNome(),funcionario.getEmail(),funcionario.getSetor().getNome(),funcionario.getIdade(),funcionario.getSalario());
		return funcionarioDto;
	}
	
	/*
	 * Objetivo: Permite criar um objeto do tipo funcionario
	 * Retorno: Novo objeto do tipo funcioanrio
	 * */
	public String createFuncioanrio(FuncionarioDto newFuncioanrio) {
		Setor setor = setorRepository.findByNome(newFuncioanrio.getSetor().toUpperCase());
		if(setor != null) {
			if(funcionarioRepository.findByEmail(newFuncioanrio.getEmail()) == null) {
				Funcionario funcionario = new Funcionario(null, newFuncioanrio.getNome(),setor,newFuncioanrio.getSalario(),newFuncioanrio.getEmail(),newFuncioanrio.getIdade());
				funcionarioRepository.save(funcionario);
				return "Funcionario cadastrado!";
			}
			else {
				return "Já existe um funcionario cadastrado com estes dados!";
			}
		}
		else {
			return "O setor informado ainda não foi cadastrado no sistema!";
		}
	}
	
	/*
	 * Objetivo: Permite deletar um objeto do tipo funcionario
	 * Retorno: Sem retorno
	 * */
	public void deletaFuncionario(Integer id) {
		funcionarioRepository.delete(funcionarioRepository.findById(id).get());
	}
	
	/*
	 * Objetivo: Permite atualizar os dados de um objeto do tipo funcioanrio
	 * Retorno: O objeto do tipo funcionario com os dados atualizados 
	 * */
	public FuncionarioDto updateFuncionario(Integer id, FuncionarioDto funcionario) {
		Funcionario oldFuncionario = funcionarioRepository.getOne(id);
		atualizaDadosFuncionario(oldFuncionario,funcionario);
		Funcionario funcionarioReborn = funcionarioRepository.save(oldFuncionario);
		funcionario = new FuncionarioDto(funcionarioReborn.getId(), funcionarioReborn.getNome(), funcionarioReborn.getEmail(), funcionarioReborn.getSetor().getNome(), funcionarioReborn.getIdade(), funcionarioReborn.getSalario());
		return funcionario;
	}
	
	/*
	 * Objetivo: Realiza a atualização dos dados dos entre dos dois objetos do tipo funcionario informados
	 * Retorno: Sem retorno
	 * */
	private void atualizaDadosFuncionario(Funcionario oldDados, FuncionarioDto newDados) {
		oldDados.setNome(newDados.getNome());
		oldDados.setSalario(newDados.getSalario());
		Setor setor = setorRepository.findByNome(newDados.getSetor().toUpperCase());
		oldDados.setSetor(setor);
		oldDados.setIdade(newDados.getIdade());
		oldDados.setEmail(newDados.getEmail());
	}
}
