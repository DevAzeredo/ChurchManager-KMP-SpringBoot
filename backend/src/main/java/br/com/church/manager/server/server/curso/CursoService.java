package br.com.church.manager.server.server.curso;

import br.com.church.manager.server.server.pessoa.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public Curso createCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    public List<Curso> getPessoasByCursoId(Integer id) {
        return cursoRepository.findCursosByPessoaId(id);
    }

    public List<Curso> getProximosCursos(int limit) {
        LocalDate today = LocalDate.now();
        return cursoRepository.findAll().stream()
                .filter(curso -> curso.getData().isAfter(today))
                .sorted(Comparator.comparing(Curso::getData))
                .limit(limit)
                .collect(Collectors.toList());
    }
    public Optional<Curso> getCursoById(Long id) {
        return cursoRepository.findById(id);
    }

    public List<Curso> getAllCursos() {
        return cursoRepository.findAll();
    }

    public Curso updateCurso(Long id, Curso cursoDetails) {
        Curso curso = cursoRepository.findById(id).orElseThrow(() -> new RuntimeException("Curso not found"));
        curso.setNome(cursoDetails.getNome());
        return cursoRepository.save(curso);
    }


    public void deleteCurso(Long id) {
        cursoRepository.deleteById(id);
    }
}