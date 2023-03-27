package codelab.trabalho1;

import static javax.swing.JOptionPane.*;

public class Sistema {
    Database db = new Database();
    double valorPassagem = 4.4;

    public void iniciar() {
        loopInicial();
    }

    private void loopInicial() {
        int opcao;
        boolean alive = true;
        do {
            opcao = dialogoOpcoes(new String[] { "Administrador", "Usuário", "Finalizar" });
            switch (opcao) {
                case 1:
                    loopAdministrador();
                    break;
                case 2:
                    loopUsuario();
                    break;
                case 3:
                    alive = false;
                    break;

                default:
                    showMessageDialog(null, "Por favor selecione uma opção válida");
                    break;
            }
        } while (alive);
    }

    private void loopAdministrador() {
        int opcao;
        do {
            opcao = dialogoOpcoes(new String[] { "Cadastrar bilhete", "Consultar bilhete", "Listar Bilhete", "Sair" });

            switch (opcao) {
                case 1:
                    Usuario usuarioCadastrado = cadastrarUsuario();
                    if (usuarioCadastrado != null) {
                        cadastrarBilhete(usuarioCadastrado);
                    }
                    break;
                case 2:
                    String message = "Bilhete único não encontrado!";
                    Usuario usuarioEncontrado = encontrarUsuario();
                    if (usuarioEncontrado != null) {
                        BilheteUnico bilheteUnico = encontrarBilheteUnico(usuarioEncontrado);
                        message = bilheteUnico.toString();
                    }
                    showMessageDialog(null, message);
                    break;
                case 3:
                    showMessageDialog(null, listarBilhetesString());
                    break;
                case 4:
                    return;

                default:
                    showMessageDialog(null, "Digite uma opção válida");
                    break;
            }
        } while (true);
    }

    private String listarBilhetesString() {
        BilheteUnico[] bilhetes = db.listarBilhetes();
        String msg = "";
        for (int i = 0; i < bilhetes.length; i++) {
            msg += bilhetes[i] + "\n";
        }

        return msg;
    }

    private Usuario encontrarUsuario() {
        String cpf = showInputDialog(null, "Digite o cpf do usuário");

        return db.encontrarUsuario(cpf);
    }

    private BilheteUnico encontrarBilheteUnico(Usuario usuario) {
        return db.encontrarBilheteUnico(usuario);
    }

    private void cadastrarBilhete(Usuario usuario) {

        double valorDaTarifa = 4.4;

        if (usuario.getTipoDeTarifa().equals("estudante") || usuario.getTipoDeTarifa().equals("professor")) {
            valorDaTarifa = valorDaTarifa * 0.5;
        }

        BilheteUnico resultado = null;
        do {
            resultado = db.inserirBilhete(valorDaTarifa, usuario);
        } while (resultado == null);
    }

    private Usuario cadastrarUsuario() {
        String nome = showInputDialog(null, "Digite o nome do usuário dono do bilhete único");

        String cpf = showInputDialog(null, "Digite o cpf do usuário dono do bilhete único");

        String tipoDeTarifa = dialogoOpcoesValor(new String[] { "Estudante", "Professor", "Normal" },
                new String[] { "estudante", "professor", "normal" });

        Usuario newUsuario = db.inserirUsuario(new Usuario(nome, cpf, tipoDeTarifa));
        if (newUsuario != null) {
            showMessageDialog(null, "Usuário \"" + nome + "\" foi inserido!");

        } else {
            showMessageDialog(null, "Erro inserindo usuário \"" + nome + "\"!");
        }

        return newUsuario;
    }

    private void loopUsuario() {
        int opcao;
        do {
            opcao = dialogoOpcoes(new String[] { "Consultar saldo", "Carregar saldo do bilhete", "Passar na catraca", "Sair" });

            String message = "";
            Usuario usuarioEncontrado = null;

            switch (opcao) {
                case 1:
                    message = "Usuário não encontrado!";
                    usuarioEncontrado = encontrarUsuario();
                    if (usuarioEncontrado != null) {
                        BilheteUnico bilheteUnico = encontrarBilheteUnico(usuarioEncontrado);
                        message = String.format("Saldo atual: R$%.2f", bilheteUnico.getSaldo());
                    }
                    showMessageDialog(null, message);
                    break;
                case 2:
                    usuarioEncontrado = encontrarUsuario();
                    if (usuarioEncontrado != null) {
                        BilheteUnico bilheteUnico = encontrarBilheteUnico(usuarioEncontrado);
                        String valorCarregarString = showInputDialog(null,
                                "Insira o valor de recarga do bilhete único: " + usuarioEncontrado.getNome())
                                .replace(",", ".");

                        double valorCarregar = Double.parseDouble(valorCarregarString);
                        if (valorCarregar <= 0) {
                            showMessageDialog(null, "Valor inválido!");
                            break;
                        }

                        bilheteUnico.carregarBilhete(valorCarregar);
                    } else {
                        showMessageDialog(null, "Usuário não encontrado!");
                    }
                    break;
                case 3:
                    usuarioEncontrado = encontrarUsuario();
                    if (usuarioEncontrado != null) {
                        BilheteUnico bilheteUnico = encontrarBilheteUnico(usuarioEncontrado);

                        if (bilheteUnico.passarNaCatraca()) {
                            showMessageDialog(null, "Usuário passou na catraca!");
                        } else {
                            showMessageDialog(null, "Usuário não tem saldo o suficiente para passar na catraca!");
                        }
                    } else {
                        showMessageDialog(null, "Usuário não encontrado!");
                    }
                    break;
                case 4:
                    return;

                default:
                    showMessageDialog(null, "Digite uma opção válida");
                    break;
            }
        } while (true);
    }

    private int dialogoOpcoes(String[] opcoes) {
        String message = "";

        for (int i = 0; i < opcoes.length; i++) {
            message += (i + 1) + " - " + opcoes[i] + "\n";
        }

        return Integer.parseInt(showInputDialog(null, message));
    }

    private String dialogoOpcoesValor(String[] opcoes, String[] valores) {
        int opcao = dialogoOpcoes(opcoes);

        return valores[opcao - 1];
    }
}
