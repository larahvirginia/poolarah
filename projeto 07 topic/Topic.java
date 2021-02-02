import java.util.ArrayList;
import java.util.Scanner;

class Pass {
    private String nome;
    private int idade;
    public int id;

    public Pass (String id, int idade) {
        this.nome = id;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade () {
        return idade;
    }

    public void setIdade (int idade) {
        this.idade = idade;
    }

    public boolean ehPreferencial () {
        if (getIdade() > 60) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return nome + ":" + idade;
    }
}

public class Topic {
    public ArrayList<Pass> cadeiras = new ArrayList<>();
    Pass pass;
    public int qntPref;

    public Topic (int tamTopic, int qntPref) {
        cadeiras = new ArrayList();
        this.qntPref = qntPref;
        for (int i = 0; i < tamTopic; i++) {
            cadeiras.add(null);
        }
    }

    public String toString () {
        String saida = "[ ";
        for (int i = 0; i < qntPref; i++) {
            if (cadeiras.get(i) == null) {
                saida += "@ ";
            } else {
                saida += "@" + cadeiras.get(i) + " ";
            }
        }for (int i = qntPref; i < cadeiras.size(); i++) {
            if (cadeiras.get(i) == null) {
                saida += " =";
            } else {
                saida += " =" + cadeiras.get(i);
            }
        } return saida += " ]";
    }

    int temCadeiraVazia (int t, int t2) {
        for (int i = t; i < t2; i++) {
            if (cadeiras.get(i) == null) {
                return i;
            }
        } return -1;
    }

    Pass procurarPessoa (String id) {
        for (int i = 0; i < cadeiras.size(); i++) {
            pass = cadeiras.get(i);
            if (pass != null && pass.getNome().equals(id)) {
                return pass;
            }
        } return null;
    }

    public void subir (Pass pass) {
        int pos = -1;
        if (procurarPessoa(pass.getNome()) == null) {
            if (pass.ehPreferencial()) {
                pos = this.temCadeiraVazia(0, cadeiras.size());
            } else {
                pos = this.temCadeiraVazia(this.qntPref, cadeiras.size());
                if (pos == -1) {
                    pos = this.temCadeiraVazia(0, this.qntPref);
                }
            } if (pos == -1) {
                System.out.println("lotado");
            } else {
                cadeiras.set(pos, pass);
            }
        } else {
            System.out.println("essa pessoa já está na topic");
        }
    }

    Pass descer (String nome) {
        Pass aux = procurarPessoa(nome);
        if (aux == null) {
            System.out.println("essa pessoa não existe");
        } else {
            cadeiras.remove(aux);
            cadeiras.add(null);
        } return pass;
    }

    public static void main(String[] args) {
        Topic topic = new Topic(0, 0);
        Scanner leitor = new Scanner(System.in);

        while (true) {
            String line = leitor.nextLine();
            String [] ui = line.split(" ");
            if (ui[0].equals("finalizar")) {
                break;
            } else if (ui[0].equals("topic")) {
                topic = new Topic(Integer.parseInt(ui[1]), Integer.parseInt(ui[2]));
                System.out.println(topic);
            } else if (ui[0].equals("subir")) {
                topic.subir(new Pass(ui[1], Integer.parseInt(ui[2])));
                System.out.println(topic);
            } else if (ui[0].equals("descer")) {
                topic.descer(ui[1]);
                System.out.println(topic);
            } else {
                System.out.println("comando inválido");
            }            
        } leitor.close();
    }
}