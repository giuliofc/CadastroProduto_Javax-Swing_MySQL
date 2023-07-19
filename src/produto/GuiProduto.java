/*
 * 
 */
package produto;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTabbedPane; //classe q permite que varias abas sejam inseridas nele
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent; 
import javax.swing.JOptionPane;
import java.sql.ResultSet;

/**
 *
 * @author giulio
 */
public class GuiProduto extends JFrame{
    
    private JPanel jPTelaInicial;
    private JButton jBSalvar, jBListar, jBAlterar, jBExcluir, jBLimpar, jBSair, jBLucro;
    private JLabel jLTituloTelaInicial, jLNome, jLPrecoVenda, jLPrecoCusto, jLUnidade, jLCategoria;
    private JTextField jTTituloTelaInicial, jTNome, jTPrecoVenda, jTPrecoCusto, jTUnidade, jTCategoria;
    private int alteradoID=-1;
    
    /**
     * @param args the command line arguments
     */
    public GuiProduto(){
        initComponents();
    } //fim do construtor
    
    public void initComponents(){
        
        this.setSize(new Dimension(430, 390));
        this.setTitle("Gerênciamento de Produto");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); //posiciona a janela no meio do monitor
        
        //----- Caracteristicas dos botoes -----
        // <editor-fold defaultstate="collapsed" desc="Generated Code">
        jPTelaInicial=new JPanel();
        jPTelaInicial.setLayout(null);
        
        jBSalvar=new JButton("Salvar");
        jBSalvar.setBounds(new Rectangle(300, 20, 100, 27));
        
        jBListar=new JButton("Listar");
        jBListar.setBounds(new Rectangle(300, 52, 100, 27));
        
        jBLucro=new JButton("\u0025 Lucro");
        jBLucro.setBounds(new Rectangle(300,84,100,27)); 
        
        jBAlterar=new JButton("Alterar");
        jBAlterar.setBounds(new Rectangle(300, 116, 100, 27)); 
        
        jBExcluir=new JButton("Excluir");
        jBExcluir.setBounds(new Rectangle(300, 148, 100, 27)); 
        
        jBLimpar=new JButton("Limpar");
        jBLimpar.setBounds(new Rectangle(300, 180, 100, 27)); 
        
        jBSair=new JButton("Sair");
        jBSair.setBounds(new Rectangle(300, 212, 100, 27)); 
        
        jPTelaInicial.add(jBSalvar);
        jPTelaInicial.add(jBListar);
        jPTelaInicial.add(jBAlterar);
        jPTelaInicial.add(jBExcluir);
        jPTelaInicial.add(jBLimpar);
        jPTelaInicial.add(jBSair);
        jPTelaInicial.add(jBLucro);
        // </editor-fold> 
        
        //------ Caracteristicas dos Labels
        // <editor-fold defaultstate="collapsed" desc="Generated Code">
        jLTituloTelaInicial=new JLabel("Dados de Produto");
        jLTituloTelaInicial.setBounds(new Rectangle(20, 25, 200, 27));
        Font f = new Font("SansSerif", Font.BOLD, 17);
        jLTituloTelaInicial.setFont(f);
        
        jLNome=new JLabel("Nome:");
        jLNome.setBounds(new Rectangle(20, 77, 200, 27));
        
        jLPrecoVenda=new JLabel("Preço Venda:");
        jLPrecoVenda.setBounds(new Rectangle(20, 130, 200, 27));
        
        jLPrecoCusto=new JLabel("Preço Custo:");
        jLPrecoCusto.setBounds(new Rectangle(20, 183, 200, 27));
        
        jLUnidade=new JLabel("Unidade:");
        jLUnidade.setBounds(new Rectangle(20, 234, 200, 27));
        
        jLCategoria=new JLabel("Categoria:");
        jLCategoria.setBounds(new Rectangle(20, 285, 200, 27));
        
            //adicionando Labels
        jPTelaInicial.add(jLTituloTelaInicial);
        jPTelaInicial.add(jLNome);
        jPTelaInicial.add(jLPrecoVenda);
        jPTelaInicial.add(jLPrecoCusto);
        jPTelaInicial.add(jLUnidade);
        jPTelaInicial.add(jLCategoria);
        // </editor-fold> 
        
        //Caracteristicas dos JTextfields
        // <editor-fold defaultstate="collapsed" desc="Generated Code">
        jTNome=new JTextField();
        jTNome.setBounds(new Rectangle(20, 101, 200, 23));
        
        jTPrecoVenda=new JTextField();
        jTPrecoVenda.setBounds(new Rectangle(20, 155, 140, 23));
        
