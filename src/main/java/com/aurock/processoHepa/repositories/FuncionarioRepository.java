package com.aurock.processoHepa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurock.processoHepa.entites.Funcionario;

/*
 * Camada de responsável por interfacear a aplicação com o banco de dados
 * Não necessita da notação @Repository pois já herda da classe JpaRepository
 * 
 * */
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer>{

}
