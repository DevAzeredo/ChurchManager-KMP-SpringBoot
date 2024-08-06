package br.com.church.manager.server.server.curso;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    @Query("SELECT c FROM Curso c JOIN c.pessoas p WHERE p.id = :pessoaId")
    List<Curso> findCursosByPessoaId(@Param("pessoaId") Integer pessoaId);

}
