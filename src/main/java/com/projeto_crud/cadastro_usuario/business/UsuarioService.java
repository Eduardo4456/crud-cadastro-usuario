package com.projeto_crud.cadastro_usuario.business;

import com.projeto_crud.cadastro_usuario.infrastructure.entitys.Usuario;
import com.projeto_crud.cadastro_usuario.infrastructure.entitys.repositorys.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public void salvarUsuario(Usuario usuario) {
        repository.saveAndFlush(usuario);
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        return repository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Email não encontrado!")
        );
    }

    public void deletarUsuarioPorEmail(String email) {
        repository.deleteByEmail(email);
    }

    public void atualizarUsuarioPorEmail(String email, Usuario usuario) {
        Usuario usuarioEntity = buscarUsuarioPorEmail(email);
        Usuario usuarioAtualizado = usuario.builder()
                .email(email)
                .nome(usuario.getNome() != null ? usuario.getNome() : usuarioEntity.getNome())
                .id(usuarioEntity.getId())
                .build();

        repository.saveAndFlush(usuarioAtualizado);
    }
}