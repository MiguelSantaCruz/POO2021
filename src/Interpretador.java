import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Interpretador {
    public static void run() throws LinhaIncorretaException{
        Scanner scanner = new Scanner(System.in);
        Parser p = new Parser();
        p.parse();
        while(true){ 
            clearScreen();
            ArrayList <String> opcoes = new ArrayList<>();
            opcoes.add("Menu");
            opcoes.add("Consultar equipas");
            opcoes.add("Consultar jogadores");
            opcoes.add("Sair");
            menu(opcoes);
            int escolha = scanner.nextInt();
            switch (escolha) {
                case 1:
                    procurarEquipa(p,scanner);
                    break;
                case 3:
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

    public static void procurarEquipa(Parser p,Scanner s){
        ArrayList <String> opcoes = new ArrayList<>();
        opcoes.add("Consultar lista de equipas ");
        opcoes.add("Procurar por nome");
        opcoes.add("Consultar lista de equipas");
        opcoes.add("Consultar planteis");
        opcoes.add("Voltar");
        clearScreen();
        System.out.println("Foram lidas: " + p.getEquipas().size() + " equipas");
        menu(opcoes);
        int escolha = s.nextInt();
        s.nextLine();
        switch (escolha) {
            case 1: 
                System.out.println("Insira um nome para procurar:");
                String search = s.nextLine();
                if (p.getEquipas().containsKey(search)) {
                    showEquipa(p.getEquipas().get(search));
                    System.out.println(p.getEquipas().get(search).getplantel()+"");
                }
                else System.out.println("Não encontrado");
                break;
            case 2:
                for (Equipa e: p.getEquipas().values()){
                    showEquipa(e);
                }
                break;
            case 3:
                
                break;
            case 4:
                break;
            default:
                break;
        }
        System.out.println("-- press enter --");
        s.nextLine();
        return;
    }

    public static void showEquipa(Equipa e){
        System.out.println("Nome: " + e.getNome());
        System.out.println("Data de fundação: " + e.getDataDeFundação());
        System.out.print("Habilidade global: " + e.getHabilidadeGlobal());
        if(e.getHabilidadeGlobal()>60) System.out.println(" [Muito Elevada]");
        if(e.getHabilidadeGlobal()>50 && e.getHabilidadeGlobal()<60) System.out.println(" [Elevada]");
        if(e.getHabilidadeGlobal()>40 && e.getHabilidadeGlobal()<50) System.out.println(" [Mediana]");
        if(e.getHabilidadeGlobal()>30 && e.getHabilidadeGlobal()<40) System.out.println(" [Baixa]");
        if(e.getHabilidadeGlobal()<20) System.out.println(" [Muito Baixa]");
    }

    public static void showJogador(Jogador j){
        System.out.println("Nome: " + j.getNomeAtleta());
        System.out.println("Número de atleta: " + j.getIdAtleta());
        System.out.println("Idade " + j.getIdade());
        System.out.println("Habilidade geral: " + j.getHabilidade());
        if(j.getHabilidade()>60) System.out.println(" [Muito Elevada]");
        if(j.getHabilidade()>50 && j.getHabilidade()<60) System.out.println(" [Elevada]");
        if(j.getHabilidade()>40 && j.getHabilidade()<50) System.out.println(" [Mediana]");
        if(j.getHabilidade()>30 && j.getHabilidade()<40) System.out.println(" [Baixa]");
        if(j.getHabilidade()<20) System.out.println(" [Muito Baixa]");
    }

    public static void menu(ArrayList<String> l){
        int i = 1;
        for (String string : l) {
            if (i == 1) System.out.println(string); 
            else System.out.println("(" + (i-1) + "): " + string);
            i++;
        }
    }
}