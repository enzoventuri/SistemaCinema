import java.util.ArrayList;

public class Cinema {
    private String nomeFilme;
    private String sala;
    private String horarioSessao;
    private int capacidade;
    private double preco;
    private ArrayList<String> ingressosVendido;
    private String[][] assentos;

    public Cinema(String nomeFilme, String sala, String horarioSessao, int capacidade, double preco) {
        this.nomeFilme = nomeFilme;
        this.sala = sala;
        this.horarioSessao = horarioSessao;
        this.capacidade = capacidade;
        this.ingressosVendido = new ArrayList<>();
        this.preco = preco;

        int linhas = 5;
        int colunas = (int) Math.ceil((double) capacidade / linhas);
        assentos = new String[linhas][colunas];

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                assentos[i][j] = ".";
            }
        }
    }

    public void adicionarIngressosVendido(String nomeFilme) {
        this.ingressosVendido.add(nomeFilme);
    }

    public void lugaresDisponiveis() {
        for (int i = 0; i < assentos.length; i++) {
            for (int j = 0; j < assentos[i].length; j++) {
                System.out.print(assentos[i][j] + " | ");
            }

            System.out.println();
        }
    }

    public void ocuparLugar(int linha, int coluna) {
        if (assentos[linha][coluna].equalsIgnoreCase("X")) {
            System.out.println("Assento indisponível!");
            return;
        }

        for (int i = 0; i < assentos.length; i++) {
            for (int j = 0; j < assentos[i].length; j++) {
                if (i == linha) {
                    if (j == coluna) {
                        String ocupado = "X";
                        System.out.print(assentos[i][j] = ocupado + " | ");
                    } else {
                        System.out.print(assentos[i][j] + " | ");
                    }
                } else {
                    System.out.print(assentos[i][j] + " | ");
                }
            }

            System.out.println();
        }
    }

    public String getNomeFilme() {
        return nomeFilme;
    }

    public String getSala() {
        return sala;
    }

    public String getHorarioSessao() {
        return horarioSessao;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public double getPreco() {
        return preco;
    }

    public ArrayList<String> getIngressosVendido() {
        return ingressosVendido;
    }

}
