import java.util.ArrayList;
import java.util.List;

public class Menu {
    private String titulo;
    private List<String> opcoes;

    /**
     * Construtor vazio de Menu.
     */
    public Menu(){
        this.titulo = "NaN";
        this.opcoes = new ArrayList<>();
    }

    /**
     * Alterar o titulo do Menu.
     * @param titulo O novo titulo.
     */
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    /**
     * Adicionar uma opção ao menu.
     * @param opcao A nova opção.
     */
    public void adicionaOpcao(String opcao){
        this.opcoes.add(opcao);
    }

    /**
     * Remover uma opção do menu.
     * @param opcao A opção a remover.
     */
    public void removeOpcao(String opcao){
        this.opcoes.remove(opcao);
    }

    /**
     * Mostrar as opções do Menu.
     */
    public void show(boolean cursorVisible){
        System.out.println("\u001B[36m" + titulo + "\u001B[0m");
        System.out.println("-----------------------------");
        int i = 1;
        for (String string : this.opcoes) {
            System.out.println("(" + i + "): " + string);
            i++;
        }
        if(cursorVisible) System.out.print("> ");
    }
}