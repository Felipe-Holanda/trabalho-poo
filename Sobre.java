import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Sobre extends JFrame implements ActionListener, MouseListener {
  JLabel lVersao, lData, lAutor;
  JTextArea texto;
  JButton bSair;

  public Sobre() {
    texto = new JTextArea();
    texto.setText("Sistema 1.0\nCriado em 01/06/2022\nFeito por Lucas Frazão e Tiago Figueiredo");
    add(texto);

    bSair = new JButton("Sair");
    bSair.addActionListener(this);
    add(bSair);

    setLayout(new FlowLayout());
    addMouseListener(this);
    setSize(300, 400);
    setVisible(true);
    setTitle("Sobre");
  }

  public String FalaOi() {
    return "Oi";
  }

  @Override
  public void actionPerformed(ActionEvent ae) {
    var source = ae.getSource();

    if (source == bSair) {
      setVisible(false);
    }
  }

  @Override
  public void mouseClicked(MouseEvent me) {
    JOptionPane.showMessageDialog(this, "Click");
    System.out.println("Clicado");
  }

  @Override
  public void mousePressed(MouseEvent me) {
    System.out.println("Pressionado");
  }

  @Override
  public void mouseReleased(MouseEvent me) {
    System.out.println("Click e solta");
  }

  @Override
  public void mouseEntered(MouseEvent me) {
    System.out.println("Entrou");
  }

  @Override
  public void mouseExited(MouseEvent me) {
    System.out.println("Saiu");
  }
}