import java.util.*;

import javax.management.RuntimeErrorException;

class Fone {
    private String label;
    private String numero;

    public Fone(String label, String numero) {
        this.label = label;
        this.numero = numero;

        String validos = "0123456789()-";
        for (int i = 0; i < numero.length(); i++) {
            if (!validos.contains("" + numero.charAt(i))) {
                throw new RuntimeException("fail: número inválido");
            }
        }
    }

    Fone(String serial) {
        String array[] = serial.split(":");
        label = array[0];
        numero = array[1];
    }

    public String getLabel() {
        return this.label;
    }

    public String getNumero() {
        return this.numero;
    }

    public String toString() {
        return label + ":" + numero;
    }
}

class Contato {
    protected String nome;
    protected ArrayList<Fone> fones;

    public Contato(String nome, List <Fone> fones) {
        this.nome = nome;
        this.fones = new ArrayList<>();
        if (fones != null) {
            for (Fone fone : fones) {
                this.fones.add(fone);
            }
        }
    }

    public String getNome() {
        return nome;
    }

    public void addFone(String label, String numero) {
        fones.add(new Fone(label, numero));
    }

    public void addFone(Fone fone) {
        this.addFone(fone.getLabel(), fone.getNumero());
    }

    public void rmFone(int index) {
        fones.remove(fones.get(index));
    }

    public String toString() {
        String saida = "";
        saida += "" + nome + ":" + fones;
        return saida;
    }
}

class ContatoPlus extends Contato {
    private boolean favorito;

    public ContatoPlus(String nome, List<Fone> fones) {
        super(nome, fones);
    }

    public String getNome() {
        return nome;
    }

    public void setFavorito(boolean valor) {
        this.favorito = valor;
    }
    
    public boolean getFavorito () {
        return favorito;
    }

    public String toString() {
        String saida = "";
        saida += (this.favorito ? "@":"-") + " " + nome + ":" + fones;
        return saida;
    }
}

class Agenda {
    Map <String, Contato> contatos;

    public Agenda() {
        contatos = new TreeMap<>();
    }

    public void addContato (Contato contato, List<Fone> fones) {
        if (!contatos.containsKey(contato.nome)) {
            contatos.put(contato.nome, contato);
        } else {
            contato = this.getContato(contato.nome);
            for (Fone fone : fones) {
                contato.addFone(fone.getLabel(), fone.getNumero());
            }
        }
    }

    public boolean rmContato(String nome) {
        Contato contato = getContato(nome);
        contatos.remove(nome);
        return true;
    }

    ArrayList <Contato> procurar(String padrao) {
        ArrayList<Contato> aux = new ArrayList<>();
        for (Contato contato : contatos.values()) {
            if (contato.toString().contains(padrao)) {
                aux.add(contato);
            }
        } return aux;
    }

    ArrayList<Contato> getContatos() {
        return new ArrayList<Contato>(contatos.values());
    }

    Contato getContato(String nome) {
        Contato contato = contatos.get(nome);
        if (contato == null) {
            throw new RuntimeException("fail: contato não existe");
        } return contato;
    }

    public String toString() {
        String saida = "";
        for (Contato contato : contatos.values()) {
            saida += contato + "\n";
        } return saida;
    }
}

class AgendaPlus extends Agenda {
    TreeMap<String, Contato> favoritos;

    public AgendaPlus() {
        favoritos = new TreeMap<>();
    }

    public void favoritar(String nome) {
        Contato contato = this.getContato(nome);
        if (contato instanceof ContatoPlus) {
            ContatoPlus cp = (ContatoPlus) contato;
            if (cp.getFavorito()) {
                favoritos.put(nome, contato);
            } else {
                favoritos.put(nome, contato);
                cp.setFavorito(true);
            }
        }
    }

    public void desfavoritar (String nome) {
        Contato contato = this.getContato(nome);
        ContatoPlus cp = (ContatoPlus) contato;
        if (cp.getFavorito()) {
            favoritos.remove(nome, contato);
            cp.setFavorito(false);
        }
    }

    ArrayList<Contato> getFavoritos() {
        return new ArrayList<Contato> (favoritos.values());
    }
}

public class Contatostar {
    public static void main(String[] args) {
        AgendaPlus agenda = new AgendaPlus();
        try (Scanner leitor = new Scanner(System.in)) {
            while(true) {
                try{
                    String line = leitor.nextLine();
                    String [] ui = line.split(" ");
                    if (ui[0].equals("fim")) {
                        break;
                    } else if (ui[0].equals("add")) {
                        List<Fone> fones = new ArrayList<>();
                        for (int i = 2; i < ui.length-1; i++) {
                            fones.add(new Fone(ui[i]));
                        }
                        ContatoPlus contato = new ContatoPlus(ui[1], fones);
                        if (ui[ui.length-1].equals("sim")) {
                            contato.setFavorito(true);
                        }
                        agenda.addContato(contato, fones);
                        System.out.println(agenda);
                    } else if(ui[0].equals("getcontatos")) {
                        System.out.println(agenda);
                    } else if(ui[0].equals("getcontato")) {
                        System.out.print(agenda.getContato(ui[1]));
                    } else if(ui[0].equals("rm")) {
                        agenda.rmContato(ui[1]);
                        System.out.println(agenda);
                    } else if(ui[0].equals("procurar")) {
                        System.out.print(agenda.procurar(ui[1]));
                    } else if(ui[0].equals("favoritar")) {
                        agenda.favoritar(ui[1]);
                        System.out.println(agenda);
                    } else if(ui[0].equals("desfavoritar")) {
                        agenda.desfavoritar(ui[1]);
                        System.out.println(agenda);
                    } else if(ui[0].equals("getfavoritos")) {
                        System.out.print(agenda.getFavoritos());
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw e;
                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}