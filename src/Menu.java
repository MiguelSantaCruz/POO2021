import java.util.ArrayList;
import java.util.List;

public class Menu {
    private String titulo;
    private List<String> opcoes;

    public Menu(){
        this.titulo = "NaN";
        this.opcoes = new ArrayList<>();
    }

    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    public void adicionaOpcao(String opcao){
        this.opcoes.add(opcao);
    }

    public void removeOpcao(String opcao){
        this.opcoes.remove(opcao);
    }

    public void show(){
        System.out.println("\u001B[36m" + titulo + "\u001B[0m");
        System.out.println("-----------------------------");
        int i = 1;
        for (String string : this.opcoes) {
            System.out.println("(" + i + "): " + string);
            i++;
        }
        System.out.print("> ");
    }
}