package com.macelodev.gerenciador_pedidos.service;

import com.macelodev.gerenciador_pedidos.DTOs.UsuarioCadastroDTO;
import com.macelodev.gerenciador_pedidos.model.Usuario;
import com.macelodev.gerenciador_pedidos.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder encoder;

    public UsuarioService(UsuarioRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    // Método para o Controller usar no POST /cadastro
    public Usuario cadastrar(UsuarioCadastroDTO dto) {
        if (repository.existsByEmail(dto.email())) {
            throw new RuntimeException("Email já cadastrado");
        }

        Usuario usuario = new Usuario(
                dto.email(),
                encoder.encode(dto.senha()),
                dto.perfil()
        );

        return repository.save(usuario);
    }

    // --- ADICIONE OS MÉTODOS ABAIXO PARA RESOLVER OS ERROS ---

    // Resolve: method listar()
    public List<Usuario> listar() {
        return repository.findAll();
    }

    // Resolve: method salvar(Usuario)
    public Usuario salvar(Usuario usuario) {
        // Se a senha estiver vindo bruta do controller, encripte-a aqui
        if (usuario.getSenha() != null && !usuario.getSenha().startsWith("$2a$")) {
            usuario.setSenha(encoder.encode(usuario.getSenha()));
        }
        return repository.save(usuario);
    }

    // Resolve: method buscarPorId(Long)
    public Usuario buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
}
