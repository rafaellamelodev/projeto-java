/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frames;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 *
 * @author D18_11
 */
public class TelaDisciplinas extends javax.swing.JFrame {
     private JTextField txtCodigo = new JTextField(12);
    private JTextField txtNome   = new JTextField(24);
    private JSpinner   spCarga   = new JSpinner(new SpinnerNumberModel(40, 1, 1000, 1));
    
    private JButton btnSalvar   = new JButton("Salvar");
    private JButton btnAtualizar= new JButton("Atualizar");
    private JButton btnExcluir  = new JButton("Excluir");
    private JButton btnLimpar   = new JButton("Limpar");
    
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

        setLayout(new BorderLayout(8,8));
        add(criarPainelFormulario(), BorderLayout.NORTH);
        add(criarPainelTabela(), BorderLayout.CENTER);

        // Handlers vazios para ligar depois ao BD
        btnSalvar.addActionListener(e -> onSalvar());
        btnAtualizar.addActionListener(e -> onAtualizar());
        btnExcluir.addActionListener(e -> onExcluir());
        btnLimpar.addActionListener(e -> onLimpar());

        // Seleção na tabela (só popular campos; lógica virá depois)
        tabela.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tabela.getSelectedRow() >= 0) {
                int row = tabela.getSelectedRow();
                
            }
        });
    }
    
     private JComponent criarPainelFormulario() {
        JPanel box = new JPanel(new BorderLayout());
        box.setBorder(BorderFactory.createTitledBorder("Cadastro de Disciplinas"));

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(6,6,6,6);
        gc.anchor = GridBagConstraints.WEST;
        gc.fill = GridBagConstraints.HORIZONTAL;

        // Linha 1: Código
        gc.gridx = 0; gc.gridy = 0;
        form.add(new JLabel("Código:"), gc);
        gc.gridx = 1; gc.gridy = 0; gc.weightx = 1;
        form.add(txtCodigo, gc);

        // Linha 2: Nome
        gc.gridx = 0; gc.gridy = 1; gc.weightx = 0;
        form.add(new JLabel("Nome:"), gc);
        gc.gridx = 1; gc.gridy = 1; gc.weightx = 1;
        form.add(txtNome, gc);

        // Linha 3: Carga Horária
        gc.gridx = 0; gc.gridy = 2; gc.weightx = 0;
        form.add(new JLabel("Carga Horária (h):"), gc);
        gc.gridx = 1; gc.gridy = 2; gc.weightx = 1;
        form.add(spCarga, gc);

        // Botões ao lado direito
        JPanel botoes = new JPanel(new GridLayout(2, 2, 6, 6));
        botoes.add(btnSalvar);
        botoes.add(btnAtualizar);
        botoes.add(btnExcluir);
        botoes.add(btnLimpar);

        box.add(form, BorderLayout.CENTER);
        box.add(botoes, BorderLayout.EAST);
        return box;
    }
     
       private JComponent criarPainelTabela() {
        tabela.setModel(modelo);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane sp = new JScrollPane(tabela);
        sp.setBorder(BorderFactory.createTitledBorder("Disciplinas cadastradas"));
        return sp;
    }
       
        private void onSalvar() {
        // TODO: validar campos e inserir no banco
        // Ex.: INSERT INTO disciplinas (codigo, nome, carga_horaria) VALUES (?,?,?)
        JOptionPane.showMessageDialog(this, "Salvar (lógica de BD ainda não ligada).");
    }

    private void onAtualizar() {
        // TODO: validar seleção e atualizar no banco
        // Ex.: UPDATE disciplinas SET nome=?, carga_horaria=? WHERE id=?
        JOptionPane.showMessageDialog(this, "Atualizar (lógica de BD ainda não ligada).");
    }

    private void onExcluir() {
        // TODO: validar seleção e excluir no banco
        // Ex.: DELETE FROM disciplinas WHERE id=?
        JOptionPane.showMessageDialog(this, "Excluir (lógica de BD ainda não ligada).");
    }

    private void onLimpar() {
        txtCodigo.setText("");
        txtNome.setText("");
        spCarga.setValue(40);
        tabela.clearSelection();
        txtCodigo.requestFocus();
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
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaDisciplinas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaDisciplinas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaDisciplinas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaDisciplinas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaDisciplinas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
