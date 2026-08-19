package entidades;

import excecoes.PersonagemDerrotadoException;

public abstract class Personagem {

    private static int totalPersonagens = 0;

    protected String nome;
    protected int pontosVida;
    protected int poderAtaque;
    protected TipoPersonagem tipo;

    public Personagem(String nome, int pontosVida, int poderAtaque, TipoPersonagem tipo) {
        this.nome = nome;
        this.pontosVida = pontosVida;
        this.poderAtaque = poderAtaque;
        this.tipo = tipo;
        totalPersonagens++;
    }

    public static int getTotalPersonagens() {
        return totalPersonagens;
    }

    public String getNome() {
        return nome;
    }

    public int getPontosVida() {
        return pontosVida;
    }

    public int getPoderAtaque() {
        return poderAtaque;
    }

    public TipoPersonagem getTipo() {
        return tipo;
    }

    public abstract void lutar(Personagem adversario) throws PersonagemDerrotadoException;

    public void receberDano(int dano) throws PersonagemDerrotadoException {
        if (this.pontosVida <= 0) {
            throw new PersonagemDerrotadoException(
                this.nome + " já está derrotado e não pode receber mais dano!"
            );
        }
        this.pontosVida -= dano;
    }

    public void receberDano(int dano, boolean defesaAtiva) throws PersonagemDerrotadoException {
        if (this.pontosVida <= 0) {
            throw new PersonagemDerrotadoException(
                this.nome + " já está derrotado e não pode receber mais dano!"
            );
        }
        if (defesaAtiva) {
            this.pontosVida -= dano / 2;
        } else {
            this.pontosVida -= dano;
        }
    }

    public final void exibirStatus() {
        System.out.println("------------------------------------");
        System.out.println("Nome      : " + nome);
        System.out.println("Tipo      : " + tipo);
        System.out.println("Vida      : " + pontosVida);
        System.out.println("Ataque    : " + poderAtaque);
        System.out.println("------------------------------------");
    }
}
