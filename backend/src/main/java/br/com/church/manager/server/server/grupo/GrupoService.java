package br.com.church.manager.server.server.grupo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrupoService {

    @Autowired
    private GrupoRepository grupoRepository;

    public Grupo create(Grupo grupo) {
        return grupoRepository.save(grupo);
    }
    public List<Grupo> getAll() {
        return grupoRepository.findAll();
    }
}
