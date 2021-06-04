package com.aurock.processoHepa.entites.dto;

import java.io.Serializable;

public class FuncionarioDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private String email;
	private String setor;
	private Integer idade;
	private Double salario;
	
	public FuncionarioDto() {}

	public FuncionarioDto(Integer id, String nome, String email, String setor, Integer idade, Double salario) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.setor = setor;
		this.idade = idade;
		this.salario = salario;
	}

	

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getSetor() {
		return setor;
	}

	public Integer getIdade() {
		return idade;
	}

	public Double getSalario() {
		return salario;
	}
}
