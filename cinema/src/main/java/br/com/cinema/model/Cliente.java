package br.com.cinema.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cliente extends Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int idade;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Ingresso> ingressos = new ArrayList<>();

    
    public Cliente() {
        super();
    }

    
    public Cliente(String cpf, String nome, String email, int idade) {
        super(cpf, nome, email);
        this.idade = idade;
    }

    public Long getId() {
        return id;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public List<Ingresso> getIngressos() {
        return ingressos;
    }

    
    public Ingresso comprarIngresso(Filme filme, double preco) {
        if (preco <= 0) {
            throw new IllegalArgumentException("Preço deve ser positivo.");
        }

        Ingresso ingresso = new Ingresso(filme, preco, this);
        ingressos.add(ingresso);
        return ingresso;
    }

    public boolean cancelarIngresso(Ingresso ingresso) {
        return ingressos.remove(ingresso);
    }

    @Override
    public void imprimirInfo() {
        System.out.println("Cliente: " + getNome());
        System.out.println("Idade: " + idade);
        System.out.println("Ingressos comprados: " + ingressos.size());
    }

    @Override
    public String toString() {
        return "Cliente: " + getNome() + " (" + idade + " anos)";
    }
}
