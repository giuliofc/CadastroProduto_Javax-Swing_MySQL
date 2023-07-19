/*
*
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
import java.math.BigDecimal;
import java.math.RoundingMode;
/**
 * @author giulio
 */
public class GuiJTable_Lucro extends JFrame{
    
    private JTable jTTabela;
    private JPanel jPTitulo, jPTabela, jPBotao;
    private JLabel jLTitulo;
    private JButton jBFechar;
    private JScrollPane jSBarraRolagem;
    private DefaultTableModel modeloTabela;
    private ResultSet resultado;
    
    public GuiJTable_Lucro(ResultSet rst){
        super("Listar Lucro");
        
        resultado=rst; //recebe o ResultSet para contruir a tabela
        
        this.setSize(new Dimension(630, 290));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); //posiciona a janela no meio do monitor
        
        jPTitulo=new JPanel(new FlowLayout(FlowLayout.LEADING, 15, 5));
        JLabel jLTitulo=new JLabel("Listar Porcentagem de Lucro");
        jLTitulo.setBounds(new Rectangle(20, 5, 200, 27));
        Font f = new Font("SansSerif", Font.BOLD, 17);
        jLTitulo.setFont(f);
        jPTitulo.add(jLTitulo);
        
                               //new FlowLayout(align, hgap, vgap)
        jPBotao=new JPanel(new FlowLayout(FlowLayout.TRAILING, 15, 5) );
        jBFechar=new JButton("Fechar");
        jBFechar.setBounds(new Rectangle(515, 218, 80, 27));
        jPBotao.add(jBFechar);
        
        String[] colunasTabela=new String[]{"produtoID","nome","precoVenda",
                                    "precoCusto","Lucro \u0025"};
        
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
                       //porcentLucro=100-(custo-venda)*100
                double porcentLucro= 100-(resultado.getDouble("precoCusto")/
                        resultado.getDouble("precoVenda"))*100;
                //String sPorcentLucro=Double.toString(porcentLucro);          
                           //arredonda para 2 casas decimais o mais proximo tanto 
                           //pra cima quanto pra baixo
                BigDecimal lucroArredondado=new BigDecimal(porcentLucro).
                            setScale(2, RoundingMode.HALF_EVEN);

                modeloTabela.addRow(new String[]{
                    resultado.getString("produtoID"),
                    resultado.getString("nome"),
                    resultado.getString("precoVenda"),
                    resultado.getString("precoCusto"),
                    lucroArredondado.toString()
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
         
    }//fim do construtor
    
    
    public void associarEventos(){

        jBFechar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                GuiProduto guiproduto=new GuiProduto();
                dispose();
                guiproduto.setVisible(true);   
            }
        });
    }
       
} //fim da classe 
