import java.util.ArrayList;

public interface IJogador extends IAtleta {
   public boolean getSuplente ();
   public void setSuplente (boolean t);
   public boolean getTitular();
   public void setTitular(boolean t);
   public int getPosicao();
   public void setPosicao(int p);
   public ArrayList<IEquipa> getEquipas();
   public void addEquipa(IEquipa eq);
   public int getNumeroJogador();
   public float getHabilidade();
   public int getVelocidade();
   public int getResistencia();
   public int getDestreza();
   public int getImpulsao();
   public int getJogoDeCabeca();
   public int getRemate();
   public int getCapacidadeDePasse();
   public void setNumeroJogador(int numero);
   public void setVelocidade(int velocidade);
   public void setResistencia(int resistencia);
   public void setDestreza(int destreza);
   public void setImpulsao(int impulsao);
   public void setJogoDeCabeca(int jogo_de_cabeça);
   public void setRemate(int remate);
   public void setCapacidadeDePasse(int capacidade_de_passe);
   public void setHabilidade(float habilidade);
   public IJogador clone();
   public String toString();
}
