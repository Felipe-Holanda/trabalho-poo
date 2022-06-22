import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class Formulario extends JFrame implements ActionListener {
  JLabel lNome, lValor, lMarca, lEquipamento;
  JTextField tfNome, tfValor, tfMarca;
  JButton bSalvar, bDeletar, bLimpar, bSair;

  String[] equipamento = { "", "Processador", "Impressora", "Modem", "Monitor", "Teclado" };
  JComboBox<String> cbEquipamento;

  JTable tabela;
  DefaultTableModel dtModel;
  JScrollPane barraRolagem;

  public Formulario() {
    var layout = new FlowLayout();

    setLayout(layout);
    setSize(770, 600);
    setTitle("Loja Tech");
    setLocationRelativeTo(null); // Centraliza a janela no meio da tela
    setVisible(true);

    lNome = new JLabel("Nome:");
    add(lNome);

    tfNome = new JTextField(14);
    tfNome.setDocument(new LimitaCaracteres(60, LimitaCaracteres.TipoEntada.STRING));
    add(tfNome);

    lValor = new JLabel("Valor:");
    add(lValor);

    tfValor = new JTextField(10);
    tfValor.setDocument(new LimitaCaracteres(20, LimitaCaracteres.TipoEntada.NUMERO));

    add(tfValor);

    lMarca = new JLabel("Marca:");
    add(lMarca);

    tfMarca = new JTextField(13);
    tfMarca.setDocument(new LimitaCaracteres(60, LimitaCaracteres.TipoEntada.STRING));
    add(tfMarca);

    lEquipamento = new JLabel("Equipamento:");
    add(lEquipamento);

    cbEquipamento = new JComboBox<String>(equipamento);
    add(cbEquipamento);

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

    // TABELA
    dtModel = new DefaultTableModel();
    dtModel.addColumn("NOME");
    dtModel.addColumn("Valor");
    dtModel.addColumn("MARCA");
    dtModel.addColumn("EQUIPAMENTO");

    tabela = new JTable(dtModel) {
      @Override
      public boolean isCellEditable(int rowIndex, int vColIndex) { // Para que a células não sejam editaveis.
        return false;
      }
    };

    var dimension = new Dimension(700, 400);
    tabela.setPreferredScrollableViewportSize(dimension); // Tamanho da tabela

    barraRolagem = new JScrollPane(tabela);
    add(barraRolagem);
  }

  public static void main(String[] args) {
    Formulario f = new Formulario();
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  @Override
  public void actionPerformed(ActionEvent ae) {
    var source = ae.getSource();
    var tfNomeText = tfNome.getText();
    var tfValorText = tfValor.getText();
    var tfMarcaText = tfMarca.getText();

    boolean tfPreenchidos = tfNomeText.length() > 0 && tfValorText.length() > 0 && tfMarcaText.length() > 0;

    int cbEquipamentoIndexSelecionado = cbEquipamento.getSelectedIndex();
    boolean cbSelecionado = cbEquipamentoIndexSelecionado > 0;
    boolean camposPrenchidos = tfPreenchidos && cbSelecionado;

    if (source == bSalvar) {
      if (camposPrenchidos) {
        String equipamentoSelecionado = equipamento[cbEquipamentoIndexSelecionado];
        Object[] dados = { tfNomeText, tfValorText, tfMarcaText, equipamentoSelecionado };

        dtModel.addRow(dados);
        limparCampos();
      } else {
        JOptionPane.showMessageDialog(
            null,
            "Selecione um Cadastro para deletar.",
            "Nenhum Cadastro selecionado",
            JOptionPane.INFORMATION_MESSAGE);
      }
    }

    if (source == bDeletar) {
      int linha = tabela.getSelectedRow();

      if (linha != -1) {
        dtModel.removeRow(linha);
      } else {
        JOptionPane.showMessageDialog(null,
            "Selecione um item para deletar.",
            "Nenhum Item selecionado",
            JOptionPane.INFORMATION_MESSAGE);
      }
    }

    if (source == bLimpar) {
      limparCampos();
    }

    if (source == bSair) {
      System.exit(0);
    }
  }

  public void limparCampos() {
    tfNome.setText("");
    tfValor.setText("");
    tfMarca.setText("");

    tfNome.requestFocus();
    cbEquipamento.setSelectedIndex(0);
  }
}