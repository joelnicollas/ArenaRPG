package interfaces;

import entidades.Personagem;
import excecoes.ManaInsuficienteException;
import excecoes.PersonagemDerrotadoException;

public interface HabilidadeMagica {
    void lancarFeitico(Personagem adversario) throws ManaInsuficienteException, PersonagemDerrotadoException;
}
