package com.aurock.processoHepa.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.aurock.processoHepa.entites.Funcionario;
import com.aurock.processoHepa.entites.Setor;
import com.aurock.processoHepa.repositories.FuncionarioRepository;
import com.aurock.processoHepa.repositories.SetorRepository;


/*
 * Classe de configuração utilizando o perfil de test
 * */
@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private SetorRepository setorRpository;

	@Override
	public void run(String... args) throws Exception {
		Setor s1 = new Setor(null, "RH");
		
		Funcionario f1 = new Funcionario(null, "Bob", s1, 5000.00,"bob@gmail.com",35);	
		Funcionario f2 = new Funcionario(null, "Sarah", s1, 5000.00,"sarah@gmail.com",30);
		setorRpository.save(s1);
		funcionarioRepository.saveAll(Arrays.asList(f1,f2));
	}
}
