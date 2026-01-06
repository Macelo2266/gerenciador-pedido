package com.macelodev.gerenciador_pedidos.service;

import com.macelodev.gerenciador_pedidos.DTOs.UsuarioCadastroDTO;
import com.macelodev.gerenciador_pedidos.model.Perfil;
import com.macelodev.gerenciador_pedidos.model.Usuario;
import com.macelodev.gerenciador_pedidos.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UsuarioService(
            UsuarioRepository repository,
            BCryptPasswordEncoder passwordEncoder
    ) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public void cadastrar(UsuarioCadastroDTO dto) {
        criarCliente(dto.email(), dto.senha());
    }

    public Usuario criarCliente(String email, String senha) {
        if (repository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email já cadastrado");
        }

        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setSenha(passwordEncoder.encode(senha));
        usuario.setPerfil(Perfil.ROLE_CLIENTE);

        return repository.save(usuario);
    }
}
