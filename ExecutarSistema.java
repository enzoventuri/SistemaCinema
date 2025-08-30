import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ExecutarSistema {

    private static final Scanner S = new Scanner(System.in);
    private static final ArrayList<Cinema> FILMES = new ArrayList<>();

    public static void main(String[] args) {
        boolean continuar = true;

        System.out.println("===========================");
        System.out.println("      SISTEMA CINEMA");
        System.out.println("===========================");

        while (continuar) {
            try {
                Thread.sleep(500);

                System.out.println("\n\n1 - Adicionar filme");
                System.out.println("2 - Comprar ingresso");
                System.out.println("3 - Verificar lugares disponíveis");
                System.out.println("4 - Informações da sessão");
                System.out.println("5 - Ingressos vendidos");
                System.out.println("6 - Sair");
                System.out.println("=======================================");
                System.out.print("SELECIONE UMA DAS OPÇÕES ACIMA: ");
                int opcao = S.nextInt();

                S.nextLine();

                limparTela();

                switch (opcao) {
                    case 1:
                        adicionarFilme();
                        break;
                    case 2:
                        comprarIngresso();
                        break;
                    case 3:
                        lugaresDisponiveis();
                        break;
                    case 4:
                        informacoesSessao();
                        break;
                    case 5:
                        ingressosVendidos();
                        break;
                    case 6:
                        System.out.println("Muito obrigado por utilizar o sistema!");
                        continuar = false;
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente:");
                }

            } catch (InputMismatchException | InterruptedException e) {
                System.out.println("Opção inválida! Tente novamente:");
                S.nextLine();
            }
        }
    }

    private static void ingressosVendidos() {
        if (FILMES.isEmpty()) {
            System.out.println("Não tem filmes que foram comprados!");
            return;
        }

        for (Cinema filmes : FILMES) {
            System.out.println("Filme: " + filmes.getNomeFilme());
            System.out.println("Ingressos vendido(s): " + filmes.getIngressosVendido().size());
        }
    }

    private static void lugaresDisponiveis() {
        System.out.print("Digite o nome do filme que deseja ver os lugares disponíveis: ");
        String nomeFilme = S.nextLine();

        for (Cinema filmes : FILMES) {
            if (filmes.getNomeFilme().equalsIgnoreCase(nomeFilme)) {
                int capacidade = filmes.getCapacidade();

                String[][] e = new String[capacidade][capacidade];

                filmes.lugaresDisponiveis();
                return;
            }
        }

        System.out.println("Filme não cadastrado no sistema!");
    }

    private static void adicionarFilme() {
        System.out.print("Digite o nome do filme: ");
        String nomeFilme = S.nextLine();

        for (Cinema filmes : FILMES) {
            if (filmes.getNomeFilme().equalsIgnoreCase(nomeFilme)) {
                System.out.println("Nome já cadastrado no sistema!");
                return;
            }
        }

        System.out.print("Digite a sala do filme: ");
        String sala = S.nextLine();

        System.out.print("Digite o horário do filme: ");
        String horarioSessao = S.nextLine();

        System.out.print("Digite a capacidade do filme: ");
        int capacidade = S.nextInt();

        System.out.print("Digite o preço do filme: ");
        double preco = S.nextDouble();

        Cinema cinema = new Cinema(nomeFilme, sala, horarioSessao, capacidade, preco);

        FILMES.add(cinema);

        S.nextLine();
    }

    private static void comprarIngresso() {
        System.out.println("Digite o nome do filme:");
        String nomeFilme = S.nextLine();

        for (Cinema filmes : FILMES) {
            if (filmes.getNomeFilme().equalsIgnoreCase(nomeFilme)) {
                filmes.lugaresDisponiveis();

                System.out.println("Digite a linha que você deseja (horizontal)");
                int linha = S.nextInt() - 1;

                System.out.println("Digite a coluna que você deseja (vertical)");
                int coluna = S.nextInt() - 1;

                filmes.ocuparLugar(linha, coluna);

                filmes.adicionarIngressosVendido(nomeFilme);
                return;
            }
        }

        System.out.println("Filme não encontrado no sistema!");
    }

    private static void informacoesSessao() {
        if (FILMES.isEmpty()) {
            System.out.println("Não tem filmes na sessão!");
            return;
        }

        for (Cinema filmes : FILMES) {
            System.out.printf("%nNome do filme: %s, Sala: %s, Horário: %s, Capacidade: %d, Preço: %.2f",
                    filmes.getNomeFilme(), filmes.getSala(), filmes.getHorarioSessao(), filmes.getCapacidade(),
                    filmes.getPreco());
        }
    }

    private static void limparTela() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}
