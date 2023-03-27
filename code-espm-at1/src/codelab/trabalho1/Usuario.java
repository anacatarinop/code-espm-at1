package codelab.trabalho1;

import codelab.trabalho1.Usuario;

public class Usuario {
    private String nome;
    private String cpf;
    private String tipoDeTarifa;

    public Usuario(String nome, String cpf, String tipoDeTarifa) {
        this.nome = nome;
        this.cpf = cpf;
        this.tipoDeTarifa = tipoDeTarifa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTipoDeTarifa() {
        return tipoDeTarifa;
    }

    public void setTipoDeTarifa(String tipoDeTarifa) {
        this.tipoDeTarifa = tipoDeTarifa;
    }

    @Override
    public String toString() {
        return "Usuario [nome=" + nome + ", cpf=" + cpf + "]";
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Usuario) && ((Usuario) obj).cpf.equals(this.cpf);
    }
}