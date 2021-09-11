package save.time.ferramentas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;
import org.joda.time.ReadableInstant;

public class DatasUtil {
  public static Calendar alteraData(Calendar cal, Integer dia, Integer hora, Integer minuto, Integer segundo, Integer milissegundo) {
    if (dia.intValue() != 0)
      cal.add(5, dia.intValue()); 
    cal.set(11, hora.intValue());
    cal.set(12, minuto.intValue());
    cal.set(13, segundo.intValue());
    cal.set(14, milissegundo.intValue());
    return cal;
  }
  
  public static SimpleDateFormat getSimpleFormatDate(String formato) {
    SimpleDateFormat dataAtual = new SimpleDateFormat(formato);
    return dataAtual;
  }
  
  public static Calendar getCalendarDate() {
    Date d = new Date();
    Calendar cal = new GregorianCalendar();
    cal.setTime(d);
    return cal;
  }
  
  public static String getData(String formato) {
    Date dataTemp = new Date();
    DateTime dataAtual = new DateTime(dataTemp);
    String data = dataAtual.toString(formato);
    return data;
  }
  
  public static String convertAndFormatToString(Date data, String formato) {
    SimpleDateFormat dataAtual = new SimpleDateFormat(formato);
    String strDate = dataAtual.format(data);
    return strDate;
  }
  
  public static Integer calcAnosPorDataDeNascimento(Integer ano, Integer mes, Integer dia) {
    DateTime dataAtual = new DateTime();
    DateTime dataNascimento = new DateTime(ano.intValue(), mes.intValue(), dia.intValue(), 0, 0);
    Interval intervalo = new Interval((ReadableInstant)dataNascimento, (ReadableInstant)dataAtual);
    Period period = intervalo.toPeriod();
    return Integer.valueOf(period.getYears());
  }
  
  public static boolean comparaDatasDate(Date dataInicial, Date dataFinal) {
    boolean maior = dataFinal.after(dataInicial);
    return maior;
  }
  
  public static Boolean comparaDatasString(String dataInicial, String dataFinal, String formato) throws ParseException {
    SimpleDateFormat formatoString = new SimpleDateFormat(formato);
    Date dataFinalDate = formatoString.parse(dataFinal);
    Date dataInicialDate = formatoString.parse(dataInicial);
    boolean maior = dataFinalDate.after(dataInicialDate);
    return Boolean.valueOf(maior);
  }
  
  public static Boolean comparaDatasSqlDate(Date dataInicial, Date dataFinal, String formato) throws ParseException {
    String dataEntregaString = dataFinal.toString();
    String dataAtualString = dataInicial.toString();
    SimpleDateFormat formatoString = new SimpleDateFormat(formato);
    Date dataFinalDate = formatoString.parse(dataEntregaString);
    Date dataAtualDate = formatoString.parse(dataAtualString);
    boolean maior = dataFinalDate.after(dataAtualDate);
    return Boolean.valueOf(maior);
  }
}
