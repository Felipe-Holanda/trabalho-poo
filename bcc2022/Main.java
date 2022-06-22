package bcc2022;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class Main extends JFrame implements ActionListener {
  JLabel lName, lValue, lBrand, lEquipment,lPayment;
  JTextField tfName, tfValue, tfBrand;
  JRadioButton rbAvista, rbParcelado;
  JButton bSalvar, bDeletar, bLimpar, bSair;
  ButtonGroup buttonGroup;
  JMenuBar mbBarra;
  JMenu mArquivo, mAjuda;
  JMenuItem miSobre;

  String[] equipamento = { "", "Processador", "Impressora", "Modem", "Monitor", "Teclado" };
  JComboBox<String> cbEquipamento;

  JTable tabela;
  DefaultTableModel dtModel;
  JScrollPane barraRolagem;

  public Main() {
    var layout = new FlowLayout();

    setLayout(layout);
    setSize(800, 600);
    setTitle("Loja Tech");
    setLocationRelativeTo(null);
    setVisible(true);

    miSobre = new JMenuItem("Sobre");
    miSobre.addActionListener(this);
    mAjuda = new JMenu("Ajuda");
    mAjuda.add(miSobre);
    
    mbBarra = new JMenuBar();
    mbBarra.add(mAjuda);
    setJMenuBar(mbBarra);
    
    lName = new JLabel("Nome:");
    add(lName);

    tfName = new JTextField(14);
    tfName.setDocument(new LengthCharacter(60));
    add(tfName);

    lValue = new JLabel("Valor:");
    add(lValue);

    tfValue = new JTextField(10);
    tfValue.setDocument(new LengthCharacter(20));
    add(tfValue);

    lBrand = new JLabel("Marca:");
    add(lBrand);

    tfBrand = new JTextField(13);
    tfBrand.setDocument(new LengthCharacter(60));
    add(tfBrand);

    lEquipment = new JLabel("Equipamento:");
    add(lEquipment);
    
    cbEquipamento = new JComboBox<String>(equipamento);
    add(cbEquipamento);

    
    lPayment = new JLabel("Forma de Pagamento:");
        add(lPayment);

        rbAvista = new JRadioButton("À vista");
        add(rbAvista);
        rbParcelado = new JRadioButton("Parcelado");
        add(rbParcelado);
    
        buttonGroup = new ButtonGroup();
        buttonGroup.add(rbAvista);
        buttonGroup.add(rbParcelado);

  
    bSalvar = new JButton("Salvar");
    bSalvar.addActionListener(this);
    add(bSalvar);

    bDeletar = new JButton("Deletar");
    bDeletar.addActionListener(this);
    add(bDeletar);

    bLimpar = new JButton("Limpar");
    bLimpar.addActionListener(this);
    add(bLimpar);

    bSair = new JButton("Sair");
    bSair.addActionListener(this);
    add(bSair);

    
    dtModel = new DefaultTableModel();
    dtModel.addColumn("NOME");
    dtModel.addColumn("VALOR");
    dtModel.addColumn("MARCA");
    dtModel.addColumn("EQUIPAMENTO");
    dtModel.addColumn("FORMA DE PAGAMENTO");

    tabela = new JTable(dtModel) {
      @Override
      public boolean isCellEditable(int rowIndex, int vColIndex) { 
        return false;
      }
    };

    var dimension = new Dimension(700, 400);
    tabela.setPreferredScrollableViewportSize(dimension); 
    barraRolagem = new JScrollPane(tabela);

    add(barraRolagem);
    barraRolagem.setVerticalScrollBarPolicy(
                        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
  }

  public static void main(String[] args) {
    Main f = new Main();
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  @Override
  public void actionPerformed(ActionEvent ae) {
    var source = ae.getSource();
    var tfNomeText = tfName.getText();
    var tfValorText = tfValue.getText();
    var tfMarcaText = tfBrand.getText();

    int cbEquipamentoIndexSelecionado = cbEquipamento.getSelectedIndex();

    boolean tfNomePreenchido = tfNomeText.length() > 0;
    boolean tfValorPreenchido = tfValorText.length() > 0;
    boolean tfMarcaPreenchido = tfMarcaText.length() > 0;
    boolean cbSelecionado = cbEquipamentoIndexSelecionado > 0;
    boolean rbSelecionado = rbAvista.isSelected() || rbParcelado.isSelected();
    boolean camposPrenchidos = tfNomePreenchido && tfValorPreenchido && tfMarcaPreenchido &&cbSelecionado && rbSelecionado;

    if (source == miSobre){
        JOptionPane.showMessageDialog(null, "Loja de Informática\nFeita por Felipe Holanda, Lucas Frazão e Thiago Figueredo");
    }
    
    if (source == bSalvar) {
         if(camposPrenchidos){
                String rbSelecinados = "";
                if(rbAvista.isSelected()){
                    rbSelecinados = "À vista";
                }else if(rbParcelado.isSelected()){
                    rbSelecinados = "Parcelado";
                } 
        
        if (camposPrenchidos) {
        String equipamentoSelecionado = equipamento[cbEquipamentoIndexSelecionado];
        Object[] dados = { tfNomeText, tfValorText, tfMarcaText, equipamentoSelecionado,rbSelecinados };

        dtModel.addRow(dados);
        clearFields();
      } 
    }else if(!rbSelecionado) {
        JOptionPane.showMessageDialog(null, "Selecione método de pagamento");
        
      }else if(!tfNomePreenchido) {
        JOptionPane.showMessageDialog(null, "Nome não Preenchido");
      
      }else if(!tfValorPreenchido) {
        JOptionPane.showMessageDialog(null, "Valor não Preenchido");
        
        }else if(!tfMarcaPreenchido) {
        JOptionPane.showMessageDialog(null, "Marca não Preenchida");
        
      }else if(!cbSelecionado){
        JOptionPane.showMessageDialog(null, "Selecione o equipamento");
      }
    }

    if (source == bDeletar) {
      int linha = tabela.getSelectedRow();

      if (linha != -1) {
        dtModel.removeRow(linha);
      } else {
        JOptionPane.showMessageDialog(null, "Nenhuma linha selecionada para remoção");
      }
    }

    if (source == bLimpar) {
      clearFields();
    }

    if (source == bSair) {
      System.exit(0);
    }
  }
  
  public void clearFields() {
    tfName.setText("");
    tfValue.setText("");
    tfBrand.setText("");
    tfName.requestFocus();
   cbEquipamento.setSelectedIndex(0);
     buttonGroup.clearSelection();
  }
}