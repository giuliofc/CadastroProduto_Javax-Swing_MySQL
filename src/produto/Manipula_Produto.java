/*
 * To change this license header, choose License Headers in Project Properties.
 */
package produto;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * @author giulio
 */
public class Manipula_Produto {
    
    private Conexao minhaConexao;
    private Produto meuProduto;
    
    public Manipula_Produto(Conexao con, Produto prod){
        setConexao(con);
        setProduto(prod);
    }
    
    public void setConexao(Conexao con){minhaConexao = con; }
    
    public Conexao getConexao(){return minhaConexao; }
    
    public void setProduto(Produto produto){ meuProduto = produto; } 
    
    public Produto getProduto(){ return meuProduto; }        
    
    //coloca string entre aspas simples
    private String QuotedStr(String Item){return "\'"+Item+"\'";}    
    
    //colocar double entre aspas simples
    private String QuotedStr(double Item){return "\'"+Item+"\'";}
    
    public void inserirDados(){
        
        String colunas="("+meuProduto.getCampos().get(1)+","
                          +meuProduto.getCampos().get(2)+","
                          +meuProduto.getCampos().get(3)+","
                          +meuProduto.getCampos().get(4)+","
                          +meuProduto.getCampos().get(5)+")";
        
        
        String valores="("+QuotedStr(meuProduto.getNome())+","
                          +QuotedStr(meuProduto.getPrecoVenda())+","
                          +QuotedStr(meuProduto.getPrecoCusto())+","
                          +QuotedStr(meuProduto.getUnidade())+","
                          +QuotedStr(meuProduto.getCategoria())+")"; 
        //JOptionPane.showMessageDialog(null, valores);
        String Comando = "INSERT INTO Produto"+colunas+" VALUES"+valores;
        getConexao().ExpressaoSQL(Comando);
        getConexao().FecharConexao();
                
    }
    
    public void updateDados(int produtoid){
        
        String colunas="("+meuProduto.getCampos().get(1)+","
                          +meuProduto.getCampos().get(2)+","
                          +meuProduto.getCampos().get(3)+","
                          +meuProduto.getCampos().get(4)+","
                          +meuProduto.getCampos().get(5)+")";
        
        
        String valores="("+QuotedStr(meuProduto.getNome())+","
                          +QuotedStr(meuProduto.getPrecoVenda())+","
                          +QuotedStr(meuProduto.getPrecoCusto())+","
                          +QuotedStr(meuProduto.getUnidade())+","
                          +QuotedStr(meuProduto.getCategoria())+")"; 
        
        String Comando = "UPDATE Produto SET "
        +meuProduto.getCampos().get(1)+"="+QuotedStr(meuProduto.getNome())+","
        +meuProduto.getCampos().get(2)+"="+QuotedStr(meuProduto.getPrecoVenda())+","
        +meuProduto.getCampos().get(3)+"="+QuotedStr(meuProduto.getPrecoCusto())+","
        +meuProduto.getCampos().get(4)+"="+QuotedStr(meuProduto.getUnidade())+","
        +meuProduto.getCampos().get(5)+"="+QuotedStr(meuProduto.getCategoria())+" "
        +"WHERE produtoID="+produtoid+";";
        
        getConexao().ExpressaoSQL(Comando);
        getConexao().FecharConexao();
                
    }
    
    
    public void getListaProdutos(){
        
        String Comando="SELECT * FROM Produto;";
        
        ResultSet rst=null;
        
        try{
            minhaConexao.ExpressaoSQL(Comando);
            rst=minhaConexao.getDados();    
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos!", "", 
                            JOptionPane.WARNING_MESSAGE);
        }
        
        GuiJTable_Listar gjt=new GuiJTable_Listar(rst);
        gjt.setVisible(true);
        
        minhaConexao.FecharConexao();
    }
    
    public void getListaLucro(){
        String Comando="SELECT * FROM Produto;";
        
        ResultSet rst=null;
        
        try{
            minhaConexao.ExpressaoSQL(Comando);
            rst=minhaConexao.getDados();    
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos!", "", 
                            JOptionPane.WARNING_MESSAGE);
        }
        
        GuiJTable_Lucro gjt=new GuiJTable_Lucro(rst);
        gjt.setVisible(true);
        
        minhaConexao.FecharConexao();
        
        
    }
    
    public String[] consultarDados(int id){
        String Comando="SELECT nome, precoVenda, precoCusto, unidade, "
                + "categoria FROM Produto WHERE produtoID="+id+";";
        
        ResultSet rst=null;
        String[] produto=new String[5];
        
        try{
            minhaConexao.ExpressaoSQL(Comando);
            rst=minhaConexao.getDados();
            
            //.isBeforeFirst() - Este método retornará true se o cursor estiver 
            //antes do primeiro registro e false se o cursor estiver em qualquer 
            //posição ou se não há registros.
            if( !rst.isBeforeFirst() ){
                JOptionPane.showMessageDialog(null,"ProdutoID não encontrado!");
            }
            else{
                while(rst.next()){
                    produto[0]=rst.getString("nome");
                    produto[1]=rst.getString("precoVenda");
                    produto[2]=rst.getString("precoCusto");
                    produto[3]=rst.getString("unidade");
                    produto[4]=rst.getString("categoria");
                }
            } 
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao tentar encontrar produto!", "", 
                            JOptionPane.WARNING_MESSAGE);
        }
        
        minhaConexao.FecharConexao();
        return produto;  
    }
    
    public void excluirDados(int produtoid){
        String Comando="DELETE FROM Produto WHERE ProdutoID="+produtoid+";";
        
        try{
            minhaConexao.ExpressaoSQL(Comando);
            minhaConexao.FecharConexao();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao tentar excluir o produto!", "", 
                            JOptionPane.WARNING_MESSAGE);
        }
    }
    
    
    
    
    
    /*
    public static void main(String[] args) {
        Conexao con=new Conexao();
        Produto prod=new Produto();
        Manipula_Produto mp=new Manipula_Produto(con, prod);
        mp.getProdutos();
    }
    */
    
    
} //fim da casse
