CREATE DATABASE IF NOT EXISTS matchnews;
USE matchnews;

CREATE TABLE IF NOT EXISTS noticias (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    resumo VARCHAR(200) NOT NULL,
    texto VARCHAR(300) NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    autor VARCHAR(100) NOT NULL,
    fonte VARCHAR(150)
    );

INSERT INTO noticias (titulo, resumo, texto, categoria, autor) VALUES (
    "Teste",
    "Teste resumo",
    "Teste texto",
    "Teste categoria",
    "asasasasasas"
);