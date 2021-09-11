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
@Table(name = "ST_LEITURAS")
public class Leituras {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID")
	private User usuario;

	@Column(name = "NOME_LIVRO")
	private String nomeLivro;

	@Column(name = "TOTAL_PAGINAS")
	private int pgTotal;

	@Column(name = "PAGINA_ATUAL")
	private int pgAtual;

	@Column(name = "DATA_ATUAL")
	private Date data;

	@Column(name = "TEMPO_GASTO")
	private String tempoGasto;

	@Column(name = "CONCLUSAO")
	private Date conclusao;

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

	public String getNomeLivro() {
		return this.nomeLivro;
	}

	public void setNomeCurso(String nomeLivro) {
		this.nomeLivro = nomeLivro;
	}

	public int getPgTotal() {
		return this.pgTotal;
	}

	public void setPgTotal(int pgTotal) {
		this.pgTotal = pgTotal;
	}

	public int getPgAtual() {
		return this.pgAtual;
	}

	public void setPgAtual(int pgAtual) {
		this.pgAtual = pgAtual;
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

	public Date getConclusao() {
		return this.conclusao;
	}

	public void setConclusao(Date conclusao) {
		this.conclusao = conclusao;
	}
}
