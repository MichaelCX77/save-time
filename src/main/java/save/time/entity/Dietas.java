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
@Table(name = "ST_DIETAS")
public class Dietas {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID")
  private User usuario;
  
  @Column(name = "DATA_ATUAL")
  private Date data;
  
  @Column(name = "HORARIO")
  private String horario;
  
  @Column(name = "REFEICAO")
  private String refeicao;
  
  @Column(name = "QUANTIDADE")
  private String quantidade;
  
  @Column(name = "ALIMENTO")
  private String alimento;
  
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
  
  public Date getData() {
    return this.data;
  }
  
  public void setData(Date data) {
    this.data = data;
  }
  
  public String getHorario() {
    return this.horario;
  }
  
  public void setHorario(String horario) {
    this.horario = horario;
  }
  
  public String getRefeicao() {
    return this.refeicao;
  }
  
  public void setRefeicao(String refeicao) {
    this.refeicao = refeicao;
  }
  
  public String getQuantidade() {
    return this.quantidade;
  }
  
  public void setQuantidade(String quantidade) {
    this.quantidade = quantidade;
  }
  
  public String getAlimento() {
    return this.alimento;
  }
  
  public void setAlimento(String alimento) {
    this.alimento = alimento;
  }
}
