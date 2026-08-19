package entidades;

import excecoes.PersonagemDerrotadoException;
import interfaces.Apresentavel;

public final class Chefao extends Personagem implements Apresentavel {

    private boolean furia;
    public Chefao(String nome, int pontosVida, int poderAtaque, boolean furia) {
        super(nome, pontosVida, poderAtaque, TipoPersonagem.CHEFAO);
        this.furia = furia;
    }

    public boolean isFuria() {
        return furia;
    }
    public void setFuria(boolean furia) {
        this.furia = furia;
    }

    @Override
    public void apresentar() {
        System.out.println("EU SOU " + nome + ", O CHEFAO! Ninguem sobrevive a minha ira. "
            + "Furia ativa: " + furia + " | Vida: " + pontosVida);
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

        int dano = furia ? this.poderAtaque * 2 : this.poderAtaque;
        System.out.println("O Chefao " + nome + " esmagou " + adversario.getNome() + "!"
            + (furia ? " [FURIA ATIVA - dano dobrado!]" : ""));
        adversario.receberDano(dano);

        if (adversario.getPontosVida() <= 0) {
            System.out.println("*** VITORIA! O " + nome + " venceu a luta e derrotou o " + adversario.getNome() + "! ***");
        }
    }
}
