package school.sptech.matchnews_back.model;

import java.time.LocalDateTime;

public class Noticia {
    private Integer id;
    private String titulo;
    private String resumo;
    private String texto;
    private String categoria;
    private String autor;
    private String fonte;
    private LocalDateTime data;

    public Noticia() {
    }

    public Noticia(Integer id, String titulo, String resumo, String texto, String categoria,
                   String urlImagem, String autor, String fonte, LocalDateTime data) {
        this.id = id;
        this.titulo = titulo;
        this.resumo = resumo;
        this.texto = texto;
        this.categoria = categoria;
        this.autor = autor;
        this.fonte = fonte;
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getFonte() {
        return fonte;
    }

    public void setFonte(String fonte) {
        this.fonte = fonte;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
}
