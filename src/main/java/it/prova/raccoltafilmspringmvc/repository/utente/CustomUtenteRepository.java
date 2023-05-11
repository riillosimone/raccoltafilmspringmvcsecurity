package it.prova.raccoltafilmspringmvc.repository.utente;

import java.util.List;

import it.prova.raccoltafilmspringmvc.model.Utente;

public interface CustomUtenteRepository {

	List<Utente> findByExample(Utente example);
}
