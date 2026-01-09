package com.conetdev.mydailydatabase.service.impl;

import com.conetdev.mydailydatabase.model.Usuarios;
import com.conetdev.mydailydatabase.repository.UsuarioRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public LoginServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuarios usuario = usuarioRepository.findByIdentidadUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return org.springframework.security.core.userdetails.User.builder()
                .username(usuario.getIdentidadUsuario())
                .password(usuario.getPasswordUsuario()) // password stored as BCrypt hash
                .authorities(usuario.getRoles().stream()
                        // .map(r -> "ROLE_" + r.getNombreLargo())
                        .map(r -> r.getNombreLargo())
                        .toArray(String[]::new))
                .accountLocked(!usuario.isActivo())
                .build();
    }
}