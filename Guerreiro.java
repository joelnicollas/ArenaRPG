package entidades;

import excecoes.PersonagemDerrotadoException;
import interfaces.Apresentavel;

public class Guerreiro extends Personagem implements Apresentavel {

    private int forcaFisica;
    public Guerreiro(String nome, int pontosVida, int poderAtaque, int forcaFisica) {
        super(nome, pontosVida, poderAtaque, TipoPersonagem.GUERREIRO);
        this.forcaFisica = forcaFisica;
    }

    public int getForcaFisica() {
        return forcaFisica;
    }

    @Override
    public void apresentar() {
        System.out.println("Sou " + nome + ", o Guerreiro! Minha espada nunca falha. "
            + "Forca fisica: " + forcaFisica + " | Vida: " + pontosVida);
    }

    @Override
    public void lutar(Personagem adversario) throws PersonagemDerrotadoException {
        if (this.pontosVida <= 0) {
            throw new PersonagemDerrotadoException(
                nome + " está derrotado e não pode lutar!"
            );
        }
        if (adversario.getPontosVida() <= 0) {
            throw new PersonagemDerrotadoException(
                adversario.getNome() + " já está derrotado antes do golpe!"
            );
        }

        int danoTotal = this.poderAtaque + this.forcaFisica;
        System.out.println("O Guerreiro " + nome + " atacou " + adversario.getNome() + " com sua espada!");
        adversario.receberDano(danoTotal);

        if (adversario.getPontosVida() <= 0) {
            System.out.println("*** VITORIA! O " + nome + " venceu a luta e derrotou o " + adversario.getNome() + "! ***");
        }
    }
}