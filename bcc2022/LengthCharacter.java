package bcc2022;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class LengthCharacter extends PlainDocument {
  

  private int qtdCaracteres;

  public LengthCharacter(int qtdCaracteres) {
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
      super.insertString(offs, str.substring(0,qtdCaracteres), as);
    }
  }
}