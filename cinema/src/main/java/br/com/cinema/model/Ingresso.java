package br.com.cinema.model;

import jakarta.persistence.*;

@Entity
public class Ingresso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double preco;

    @ManyToOne
    private Filme filme;

    @ManyToOne
    private Cliente cliente;


    public Ingresso() {
    }

    
    public Ingresso(Filme filme, double preco, Cliente cliente) {
        this.filme = filme;
        this.preco = preco;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Ingresso para o filme '" + filme.getTitulo() +
                "' - R$ " + preco +
                " (Cliente: " + cliente.getNome() + ")";
    }
}
