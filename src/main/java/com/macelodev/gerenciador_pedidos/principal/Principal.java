package com.macelodev.gerenciador_pedidos.principal;

import com.macelodev.gerenciador_pedidos.model.Categoria;
import com.macelodev.gerenciador_pedidos.model.Fornecedor;
import com.macelodev.gerenciador_pedidos.model.Pedido;
import com.macelodev.gerenciador_pedidos.model.Produto;
import com.macelodev.gerenciador_pedidos.repository.CategoriaRepository;
import com.macelodev.gerenciador_pedidos.repository.FornecedorRepository;
import com.macelodev.gerenciador_pedidos.repository.PedidoRepository;
import com.macelodev.gerenciador_pedidos.repository.ProdutoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Component
public class Principal implements CommandLineRunner {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;
    private final PedidoRepository pedidoRepository;
    private final Scanner leitura = new Scanner(System.in);
    private final FornecedorRepository fornecedorRepository;

    public Principal(ProdutoRepository produtoRepository,
                     CategoriaRepository categoriaRepository,
                     PedidoRepository pedidoRepository, FornecedorRepository fornecedorRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
        this.pedidoRepository = pedidoRepository;
        this.fornecedorRepository = fornecedorRepository;
    }

    @Override
    public void run(String... args) {
        exibeMenu();
    }

    public void exibeMenu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("""
                    ====== MENU ======
                    1 - Adicionar Categoria
                    2 - Adicionar Produto
                    3 - Adicionar Pedido
                    4 - Adicionar Fornecedor 
                    5 - Listar Categorias
                    6 - Listar Produtos
                    7 - Listar Pedidos
                    8 - Consultas Especiais
                    0 - Sair
                    ===================
                    """);
            System.out.print("Escolha uma opção: \n");
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1 -> adicionarCategoria();
                case 2 -> adicionarProduto();
                case 3 -> adicionarPedido();
                case 4 -> adicionarFornecedor();
                case 5 -> listarCategorias();
                case 6 -> listarProdutos();
                case 7 -> listarPedidos();
                case 8 -> exibeConsultasEspeciais();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private void exibeConsultasEspeciais() {
        System.out.println("""
                === CONSULTAS ===
                1 - Produtos com nome exato
                2 - Produtos por categoria
                3 - Produtos com preço maior que
                4 - Produtos com preço menor que
                5 - Produtos com nome contendo termo
                6 - Pedidos sem data de entrega
                7 - Pedidos com data de entrega
                8 - Produtos de uma categoria por preço crescente
                9 - Produtos de uma categoria por preço decrescente
                10 - Contagem de produtos por categoria
                11 - Contagem de produtos com preço maior que
                12 - Produtos com preço menor ou nome contendo termo
                13 - Pedidos após data
                14 - Pedidos antes de data
                15 - Pedidos entre datas
                16 - Top 3 produtos mais caros
                17 - Top 5 produtos mais baratos de uma categoria
                0 - Voltar ao menu principal
                """);

        int escolha = leitura.nextInt();
        leitura.nextLine();

        switch (escolha) {
            case 1 -> {
                System.out.print("Nome exato: ");
                String nome = leitura.nextLine();
                produtoRepository.findByNomeContaining(nome)
                        .forEach(System.out::println);
            }
            case 2 -> {
                Categoria categoria = selecionarCategoria();
                if (categoria != null) produtoRepository.findByCategoria(categoria)
                        .forEach(System.out::println);
            }
            case 3 -> {
                System.out.print("Preço mínimo: ");
                BigDecimal preco = leitura.nextBigDecimal();
                leitura.nextLine();
                produtoRepository.findByPrecoGreaterThan(preco)
                        .forEach(System.out::println);
            }
            case 4 -> {
                System.out.print("Preço máximo: ");
                BigDecimal preco = leitura.nextBigDecimal();
                leitura.nextLine();
                produtoRepository.findByPrecoLessThan(preco)
                        .forEach(System.out::println);
            }
            case 5 -> {
                System.out.print("Termo: ");
                String termo = leitura.nextLine();
                produtoRepository.findByNomeContainingIgnoreCase(termo)
                        .forEach(System.out::println);
            }
            case 6 -> pedidoRepository.findByDataEntregaIsNull().forEach(System.out::println);
            case 7 -> pedidoRepository.findByDataEntregaIsNotNull().forEach(System.out::println);
            case 8 -> {
                Categoria categoria = selecionarCategoria();
                if (categoria != null)
                    produtoRepository.findByCategoriaOrderByPrecoAsc(categoria)
                            .forEach(System.out::println);
            }
            case 9 -> {
                Categoria categoria = selecionarCategoria();
                if (categoria != null)
                    produtoRepository.findByCategoriaOrderByPrecoDesc(categoria)
                            .forEach(System.out::println);
            }
            case 10 -> {
                Categoria categoria = selecionarCategoria();
                if (categoria != null) {
                    long count = produtoRepository.countByCategoria(categoria);
                    System.out.println("Total de produtos: " + count);
                }
            }
            case 11 -> {
                System.out.print("Preço: ");
                BigDecimal preco = leitura.nextBigDecimal();
                leitura.nextLine();
                long count = produtoRepository.countByPrecoGreaterThan(preco);
                System.out.println("Total: " + count);
            }
            case 12 -> {
                System.out.print("Preço: ");
                BigDecimal preco = leitura.nextBigDecimal();
                leitura.nextLine();
                System.out.print("Termo: ");
                String termo = leitura.nextLine();
                produtoRepository.findByPrecoLessThanOrNomeContainingIgnoreCase(preco, termo)
                        .forEach(System.out::println);
            }
            case 13 -> {
                LocalDate data = lerData("Data (yyyy-MM-dd): ");
                pedidoRepository.findByDataAfter(data).forEach(System.out::println);
            }
            case 14 -> {
                LocalDate data = lerData("Data (yyyy-MM-dd): ");
                pedidoRepository.findByDataBefore(data).forEach(System.out::println);
            }
            case 15 -> {
                LocalDate inicio = lerData("Data início (yyyy-MM-dd): ");
                LocalDate fim = lerData("Data fim (yyyy-MM-dd): ");
                pedidoRepository.findByDataBetween(inicio, fim).forEach(System.out::println);
            }
            case 16 -> produtoRepository.findTop3ByOrderByPrecoDesc().forEach(System.out::println);
            case 17 -> {
                Categoria categoria = selecionarCategoria();
                if (categoria != null)
                    produtoRepository.findTop5ByCategoriaOrderByPrecoAsc(categoria)
                            .forEach(System.out::println);
            }
            case 0 -> {}
            default -> System.out.println("Opção inválida!");
        }
    }

