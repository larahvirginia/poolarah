import java.util.ArrayList;
import java.util.Scanner;

class Crianca {
    private String nome;
    private int idade;

    Crianca (String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    String getNome() {
        return nome;

    }

    int getIdade() {
        return idade;
    }

    public String toString() {
        return nome + " : " + idade;
    }
}

public class PulaPula {

    ArrayList<Crianca> esperando = new ArrayList<>();
    ArrayList<Crianca> brincando = new ArrayList<>();

    // chegou no pula pula
    void chegar (Crianca crianca) {
        esperando.add(crianca);
    }

    void mostrar () {
        System.out.println("fila de espera: " + esperando);
        System.out.println("brincando no pula pula: " + brincando);
    }
    // sai do vetor esperando pro brincando
    void entrar() {
        brincando.add(esperando.get(0));
        esperando.remove(esperando.get(0));

    }
    // sai do vetor brincando pro esperando (final a fila) 
    void sair() {
        esperando.add(brincando.get(0));
        brincando.remove(brincando.get(0));
    }


    public static void main(String[] args) {
        PulaPula trampolim = new PulaPula();
        Scanner leitor = new Scanner (System.in);

        while (true) {
            String line = leitor.nextLine();
            String [] ui = line.split(" ");
            if (ui[0].equals("end")) {
                break;
            } else if (ui[0].equals("chegar")) {
                trampolim.chegar(new Crianca(ui[1], Integer.parseInt(ui[2])));
                trampolim.mostrar();
            } else if (ui[0].equals("entrar")) {
                trampolim.entrar();
                trampolim.mostrar();
            } else if (ui[0].equals("sair")) {
                trampolim.sair();
                trampolim.mostrar();
            }
        }

        leitor.close();
    }
}
