package br.com.fiap.doctorchat.controllers;

import java.util.List;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.doctorchat.exceptions.RestNotFoundException;
import br.com.fiap.doctorchat.models.Usuario;
import br.com.fiap.doctorchat.models.Credencial;
import br.com.fiap.doctorchat.repository.UsuarioRepository;
import br.com.fiap.doctorchat.service.TokenJwtService;
import jakarta.validation.Valid;

@RestController
@RequestMapping
public class UsuarioController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    UsuarioRepository repository;

    @Autowired
    AuthenticationManager manager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    TokenJwtService tokenJwtService;

    @PostMapping("/api/cadastrar")
    public ResponseEntity<Usuario> registrar(@RequestBody @Valid Usuario usuario) {
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        repository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @PostMapping("/api/login")
    public ResponseEntity<Object> login(@RequestBody @Valid Credencial credencial) {
        manager.authenticate(credencial.toAuthentication());
        var token = tokenJwtService.generateToken(credencial);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/api/usuarios")
    public List<Usuario> index() {
        return repository.findAll();
    }

    @GetMapping("/api/usuarios/{id}")
    public ResponseEntity<Usuario> show(@PathVariable Long id) {
        log.info("Detalhando usuarios {}", id);
        return ResponseEntity.ok(getUsuario(id));
    }

    @PutMapping("/api/contas/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody @Valid Usuario usuario) {
        log.info("Atualizando usuario {}", id);
        getUsuario(id);
        usuario.setId(id);
        repository.save(usuario);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/api/contas/{id}")
    public ResponseEntity<Usuario> destroy(@PathVariable Long id) {
        log.info("Apagando usuario {}", id);
        var conta = getUsuario(id);
        repository.delete(conta);
        return ResponseEntity.noContent().build();
    }

    private Usuario getUsuario(Long id) {
        return repository.findById(id).orElseThrow(() -> new RestNotFoundException("Usuario não encontrada"));
    }
}