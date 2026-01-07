package com.macelodev.gerenciador_pedidos.model;

import com.fasterxml.jackson.annotation.JsonIgnore; // Importação necessária
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    @JsonIgnore // Impede que a senha apareça nos GETs do Insomnia
    private String senha;

    @Enumerated(EnumType.STRING)
    private Perfil perfil;

    public Usuario() {}

    public Usuario(String email, String senha, Perfil perfil) {
        this.email = email;
        this.senha = senha;
        this.perfil = perfil;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public String getEmail() { return email; }

    public String getSenha() {
        return senha;
    }

    public Perfil getPerfil() { return perfil; }

    @Override
    public String getPassword() { return senha; }

    @Override
    public String getUsername() { return email; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of(new SimpleGrantedAuthority("ROLE_" + perfil.name().toUpperCase()));
    }

    // Métodos de controle do Spring Security
    @Override @JsonIgnore public boolean isAccountNonExpired() { return true; }
    @Override @JsonIgnore public boolean isAccountNonLocked() { return true; }
    @Override @JsonIgnore public boolean isCredentialsNonExpired() { return true; }
    @Override @JsonIgnore public boolean isEnabled() { return true; }

    // Setters necessários para o JPA/Hibernate
    public void setId(Long id) { this.id = id; }
    public void setEmail(String email) { this.email = email; }
    public void setSenha(String senha) { this.senha = senha; }
    public void setPerfil(Perfil perfil) { this.perfil = perfil; }
}

