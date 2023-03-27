package codelab.trabalho1;

import java.util.Random;

import lista.dupla.ListaDuplaGenerica;
import lista.dupla.NodoGenerico;

public class Database {
    private ListaDuplaGenerica<BilheteUnico> bilhetes = new ListaDuplaGenerica<BilheteUnico>();
    private ListaDuplaGenerica<Usuario> usuarios = new ListaDuplaGenerica<Usuario>();

    public BilheteUnico inserirBilhete(double valorDaTarifa, Usuario usuario) {
        BilheteUnico newBilhete = new BilheteUnico(gerarNumeroBilhete(), 0.0, valorDaTarifa, usuario);

        if (this.bilhetes.pesquisar(newBilhete) != null) {
            return null;
        }

        this.bilhetes.inserirInicio(newBilhete);
        return newBilhete;
    }

    private int gerarNumeroBilhete() {
        Random r = new Random();
        int numero = r.nextInt(4900) + 100;
        return numero;
    }

    public Usuario inserirUsuario(Usuario newUsuario) {
        if (this.usuarios.pesquisar(newUsuario) != null) {
            return null;
        }

        this.usuarios.inserirInicio(newUsuario);
        return newUsuario;
    }

    public Usuario encontrarUsuario(String cpf) {
        NodoGenerico<Usuario> resultado = usuarios.pesquisar(new Usuario("", cpf, ""));
        if (resultado != null) {
            return resultado.getDado();
        } else {
            return null;
        }
    }

    public BilheteUnico encontrarBilheteUnico(Usuario usuario) {
        NodoGenerico<BilheteUnico> resultado = bilhetes.pesquisar(new BilheteUnico(0, 0, 0, usuario));
        if (resultado == null) {
            return null;
        } else {
            return resultado.getDado();
        }
    }

    public BilheteUnico[] listarBilhetes() {
        NodoGenerico<BilheteUnico> curr = bilhetes.getInicio();
        BilheteUnico[] output = new BilheteUnico[bilhetes.getTamanho()];
        int currIndex = 0;

        do {
            output[currIndex] = curr.getDado();
            curr = curr.getDireita();
            currIndex++;
        } while (curr != null);

        return output;
    }
}
