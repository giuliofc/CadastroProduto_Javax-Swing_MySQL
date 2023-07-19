/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produto;
import java.util.*;
import java.text.*;
import javax.swing.JOptionPane;
/**
 * @author giulio
 */
public class Produto {
    
    private int iProdutoID;
    private String sNome, sUnidade, sCategoria;
    private double dPrecoVenda, dPrecoCusto;
    private boolean bConsistencia;
    
    public Produto(){
        setProdutoID(0);
        setNome("");
        setPrecoVenda(0);
        setPrecoCusto(0);
        setUnidade("");
        setCategoria("");
    }
    
    //verifica a consistencia em todos os metodos
    public void setConsistencia(boolean consistencia){
        bConsistencia =consistencia;
    }
    
    //grava os codigos dos projetos desde q o valor seja maior que zero
    public void setProdutoID(int produtoid){
        if(produtoid >=0) 
            this.iProdutoID = produtoid;
        else{
            this.iProdutoID =-1;
            JOptionPane.showMessageDialog(null, "Código do produto Incorreto.", ""
                            , JOptionPane.WARNING_MESSAGE);
            setConsistencia(false);
            return; //para sair sem passar pelo setConsistencia(true)
        } 
        setConsistencia(true);
    }
    
    public void setNome(String nome){
        if(nome.length() >0)
            sNome = nome;
        else{
            sNome="Anônimo";
            setConsistencia(false);
            return;
        }
        setConsistencia(true);
    }
    
    public void setPrecoVenda(double precovenda){
        if(precovenda >=0)
            dPrecoVenda = precovenda;
        else{
            JOptionPane.showMessageDialog(null, "Preço de Venda do produto Incorreto.", ""
                            , JOptionPane.WARNING_MESSAGE);
            setConsistencia(false);
        }
        setConsistencia(true);
    }
    
    public void setPrecoCusto(double precocusto){
        if(precocusto >=0)
            dPrecoCusto = precocusto;
        else{
            JOptionPane.showMessageDialog(null, "Preço de Custo do produto Incorreto.", ""
                            , JOptionPane.WARNING_MESSAGE);
            setConsistencia(false);
        }
        setConsistencia(true);
    }
    
    public void setUnidade(String unidade){
        if(unidade.length() >0)
            sUnidade = unidade;
        else{
            sUnidade="";
            setConsistencia(false);
            return;
        }
        setConsistencia(true);
    }
    
    public void setCategoria(String categoria){
        if(categoria.length() >0)
            sCategoria = categoria;
        else{
            sCategoria="";
            setConsistencia(false);
            return;
        }
        setConsistencia(true);
    }
    
    public int getProdutoID(){return iProdutoID;}
    public String getNome(){return sNome;}
    public double getPrecoVenda(){return dPrecoVenda;}
    public double getPrecoCusto(){return dPrecoCusto;}
    public String getUnidade(){return sUnidade;}
    public String getCategoria(){return sCategoria;}
    
    //retorna um vetor com os nomes dos campos da tabela Produto
    public Vector getCampos(){
        Vector Campos=new Vector();
        Campos.add("produtoID");
        Campos.add("nome");
        Campos.add("precoVenda");
        Campos.add("precoCusto");
        Campos.add("unidade");
        Campos.add("categoria");
        return Campos;
    }
    
} //fim da classe Produto
