/*
 * To change this license header, choose License Headers in Project Properties.
 */
package produto;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * @author giulio
 */
public class GuiJTable_Listar extends JFrame{
    
    private JTable jTTabela;
    private JPanel jPTitulo, jPTabela, jPBotao;
    private JLabel jLTitulo;
    private JButton jBFechar;
    private JScrollPane jSBarraRolagem;
    private DefaultTableModel modeloTabela;
    private ResultSet resultado;
    
    
    public GuiJTable_Listar(ResultSet rst){
        super("Listar Produto");
        
        resultado=rst; //recebe o ResultSet para contruir a tabela
        
        this.setSize(new Dimension(630, 290));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); //posiciona a janela no meio do monitor
        
        jPTitulo=new JPanel(new FlowLayout(FlowLayout.LEADING, 15, 5));
        JLabel jLTitulo=new JLabel("Listar Produtos");
        jLTitulo.setBounds(new Rectangle(20, 5, 200, 27));
        Font f = new Font("SansSerif", Font.BOLD, 17);
        jLTitulo.setFont(f);
        jPTitulo.add(jLTitulo);
        
                               //new FlowLayout(align, hgap, vgap)
        jPBotao=new JPanel(new FlowLayout(FlowLayout.TRAILING, 15, 5) );
        jBFechar=new JButton("Fechar");
        jBFechar.setBounds(new Rectangle(515, 218, 80, 27));
        jPBotao.add(jBFechar);
        
        /*
        String[] cabecalho =
                {"Primeiro Nome","Último Nome","Esporte","# Anos", "Vegetariano"};
        Object[][] dados = {
                {"João", "Silva", "Futebol", new Integer(5), new Boolean(false)},
                {"Maria", "Silva", "Patins", new Integer(3), new Boolean(true)},
                {"Antônio", "Silva", "Surfboard", new Integer(2), new Boolean(false)},
                {"Joana", "Silva", "Corrida", new Integer(20), new Boolean(true)},
                {"Luiz", "Silva", "Volei", new Integer(10), new Boolean(false)}};
        */
        
        String[] colunasTabela=new String[]{"produtoID","nome","precoVenda",
                                    "precoCusto","unidade","categoria"};
        
        modeloTabela=new DefaultTableModel(null, colunasTabela) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        
        try {
            if (resultado.getRow() == 1){
                resultado.first();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Não encontrou a primeira linha");
        }
        
        try {
            while (resultado.next()) {

                modeloTabela.addRow(new String[]{
                    resultado.getString("produtoID"),
                    resultado.getString("nome"),
                    resultado.getString("precoVenda"),
                    resultado.getString("precoCusto"),
                    resultado.getString("unidade"),
                    resultado.getString("categoria")
                });
            }
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Não foi possivel adicionar as linhas na tabela.");
        }

        
        jPTabela=new JPanel();
        jPTabela.setLayout(new GridLayout(1,0));
        jTTabela=new JTable(modeloTabela);
        //jTTabela.setModel(new javax.swing.table.DefaultTableModel(dados,cabecalho));
        //jTTabela.setEnabled(false); //desabilita a edição das celulas pelo usuario
        jSBarraRolagem=new JScrollPane();
        jSBarraRolagem.setViewportView(jTTabela);
        jPTabela.add(jSBarraRolagem);
        
        
        add(jPTitulo, BorderLayout.NORTH);
        add(jPTabela, BorderLayout.CENTER);
        add(jPBotao, BorderLayout.SOUTH);
        
        associarEventos();
        
        //Ajusta o tamanho da janela de acordo com os componentes
        //getContentPane().pack();
   
    } //fim do construtor
    
    
    public void associarEventos(){

        jBFechar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                GuiProduto guiproduto=new GuiProduto();
                dispose();
                guiproduto.setVisible(true);   
            }
        });
    }
    
    
    /*
    public static void main(String[] args) {
        GuiJTable_Listar jtable = new GuiJTable_Listar();
        jtable.setVisible(true);
    }*/
    
 
} //fim da classe
