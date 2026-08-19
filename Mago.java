package entidades;

import excecoes.ManaInsuficienteException;
import excecoes.PersonagemDerrotadoException;
import interfaces.Apresentavel;
import interfaces.HabilidadeMagica;

public class Mago extends Personagem implements HabilidadeMagica, Apresentavel {

    private int mana;

    public Mago(String nome, int pontosVida, int poderAtaque, int mana) {
        super(nome, pontosVida, poderAtaque, TipoPersonagem.MAGO);
        this.mana = mana;
    }

    public int getMana() {
        return mana;
    }

    @Override
    public void apresentar() {
        System.out.println("Sou " + nome + ", o Mago! Meus feiticos sao devastadores. "
            + "Mana: " + mana + " | Vida: " + pontosVida);
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

        System.out.println("O Mago " + nome + " golpeou " + adversario.getNome() + " com seu cajado!");
        adversario.receberDano(this.poderAtaque);

        if (adversario.getPontosVida() <= 0) {
            System.out.println("*** VITORIA! O " + nome + " venceu a luta e derrotou o " + adversario.getNome() + "! ***");
        }
    }

    @Override
    public void lancarFeitico(Personagem adversario) throws ManaInsuficienteException, PersonagemDerrotadoException {
        if (this.mana < 20) {
            throw new ManaInsuficienteException(
                nome + " não tem mana suficiente para lançar o feitiço! Mana atual: " + mana
            );
        }
        if (adversario.getPontosVida() <= 0) {
            throw new PersonagemDerrotadoException(
                adversario.getNome() + " já está derrotado!"
            );
        }

        this.mana -= 20;
        int danoFeitico = this.poderAtaque * 3;
        System.out.println("O Mago " + nome + " lançou um feitiço devastador em " + adversario.getNome()
            + "! Dano: " + danoFeitico + " | Mana restante: " + mana);
        adversario.receberDano(danoFeitico);

        if (adversario.getPontosVida() <= 0) {
            System.out.println("*** VITORIA! O " + nome + " venceu a luta e derrotou o " + adversario.getNome() + " com seu feitico! ***");
        }
    }
}
