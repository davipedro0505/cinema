package br.com.cinema.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String genero;
    private String duracao;

    @ManyToMany
    @JoinTable(
        name = "filme_ator",
        joinColumns = @JoinColumn(name = "filme_id"),
        inverseJoinColumns = @JoinColumn(name = "ator_id")
    )
    private List<Ator> atores = new ArrayList<>();


    public Filme() {
    }


    public Filme(String titulo, String genero, String duracao) {
        this.titulo = titulo;
        this.genero = genero;
        this.duracao = duracao;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public List<Ator> getAtores() {
        return atores;
    }

    public void setAtores(List<Ator> atores) {
        this.atores = atores;
    }

    public void adicionarAtor(Ator ator) {
        if (!atores.contains(ator)) {
            atores.add(ator);
            ator.getFilmes().add(this);
        }
    }

    public void removerAtor(Ator ator) {
        atores.remove(ator);
        ator.getFilmes().remove(this);
    }

    public void listarAtores() {
        System.out.println("Atores do filme '" + titulo + "':");
        for (Ator ator : atores) {
            System.out.println("- " + ator.getNome() + " (" + ator.getIdade() + " anos)");
        }
    }

    public void imprimirInfo() {
        System.out.println("Título: " + titulo);
        System.out.println("Gênero: " + genero);
        System.out.println("Duração: " + duracao);
        System.out.println("Quantidade de atores: " + atores.size());
    }
}
