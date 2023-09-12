package br.com.fiap.doctorchat.models;

import java.time.LocalDate;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;

import br.com.fiap.doctorchat.controllers.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 1, max = 90)
    private String nomeDoutor;

    @NotNull
    private LocalDate data;

    @NotBlank
    @Size(min = 5, max = 255)
    private String descricao;

    @ManyToOne
    private Usuario usuario;

    public EntityModel<Agenda> toEntityModel() {
        return EntityModel.of(
                this,
                linkTo(methodOn(AgendaController.class).show(id)).withSelfRel(),
                linkTo(methodOn(AgendaController.class).destroy(id)).withRel("delete"),
                linkTo(methodOn(AgendaController.class).index(null, Pageable.unpaged())).withRel("all"),
                linkTo(methodOn(UsuarioController.class).show(this.getUsuario().getId())).withRel("usuario"));
    }

}