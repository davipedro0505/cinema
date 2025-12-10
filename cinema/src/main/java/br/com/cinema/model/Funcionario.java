package br.com.cinema.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Funcionario extends Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cargo;
    private float salario;

  
    public Funcionario() {
        super();
    }


    public Funcionario(String cpf, String nome, String email, String cargo, float salario) {
        super(cpf, nome, email);
        this.cargo = cargo;
        this.salario = salario;
    }

    public Long getId() {
        return id;
    }

    
    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public void addAumento(float valor) {
        this.salario += valor;
    }

    @Override
    public void imprimirInfo() {
        System.out.println("=== Funcionário ===");
        System.out.println("Nome: " + getNome());
        System.out.println("CPF: " + getCpf());
        System.out.println("Email: " + getEmail());
        System.out.println("Cargo: " + cargo);
        System.out.println("Salário: R$ " + salario);
    }
}
