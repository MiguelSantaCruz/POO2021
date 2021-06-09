import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Interpretador {
    public static void run() throws LinhaIncorretaException{
        Scanner scanner = new Scanner(System.in);
        Parser p = new Parser();
        p.parse();
        while(true){ 
            clearScreen();
            Menu menu = new Menu();
            System.out.println("[Foram lidas: " + p.getEquipas().size() + " equipas de " + p.getPath()+"]");
            menu.setTitulo("Football Manager ⚽");
            menu.adicionaOpcao("Consultar equipas 👥");
            menu.adicionaOpcao("Consultar jogadores ⛹️‍");
            menu.adicionaOpcao("Adicionar equipa ➕👥");
            menu.adicionaOpcao("Adicionar jogador ➕⛹️");
            menu.adicionaOpcao("Transferir jogador ➡️ ⛹️");
            menu.adicionaOpcao("Jogar 🎮‍");
            menu.adicionaOpcao("Sair ❌");
            menu.show();
            int escolha = 0;
            try {
                    escolha = scanner.nextInt();
                    scanner.nextLine();
            } catch (InputMismatchException e) {
                    escolha = -1;
                    scanner.nextLine();
            }
            switch (escolha) {
                case 1:
                    procurarEquipa(p,scanner);
                    break;
                case 2:
                    procurarJogador(p,scanner);
                    break;
                case 3:
                    adicionaEquipa(p,scanner);
                    break;
                case 4:
                    adicionaJogador(p,scanner);
                    break;
                case 6:
                    clearScreen();
                    Menu menuEquipas = new Menu();
                    menuEquipas.setTitulo("Selecione duas equipas");
                    for (Equipa e : p.getEquipas().values()) {
                        menuEquipas.adicionaOpcao(e.getNome());
                    }
                    menuEquipas.show();
                    ArrayList<String> text = new ArrayList<>();
                    text.add("Insira a primeira equipa");
                    text.add("Insira a segunda equipa");
                    ArrayList<String> numEquipas = new ArrayList<>();
                    numEquipas = leNargumentos(p, scanner, 2, true, text);
                    int i = 1;
                    Equipa eqCasa = new Equipa();
                    Equipa eqVisitante = new Equipa();
                    for (Equipa equipa : p.getEquipas().values()){
                        if(String.valueOf(i).equals(numEquipas.get(0))) eqCasa = equipa;
                        if(String.valueOf(i).equals(numEquipas.get(1))) eqVisitante = equipa; 
                        i++;
                    }
                    jogar(eqCasa,eqVisitante,scanner);
                    break;
                case 7:
                    scanner.close();
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

    public static void procurarJogador(Parser p, Scanner s){
        clearScreen();
        Menu menu = new Menu();
        menu.adicionaOpcao("Procurar jogador nos planteis 📕");
        menu.adicionaOpcao("Ver detalhes do jogador 📊");
        menu.adicionaOpcao("Voltar");
        menu.setTitulo("Consultar jogadores ⚽");
        menu.show();
        boolean detalhesJogador = false;
        int escolha;
        try {
            escolha = s.nextInt();
        } catch (InputMismatchException e) {
            escolha = -1;
        }
        s.nextLine();
        if(escolha == 2) {
            escolha = 1;
            detalhesJogador = true;
        }
        switch (escolha) {
            case 1: 
                System.out.println("Insira um nome para procurar:");
                String search = s.nextLine();
                boolean playerExists = false;
                for (Equipa e : p.getEquipas().values()) {
                    if (playerExists) break;
                    for (Jogador j : e.getplantel().values()) {
                        if (j.getNomeAtleta().toLowerCase().equals(search.toLowerCase())){
                           playerExists = true;
                           if(detalhesJogador) showDetalhesJogador(j,e);
                           else showEquipa(e, true, j.getNumeroJogador());
                           break;
                        }
                        
                    }
                }
                if(!playerExists) System.out.println("Não encontrado");
                break;
            case 3:
                return;
            default:
                System.out.println("-- [Erro]: pressione enter --");
                s.nextLine();
                procurarJogador(p, s);
                break;  
        }
        System.out.println("-- press enter --");
        s.nextLine();
        return;
    }

    public static void procurarEquipa(Parser p,Scanner s){
        clearScreen();
        Menu menu = new Menu();
        menu.adicionaOpcao("Procurar por nome 📕");
        menu.adicionaOpcao("Consultar lista 📋");
        menu.adicionaOpcao("Voltar");
        menu.setTitulo("Consultar equipas ⚽");
        menu.show();
        ArrayList<String> text = new ArrayList<>();
        ArrayList<String> args = new ArrayList<>();
        args = leNargumentos(p, s, 1, false, null);
        switch (Integer.valueOf(args.get(0))) {
            case 1: 
                System.out.println("Insira um nome para procurar:");
                String search = s.nextLine();
                if (p.getEquipas().containsKey(search.toLowerCase())) {
                    clearScreen();
                    showEquipa(p.getEquipas().get(search.toLowerCase()),true,-1);
                }
                else System.out.println("Não encontrado");
                break;
            case 2:
                for (Equipa e: p.getEquipas().values()){
                    showEquipa(e,false,-1);
                }
                break;
            case 3:
                return;
            default:
                System.out.println("-- [Erro]: pressione enter --");
                s.nextLine();
                procurarEquipa(p, s);
                break;
        }
        System.out.println("-- press enter --");
        s.nextLine();
        return;
    }

    public static void showDetalhesJogador(Jogador j,Equipa e){
        System.out.println("Equipa: " + e.getNome());
        System.out.println("Nome: " + (j.getNomeAtleta()));
        System.out.println("Número: " + j.getIdAtleta());
        System.out.println("Idade " + j.getIdade());
        System.out.println("Habilidade geral: " + j.getHabilidade() + avaliaHabilidade(j.getHabilidade()));
        System.out.println("Posição: " + j.getClass().getName());
        System.out.println(j.toString());
    }

    public static void showEquipa(Equipa e,boolean showPlantel,int highlight){
        System.out.print("Nome: " + truncateString(e.getNome(), 30));
        System.out.print("  Data de fundação: " + e.getDataDeFundação());
        System.out.println("            Habilidade global: " + e.getHabilidadeGlobal() + avaliaHabilidade(e.getHabilidadeGlobal()));
        if(showPlantel){
            System.out.println("Plantel:");
            for (Map.Entry<Integer,Jogador> plantel: e.getplantel().entrySet()) {
                if(plantel.getKey() == highlight) showJogador(plantel.getValue(),true);
                else showJogador(plantel.getValue(), false);
                
            }
        }
    }

    public static void showJogador(Jogador j,boolean highlight){
        if (highlight) {
            System.out.print("\u001B[47m");
            System.out.print("\u001B[30m");
        }
        System.out.print("Nome: " + truncateString(j.getNomeAtleta(),30));
        System.out.print(" │ Número: " + truncateString(String.valueOf(j.getIdAtleta()),3));
        System.out.print(" │ Idade " + truncateString(String.valueOf(j.getIdade()),3));
        System.out.println(" │ Habilidade geral: " + j.getHabilidade() + avaliaHabilidade(j.getHabilidade()));
        System.out.print("\u001B[0m");
    }

    public static String truncateString(String s, int n) {
        return String.format("%-" + n + "s", s);  
    }

    public static String avaliaHabilidade(float habilidade){
        StringBuilder sb = new StringBuilder();
        if(habilidade >= 90) sb.append("[Lendário]");
        if(habilidade >= 80 && habilidade < 90) sb.append("[Muito elevado]");
        if(habilidade >= 60 && habilidade < 80) sb.append("[Elevado]");
        if(habilidade >= 40 && habilidade < 60) sb.append("[Mediano]");
        if(habilidade >= 20 && habilidade < 40) sb.append("[Baixo]");
        if(habilidade < 20) sb.append("[Muito baixo]");
        return sb.toString();
    }

    public static void jogar(Equipa equipaCasa, Equipa equipaVisitante,Scanner s){
        clearScreen();
        System.out.println("\u001B[36mJogo entre " + equipaCasa.getNome() + " e " + equipaVisitante.getNome() + " [Ainda não dá para selecionar]\u001B[0m");
        System.out.println("-----------------------------------------");
        Jogo j = new Jogo();
        j.setEqVisitante(equipaVisitante);
        j.setEqCasa(equipaCasa);
        j.setPosicaoBola(0);
        System.out.println("Bola ao meio campo");
        for (int i = 0; i < 90; i++) {
            GameResult.calculaJogada(j);
            System.out.println(" (" + (i+1) + "')");
            try
            {
            Thread.sleep(200);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("\u001B[32mResultado final: " + j.getEqCasa().getNome() + " " + j.getGolosCasa() + " : " + j.getGolosVisitante() + " " + j.getEqVisitante().getNome()+"\u001B[0m");
        System.out.println("-- press enter --");
        s.nextLine();
    }

    public static void adicionaEquipa(Parser p, Scanner s){
        Equipa e = new Equipa();
        e.setplantel(new HashMap<Integer,Jogador>());
        e.setJogosAgendados(new ArrayList<Jogo>());
        ArrayList<String> args = new ArrayList<>();
        ArrayList<String> text = new ArrayList<>();
        text.add("Insira o nome");
        text.add("Insira a data de fundação (aaaa-mm-dd)");
        args = leNargumentos(p, s, 2, false,text);
        e.setNome(args.get(0));
        e.setDataDeFundação(LocalDate.parse(args.get(1)));
        p.addEquipa(e);
    }

    public static void adicionaJogador(Parser p, Scanner s){
        Jogador j = new Jogador();
        ArrayList<String> args = new ArrayList<>();
        ArrayList<String> text = new ArrayList<>();
        text.add("Insira o nome");
        args = leNargumentos(p, s, 1, false,text);
        ArrayList<String> args2 = new ArrayList<>();
        ArrayList<String> text2 = new ArrayList<>();
        text2.add("Insira a idade");
        text2.add("Insira o número");
        args2 = leNargumentos(p, s, 2, true,text2);
        j.setNomeAtleta(args.get(0));
        j.setIdade(Integer.valueOf(args2.get(0)));
        j.setNumeroJogador(Integer.valueOf(args2.get(1)));
        j.setCapacidadeDePasse((int) (Math.random()*100));
        j.setDestreza((int) (Math.random()*100));
        j.setImpulsao((int) (Math.random()*100));
        j.setJogoDeCabeca((int) (Math.random()*100));
        j.setRemate((int) (Math.random()*100));
        j.setResistencia((int) (Math.random()*100));
        j.setVelocidade((int) (Math.random()*100));
        j.setTitular(true);
        j.setSuplente(false);
        Menu menuEquipas = new Menu();
        menuEquipas.setTitulo("Selecione uma equipa");
            for (Equipa e : p.getEquipas().values()) {
                menuEquipas.adicionaOpcao(e.getNome());
            }
        menuEquipas.show();
        ArrayList<String> texto = new ArrayList<>();
        text.add("Insira a equipa");
        ArrayList<String> numEquipas = new ArrayList<>();
        numEquipas = leNargumentos(p, s, 1, true, texto);
        int i = 1;
        for (Equipa equipa : p.getEquipas().values()){
            if(String.valueOf(i).equals(numEquipas.get(0))) {
                equipa.insereJogador(j);
                j.addEquipa(equipa);
                break;
            }
            i++;
        }
        Menu menu = new Menu();
        menu.setTitulo("Selecione a posição");
        menu.adicionaOpcao("Medio");
        menu.adicionaOpcao("Lateral");
        menu.adicionaOpcao("Defesa");
        menu.adicionaOpcao("Guarda-Redes");
        menu.adicionaOpcao("Avançado");
        menu.show();
        int escolha = 0;
        while(true) {
            try {
                  escolha = Integer.parseInt(s.nextLine());
                  break;
            }catch (NumberFormatException e) {
                System.out.print("[Erro] Introduza de novo: ");
                continue;
            }
        }
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

    public static ArrayList<String> leNargumentos(Parser p, Scanner s,int n, boolean isNumeric,ArrayList<String> text){
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