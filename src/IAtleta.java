/**
 * Classe Atleta
 * @version 1
 */

public interface IAtleta {
    public int getIdade();
    public String getNomeAtleta();
    public int getIdAtleta();
    public void setIdAtleta(int idAtleta);
    public void setNomeAtleta(String nomeAtleta);
    public void setIdade(int idade);
    public String toString();
    public IAtleta clone();
}
