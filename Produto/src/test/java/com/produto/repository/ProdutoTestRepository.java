package com.produto.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.produto.entity.Produto;

@DataJpaTest
class ProdutoTestRepository {
	@Autowired
	private ProdutoRepository produtoRepository;


	@DisplayName("Testando o save")
	@Test
	void test() {

		Produto produto1 = new Produto(null, "Savinho", "descricao", 20000 );

		Produto saveProduto = produtoRepository.save(produto1);

		assertNotNull(saveProduto);
		assertTrue(saveProduto.getId() > 0);
	}

	@DisplayName("Testando get para todos os Produtos")
	@Test
	void testGetAllRepsitory() {
		Produto produto1 = new Produto (null, "Savinho", "descricao", 20000 );
		Produto produto2 = new Produto (null, "Savinho", "descricao", 20000 );
		produtoRepository.save(produto1);
		produtoRepository.save(produto2);

		List<Produto> produtoList = produtoRepository.findAll();

		assertNotNull(produtoList);
		assertEquals(2, produtoList.size());
	}
	@DisplayName("Testando Get By ID")
	@Test
	void testGetById() {
		Produto produto1 = new Produto (null, "Savinho", "descricao", 20000 );
		produtoRepository.save(produto1);

		Produto saveProduto = produtoRepository.findById(produto1.getId()).get();

		assertNotNull(saveProduto);
		assertEquals(produto1.getId(), saveProduto.getId());
	}
	@DisplayName("Testando o Update")
	@Test
	void testUpdateProduto() {
		Produto produto1 = new Produto (null,"Savinho", "descricao", 20000 );
		produtoRepository.save(produto1);

		Produto saveProduto = produtoRepository.findById(produto1.getId()).get();
		produto1.setNome("Relógio");
		produto1.setPreco(559.00);

		Produto updateProduto = produtoRepository.save(saveProduto);

		assertNotNull(updateProduto);
		assertEquals("Relógio", updateProduto.getNome());
		assertEquals(559.00, updateProduto.getPreco());
	}
	@DisplayName("Testando o Delete")
	@Test
	void testDeleteProduto() {
		Produto produto1 = new Produto (null, "Savinho", "descricao", 20000 );
		produtoRepository.save(produto1);

		produtoRepository.deleteById(produto1.getId());
		Optional <Produto> produtoOptional = produtoRepository.findById(produto1.getId());

		assertTrue(produtoOptional.isEmpty());
	}
}
