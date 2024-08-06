package br.com.church.manager.server.server.curso;

import br.com.church.manager.server.server.pessoa.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping
    public ResponseEntity<Curso> createCurso(@RequestBody Curso curso) {
        return ResponseEntity.ok(cursoService.createCurso(curso));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> getCursoById(@PathVariable Long id) {
        return cursoService.getCursoById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    @GetMapping
    public ResponseEntity<List<Curso>> getAllCursos(@RequestParam(required = false) Integer pessoaId) {
        if (pessoaId != null) {
            return ResponseEntity.ok(cursoService.getPessoasByCursoId(pessoaId));
        }
        return ResponseEntity.ok(cursoService.getAllCursos());
    }
    @GetMapping("/proximos")
    public ResponseEntity<List<Curso>> getNextCursos(@RequestParam(defaultValue = "5") int limit) {
        return ResponseEntity.ok(cursoService.getProximosCursos(limit));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> updateCurso(@PathVariable Long id, @RequestBody Curso cursoDetails) {
        return ResponseEntity.ok(cursoService.updateCurso(id, cursoDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurso(@PathVariable Long id) {
        cursoService.deleteCurso(id);
        return ResponseEntity.noContent().build();
    }
}
