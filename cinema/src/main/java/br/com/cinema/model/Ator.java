package br.com.cinema.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private int idade;

    @ManyToMany(mappedBy = "atores")
    private List<Filme> filmes = new ArrayList<>();

    public Ator() {
        // construtor vazio
    }

    public Ator(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public List<Filme> getFilmes() {
        return filmes;
    }

    public void setFilmes(List<Filme> filmes) {
        this.filmes = filmes;
    }

    public void adicionarFilme(Filme filme) {
        if (!filmes.contains(filme)) {
            filmes.add(filme);
        }
    }

    public void mostrarFilmes() {
        System.out.println("Ator: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Filmes de " + nome + ":");

        if (filmes.isEmpty()) {
            System.out.println("Nenhum filme cadastrado para este ator.");
        } else {
            for (Filme filme : filmes) {
                System.out.println("- " + filme.getTitulo());
            }
        }
    }
}
