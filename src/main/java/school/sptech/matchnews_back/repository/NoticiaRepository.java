package school.sptech.matchnews_back.repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import school.sptech.matchnews_back.model.Noticia;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class NoticiaRepository {
    private final JdbcTemplate jdbcTemplate;

    public NoticiaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Noticia cadastrar(Noticia novaNoticia) {
        String sql = "INSERT INTO noticias (titulo, resumo, texto,categoria,autor,fonte) " +
                "VALUES (?,?,?,?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, novaNoticia.getTitulo());
            ps.setString(2, novaNoticia.getResumo());
            ps.setString(3, novaNoticia.getTexto());
            ps.setString(4, novaNoticia.getCategoria());
            ps.setString(5, novaNoticia.getAutor());
            ps.setString(6, novaNoticia.getFonte());
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        if (key!=null) {
        novaNoticia.setId(key.intValue());
        }

        return novaNoticia;
    }

    public List<Noticia> buscarTodas() {
        String sql = "SELECT * FROM noticias";

        return jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(Noticia.class));
    }

//    public Boolean hasIgual(Noticia noticia) {
//        String sql = "SELECT COUNT(*) FROM noticias WHERE lower(titulo) = ?";
//
//        Integer quantidade = jdbcTemplate.queryForObject(sql,
//                new BeanPropertyRowMapper<>(Integer.class), noticia.getTitulo());
//
//        return quantidade >= 1;
//    }

//    public Noticia buscarPorId(Integer id) {
//        String sql = "SELECT * FROM noticias WHERE id = ?";
//
//        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Noticia.class), id);
//    }
}
