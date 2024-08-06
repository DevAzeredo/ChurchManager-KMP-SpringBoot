package br.com.church.manager.server.server.pessoa;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    @Query("SELECT p FROM Pessoa p JOIN p.cursos c WHERE c.id = :cursoId")
    List<Pessoa> findPessoasByCursoId(@Param("cursoId") Long id);

    @Query("SELECT p FROM Pessoa p WHERE EXTRACT(MONTH FROM p.dataNascimento) = :mes")
    List<Pessoa> findAniversariantesDoMes(@Param("mes") int mes);

    @Query("SELECT p FROM Pessoa p JOIN p.grupos g WHERE g.id = :grupoId")
    List<Pessoa> findPessoasByGrupoId(@Param("grupoId") Long id);
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM curso_pessoa WHERE pessoa_id = :pessoaId AND curso_id = :cursoId", nativeQuery = true)
    void removeCursoFromPessoa(@Param("pessoaId") Long pessoaId, @Param("cursoId") Long cursoId);
}