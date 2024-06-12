package com.micael.spring.app.services.administracionDeUsuarios.usuarioServicios;


import com.micael.spring.app.entities.administracionDeUsuarios.Usuario;
import com.micael.spring.app.repositories.administracionDeUsuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JpaUsuarioDetailsService implements UserDetailsService {
    @Autowired
    private UsuarioRepository repository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOptional= repository.findByEmail(username);
        if(usuarioOptional.isEmpty()){
            throw  new UsernameNotFoundException(String.format("Email %s no existe en el sistema",username));
        }
        Usuario usuario=usuarioOptional.orElseThrow();
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(usuario.getRol().getCargo()));

        return new org.springframework.security.core.userdetails.User(
                usuario.getEmail(),
                usuario.getPassword(),
                true,
                true,
                true,
                true,
                authorities);
    }
}
