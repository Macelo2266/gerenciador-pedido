package com.macelodev.gerenciador_pedidos.service;

import com.macelodev.gerenciador_pedidos.model.Fornecedor;
import com.macelodev.gerenciador_pedidos.repository.FornecedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FornecedorService {

    private final FornecedorRepository repository;

    public FornecedorService(FornecedorRepository repository) {
        this.repository = repository;
    }

    public Fornecedor criar(String nome, String email, String telefone) {
        if (repository.existsByNomeIgnoreCase(nome)) {
            throw new RuntimeException("Fornecedor já existe");
        }

        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(nome);
        fornecedor.setEmail(email);
        fornecedor.setTelefone(telefone);

        return repository.save(fornecedor);
    }

    public Fornecedor salvar(Fornecedor fornecedor) {
        return repository.save(fornecedor);
    }

    public List<Fornecedor> listar() {
        return repository.findAll();
    }

    public Fornecedor buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));
    }
    public void remover(Long id) {
        repository.deleteById(id);
    }
}

