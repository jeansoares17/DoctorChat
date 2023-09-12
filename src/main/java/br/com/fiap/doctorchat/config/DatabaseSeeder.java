package br.com.fiap.doctorchat.config;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.doctorchat.models.Usuario;
import br.com.fiap.doctorchat.models.Agenda;
import br.com.fiap.doctorchat.repository.UsuarioRepository;
import br.com.fiap.doctorchat.repository.AgendaRepository;

@Configuration
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    UsuarioRepository usuariorepository;

    @Autowired
    AgendaRepository agendarepository;

    @Override
    public void run(String... args) throws Exception {
        Usuario c1 = new Usuario(1L, "Mateus", "rm94075@fiap.com.br", "123312");
        Usuario c2 = new Usuario(2L, "Amanda", "amanda@fiap.com.br", "12345");
        Usuario c3 = new Usuario(3L, "Jean", "jean5@fiap.com.br", "54321");
        Usuario c4 = new Usuario(4L, "Kaio", "kaio@fiap.com.br", "00000");
        Usuario c5 = new Usuario(5L, "João", "joao@fiap.com.br", "98765");
        usuariorepository.saveAll(List.of(c1, c2, c3, c4, c5));

        agendarepository.saveAll(List.of(
                Agenda.builder().nomeDoutor("joao").data(LocalDate.now()).descricao("Exame de Rotina").usuario(c1)
                        .build()));
    }
}