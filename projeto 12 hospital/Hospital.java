import java.util.*;

import org.graalvm.compiler.lir.util.ValueSet;

class Paciente{
    String id;
    Map<String, Medico> medicos;
    
    public Paciente(String id){
        this.id = id;
        this.medicos = new TreeMap<>();
    }

    void addMedico(String id, Medico medico){
        if(!medicos.containsKey(id)){
            this.medicos.put(medico.id, medico);
            medico.addPaciente(this.id, this);
        }
        return;
    }
    
    void rmMedico(String id){
        if(!medicos.containsKey(id)){
            return;
        }
        Medico medico = medicos.remove(id);
        medico.rmPaciente(this.id);
    }
    
    public String toString(){
        String saida = id + "[";
        for(Medico medico : medicos.values())
            saida += medico.id + " ";
        return saida + "]";
    }
}

class Medico extends Paciente{
    Map<String, Paciente> pacientes;
    
    public Medico(String id){
        super(id);
        this.pacientes = new TreeMap<>();
    }
    
    void addPaciente(String id, Paciente paciente){
        if(!pacientes.containsKey(id)){
            this.pacientes.put(paciente.id, paciente);
            paciente.addMedico(this.id, this);
        }
        
        return;
    }    
    void rmPaciente(String idPaciente){
        if(pacientes.containsKey(idPaciente)){
            Paciente paciente = pacientes.remove(idPaciente);
            paciente.rmMedico(this.id);
        }
    }
    
    public String toString(){
        String saida = id + "[";
        for(Paciente paciente : pacientes.values())
            saida += paciente.id + " ";
        return saida + "]";
    }
}

public class Hospital {

    Map<String, Medico> medicos;
    Map<String, Paciente> pacientes;

    public Hospital(){
        this.medicos = new TreeMap<>();
        this.pacientes = new TreeMap<>();
    }
    
    Medico getMedico(String id){
        Medico medico = medicos.get(id);
        if(medico == null){
            throw new RuntimeException("Medico não existe");
        }    
        return medico;
    }
    
    Paciente getPaciente(String id){
        Paciente paciente = pacientes.get(id);
        if(paciente == null){
            throw new RuntimeException("Paciente não existe");
        }
        return paciente;
    }
    
    void addMedico(Medico idMedico){
        if(this.medicos.containsKey(idMedico.id)){  
            throw new RuntimeException ("Medico já existe");
        }
        this.medicos.put(idMedico.id, idMedico);
        this.pacientes.put(idMedico.id, idMedico);

    }
    
    void addPaciente(Paciente idPaciente){
 	if(this.pacientes.containsKey(idPaciente.id)){
            throw new RuntimeException ("Paciente já existe");
        }
        pacientes.put(idPaciente.id, idPaciente);
    }
    
    void vincular(String idPaciente, String idMedico){
        if (idPaciente.equals(idMedico)){ 
            throw new RuntimeException("O médico não pode se consultar com ele mesmo");
        }
	getPaciente(idPaciente).addMedico(idMedico, getMedico(idMedico));
    }
    
    void desvincular(String idPaciente, String idMedico){
	getPaciente(idPaciente).rmMedico(idMedico);
    }
    
    void rmMedico(String idMedico){
        Medico medico = getMedico(idMedico);
        ArrayList<String> pacient = new ArrayList<>(medicos.keySet());
        for(Paciente paciente : medico.pacientes.values()){
            medico.rmPaciente(idMedico);
            paciente.rmMedico(idMedico);
        }
        medicos.remove(idMedico);
        pacientes.remove(idMedico);
    }
    
    void rmPaciente(String idPaciente){
        Paciente paciente = getPaciente(idPaciente);
        ArrayList<String> medic = new ArrayList<>(pacientes.keySet());
        for(Medico medico : paciente.medicos.values()){
            paciente.rmMedico(idPaciente);
            medico.rmPaciente(idPaciente);
        }
        pacientes.remove(idPaciente);
        
    }
    
    public String toString(){
        
        String saida="";
        saida+= "pacientes: " + pacientes.values() + "\n" + "medicos: " + medicos.values();
        return saida;
    }

    public static void main(String[] args) {
        Hospital hospital = new Hospital();
        try (Scanner leitor = new Scanner(System.in)) {
            while(true){
                try{
                    String line = leitor.nextLine();
                    String[] ui = line.split(" "); 
                    if(ui[0].equals("finalizar")){
                        break;
                    }else if(ui[0].equals("addmedico")){
                        hospital.addMedico(new Medico(ui[1]));
                        System.out.println(hospital);
                    }else if(ui[0].equals("addpaciente")){
                        hospital.addPaciente(new Paciente(ui[1]));
                        System.out.println(hospital);
                    }else if(ui[0].equals("vincular")){
                        hospital.vincular(ui[1], ui[2]);
                        System.out.println(hospital);
                    }else if(ui[0].equals("desvincular")){
                        hospital.desvincular(ui[1], ui[2]);
                        System.out.println(hospital);
                    }else if(ui[0].equals("rmmedico")){
                        hospital.rmMedico(ui[1]);
                        System.out.println(hospital);
                    }else if(ui[0].equals("rmpaciente")){
                        hospital.rmPaciente(ui[1]);
                        System.out.println(hospital);
                    } else if (ui[0].equals("hospital")) {
                        System.out.println(hospital);
                    }
                }catch(RuntimeException e){
                    System.out.println(e.getMessage());
                }
            }
        }
         System.out.println(hospital);
    }    
}

    
