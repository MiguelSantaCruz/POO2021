import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Interpretador {
    public static void run() throws LinhaIncorretaException, ClassNotFoundException, IOException{
        IView view = new View();
        IInput input = new Input();
        IModel model = new Model();
        IParser p = new Parser();
        p.parse(model);
        while(true){ 
            view.mostrarMenuPrincipal();
            int escolha = input.InputInteger();
            switch (escolha) {
                case 1:
                    procurarEquipa(p, input,view,model);
                    break;
                case 2:
                    procurarJogador(p,input,view,model);
                    break;
                case 3:
                    adicionaEquipa(p,input,view,model);
                    break;
                case 4:
                    adicionaJogador(p,input,view,model);
                    break;
                case 6:
                    view.mostrarEquipas(model);
                    view.mostraMensagem("Insira a primeira equipa");
                    int equipa1 = input.InputInteger();
                    view.mostraMensagem("Insira a segunda equipa");
                    int equipa2 = input.InputInteger();
                    int i = 1;
                    IEquipa eqCasa = new Equipa();
                    IEquipa eqVisitante = new Equipa();
                    for (IEquipa equipa : model.getEquipas().values()){
                        if(i == equipa1) eqCasa = equipa;
                        if(i == equipa2) eqVisitante = equipa; 
                        i++;
                    }
                    jogar(eqCasa,eqVisitante,view,input);
                    break;
                case 7:
                    view.mostraMensagem("Insira o nome do ficheiro: ");
                    String filePath = input.InputString();
                    model = p.readBin(filePath);
                    view.mostraMensagem("Lidas: " + model.getEquipas().values().size() + " equipas de [" + filePath + "]");
                    view.mostraMensagem("--- press enter ---");
                    input.InputString();
                    break;
                case 8:
                    view.mostraMensagem("Insira o nome do ficheiro: ");
                    String filePath2 = input.InputString();
                    p.guardaBin(filePath2,model);  
                    break;
                case 9:
                    input.closeScanner();
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }

    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }  

    public static void procurarJogador(IParser p, IInput input,IView view,IModel model){
        view.mostrarMenuDeConsultaDeJogador();
        boolean detalhesJogador = false;
        int escolha = input.InputInteger();
        if(escolha == 2) {
            escolha = 1;
            detalhesJogador = true;
        }
        switch (escolha) {
            case 1:
                view.mostraMensagem("Insira um nome para procurar:");
                String search = input.InputString();
                boolean playerExists = false;
                for (IEquipa e : model.getEquipas().values()) {
                    if (playerExists) break;
                    for (IJogador j : e.getplantel().values()) {
                        if (j.getNomeAtleta().toLowerCase().equals(search.toLowerCase())){
                           playerExists = true;
                           if(detalhesJogador) IView.showDetalhesJogador(j,e);
                           else IView.showEquipa(e, true, j.getNumeroJogador());
                           break;
                        }
                        
                    }
                }
                if(!playerExists) view.mostraMensagem("Não encontrado");
                break;
            case 3:
                return;
            default:
                view.mostraMensagem("-- [Erro]: pressione enter --");
                input.InputString();
                procurarJogador(p, input,view,model);
                break;  
        }
        System.out.println("-- press enter --");
        input.InputString();
        return;
    }

    public static void procurarEquipa(IParser p,IInput input,IView view,IModel model){
        view.mostrarMenuDeConsultaDeEquipas();
        int escolha = input.InputInteger();
        switch (escolha) {
            case 1: 
                view.mostraMensagem("Insira um nome para procurar");
                String search = input.InputString();
                if (model.getEquipas().containsKey(search.toLowerCase())) {
                    clearScreen();
                    IView.showEquipa(model.getEquipas().get(search.toLowerCase()),true,-1);
                }
                else  view.mostraMensagem("Não encontrado");
                break;
            case 2:
                for (IEquipa e: model.getEquipas().values()){
                    IView.showEquipa(e,false,-1);
                }
                break;
            case 3:
                return;
            default:
                view.mostraMensagem("-- [Erro]: pressione enter --");
                input.InputString();
                procurarEquipa(p, input,view,model);
                break;
        }
        view.mostraMensagem("-- pressione enter --");
        input.InputString();
        return;
    }

    public static void jogar(IEquipa equipaCasa, IEquipa equipaVisitante,IView view,IInput input){
        IJogo j = new Jogo();
        j.setEqVisitante(equipaVisitante);
        j.setEqCasa(equipaCasa);
        j.setPosicaoBola(0);
        view.showInicioJogo(equipaCasa, equipaVisitante);
        for (int i = 0; i < 90; i++) {
            GameResult.calculaJogada(j);
            view.mostraMensagem(" (" + (i+1) + "')");
            try
            {
            Thread.sleep(200);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
        }
        view.showFimJogo(j);
        view.mostraMensagem("-- press enter --");
        input.InputString();
    }

    public static void adicionaEquipa(IParser p, IInput input,IView view,IModel model){
        IEquipa e = new Equipa();
        e.setplantel(new HashMap<Integer,IJogador>());
        e.setJogosAgendados(new ArrayList<IJogo>());
        view.mostraMensagem("Insira o nome");
        String nome = input.InputString();
        view.mostraMensagem("Insira a data de fundação (aaaa-mm-dd)");
        String dataDeFundacao = input.InputString();
        e.setNome(nome);
        e.setDataDeFundação(LocalDate.parse(dataDeFundacao));
        model.addEquipa(e);
    }

    public static void adicionaJogador(IParser p, IInput input,IView view,IModel model){
        IJogador j = new Jogador();
        view.mostraMensagem("Insira o nome");
        String nome = input.InputString();
        view.mostraMensagem("Insira a idade");
        Integer idade = input.InputInteger();
        view.mostraMensagem("Insira o número");
        Integer numero = input.InputInteger();
        j.setNomeAtleta(nome);
        j.setIdade(idade);
        j.setNumeroJogador(numero);
        j.setCapacidadeDePasse((int) (Math.random()*100));
        j.setDestreza((int) (Math.random()*100));
        j.setImpulsao((int) (Math.random()*100));
        j.setJogoDeCabeca((int) (Math.random()*100));
        j.setRemate((int) (Math.random()*100));
        j.setResistencia((int) (Math.random()*100));
        j.setVelocidade((int) (Math.random()*100));
        j.setTitular(true);
        j.setSuplente(false);
        view.mostrarEquipas(model);
        view.mostraMensagem("Insira a equipa");
        int numEquipas = input.InputInteger();
        int i = 1;
        for (IEquipa equipa : model.getEquipas().values()){
            if(i == numEquipas) {
                equipa.insereJogador(j);
                j.addEquipa(equipa);
                break;
            }
            i++;
        }
        view.mostrarListaDeJogadores();
        int escolha = input.InputInteger();
        switch (escolha) {
            case 1:
                Medio m = (Medio) j;
                m.setRecuperacao_bolas((int) (Math.random()*100));
                m.setHabilidade(m.calculaHabilidade());
                break;
            case 2:
                Lateral l =(Lateral) j;
                l.setCruzamentos((int) (Math.random()*100));
                l.setHabilidade(l.calculaHabilidade());
                break;
            case 3:
                Defesa d =(Defesa) j;
                d.setIntersecao((int) (Math.random()*100));
                d.setDrible((int) (Math.random()*100));
                d.setHabilidade(d.calculaHabilidade());
                break;
            case 4:
                Guarda_Redes g =(Guarda_Redes) j;
                g.setElasticidade((int) (Math.random()*100));
                g.setHabilidade(g.calculaHabilidade());
                break;
            case 5:
                Avancado a =(Avancado) j;
                a.setFinalizacao((int) (Math.random()*100));
                a.setSprint((int) (Math.random()*100));
                a.setHabilidade(a.calculaHabilidade());
                break;
            default:
                Avancado a2 =(Avancado) j;
                a2.setFinalizacao((int) (Math.random()*100));
                a2.setSprint((int) (Math.random()*100));
                a2.setHabilidade(a2.calculaHabilidade());
                break;
        }
    }

    public static ArrayList<String> leNargumentos(IParser p, Scanner s,int n, boolean isNumeric,ArrayList<String> text){
        ArrayList<String> l = new ArrayList<>();
        int inputInt = 0;
        String inputStr = "";
        for (int i = 0; i < n; i++) {
            if(text != null && text.size()>i) System.out.print(text.get(i) + ": ");
            while(true) {
                try {
                      if (isNumeric) inputInt = Integer.parseInt(s.nextLine());
                      else inputStr = s.nextLine();
                      break;
                }catch (NumberFormatException e) {
                    System.out.println("[Erro] Insira de novo: ");
                    continue;
                }
            }
            if(isNumeric) l.add(String.valueOf(inputInt));
            else l.add(inputStr);
        }
        return l;
    }
}