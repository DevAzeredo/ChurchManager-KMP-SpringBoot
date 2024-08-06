package br.com.church.manager.server.server.pessoa;

import br.com.church.manager.server.server.grupo.Grupo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<Pessoa> createPessoa(@RequestBody Pessoa pessoa) {
        return ResponseEntity.ok(pessoaService.createPessoa(pessoa));
    }

    @GetMapping("/aniversariantes")
    public ResponseEntity<List<Pessoa>> getAniversariantesDoMes(@RequestParam int mes) {
        return ResponseEntity.ok(pessoaService.getAniversariantesDoMes(mes));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> getPessoaById(@PathVariable Long id) {
        return pessoaService.getPessoaById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> getAllPessoas(@RequestParam(required = false) Long cursoId, @RequestParam(required = false) Long grupoId) {
        if (cursoId != null) {
            return ResponseEntity.ok(pessoaService.getPessoasByCursoId(cursoId));
        }
        if (grupoId != null) {
            return ResponseEntity.ok(pessoaService.getPessoasByGrupoId(grupoId));
        }
        return ResponseEntity.ok(pessoaService.getAllPessoas());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> updatePessoa(@PathVariable Long id, @RequestBody Pessoa pessoaDetails) {
        return ResponseEntity.ok(pessoaService.updatePessoa(id, pessoaDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePessoa(@PathVariable Long id) {
        pessoaService.deletePessoa(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{pessoaId}/addCurso/{cursoId}")
    public ResponseEntity<Pessoa> addCursoToPessoa(@PathVariable Long pessoaId, @PathVariable Long cursoId) {
        return ResponseEntity.ok(pessoaService.addCursoToPessoa(pessoaId, cursoId));
    }

    @PutMapping("/{pessoaId}/removeCurso/{cursoId}")
    public ResponseEntity<Void> removeCursoFromPessoa(@PathVariable Long pessoaId, @PathVariable Long cursoId) {
        pessoaService.removeCursoFromPessoa(pessoaId, cursoId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{pessoaId}/addGrupo/{grupoId}")
    public ResponseEntity<Pessoa> addGrupoToPessoa(@PathVariable Long pessoaId, @PathVariable Long grupoId) {
        return ResponseEntity.ok(pessoaService.addGrupoToPessoa(pessoaId, grupoId));
    }

    @PutMapping("/{pessoaId}/removeGrupo/{grupoId}")
    public ResponseEntity<Pessoa> removeGrupoFromPessoa(@PathVariable Long pessoaId, @PathVariable Long grupoId) {
        return ResponseEntity.ok(pessoaService.removeGrupoFromPessoa(pessoaId, grupoId));
    }
}