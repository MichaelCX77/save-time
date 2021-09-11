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
@Table(name = "ST_ESPIRITUAL")
public class Espiritual {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID")
  private User usuario;
  
  @Column(name = "ATIVIDADE")
  private String atividade;
  
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
  
  public String getAtividade() {
    return this.atividade;
  }
  
  public void setAtividade(String atividade) {
    this.atividade = atividade;
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
