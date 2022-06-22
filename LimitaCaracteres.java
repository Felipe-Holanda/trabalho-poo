import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class LimitaCaracteres extends PlainDocument {
  public enum TipoEntada {
    NUMERO, STRING
  };

  private int qtdCaracteres;

  public LimitaCaracteres(int qtdCaracteres, TipoEntada tpEntrada) {
    this.qtdCaracteres = qtdCaracteres;
  }

  @Override
  public void insertString(int offs, String str, AttributeSet as) throws BadLocationException {
    if (str == null || getLength() == qtdCaracteres) {
      return;
    }

    int totalCaracteres = getLength() + str.length();

    if (totalCaracteres <= qtdCaracteres) {
      super.insertString(offs, str, as);
    } else {
      String nova = str.substring(0, qtdCaracteres);
      super.insertString(offs, nova, as);
    }
  }
}