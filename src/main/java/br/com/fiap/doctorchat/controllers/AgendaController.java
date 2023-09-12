package br.com.fiap.doctorchat.controllers;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.doctorchat.exceptions.RestNotFoundException;
import br.com.fiap.doctorchat.models.Agenda;
import br.com.fiap.doctorchat.repository.AgendaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/agenda")
@Slf4j
@SecurityRequirement(name = "bearer-key")
@Tag(name = "agenda")
public class AgendaController {
    @Autowired
    AgendaRepository agendaRepository;
    @Autowired
    PagedResourcesAssembler<Object> assembler;

    @GetMapping
    public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String busca,
            @ParameterObject @PageableDefault(size = 5) Pageable pageable) {
        Page<Agenda> agenda = (busca == null) ? agendaRepository.findAll(pageable)
                : agendaRepository.findByDescricaoContaining(busca, pageable);

        return assembler.toModel(agenda.map(Agenda::toEntityModel));
    }

    @PostMapping
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Consulta cadastrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Os campos enviados são inválidos")
    })
    public ResponseEntity<Object> create(@RequestBody @Valid Agenda agenda) {
        log.info("cadastrando consulta " + agenda);
        agendaRepository.save(agenda);
        return ResponseEntity.status(HttpStatus.CREATED).body(agenda.toEntityModel());
    }

    @GetMapping("{id}")
    @Operation(summary = "Detalhar agenda", description = "Endpoint que recebe um id e retorna os dados da agenda. O id deve ser ..."

    )
    public EntityModel<Agenda> show(@PathVariable Long id) {
        log.info("consultando agenda " + id);
        var agenda = agendaRepository.findById(id)
                .orElseThrow(() -> new RestNotFoundException("consulta não encontrada"));

        return agenda.toEntityModel();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Agenda> destroy(@PathVariable Long id) {
        log.info("apagando consulta " + id);
        var agenda = agendaRepository.findById(id)
                .orElseThrow(() -> new RestNotFoundException("Erro ao apagar, consulta não encontrada"));

        agendaRepository.delete(agenda);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public EntityModel<Agenda> update(@PathVariable Long id, @RequestBody @Valid Agenda agenda) {
        log.info("atualizando consulta " + id);
        agendaRepository.findById(id)
                .orElseThrow(() -> new RestNotFoundException("Erro ao apagar, consulta não encontrada"));

        agenda.setId(id);
        agendaRepository.save(agenda);

        return agenda.toEntityModel();
    }

}