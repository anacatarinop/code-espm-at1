package codelab.trabalho1;


public class BilheteUnico {
    private int numero;
    private double saldo;
    private double valorDaTarifa;
    private Usuario usuario;

    public BilheteUnico(int numero, double saldo, double valorDaTarifa, Usuario usuario) {
        this.numero = numero;
        this.saldo = saldo;
        this.valorDaTarifa = valorDaTarifa;
        this.usuario = usuario;
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public void carregarBilhete(double valorRecarga) {
        if (valorRecarga <= 0) {
            return;
        }

        this.saldo += valorRecarga;
    }

    public double getValorDaTarifa() {
        return valorDaTarifa;
    }

    public void setValorDaTarifa(double valorDaTarifa) {
        this.valorDaTarifa = valorDaTarifa;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean passarNaCatraca() {
        if (valorDaTarifa > saldo) {
            return false;
        } else {
            this.saldo -= valorDaTarifa;

            return true;
        }
    }

    @Override
    public String toString() {
        return "BilheteUnico [numero=" + numero + ", saldo=" + saldo + ", usuario=" + usuario + "]";
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof BilheteUnico) && ((BilheteUnico) obj).numero == this.numero
                || ((BilheteUnico) obj).usuario == this.usuario;
    }

}
