package com.produto.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.produto.entity.Produto;
import com.produto.repository.ProdutoRepository;
import com.produto.service.ProdutoServices;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
class ProdutoServicesTest {
	@Autowired
	private ProdutoServices produtoServices;
	@Autowired
	private ProdutoRepository produtoRepository;

	@BeforeEach
	void setUp() {
		produtoRepository.deleteAll();
	}

	@DisplayName("Testando salvar Hóspede")
	@Test
	void testSalvarProduto() {
		Produto produto = new Produto(null, "Savinho", "descricao", 20000);
		Produto resultado = produtoServices.salvarProduto(produto);
		assertNotNull(resultado);
		assertEquals("Savinho", resultado.getNome());
		assertTrue(resultado.getId() > 0);
	}

	@DisplayName("Testando listar todos os hóspedes")
	@Test
	void testListarTodos() {
		Produto produto1 = new Produto(null, "Savinho", "descricao", 20000);
		Produto produto2 = new Produto(null, "Savinho", "descricao", 20000);
		produtoServices.salvarProduto(produto1);
		produtoServices.salvarProduto(produto2);
		List<Produto> resultado = produtoServices.listarTodos();
		assertNotNull(resultado);
		assertEquals(2, resultado.size());
	}

	@DisplayName("Testando buscar hóspede por ID")
	@Test
	void testBuscarPorId() {
		Produto produto = new Produto(null, "Savinho", "descricao", 20000);
		Produto salvo = produtoServices.salvarProduto(produto);
		Optional<Produto> resultado = produtoServices.buscarPorId(salvo.getId());
		assertTrue(resultado.isPresent());
		assertEquals("Savinho", resultado.get().getNome());
	}

	@DisplayName("Testando atualizar hóspede")
	@Test
	void testAtualizarproduto() {
		Produto produto = new Produto(null, "Savinho", "descricao", 20000);
		Produto salvo = produtoServices.salvarProduto(produto);
		
		salvo.setNome("Leonardo");
		salvo.setDescricao("leonardo@gmail.com");
		salvo.setPreco(20000);
		
		Produto atualizado = produtoServices.atualizarProduto(salvo);
		
		assertNotNull(atualizado);
		assertEquals("Leonardo", atualizado.getNome());
		assertEquals("leonardo@gmail.com", atualizado.getDescricao());
	}

	@DisplayName("Testando deletar hóspede")
	@Test
	void testDeletarproduto() {
		Produto produto = new Produto(null, "Savinho", "descricao", 20000);
		Produto salvo = produtoServices.salvarProduto(produto);
		produtoServices.deletarProduto(salvo.getId());
		Optional<Produto> resultado = produtoServices.buscarPorId(salvo.getId());
		assertTrue(resultado.isEmpty());
	}
}
