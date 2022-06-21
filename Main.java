import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Main extends JFrame implements ActionListener{
    JLabel lValor1, lValor2, lRes;
    JTextField tfValor1, tfValor2, tfRes;
    JButton bSoma, bSub, bLimpar, bSair, bmult, bdiv;
    JCheckBox cbLista;
    JTextArea taLista;
    JMenuBar mbBarra;
    JMenu mArquivo, mAjuda;
    JMenuItem miLimpar, miSair, miSobre;
    JComboBox cbEstado, cbCidade;
    
    String [] ma = {"","Imperatriz","São Luís", "Bacabal"};
    String [] pa = {"","Belém","Marabá","Salinópolis"};
    String [] pi = {"","Teresina","Luiz","Timon"};
    
    Calculadora c = new Calculadora();
    
    public Main(){
        
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
        cbLista = new JCheckBox("Listar");
        taLista = new JTextArea(10,20);
        add(lValor1);
        add(tfValor1);
        add(lValor2);
        add(tfValor2);
        add(lRes);
        add(tfRes);
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
        taLista.setEditable(false);
        taLista.setBackground(Color.WHITE);
        cbLista.addActionListener(this);
        add(bSoma);
        add(bSub);
        add(bmult);
        add(bdiv);
        add(bLimpar);
        add(bSair);
        add(cbLista);
        add(taLista);
        
        setLayout(new FlowLayout());
        setSize(500, 500);
        setVisible(true);
        
                
        String [] estado = {"","Maranhão","Pará", "Piauí"};
        cbEstado = new JComboBox(estado);
        add(cbEstado);
        
        cbCidade = new JComboBox(pi);
        add(cbCidade);
        
        cbEstado.addActionListener(this);
        
              
    }
    public static void main(String[] args) {
        Main p = new Main();
        p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
       if(ae.getSource() == tfValor1){
            tfValor2.requestFocus();
       }
       if((ae.getSource() == bSair)||(ae.getSource() == miSair)){
            System.exit(0);
       }
       if((ae.getSource() == bLimpar)||(ae.getSource() == miLimpar)){
            tfValor1.setText("");
            tfValor2.setText("");
            tfRes.setText("");
            taLista.setText("");
      }
       if(ae.getSource() == bSoma){
            if((tfValor1.getText().length() > 0) && (tfValor2.getText().length() > 0)){
                String s =  c.soma(tfValor1.getText(),tfValor2.getText());

                tfRes.setText(s);
                if(cbLista.isSelected()){
                    taLista.setText(taLista.getText() + tfValor1.getText() + " + " + tfValor2.getText() + " = " + s + " \n");
                }
            } else {
                JOptionPane.showMessageDialog(null, "CAMPO VAZIO","Digite valores nos campos",JOptionPane.INFORMATION_MESSAGE);
           }
       }
       if(ae.getSource() == bSub){
           if((tfValor1.getText().length() > 0) && (tfValor2.getText().length() > 0)){
                String sub = c.subtrai(tfValor1.getText(),tfValor2.getText());
                tfRes.setText(sub);
                if(cbLista.isSelected()){
                    taLista.setText(taLista.getText() + tfValor1.getText() + " - " + tfValor2.getText() + " = " + sub + " \n");
                }
           } else {
                JOptionPane.showMessageDialog(null, "CAMPO VAZIO","Digite valores nos campos",JOptionPane.INFORMATION_MESSAGE);
           }
     
       }
       if(ae.getSource() == bmult){
            if((tfValor1.getText().length() > 0) && (tfValor2.getText().length() > 0)){
                String mult = c.multiplica(tfValor1.getText(),tfValor2.getText());
                tfRes.setText(mult);
               if(cbLista.isSelected()){
                    taLista.setText(taLista.getText() + tfValor1.getText() + " * " + tfValor2.getText() + " = " + mult + " \n");
               }
            } else {
                JOptionPane.showMessageDialog(null, "CAMPO VAZIO","Digite valores nos campos",JOptionPane.INFORMATION_MESSAGE);
           }
       }
        if(ae.getSource() == bdiv){
            if((tfValor2.getText().length() > 0) && (tfValor1.getText().length() > 0) && (!"0".equals(tfValor2.getText()))){
                String div = c.divide(tfValor1.getText(),tfValor2.getText());
                 tfRes.setText(div);
                 if(cbLista.isSelected()){
                    taLista.setText( taLista.getText() + tfValor1.getText() + " / " + tfValor2.getText() + " = " + div + " \n");
                }
            } else {
                tfValor2.setBackground(Color.red);
                JOptionPane.showMessageDialog(null, "Não foi possível realizar a divisão","Entrada Inválida",JOptionPane.INFORMATION_MESSAGE);
                tfValor2.setBackground(null);
           }
        }
        // Sobre
        if(ae.getSource() == miSobre) {
           Sobre s = new Sobre(); //nova janela
           //JOptionPane.showMessageDialog(null, s.FalaOi());;
        }
        if (ae.getSource() == cbEstado){
            switch (cbEstado.getSelectedIndex()){
                case 0:
                    cbCidade.removeAllItems();
                    break;
                case 1:
                    cbCidade.removeAllItems();
                    for (int i=0;i<=3;i++){
                         cbCidade.insertItemAt(ma[i], i);
                    }
                    break;
                case 2:
                    cbCidade.removeAllItems();
                    for (int i=0;i<=3;i++){
                         cbCidade.insertItemAt(pa[i], i);
                    }
                    break;
                case 3:
                    cbCidade.removeAllItems();
                    for (int i=0;i<=3;i++){
                         cbCidade.insertItemAt(pi[i], i);
                    }
                    break;
                    }
                        }
                                     
            }
        }