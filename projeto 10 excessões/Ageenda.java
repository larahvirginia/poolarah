import java.util.*;

class Fone {
    private String label;
    private String numero;

    public Fone (String label, String numero) {
        this.label = label;
        this.numero = numero;
    }
    
    public Fone (String serial) {
        String array[] = serial.split(":");
        label = array[0];
        numero = array[1];
    }

    String getLabel () {
        return label;
    }

    String getNumero() {
        return numero;
    }

    static public boolean validar (String numero) {
        String validos = "0123456789()-";
        for (int i = 0; i < numero.length(); i++) {
            if (!validos.contains("" + numero.charAt(i))) {
                return false;
            }
        } return true;
    }

    public String toString() {
        return label + ":" + numero;
    }
}

class Contato {
    private String nome;
    private boolean favorito;
    private ArrayList<Fone> fones;

    public Contato (String nome) {
        this.nome = nome;
        fones = new ArrayList<>();
    }

    boolean getFavorito(){
        return favorito;
    }

    public void addFone (String label, String numero) {
        if(!Fone.validar(numero)) {
            throw new RuntimeException("fail: esse número não é valido");
        }
        fones.add(new Fone(label, numero));
    }

    public void addFone (Fone fone) {
        this.addFone(fone.getLabel(), fone.getNumero());
    }

    public void rmFone (int indice) {
        fones.remove(fones.get(indice));
    }

    public void setFavotirar (boolean valor) {
        this.favorito = valor;
    }
    
    public String toString() {
        return nome + " " + fones;
    }
}

public class Ageenda {
    Map<String, Contato> contatos;
    Map<String, Contato> favoritos;

    Ageenda(){
        contatos = new TreeMap<>();
        favoritos = new TreeMap<>();
    }

    public void addContato (String nome, List <Fone> fones) {
        if (!contatos.containsKey(nome)) {
            contatos.put(nome, new Contato(nome));
        }
        Contato contato = contatos.get(nome);
        for (Fone fone : fones) {
            contato.addFone(fone.getLabel(), fone.getNumero());
        }
    }

    public boolean rmContato (String nome) {
        Contato contato = getContato(nome);
        contatos.remove(nome);
        return true;
    }

    ArrayList <Contato> procurar(String padrao) {
        ArrayList<Contato> aux = new ArrayList<>();
        for (Contato contato : contatos.values()) {
            if (contato.toString().contains(padrao)) {
                aux.add(contato);
                return aux;
            }
        } return null;
    }

    ArrayList <Contato> getContatos() {
        return new ArrayList<Contatos>(contatos.values());
    }

    Contato getContato(String nome) {
        Contato contato = contatos.get(nome);
        if (contato == null) {
            throw new RuntimeException("contato não existe");
        } return contato;
    }

    public void favoritar(String nome) {
        Contato contato = getContato(nome);
        if (!contato.getFavorito()) {
            contato.setFavotirar(true);
            favoritos.put(nome, contato);
        }
    }

    public void desfavoritar(String nome) {
        Contato contato = getContato(nome);
        if (contato.getFavorito()) {
            favoritos.remove(nome);
            contato.setFavotirar(false);
        }
    }

    ArrayList <Contato> getFavoritos () {
        return new ArrayList<Contato>(favoritos.values());
    }

    public String toString() {
        String saida = "";
        for (Contato contato : contatos.values()) {
            if (!contato.getFavorito()) {
                saida += "- " + contato + "\r\n";
            } else {
                saida += "@ " + contato;
            }
        } return saida;
    }

    public static void main (String[] args) {
        Ageenda agenda = new Ageenda();
        Scanner leitor = new Scanner(System.in);

        while (true) {
            try {
                String line = leitor.nextLine();
                String [] ui = line.split(" ");
                if (ui[0].equals("fim")) {
                    break;
                } else if (ui[0].equals("addcontato")) {
                    List <Fone> fones = new ArrayList <>();
                    for (int i = 2; i < ui.length; i++) {
                        fones.add(new Fone(ui[i]));
                    } agenda.addContato((ui[1]), fones);
                    System.out.print(agenda);
                } else if (ui[0].equals("getcontatos")) {
                    System.out.print(agenda);
                } else if (ui[0].equals("rmcontato")) {
                    agenda.rmContato(ui[1]);
                    System.out.println(agenda);
                } else if (ui[0].equals("getcontato")) {
                    agenda.getContato(ui[1]);
                    System.out.print(agenda);
                } else if (ui[0].equals("procurar")) {
                    agenda.procurar(ui[1]);
                    System.out.print(agenda);
                } else if (ui[0].equals("favoritar")) {
                    agenda.favoritar(ui[1]);
                    System.out.println(agenda);
                } else if (ui[0].equals("desfavoritar")) {
                    agenda.desfavoritar(ui[1]);
                    System.out.println(agenda);
                } else if (ui[0].equals("favoritos")) {
                    System.out.println(agenda.getFavoritos());
                }    
            } catch (RuntimeException e) {
                System.out.println(e.getLocalizedMessage());
            }
        } leitor.close();
    }
}