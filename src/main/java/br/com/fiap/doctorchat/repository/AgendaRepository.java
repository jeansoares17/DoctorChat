package br.com.fiap.doctorchat.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.doctorchat.models.*;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {

    // @Query("SELECT d FROM Agenda d WHERE d.descricao LIKE %?1%") //JPQL
    Page<Agenda> findByDescricaoContaining(String busca, Pageable pageable);

    // @Query("SELECT d FROM Agenda d ORDER BY d.id LIMIT ?1 OFFSET ?2")
    // List<Agenda> buscarPaginado(int tamanho, int offset);

}