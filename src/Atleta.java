/**
 * Classe Atleta
 * @version 1
 */

public class Atleta {
    private String idAtleta;
    private String nomeAtleta;
    private int idade;

    /**
     * Construtor vazio para objetos da classe Atleta
     */
    public Atleta (){
        this.idAtleta = "NaN";
        this.nomeAtleta = "NaN";
        this.idade = 0;
    }
    /**
     * Construtor de cópia para objetos da classe Jogador
     */
    public Atleta(Atleta a){
        this.idAtleta=a.getIdAtleta();
        this.nomeAtleta=a.getNomeAtleta();
        this.idade = a.getIdade();
    }
    /**
     * Construtor para objetos da classe Jogador
     */
    public Atleta(String idAtleta, String nomeAtleta, int idade) {
        this.idAtleta = idAtleta;
        this.nomeAtleta = nomeAtleta;
        this.idade = idade;
    }
    /**
     * Retorna a idade do Atleta
     */
    public int getIdade() {
        return this.idade;
    }
    /**
     * Retorna o nome do Atleta
     */
    public String getNomeAtleta() {
        return this.nomeAtleta;
    }
    /**
     * Retorna o id do Atleta
     */
    public String getIdAtleta() {
        return this.idAtleta;
    }
    /**
     * Atribui um id ao Atleta
     */

    public void setIdAtleta(String idAtleta) {
        this.idAtleta = idAtleta;
    }
    /**
     * Atribui o nome ao Atleta
     */
    public void setNomeAtleta(String nomeAtleta) {
        this.nomeAtleta = nomeAtleta;
    }
    /**
     * Atribui a idade ao Atleta
     */
    public void setIdade(int idade) {
        this.idade = idade;
    }

    /**
     * Converte para string o objeto Atleta
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" IdAtleta: ");
        sb.append(this.getIdAtleta());
        sb.append(" Nome: ");
        sb.append(getNomeAtleta());
        sb.append(" Idade: ");
        sb.append(getIdade());
        return sb.toString();
    }
    /**
     * Clona o objeto Atleta
     */
    public Atleta clone(){
        Atleta a = new Atleta();
        a.setIdAtleta(this.getIdAtleta());
        a.setNomeAtleta(this.getNomeAtleta());
        a.setIdade(this.getIdade());
        return a;
    }
}