        jTPrecoCusto=new JTextField();
        jTPrecoCusto.setBounds(new Rectangle(20, 207, 140, 23));
        
        jTUnidade=new JTextField();
        jTUnidade.setBounds(new Rectangle(20, 258, 45, 23));
        
        jTCategoria=new JTextField();
        jTCategoria.setBounds(new Rectangle(20, 310, 45, 23));
        
            //adicionando JTextfields
        jPTelaInicial.add(jTNome);
        jPTelaInicial.add(jTPrecoVenda);
        jPTelaInicial.add(jTPrecoCusto);
        jPTelaInicial.add(jTUnidade);
        jPTelaInicial.add(jTCategoria);
        // </editor-fold> 
        
        
        add(jPTelaInicial);
        
        associarEventos();
        
    } //fim do initComponents
    
    
    //-------- Tratamento de Eventos -----
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    public void associarEventos(){
        
        jBSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(alteradoID<=0)
                    verificarCampos();
                else
                    verificarCampos(alteradoID);
            }
        });
        
        jBListar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Conexao con=new Conexao("localhost","ProgWeb2","root","mpnet102030");
                con.Conectar();
                Produto prod=new Produto();
                Manipula_Produto mp=new Manipula_Produto(con, prod);
                mp.getListaProdutos();
                
                dispose(); //para fechar a janela atual 
            }
        });
        
        jBAlterar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                alterarCampo();
            }
        });
        
        jBExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                excluirDados();
            }
        });
        
        jBSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        }); 
        
        jBLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jBLimpar_actionPerformed(e);   
            }
        });
        
        jBLucro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Conexao con=new Conexao("localhost","ProgWeb2","root","mpnet102030");
                con.Conectar();
                Produto prod=new Produto();
                Manipula_Produto mp=new Manipula_Produto(con, prod);
                mp.getListaLucro();
                
                dispose(); //para fechar a janela atual 
            }
        });
                            
    } //fim do associarEventos
    
    //uma forma de trator o evento dentro de outra função que não a actionPerformed
    void jBLimpar_actionPerformed(ActionEvent e) {
        jTNome.setText("");
        jTPrecoVenda.setText("");
        jTPrecoCusto.setText("");
        jTUnidade.setText("");
        jTCategoria.setText("");
    }
    // </editor-fold> 
    
    
    public void verificarCampos(){
        
        String nome="", unidade="", categoria="";
        double preco_venda=0.00, preco_custo=0.00;
        boolean bConsistencia=false;
        
        //Verifica campo Nome
        // <editor-fold defaultstate="collapsed" desc="Generated Code">
        if( !jTNome.getText().equals("") && jTNome.getText()!=null && jTNome.getText().length()<=50){
            nome=jTNome.getText();
            bConsistencia=true;
        }
        else if(jTNome.getText().length()>50){
            JOptionPane.showMessageDialog(null, "Campo Nome não pode ter mais de 50 caracteres!", 
                    "", JOptionPane.WARNING_MESSAGE);
            bConsistencia=false;
            return;
        }
        else if(jTNome.getText().equals("") || jTNome.getText()==null){
            JOptionPane.showMessageDialog(null, "Campo Nome não pode ser vazio!", 
                    "", JOptionPane.WARNING_MESSAGE);
            bConsistencia=false;
            return;
        }
        // </editor-fold>
        
        //Verifica campo Preco Venda
        //se for nulo ou vazio recebe 0.00; 
        //se tiver mais que 9 digitos ou mais que 2 digitos após a virgula retorna erro
        //se não for um numero retorna erro 
        // <editor-fold defaultstate="collapsed" desc="Generated Code">
        if(!jTPrecoVenda.getText().equals("") && jTPrecoVenda.getText()!=null )
        {
            String str=jTPrecoVenda.getText();
            
            if(str.length()<=10)
            {
                    String sResto = str.substring(str.indexOf(".")+1, str.length());
                
                    if(sResto.length()<=2)
                    {
                        try{
                            preco_venda=Double.parseDouble(str);
                        }
                        catch(Exception e){
                            JOptionPane.showMessageDialog(null, "Preço de Venda incorreto!", "", 
                                JOptionPane.WARNING_MESSAGE);
                            bConsistencia=false;
                            return;
                        }        
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Preço de Venda não pode ter mais \n"
                                + "de duas casas após a virgula!", "",JOptionPane.WARNING_MESSAGE);
                        bConsistencia=false;
                        return;
                    }          
            } 
            else{
                JOptionPane.showMessageDialog(null, "Preço de Venda só pode ter no máximo 9 digitos!", "", 
                        JOptionPane.WARNING_MESSAGE);
                    bConsistencia=false;
                    return;
            }   
        }
        else if( jTPrecoVenda.getText().equals("") || jTPrecoVenda.getText()==null ){
            preco_venda=0.00;
            bConsistencia=true;
        }
        // </editor-fold>
        
        //Verifica campo Preco Custo
        //se for nulo ou vazio recebe 0.00; 
        //se tiver mais que 9 digitos ou mais que 2 digitos após a virgula retorna erro
        //se não for um numero retorna erro 
        // <editor-fold defaultstate="collapsed" desc="Generated Code">
        if(!jTPrecoCusto.getText().equals("") && jTPrecoCusto.getText()!=null )
        {
            String str=jTPrecoCusto.getText();
            
            if(str.length()<=10)
            {
                    String sResto = str.substring(str.indexOf(".")+1, str.length());
                
                    if(sResto.length()<=2)
                    {
                        try{
                            preco_custo=Double.parseDouble(str);
                        }
                        catch(Exception e){
                            JOptionPane.showMessageDialog(null, "Preço de Custo incorreto!", "", 
                                JOptionPane.WARNING_MESSAGE);
                            bConsistencia=false;
                            return;
                        }        
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Preço de Custo não pode ter mais \n"
                                + "de duas casas após a virgula!", "",JOptionPane.WARNING_MESSAGE);
                        bConsistencia=false;
                        return;
                    }          
            } 
            else{
                JOptionPane.showMessageDialog(null, "Preço de Custo só pode ter no máximo 9 digitos!", "", 
                        JOptionPane.WARNING_MESSAGE);
                    bConsistencia=false;
                    return;
            }   
        }
        else if( jTPrecoCusto.getText().equals("") || jTPrecoCusto.getText()==null ){
            preco_custo=0.00;
            bConsistencia=true;
        }
        // </editor-fold>

        //Verifica campo Unidade
        // <editor-fold defaultstate="collapsed" desc="Generated Code">
        if(!jTUnidade.getText().equals("") && jTUnidade.getText()!=null && jTUnidade.getText().length()<=2 ){
                unidade=jTUnidade.getText();
                bConsistencia=true;
        }
        else if(jTUnidade.getText().length()>2){
            JOptionPane.showMessageDialog(null, "Campo Unidade não pode ter mais de 2 caracteres!", 
                    "", JOptionPane.WARNING_MESSAGE);
            bConsistencia=false;
            return;
        }
        else{ 
            unidade="";
            bConsistencia=true;
        }
        // </editor-fold>
        
        //Verifica campo Categoria
        // <editor-fold defaultstate="collapsed" desc="Generated Code">
        if(!jTCategoria.getText().equals("") && jTCategoria.getText()!=null && jTCategoria.getText().length()<=25 ){
            categoria=jTCategoria.getText();
            bConsistencia=true;
        }
        else if(jTCategoria.getText().length()>25){
            JOptionPane.showMessageDialog(null, "Campo Categoria não pode ter mais de 25 caracteres!", 
                    "", JOptionPane.WARNING_MESSAGE);
            bConsistencia=false;
            return;
        }
        else{ //é permitido escrever vazio
            categoria="";
            bConsistencia=true;
        }
        // </editor-fold>
        
        
        //salva valores no objeto Produto
        if(bConsistencia){
            Produto pd=new Produto();
            pd.setNome(nome);
            pd.setPrecoVenda(preco_venda);
            pd.setPrecoCusto(preco_custo);
            pd.setUnidade(unidade);
            pd.setCategoria(categoria);
            //JOptionPane.showMessageDialog(null, pd.getNome()+"\n"+pd.getPrecoVenda()+"\n"+
            //        pd.getPrecoCusto()+"\n"+pd.getUnidade()+"\n"+pd.getCategoria());
            
            Conexao con=new Conexao("localhost","ProgWeb2","root","mpnet102030");
            con.Conectar();
            
            Manipula_Produto mp=new Manipula_Produto(con, pd);
            mp.inserirDados();
        }
        
    } //fim do verificaCampos
    
    
    public void verificarCampos(int produtoid){
        
        String nome="", unidade="", categoria="";
        double preco_venda=0.00, preco_custo=0.00;
        boolean bConsistencia=false;
        
        //Verifica campo Nome
        // <editor-fold defaultstate="collapsed" desc="Generated Code">
        if( !jTNome.getText().equals("") && jTNome.getText()!=null && jTNome.getText().length()<=50){
            nome=jTNome.getText();
            bConsistencia=true;
        }
        else if(jTNome.getText().length()>50){
            JOptionPane.showMessageDialog(null, "Campo Nome não pode ter mais de 50 caracteres!", 
                    "", JOptionPane.WARNING_MESSAGE);
            bConsistencia=false;
            return;
        }
        else if(jTNome.getText().equals("") || jTNome.getText()==null){
            JOptionPane.showMessageDialog(null, "Campo Nome não pode ser vazio!", 
                    "", JOptionPane.WARNING_MESSAGE);
            bConsistencia=false;
            return;
        }
        // </editor-fold>
        
        //Verifica campo Preco Venda
        //se for nulo ou vazio recebe 0.00; 
        //se tiver mais que 9 digitos ou mais que 2 digitos após a virgula retorna erro
        //se não for um numero retorna erro 
        // <editor-fold defaultstate="collapsed" desc="Generated Code">
        if(!jTPrecoVenda.getText().equals("") && jTPrecoVenda.getText()!=null )
        {
            String str=jTPrecoVenda.getText();
            
            if(str.length()<=10)
            {
                    String sResto = str.substring(str.indexOf(".")+1, str.length());
                
                    if(sResto.length()<=2)
                    {
                        try{
                            preco_venda=Double.parseDouble(str);
                        }
                        catch(Exception e){
                            JOptionPane.showMessageDialog(null, "Preço de Venda incorreto!", "", 
                                JOptionPane.WARNING_MESSAGE);
                            bConsistencia=false;
                            return;
                        }        
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Preço de Venda não pode ter mais \n"
                                + "de duas casas após a virgula!", "",JOptionPane.WARNING_MESSAGE);
                        bConsistencia=false;
                        return;
                    }          
            } 
            else{
                JOptionPane.showMessageDialog(null, "Preço de Venda só pode ter no máximo 9 digitos!", "", 
                        JOptionPane.WARNING_MESSAGE);
                    bConsistencia=false;
                    return;
            }   
        }
        else if( jTPrecoVenda.getText().equals("") || jTPrecoVenda.getText()==null ){
            preco_venda=0.00;
            bConsistencia=true;
        }
        // </editor-fold>
        
        //Verifica campo Preco Custo
        //se for nulo ou vazio recebe 0.00; 
        //se tiver mais que 9 digitos ou mais que 2 digitos após a virgula retorna erro
        //se não for um numero retorna erro 
        // <editor-fold defaultstate="collapsed" desc="Generated Code">
        if(!jTPrecoCusto.getText().equals("") && jTPrecoCusto.getText()!=null )
        {
            String str=jTPrecoCusto.getText();
            
            if(str.length()<=10)
            {
                    String sResto = str.substring(str.indexOf(".")+1, str.length());
                
                    if(sResto.length()<=2)
                    {
                        try{
                            preco_custo=Double.parseDouble(str);
                        }
                        catch(Exception e){
                            JOptionPane.showMessageDialog(null, "Preço de Custo incorreto!", "", 
                                JOptionPane.WARNING_MESSAGE);
                            bConsistencia=false;
                            return;
                        }        
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Preço de Custo não pode ter mais \n"
                                + "de duas casas após a virgula!", "",JOptionPane.WARNING_MESSAGE);
                        bConsistencia=false;
                        return;
                    }          
            } 
            else{
                JOptionPane.showMessageDialog(null, "Preço de Custo só pode ter no máximo 9 digitos!", "", 
                        JOptionPane.WARNING_MESSAGE);
                    bConsistencia=false;
                    return;
            }   
        }
        else if( jTPrecoCusto.getText().equals("") || jTPrecoCusto.getText()==null ){
            preco_custo=0.00;
            bConsistencia=true;
        }
        // </editor-fold>

        //Verifica campo Unidade
        // <editor-fold defaultstate="collapsed" desc="Generated Code">
        if(!jTUnidade.getText().equals("") && jTUnidade.getText()!=null && jTUnidade.getText().length()<=2 ){
                unidade=jTUnidade.getText();
                bConsistencia=true;
        }
        else if(jTUnidade.getText().length()>2){
            JOptionPane.showMessageDialog(null, "Campo Unidade não pode ter mais de 2 caracteres!", 
                    "", JOptionPane.WARNING_MESSAGE);
            bConsistencia=false;
            return;
        }
        else{ 
            unidade="";
            bConsistencia=true;
        }
        // </editor-fold>
        
        //Verifica campo Categoria
        // <editor-fold defaultstate="collapsed" desc="Generated Code">
        if(!jTCategoria.getText().equals("") && jTCategoria.getText()!=null && jTCategoria.getText().length()<=25 ){
            categoria=jTCategoria.getText();
            bConsistencia=true;
        }
        else if(jTCategoria.getText().length()>25){
            JOptionPane.showMessageDialog(null, "Campo Categoria não pode ter mais de 25 caracteres!", 
                    "", JOptionPane.WARNING_MESSAGE);
            bConsistencia=false;
            return;
        }
        else{ //é permitido escrever vazio
            categoria="";
            bConsistencia=true;
        }
        // </editor-fold>
        
        
        //salva valores no objeto Produto
        if(bConsistencia){
            Produto pd=new Produto();
            pd.setNome(nome);
            pd.setPrecoVenda(preco_venda);
            pd.setPrecoCusto(preco_custo);
            pd.setUnidade(unidade);
            pd.setCategoria(categoria);
            //JOptionPane.showMessageDialog(null, pd.getNome()+"\n"+pd.getPrecoVenda()+"\n"+
            //        pd.getPrecoCusto()+"\n"+pd.getUnidade()+"\n"+pd.getCategoria());
            
            Conexao con=new Conexao("localhost","ProgWeb2","root","mpnet102030");
            con.Conectar();
            
            Manipula_Produto mp=new Manipula_Produto(con, pd);
            mp.updateDados(produtoid);
        }
        
    } //fim do verificaCampos
    
    
    public void alterarCampo(){
        
        String s = (String)JOptionPane.showInputDialog(null,"Digite o ID a ser alterado", 
                "Entrada",JOptionPane.QUESTION_MESSAGE, null, null, 
                null);
        
        int id=-1;
        
        if(s !=null){
            
            try{
                id=Integer.parseInt(s);
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "O numero digitado não é um inteiro.");
            }
            
            if(id>=1){
                Conexao con=new Conexao("localhost","ProgWeb2","root","mpnet102030");
                con.Conectar();
                Produto pd=new Produto();

                Manipula_Produto mp=new Manipula_Produto(con, pd);
                String[] produto=new String[5];
                produto=mp.consultarDados(id);
                
                jTNome.setText(produto[0]);
                jTPrecoVenda.setText(produto[1]);
                jTPrecoCusto.setText(produto[2]);
                jTUnidade.setText(produto[3]);
                jTCategoria.setText(produto[4]);
                
                alteradoID=id; //salva id para ser usado no verificarCampos se 
                               //o botao salvar for abertado novamente.
            }   
        }   
    } //fim do metodo alterarDados
    
    
    public void excluirDados(){
                   //showInputDialog retorna um objeto por isso o cast para (String)
        String s = (String)JOptionPane.showInputDialog(null,"Digite o ID a ser excluido", 
                "Entrada",JOptionPane.QUESTION_MESSAGE, null, null, 
                null);
        
        int id=-1;
        
        if(s !=null){
            
            try{
                id=Integer.parseInt(s);
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "O numero digitado não é um inteiro.");
            }
            
            if(id>=1){
                Conexao con=new Conexao("localhost","ProgWeb2","root","mpnet102030");
                con.Conectar();
                Produto pd=new Produto();

                Manipula_Produto mp=new Manipula_Produto(con, pd);
                String[] produto=new String[5];
                produto=mp.consultarDados(id);
                                  //.isEmpty()-retorna true se length é 0.
                if(produto[0]==null || produto[0].isEmpty()){
                    return; //produto não encontrado retorna null
                }
                else{
                    jTNome.setText(produto[0]);
                    jTPrecoVenda.setText(produto[1]);
                    jTPrecoCusto.setText(produto[2]);
                    jTUnidade.setText(produto[3]);
                    jTCategoria.setText(produto[4]);

                    //alteradoID=id; //salva id para ser usado no verificarCampos se 
                                   //o botao salvar for abertado novamente.
                    String[] opcoes={"Sim","Não"};

                    int n = JOptionPane.showOptionDialog(null,
                        "Confirmar a exclusão?","Exclusão",JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, opcoes,null);

                    if((n==0)||(n==JOptionPane.YES_OPTION)){
                        con.Conectar();
                        mp.excluirDados(id);

                        jTNome.setText("");
                        jTPrecoVenda.setText("");
                        jTPrecoCusto.setText("");
                        jTUnidade.setText("");
                        jTCategoria.setText("");
                    }
                    /*else if(n==1){
                        //botao Não precionado
                    }
                    else if(n==JOptionPane.CLOSED_OPTION){
                        //botão fechar janela precionado
                    }*/
                    
                }//fim do else produto[0].equals("")
            } //fim do if(id>1)
        }//fim do if(s!=null)    
    } //fim do metodo excluirDados
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GuiProduto produto=new GuiProduto();
        produto.setVisible(true);
    }
    
}
