package frames;

import dao.DisciplinaDAO;
import model.Disciplina;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TelaDisciplinas extends javax.swing.JFrame {

    private JTextField txtCodigo = new JTextField(12);
    private JTextField txtNome = new JTextField(24);
    private JSpinner spCarga = new JSpinner(new SpinnerNumberModel(40, 1, 1000, 1));
    private JButton btnSalvar = new JButton("Salvar");
    private JButton btnAtualizar = new JButton("Atualizar");
    private JButton btnExcluir = new JButton("Excluir");
    private JButton btnLimpar = new JButton("Limpar");
    private JTable tabela = new JTable();

    private DefaultTableModel modelo =
            new DefaultTableModel(new Object[]{"ID", "Código", "Nome", "Carga Horária"}, 0) {
                @Override public boolean isCellEditable(int row, int column) { return false; }
            };

    public TelaDisciplinas() {
        super("Disciplinas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 560);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout(8, 8));
        add(criarPainelFormulario(), BorderLayout.NORTH);
        add(criarPainelTabela(), BorderLayout.CENTER);

        // Handlers para os botões
        btnSalvar.addActionListener(e -> onSalvar());
        btnAtualizar.addActionListener(e -> onAtualizar());
        btnExcluir.addActionListener(e -> onExcluir());
        btnLimpar.addActionListener(e -> limparCampos());

        // Lógica para preencher os campos ao selecionar uma linha da tabela
        tabela.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tabela.getSelectedRow() >= 0) {
                int row = tabela.getSelectedRow();
                txtCodigo.setText(String.valueOf(tabela.getValueAt(row, 1)));
                txtNome.setText(String.valueOf(tabela.getValueAt(row, 2)));
                spCarga.setValue(tabela.getValueAt(row, 3));
            }
        });

        carregarDisciplinas(); // Carregar as disciplinas ao abrir a tela
    }

    // Painel de formulário com campos de entrada
    private JComponent criarPainelFormulario() {
        JPanel box = new JPanel(new BorderLayout());
        box.setBorder(BorderFactory.createTitledBorder("Cadastro de Disciplinas"));

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(6, 6, 6, 6);
        gc.anchor = GridBagConstraints.WEST;
        gc.fill = GridBagConstraints.HORIZONTAL;

        gc.gridx = 0; gc.gridy = 0;
        form.add(new JLabel("Código:"), gc);
        gc.gridx = 1; gc.gridy = 0; gc.weightx = 1;
        form.add(txtCodigo, gc);

        gc.gridx = 0; gc.gridy = 1; gc.weightx = 0;
        form.add(new JLabel("Nome:"), gc);
        gc.gridx = 1; gc.gridy = 1; gc.weightx = 1;
        form.add(txtNome, gc);

        gc.gridx = 0; gc.gridy = 2; gc.weightx = 0;
        form.add(new JLabel("Carga Horária (h):"), gc);
        gc.gridx = 1; gc.gridy = 2; gc.weightx = 1;
        form.add(spCarga, gc);

        JPanel botoes = new JPanel(new GridLayout(2, 2, 6, 6));
        botoes.add(btnSalvar);
        botoes.add(btnAtualizar);
        botoes.add(btnExcluir);
        botoes.add(btnLimpar);

        box.add(form, BorderLayout.CENTER);
        box.add(botoes, BorderLayout.EAST);
        return box;
    }

    // Painel da tabela de disciplinas cadastradas
    private JComponent criarPainelTabela() {
        tabela.setModel(modelo);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane sp = new JScrollPane(tabela);
        sp.setBorder(BorderFactory.createTitledBorder("Disciplinas cadastradas"));
        return sp;
    }

    // Método para salvar uma nova disciplina
    private void onSalvar() {
        try {
            Disciplina disciplina = new Disciplina();
            disciplina.setCodigo(txtCodigo.getText().trim());
            disciplina.setNome(txtNome.getText().trim());
            disciplina.setCargaHoraria((Integer) spCarga.getValue());

            boolean sucesso = new DisciplinaDAO().salvar(disciplina);

            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Disciplina salva com sucesso!");
                limparCampos();
                carregarDisciplinas(); // Atualiza a tabela com a nova disciplina
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para atualizar uma disciplina existente
    private void onAtualizar() {
        if (txtCodigo.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Selecione uma disciplina na tabela.");
            return;
        }

        try {
            int id = Integer.parseInt(tabela.getValueAt(tabela.getSelectedRow(), 0).toString());
            Disciplina disciplina = new Disciplina();
            disciplina.setId(id);
            disciplina.setCodigo(txtCodigo.getText().trim());
            disciplina.setNome(txtNome.getText().trim());
            disciplina.setCargaHoraria((Integer) spCarga.getValue());

            boolean sucesso = new DisciplinaDAO().atualizar(disciplina);

            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Disciplina atualizada com sucesso!");
                limparCampos();
                carregarDisciplinas(); // Atualiza a tabela com os dados atualizados
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para excluir uma disciplina
    private void onExcluir() {
        if (txtCodigo.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Selecione uma disciplina na tabela.");
            return;
        }

        try {
            int id = Integer.parseInt(tabela.getValueAt(tabela.getSelectedRow(), 0).toString());
            boolean sucesso = new DisciplinaDAO().deletar(id);

            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Disciplina excluída com sucesso!");
                limparCampos();
                carregarDisciplinas(); // Atualiza a tabela após a exclusão
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para carregar todas as disciplinas na tabela
    private void carregarDisciplinas() {
        try {
            List<Disciplina> lista = new DisciplinaDAO().listarTodos();
            DefaultTableModel model = new DefaultTableModel(
                new Object[]{"ID", "Código", "Nome", "Carga Horária"}, 0
            ) { @Override public boolean isCellEditable(int row, int column) { return false; }};

            for (Disciplina d : lista) {
                model.addRow(new Object[]{ d.getId(), d.getCodigo(), d.getNome(), d.getCargaHoraria() });
            }
            tabela.setModel(model);
            tabela.getTableHeader().setReorderingAllowed(false);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar disciplinas: " + e.getMessage());
        }
    }

    // Método para limpar os campos
    private void limparCampos() {
        txtCodigo.setText("");
        txtNome.setText("");
        spCarga.setValue(40); // Valor inicial para carga horária
        tabela.clearSelection(); // Limpar a seleção da tabela
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
   public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaDisciplinas().setVisible(true);
            }
        });
    }
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
