package school.sptech.matchnews_back.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class Noticia {
    private Integer id;
    private String titulo;
    private String resumo;
    private String texto;
    private String categoria;
    private String autor;
    private String fonte;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataPublicacao;

    public Noticia() {
    }

    public Noticia(Integer id, String titulo, String resumo, String texto, String categoria,
                   String autor, String fonte, LocalDateTime dataPublicacao) {
        this.id = id;
        this.titulo = titulo;
        this.resumo = resumo;
        this.texto = texto;
        this.categoria = categoria;
        this.autor = autor;
        this.fonte = fonte;
        this.dataPublicacao = dataPublicacao;
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

    public LocalDateTime getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDateTime dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }
}
