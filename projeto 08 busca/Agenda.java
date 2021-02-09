import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Fone {
    private String label;
    private String numero;
    
    public Fone (String serial) {
        String array[] = serial.split(":");
        label = array[0];
        numero = array[1];
    }

    static boolean validar (String numero) {
        String validos = "0123456789()-";
        for (int i = 0; i < numero.length(); i++) {
            if (!validos.contains("" + numero.charAt(i))) {
                return false;
            }
        } return true;
    }

    Fone (String label, String numero) {
        this.label = label;
        this.numero = numero;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getLabel() {
        return label;
    }

    public String getNumero() {
        return numero;
    }

    public String toString () {
        return label + ":" + numero;
    }
}

class Contato {
    private String nome;
    private ArrayList<Fone> fones;
    Fone fon;

    public Contato(String nome) {
        this.nome = nome;
        fones = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void addFone (String label, String numero) {
        if(!fon.validar(numero)) {
            System.out.println("fail: esse número não é valido");
        }
        fones.add(new Fone(label, numero));
    }

    public void rmFone (int indice) {
        fones.remove(fones.get(indice));
    }

    public String toString() {
        String saida = "";
        for (int i = 0; i < fones.size(); i++) {
            Fone fone = fones.get(i);
            saida += "[" + i + ":" + fone + "]";
        }
        return getNome() + " " + saida;
    }
}

class ComparaPessoaPorNome implements Comparator <Contato> {
    public int compare (Contato c1, Contato c2) {
        return c1.getNome().compareTo(c2.getNome());
    }
}

public class Agenda {
    private ArrayList<Contato> contatos;

    public Agenda() {
        contatos = new ArrayList<>();
    }

    private int procurarContato (String nome) {
        for (int i = 0; i < contatos.size(); i++) {
            Contato contato = contatos.get(i);
            if (contato != null && contato.getNome().equals(nome)) {
                return i;
            }
        } return -1;
    }

    public void addContato(String nome, Fone fone) {
        Contato contato = this.getContato(nome);
        if (contato == null) {
            contato = new Contato(nome);
            this.contatos.add(contato);
        } contato.addFone(fone.getLabel(), fone.getNumero());
    }

    public void addContato(String nome, List <Fone> fones) {
        Contato contato = this.getContato(nome);
        if (contato == null) {
            contato = new Contato(nome);
            this.contatos.add(contato);
        } for (Fone fone : fones) {
                contato.addFone(fone.getLabel(), fone.getNumero());
        }
    }

    public boolean rmContato(String nome) {
        int aux = procurarContato(nome);
        if (aux!=-1) {
            this.contatos.remove(aux);
            return true;
        } System.out.println("fail: esse contato não existe");
        return false;
    }

    boolean rmFone(String nome, int indice) {
        Contato contato = this.getContato(nome);
        int aux = procurarContato(nome);
        if (aux != -1) {
            contato.rmFone(indice);
            return true;
        }
        System.out.println("esse contato não existe");
        return false;
    }
    
    ArrayList<Contato> getContatos() {
        for (int i = 0; i < contatos.size(); i ++) {
            System.out.println(contatos.get(i));
        } return contatos;
    }

    Contato getContato (String nome) {
        Contato contato;
        for (int i = 0; i < contatos.size(); i ++) {
            contato = contatos.get(i);
            if (contatos != null && contato.getNome().equals(nome)) {
                System.out.println(contato);
                return contato;
            }
        } return null;
    }

    ArrayList<Contato> procurar(String padrao) {
        ArrayList<Contato> aux = new ArrayList <>();
        for (Contato c : contatos) {
            if (c.toString().contains(padrao)) {
                aux.add(c);
                System.out.println(aux);
            }
        } return aux;
    }

    public String toString() {
        String saida = "";
        for (int i = 0; i < contatos.size(); i++) {
            Contato aux = contatos.get(i);
            saida += " " + " - " + aux + "\r\n";
        } return saida;
    }

    public static void main(String[] args) {
        Agenda agenda = new Agenda();
        Scanner leitor = new Scanner(System.in);

        while (true) {
            String line = leitor.nextLine();
            String [] ui = line.split(" ");
            if (ui[0].equals("fim")) {
                break;
            } else if (ui[0].equals("addcontato")) {
                List <Fone> fones = new ArrayList <>();
                for (int i = 2; i < ui.length; i++) {
                    fones.add(new Fone(ui[i]));
                } agenda.addContato((ui[1]), fones);
                Collections.sort(agenda.getContatos(), new ComparaPessoaPorNome());
                System.out.print(agenda);
            } else if (ui[0].equals("rmfone")) {
                agenda.rmFone(ui[1], Integer.parseInt(ui[2]));
                System.out.print(agenda);
            } else if (ui[0].equals("getcontatos")) {
                agenda.getContatos();
                System.out.print(agenda);
            } else if (ui[0].equals("getcontato")) {
                agenda.getContato(ui[1]);
                System.out.print(agenda);
            } else if (ui[0].equals("procurar")) {
                agenda.procurar(ui[1]);
                System.out.print(agenda);
            }
        } leitor.close();
    }
}