    private LocalDate lerData(String mensagem) {
        System.out.print(mensagem);
        String dataStr = leitura.nextLine();
        return LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    private Categoria selecionarCategoria() {
        List<Categoria> categorias = categoriaRepository.findAll();
        if (categorias.isEmpty()) {
            System.out.println("Nenhuma categoria disponível.");
            return null;
        }

        System.out.println("Selecione uma categoria:");
        categorias.forEach(c -> System.out.println(c.getId() + ": " + c.getNome()));
        Long id = leitura.nextLong();
        leitura.nextLine();
        return categoriaRepository.findById(id).orElse(null);
    }
    private Fornecedor selecionarFornecedor() {
        List<Fornecedor> fornecedores = fornecedorRepository.findAll();
        if (fornecedores.isEmpty()) {
            System.out.println("Nenhum fornecedor disponível.");
            return null;
        }

        System.out.println("Selecione o fornecedor:");
        fornecedores.forEach(f -> System.out.println(f.getId() + ": " + f.getNome()));
        Long id = leitura.nextLong();
        leitura.nextLine();

        return fornecedorRepository.findById(id).orElse(null);
    }


    private void listarPedidos() {
        pedidoRepository.findAll().forEach(System.out::println);
    }

    private void listarProdutos() {
        produtoRepository.findAll().forEach(System.out::println);
    }

    private void listarCategorias() {
        categoriaRepository.findAll().forEach(System.out::println);
    }


    private void adicionarPedido() {
        System.out.print("Data do pedido (yyyy-MM-dd): ");
        LocalDate data = lerData("");

        System.out.print("Data de entrega (yyyy-MM-dd ou vazio): ");
        String entregaInput = leitura.nextLine();
        LocalDate dataEntrega = entregaInput.isBlank() ? null : LocalDate.parse(entregaInput);

        Pedido pedido = new Pedido();
        pedido.setData(data);
        pedido.setDataEntrega(dataEntrega);

        pedidoRepository.save(pedido);
        System.out.println("Pedido salvo com sucesso!");
    }

    private void adicionarProduto() {
        System.out.print("Nome do produto: ");
        String nome = leitura.nextLine();

        System.out.print("Preço: ");
        BigDecimal preco = leitura.nextBigDecimal();
        leitura.nextLine();

        Categoria categoria = selecionarCategoria();
        if (categoria == null) return;

        Fornecedor fornecedor = selecionarFornecedor();
        if (fornecedor == null) {
            return;
        }

        Produto produto = new Produto(nome, preco, categoria, fornecedor);
        produtoRepository.save(produto);
        System.out.println("Produto salvo com sucesso!");
    }

    private void adicionarCategoria() {
        System.out.print("Nome da categoria: ");
        String nome = leitura.nextLine();

        Categoria categoria = new Categoria(nome);
        categoriaRepository.save(categoria);
        System.out.println("Categoria salva com sucesso!");
    }
    private void adicionarFornecedor() {
        System.out.print("Nome do fornecedor: ");
        String nome = leitura.nextLine();

        Fornecedor fornecedor = new Fornecedor(nome);
        fornecedorRepository.save(fornecedor);
        System.out.println("Fornecedor salvo com sucesso!");
    }

}


