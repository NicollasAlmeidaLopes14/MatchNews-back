package school.sptech.matchnews_back.repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import school.sptech.matchnews_back.model.Noticia;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class NoticiaRepository {
    private final JdbcTemplate jdbcTemplate;

    public NoticiaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Noticia cadastrar(Noticia novaNoticia) {
        LocalDateTime dataAtual = LocalDateTime.now();

        novaNoticia.setDataPublicacao(dataAtual);

        String sql = "INSERT INTO noticias (titulo, resumo, texto,categoria,autor,fonte, " +
                "dataPublicacao, dataAtualizacao) " +
                "VALUES (?,?,?,?,?,?, ?, ?)";

        novaNoticia.setDataAtualizacao(null);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, novaNoticia.getTitulo());
            ps.setString(2, novaNoticia.getResumo());
            ps.setString(3, novaNoticia.getTexto());
            ps.setString(4, novaNoticia.getCategoria());
            ps.setString(5, novaNoticia.getAutor());
            ps.setString(6, novaNoticia.getFonte());
            ps.setObject(7, novaNoticia.getDataPublicacao());
            ps.setObject(8, novaNoticia.getDataAtualizacao());
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        if (key != null) {
            novaNoticia.setId(key.intValue());
        }

        return novaNoticia;
    }

    public List<Noticia> buscarTodas() {
        String sql = "SELECT * FROM noticias ORDER BY dataPublicacao DESC";


        return jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(Noticia.class));
    }

    public Noticia editarNoticia(Integer id, Noticia noticiaAtualizada) {
        LocalDateTime dataEdicao = LocalDateTime.now();
        noticiaAtualizada.setDataAtualizacao(dataEdicao);

        String sql = "UPDATE noticias SET titulo = ?, resumo = ?, texto = ?, categoria = ?, autor" +
                " = ?, fonte = ?, dataAtualizacao = ? WHERE id = ?";

        noticiaAtualizada.setId(id);

        jdbcTemplate.update(sql,
                noticiaAtualizada.getTitulo(),
                noticiaAtualizada.getResumo(),
                noticiaAtualizada.getTexto(),
                noticiaAtualizada.getCategoria(),
                noticiaAtualizada.getAutor(),
                noticiaAtualizada.getFonte(),
                noticiaAtualizada.getDataAtualizacao(),
                id
        );

        return noticiaAtualizada;
    }

    public void deletarNoticia(Integer id) {
        String sql = "DELETE FROM noticias WHERE id = ?";

        jdbcTemplate.update(sql, id);
    }

    public Noticia buscarPorId(Integer id) {
        String sql = "SELECT * FROM noticias WHERE id = ?";

        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Noticia.class), id);
    }

    public Boolean hasIgual(Noticia noticia) {
        String sql = "SELECT COUNT(*) FROM noticias WHERE lower(titulo) = ? AND lower(resumo) = ?" +
                " AND lower(texto) = ? AND lower(categoria) = ? AND lower(autor) = ? AND lower" +
                "(fonte) = ?";

        Integer quantidade = jdbcTemplate.queryForObject(sql,
                Integer.class,
                noticia.getTitulo().toLowerCase(),
                noticia.getResumo().toLowerCase(),
                noticia.getTexto().toLowerCase(),
                noticia.getCategoria().toLowerCase(),
                noticia.getAutor().toLowerCase(),
                noticia.getFonte().toLowerCase());

        return quantidade >= 1;
    }
}
