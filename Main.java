import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Main extends JFrame implements ActionListener {
  JLabel lValor1, lValor2, lRes;
  JTextField tfValor1, tfValor2, tfRes;
  JButton bSoma, bSub, bLimpar, bSair, bmult, bdiv;
  JCheckBox cbLista;
  JTextArea taLista;
  JMenuBar mbBarra;
  JMenu mArquivo, mAjuda;
  JMenuItem miLimpar, miSair, miSobre;
  JComboBox<String> cbEstado, cbCidade;

  String[] ma = { "", "Imperatriz", "São Luís", "Bacabal" };
  String[] pa = { "", "Belém", "Marabá", "Salinópolis" };
  String[] pi = { "", "Teresina", "Luiz", "Timon" };

  Calculadora c = new Calculadora();

  private void initializeTextFields() {
    lValor1 = new JLabel("Valor 1: ");
    tfValor1 = new JTextField(5);
    tfValor1.setToolTipText("Digite o primeiro valor");
    lValor2 = new JLabel("Valor 2: ");
    tfValor2 = new JTextField(5);
    tfValor1.addActionListener(this);
    tfValor2.addActionListener(this);
    lRes = new JLabel("Resposta: ");
    tfRes = new JTextField(5);
    tfRes.setEditable(false);
  }

  private void initializeMenus() {
    miLimpar = new JMenuItem("Limpar");
    miLimpar.addActionListener(this);

    miSair = new JMenuItem("Sair");
    miSair.addActionListener(this);

    miSobre = new JMenuItem("Sobre");
    miSobre.addActionListener(this);

    mArquivo = new JMenu("Arquivo");
    mAjuda = new JMenu("Ajuda");

    mArquivo.add(miLimpar);
    mArquivo.addSeparator();

    mArquivo.add(miSair);
    mAjuda.add(miSobre);

    mbBarra = new JMenuBar();
    mbBarra.add(mArquivo);
    mbBarra.add(mAjuda);
    setJMenuBar(mbBarra);
  }

  private void addComponents(Component components[]) {
    for (var i = 0; i < components.length; i++) {
      add(components[i]);
    }
  }

  private void initializeComponents() {
    initializeMenus();
    initializeTextFields();

    cbLista = new JCheckBox("Listar");
    taLista = new JTextArea(10, 20);

    addComponents(new Component[] {
        lValor1,
        tfValor1,
        lValor2,
        tfValor2,
        lRes,
        tfRes
    });

    initializeButtons();

    taLista.setEditable(false);
    taLista.setBackground(Color.WHITE);
    cbLista.addActionListener(this);

    addComponents(new Component[] {
        bSoma,
        bSub,
        bmult,
        bdiv,
        bLimpar,
        bSair,
        cbLista,
        taLista
    });

    setLayout(new FlowLayout());
    setSize(500, 500);
    setVisible(true);

    String[] estado = { "", "Maranhão", "Pará", "Piauí" };
    cbEstado = new JComboBox<String>(estado);
    add(cbEstado);

    cbCidade = new JComboBox<String>(pi);
    add(cbCidade);

    cbEstado.addActionListener(this);
  }

  private void initializeButtons() {
    bSoma = new JButton("SOMAR");
    bSoma.addActionListener(this);
    bSub = new JButton("SUBTRAIR");
    bSub.addActionListener(this);
    bLimpar = new JButton("LIMPAR");
    bLimpar.addActionListener(this);
    bSair = new JButton("SAIR");
    bmult = new JButton("MULTIPLICAR");
    bmult.addActionListener(this);
    bdiv = new JButton("DIVIDIR");
    bdiv.addActionListener(this);
    bSair.addActionListener(this);
  }

  public Main() {
    initializeComponents();
  }

  public static void main(String[] args) {
    Main p = new Main();
    p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  private void clearText() {
    tfValor1.setText("");
    tfValor2.setText("");
    tfRes.setText("");
    taLista.setText("");
  }

  private void showMessageDialog(String message, String title, int option) {
    JOptionPane.showMessageDialog(null, message, title, option);
  }

  private void showInformationMessageDialog(String message, String title) {
    showMessageDialog(message, title, JOptionPane.INFORMATION_MESSAGE);
  }

  private void showEmptyTextFieldErrorMessage() {
    showMessageDialog("CAMPO VAZIO", "Digite valores nos campos", JOptionPane.INFORMATION_MESSAGE);
  }

  @Override
  public void actionPerformed(ActionEvent ae) {
    var source = ae.getSource();
    var taListaText = taLista.getText();
    var tfValor1Text = tfValor1.getText();
    var tfValor2Text = tfValor2.getText();
    var tfValor1TextLength = tfValor1Text.length();
    var tfValor2TextLength = tfValor2Text.length();
    var cbListaIsSelected = cbLista.isSelected();

    if (source == tfValor1) {
      tfValor2.requestFocus();
    }

    if (source == bSair || source == miSair) {
      System.exit(0);
    }

    if (source == bLimpar || source == miLimpar) {
      clearText();
    }

    if (source == bSoma) {
      if (tfValor1TextLength > 0 && tfValor2TextLength > 0) {
        String soma = c.soma(tfValor1Text, tfValor2Text);

        tfRes.setText(soma);

        if (cbLista.isSelected()) {
          taLista.setText(
              String.format("%s%s + %s = %s\n", taListaText, tfValor1Text, soma));
        }

        return;
      }

      showEmptyTextFieldErrorMessage();
    }

    if (source == bSub) {
      if (tfValor1TextLength > 0 && tfValor2TextLength > 0) {
        String sub = c.subtrai(tfValor1Text, tfValor2Text);
        tfRes.setText(sub);

        if (cbListaIsSelected) {
          taLista.setText(
              String.format("%s%s - %s = %s\n", taListaText, tfValor1Text, tfValor2Text, sub));
        }

        return;
      }

      showEmptyTextFieldErrorMessage();
    }

    if (source == bmult) {
      if (tfValor1TextLength > 0 && tfValor2TextLength > 0) {
        String mult = c.multiplica(tfValor1Text, tfValor2Text);
        tfRes.setText(mult);

        if (cbListaIsSelected) {
          taLista.setText(
              String.format("%s%s * %s = %s\n", taListaText, tfValor1Text, tfValor2Text, mult));
        }

        return;
      }

      showInformationMessageDialog("CAMPO VAZIO", "Digite valores nos campos");
    }

    if (source == bdiv) {
      if (tfValor2TextLength > 0 && tfValor1TextLength > 0 && !tfValor2Text.equals("0")) {
        String div = c.divide(tfValor1Text, tfValor2Text);
        tfRes.setText(div);

        if (cbListaIsSelected) {
          taLista.setText(
              String.format("%s%s / %s = %s\n", taListaText, tfValor1Text, tfValor2Text, div));
        }

        return;
      }

      tfValor2.setBackground(Color.red);
      showInformationMessageDialog("Não foi possível realizar a divisão", "Entrada Inválida");
      tfValor2.setBackground(null);
    }

    if (source == miSobre) {
      Sobre s = new Sobre();
      JOptionPane.showMessageDialog(null, s.FalaOi());
    }

    if (source == cbEstado) {
      switch (cbEstado.getSelectedIndex()) {
        case 0:
          cbCidade.removeAllItems();
          break;
        case 1:
          cbCidade.removeAllItems();

          for (int i = 0; i <= 3; i++)
            cbCidade.insertItemAt(ma[i], i);

          break;
        case 2:
          cbCidade.removeAllItems();

          for (int i = 0; i <= 3; i++)
            cbCidade.insertItemAt(pa[i], i);

          break;
        case 3:
          cbCidade.removeAllItems();

          for (int i = 0; i <= 3; i++)
            cbCidade.insertItemAt(pi[i], i);

          break;
      }
    }
  }
}