package com.generation.blogpessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.generation.blogpessoal.model.Usuario;


//indicando que a classe UsuarioRepositoryTest é uma classe de test, que vai rodar em uma porta aleatoria a cada teste realizado 
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

//cria uma instancia de testes, que define que o ciclo de vida do teste vai respeitar o ciclo de vida da classe(será executado e resetado após o uso) 
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class UsuarioRepositoryTest {

	@Autowired 
	private UsuarioRepository repository; 
	
	@BeforeAll 
	void start() {
		
		/** 
		 * Persiste (Grava) 4 Objetos Usuario no Banco de dados
		 */ 
		
	    repository.save(new Usuario(0L, "Jéssica Prandini", "jeh@email.com","jeh","https://i.imgur.com/FETvs2O.jpg"));
	    repository.save(new Usuario(0L, "Boris Prandini", "maria@email.com","boris","https://i.imgur.com/FETvs2O.jpg"));
		repository.save(new Usuario(0L, "Bart Prandini", "bart@email.com","bart","https://i.imgur.com/FETvs2O.jpg"));
		repository.save(new Usuario(0L, "Naruto Garcia", "naruto@email.com","naruto","https://i.imgur.com/FETvs2O.jpg"));

	} 
	
	@Test
	@DisplayName("Retorna 1 usuario")
	public void deveRetornarUmUsuario() {
		
		/**
		 *  Executa o método findByUsuario para buscar um usuario pelo nome (joao@email.com.br)
		 */
		
		Optional<Usuario> usuario = repository.findByUsuario("maria@email.com");
		
		/**
		 *  Verifica se a afirmação: "É verdade que a busca retornou o usuario joao@email.com.br" é verdadeira
		 *  Se for verdaeira, o teste passa, senão o teste falha. 
		 */
		
		assertTrue(usuario.get().getUsuario().equals("maria@email.com"));
	}	
	
	@Test
	@DisplayName("Retorna 3 usuarios")
	public void deveRetornarTresUsuarios() {

		/**
		 *  Executa o método findAllByNomeContainingIgnoreCase para buscar todos os usuarios cujo nome contenha
		 *  a palavra "Prandini"
		 */
		List<Usuario> listaDeUsuarios = repository.findAllByNomeContainingIgnoreCase("Prandini");

		/**
		 * Verifica se a afirmação: "É verdade que a busca retornou 3 usuarios, cujo nome possua a palavra Prandini" 
		 * é verdadeira
		 * Se for verdadeira, o teste passa, senão o teste falha.
		 */
		assertEquals(3, listaDeUsuarios.size());

		/**
		 *  Verifica se a afirmação: "É verdade que a busca retornou na primeira posição da Lista o usuario 
		 * João Prandini" é verdadeira
		 * Se for verdadeira, o teste passa, senão o teste falha.
		 */
		assertTrue(listaDeUsuarios.get(0).getNome().equals("Jéssica Prandini"));

		/**
		 *  Verifica se a afirmação: "É verdade que a busca retornou na segunda posição da Lista a usuaria 
		 * Manuela Prandini" é verdadeira
		 * Se for verdadeira, o teste passa, senão o teste falha.
		 */
		assertTrue(listaDeUsuarios.get(1).getNome().equals("Boris Prandini"));

		/**
		 *  Verifica se a afirmação: "É verdade que a busca retornou na primeira posição da Lista a usuaria 
		 * Adriana Prandini" é verdadeira
		 * Se for verdadeira, o teste passa, senão o teste falha.
		 */
		assertTrue(listaDeUsuarios.get(2).getNome().equals("Bart Prandini"));
		
	}
	
	@AfterAll
	public void end() {
		repository.deleteAll();
	} 
}
