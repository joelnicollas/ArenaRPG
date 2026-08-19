import entidades.Chefao;
import entidades.Guerreiro;
import entidades.Mago;
import entidades.Personagem;
import excecoes.ManaInsuficienteException;
import excecoes.PersonagemDerrotadoException;

import java.util.ArrayList;

public class ArenaMain {

    public static void main(String[] args) {

        Guerreiro guerreiro = new Guerreiro("Aragorn", 150, 30, 20);
        Mago mago = new Mago("Gandalf", 100, 25, 80);
        Chefao chefao = new Chefao("Sauron", 300, 50, false);

        System.out.println("========================================");
        System.out.println("     BEM-VINDOS A ARENA DE HEROIS!     ");
        System.out.println("========================================");

        System.out.println("\n[1] MONITORAMENTO GLOBAL");
        System.out.println("Total de combatentes na arena: " + Personagem.getTotalPersonagens());
        System.out.println("\nApresentacao dos combatentes:");
        guerreiro.apresentar();
        mago.apresentar();
        chefao.apresentar();

        System.out.println("\n[2] STATUS INICIAL DA ARENA (Polimorfismo Base)");
        ArrayList<Personagem> arena = new ArrayList<>();
        arena.add(guerreiro);
        arena.add(mago);
        arena.add(chefao);

        for (Personagem p : arena) {
            p.exibirStatus();
        }

        System.out.println("\n[3] INTERACAO, SOBRECARGA E VITORIA");

        System.out.println("\n--- Guerreiro vs Mago ---");
        try {
            guerreiro.lutar(mago);
            System.out.println("Vida do Mago apos o ataque: " + mago.getPontosVida());
        } catch (PersonagemDerrotadoException e) {
            System.out.println("[ERRO] " + e.getMessage());
        }

        System.out.println("\n--- Mago vs Guerreiro (ataque fisico) ---");
        try {
            mago.lutar(guerreiro);
            System.out.println("Vida do Guerreiro apos o ataque: " + guerreiro.getPontosVida());
        } catch (PersonagemDerrotadoException e) {
            System.out.println("[ERRO] " + e.getMessage());
        }

        System.out.println("\n--- Mago vs Chefao (ataque fisico) ---");
        try {
            mago.lutar(chefao);
            System.out.println("Vida do Chefao apos o ataque: " + chefao.getPontosVida());
        } catch (PersonagemDerrotadoException e) {
            System.out.println("[ERRO] " + e.getMessage());
        }

        System.out.println("\n--- Guerreiro vs Chefao (ataque fisico) ---");
        try {
            guerreiro.lutar(chefao);
            System.out.println("Vida do Chefao apos o ataque: " + chefao.getPontosVida());
        } catch (PersonagemDerrotadoException e) {
            System.out.println("[ERRO] " + e.getMessage());
        }

        System.out.println("\n--- Chefao vs Guerreiro (sem furia) + Guerreiro usa DEFESA ATIVA ---");
        try {
            int danoChefao = chefao.getPoderAtaque();
            System.out.println("O Chefao " + chefao.getNome() + " ataca! Dano base: " + danoChefao);
            System.out.println("O Guerreiro " + guerreiro.getNome() + " ativa sua defesa e recebe apenas metade do dano!");
            guerreiro.receberDano(danoChefao, true);
            System.out.println("Vida do Guerreiro apos defesa ativa: " + guerreiro.getPontosVida());
        } catch (PersonagemDerrotadoException e) {
            System.out.println("[ERRO] " + e.getMessage());
        }

        System.out.println("\n--- Sequencia de golpes: Guerreiro derrota o Chefao ---");
        System.out.println("(Chefao com vida atual: " + chefao.getPontosVida() + ")");
        chefao.setFuria(false);
        boolean chefaoVivo = true;
        int rodada = 1;
        while (chefaoVivo) {
            try {
                System.out.println("\nRodada " + rodada + " - Vida do Chefao: " + chefao.getPontosVida());
                guerreiro.lutar(chefao);
                if (chefao.getPontosVida() <= 0) {
                    chefaoVivo = false;
                }
                rodada++;
            } catch (PersonagemDerrotadoException e) {
                System.out.println("[ERRO] " + e.getMessage());
                chefaoVivo = false;
            }
        }

        System.out.println("\n[4] TRATAMENTO DE EXCECOES");

        System.out.println("\n--- Mago usa lancarFeitico no Chefao ate a mana acabar ---");
        Chefao chefao2 = new Chefao("Morgoth", 500, 60, true);
        System.out.println("Novo chefao instanciado: " + chefao2.getNome() + " | Vida: " + chefao2.getPontosVida());
        System.out.println("Mana inicial do Mago: " + mago.getMana());

        boolean magoTemMana = true;
        while (magoTemMana) {
            try {
                mago.lancarFeitico(chefao2);
                System.out.println("Vida do Chefao apos feitico: " + chefao2.getPontosVida());
                if (chefao2.getPontosVida() <= 0) {
                    break;
                }
            } catch (ManaInsuficienteException e) {
                System.out.println("[AVISO] " + e.getMessage());
                magoTemMana = false;
            } catch (PersonagemDerrotadoException e) {
                System.out.println("[AVISO] " + e.getMessage());
                magoTemMana = false;
            }
        }

        System.out.println("\n--- Tentativa de atacar personagem ja derrotado (PersonagemDerrotadoException) ---");
        System.out.println("O Chefao '" + chefao.getNome() + "' esta com vida: " + chefao.getPontosVida());
        System.out.println("Tentando fazer o Mago lutar contra ele...");
        try {
            mago.lutar(chefao);
        } catch (PersonagemDerrotadoException e) {
            System.out.println("[EXCECAO CAPTURADA] PersonagemDerrotadoException: " + e.getMessage());
        }

        System.out.println("\n========================================");
        System.out.println("          FIM DA SIMULACAO              ");
        System.out.println("Total de combatentes criados: " + Personagem.getTotalPersonagens());
        System.out.println("========================================");
    }
}
