package save.time.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ST_IDIOMAS")
public class Idiomas {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID")
	private User usuario;

	@Column(name = "IDIOMA")
	private String idioma;

	@Column(name = "DATA_ATUAL")
	private Date data;

	@Column(name = "TEMPO_GASTO")
	private String tempoGasto;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUsuario() {
		return this.usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}

	public String getIdioma() {
		return this.idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getTempoGasto() {
		return this.tempoGasto;
	}

	public void setTempoGasto(String tempoGasto) {
		this.tempoGasto = tempoGasto;
	}
}
