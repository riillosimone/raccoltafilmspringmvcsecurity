package it.prova.raccoltafilmspringmvc.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import it.prova.raccoltafilmspringmvc.model.Film;
import it.prova.raccoltafilmspringmvc.model.Regista;

public class FilmDTO {

	private Long id;

	@NotBlank(message = "{film.titolo.notblank}")
	@Size(min = 4, max = 40, message = "Il valore inserito '${validatedValue}' deve essere lungo tra {min} e {max} caratteri")
	private String titolo;

	@NotBlank(message = "{film.genere.notblank}")
	private String genere;

	@NotNull(message = "{film.dataPubblicazione.notnull}")
	private LocalDate dataPubblicazione;

	@NotNull(message = "{film.minutiDurata.notnull}")
	@Min(1)
	private Integer minutiDurata;

	@NotNull(message = "{film.regista.notnull}")
	private RegistaDTO regista;

	public FilmDTO() {
	}

	public FilmDTO(Long id, String titolo, String genere, LocalDate dataPubblicazione, Integer minutiDurata,
			RegistaDTO regista) {
		this.id = id;
		this.titolo = titolo;
		this.genere = genere;
		this.dataPubblicazione = dataPubblicazione;
		this.minutiDurata = minutiDurata;
		this.regista = regista;
	}

	public FilmDTO(Long id, String titolo, String genere, LocalDate dataPubblicazione, Integer minutiDurata) {
		this.id = id;
		this.titolo = titolo;
		this.genere = genere;
		this.dataPubblicazione = dataPubblicazione;
		this.minutiDurata = minutiDurata;
	}

	public FilmDTO(String titolo, String genere) {
		this.titolo = titolo;
		this.genere = genere;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}

	public LocalDate getDataPubblicazione() {
		return dataPubblicazione;
	}

	public void setDataPubblicazione(LocalDate dataPubblicazione) {
		this.dataPubblicazione = dataPubblicazione;
	}

	public Integer getMinutiDurata() {
		return minutiDurata;
	}

	public void setMinutiDurata(Integer minutiDurata) {
		this.minutiDurata = minutiDurata;
	}

	public RegistaDTO getRegista() {
		return regista;
	}

	public void setRegista(RegistaDTO regista) {
		this.regista = regista;
	}

	public Film buildFilmModel() {
		Regista registaModel = this.regista != null ? this.regista.buildRegistaModel() : null;

		return new Film(this.id, this.titolo, this.genere, this.dataPubblicazione, this.minutiDurata, registaModel);
	}

	public static FilmDTO buildFilmDTOFromModel(Film filmModel, boolean includeRegisti) {
		FilmDTO result = new FilmDTO(filmModel.getId(), filmModel.getTitolo(), filmModel.getGenere(),
				filmModel.getDataPubblicazione(), filmModel.getMinutiDurata());

		if (includeRegisti)
			result.setRegista(RegistaDTO.buildRegistaDTOFromModel(filmModel.getRegista()));

		return result;
	}

	public static List<FilmDTO> createFilmDTOListFromModelList(List<Film> modelListInput, boolean includeRegisti) {
		return modelListInput.stream().map(filmEntity -> {
			return FilmDTO.buildFilmDTOFromModel(filmEntity, includeRegisti);
		}).collect(Collectors.toList());
	}
}
