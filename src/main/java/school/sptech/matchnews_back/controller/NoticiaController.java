package school.sptech.matchnews_back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.matchnews_back.model.Noticia;
import school.sptech.matchnews_back.repository.NoticiaRepository;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/noticias")
public class NoticiaController {
    private final NoticiaRepository noticiaRepository;

    public NoticiaController(NoticiaRepository repository) {
        this.noticiaRepository = repository;
    }

    @GetMapping
    public ResponseEntity<List<Noticia>> listarTodos() {
        List<Noticia> noticias = noticiaRepository.buscarTodas();

        return !noticias.isEmpty() ? ResponseEntity.status(200).body(noticias) :
                ResponseEntity.status(204).build();
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Noticia> pegarPorId(@PathVariable Integer id) {
//        try {
//            return ResponseEntity.status(200).body(noticiaRepository.buscarPorId(id));
//        } catch (Exception e) {
//            return ResponseEntity.status(404).build();
//        }
//    }

    @PostMapping
    public ResponseEntity<?> publicar(@RequestBody Noticia noticia) {
        if (noticia.getTitulo() == null || noticia.getTitulo().isBlank())
            return ResponseEntity.status(400).body("Título não pode estar nulo ou vazio");

        if (noticia.getResumo() == null || noticia.getResumo().isBlank())
            return ResponseEntity.status(400).body("Resumo não pode estar nulo ou vazio");

        if (noticia.getTexto() == null || noticia.getTexto().isBlank())
            return ResponseEntity.status(400).body("Texto não pode estar nulo ou vazio");

        if (noticia.getCategoria() == null || noticia.getCategoria().isBlank())
            return ResponseEntity.status(400).body("Categoria não foi selecionada");

        if (noticia.getAutor() == null || noticia.getAutor().isBlank())
            return ResponseEntity.status(400).body("Autor não pode estar nulo ou vazio");

        return ResponseEntity.status(201).body(noticiaRepository.cadastrar(noticia));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        try {
            noticiaRepository.buscarPorId(id);
        } catch (Exception e) {
            return ResponseEntity.status(404).build();
        }

        noticiaRepository.deletarNoticia(id);
        return ResponseEntity.status(204).build();
    }
}
