package br.com.church.manager.server.server.grupo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grupos")
public class GrupoController {

    @Autowired
    private GrupoService grupoService;

    @PostMapping
    public ResponseEntity<Grupo> create(@RequestBody Grupo grupo) {
        return ResponseEntity.ok(grupoService.create(grupo));
    }
    @GetMapping
    public ResponseEntity<List<Grupo>> getAll() {
        return ResponseEntity.ok(grupoService.getAll());
    }

}