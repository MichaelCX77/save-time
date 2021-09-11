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
@Table(name = "ST_CURSOS")
public class Cursos {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID")
  private User usuario;
  
  @Column(name = "CURSO")
  private String curso;
  
  @Column(name = "DATA_ATUAL")
  private Date data;
  
  @Column(name = "TEMPO_GASTO")
  private String tempoGasto;
  
  @Column(name = "CONCLUSAO")
  private Date conclusao;
  
  @Column(name = "TOTAL_AULAS")
  private int auTotal;
  
  @Column(name = "AULA_ATUAL")
  private int auAtual;
  
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
  
  public String getCurso() {
    return this.curso;
  }
  
  public void setCurso(String curso) {
    this.curso = curso;
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
  
  public int getAuTotal() {
    return this.auTotal;
  }
  
  public void setAuTotal(int auTotal) {
    this.auTotal = auTotal;
  }
  
  public int getAuAtual() {
    return this.auAtual;
  }
  
  public void setAuAtual(int auAtual) {
    this.auAtual = auAtual;
  }
}
