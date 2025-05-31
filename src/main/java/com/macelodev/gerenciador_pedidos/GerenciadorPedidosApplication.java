package com.macelodev.gerenciador_pedidos;

import com.macelodev.gerenciador_pedidos.principal.Principal;
import com.macelodev.gerenciador_pedidos.repository.CategoriaRepository;
import com.macelodev.gerenciador_pedidos.repository.FornecedorRepository;
import com.macelodev.gerenciador_pedidos.repository.PedidoRepository;
import com.macelodev.gerenciador_pedidos.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GerenciadorPedidosApplication implements CommandLineRunner {
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
    @Autowired
    private FornecedorRepository fornecedorRepository;

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorPedidosApplication.class, args);

	}
	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(produtoRepository, categoriaRepository, pedidoRepository, fornecedorRepository);
		principal.exibeMenu();
	}
}
