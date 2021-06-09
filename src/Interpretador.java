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
            menu.adicionaOpcao("Jogar 🎮‍");
            menu.adicionaOpcao("Sair");
            menu.show();
            int escolha = 0;
            try {
                    escolha = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Escolha " + escolha);
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
                    clearScreen();
                    Menu menuEquipas = new Menu();
                    menuEquipas.setTitulo("Selecione duas equipas");
                    for (Equipa e : p.getEquipas().values()) {
                        menuEquipas.adicionaOpcao(e.getNome());
                    }
                    menuEquipas.show();
                    try {
                        escolha = scanner.nextInt();
                        scanner.nextLine();
                    } catch (InputMismatchException e) {
                        escolha = -1;
                    }
                    jogar(p.getEquipas().get("sc braga"),p.getEquipas().get("fc porto"),scanner);
                    break;
                case 4:
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
        int escolha;
        try {
            escolha = s.nextInt();
        } catch (InputMismatchException e) {
            escolha = -1;
        }
        s.nextLine();
        switch (escolha) {
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

}