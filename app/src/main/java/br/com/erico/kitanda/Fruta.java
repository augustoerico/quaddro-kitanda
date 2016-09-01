package br.com.erico.kitanda;

import android.content.Context;

/**
 * Created by Mobile on 24/08/2016.
 */
public class Fruta {

    private String codigo;
    private double preco;
    private char emPromocao;
    private String nome;
    private String descricao;
    private String detalhe;

    private int imagem;
    private int quantidade;

    public Fruta(String linha, Context context) {
        String[] valores = linha.split(";");

        codigo = valores[0];
        preco = Double.parseDouble(valores[1]);
        emPromocao = valores[2].charAt(0);
        nome = valores[3];
        descricao = valores[4];
        detalhe = valores[5];

        quantidade = 0;
        String imageName = Biblioteca.toImageName(nome);
        imagem = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());


    }

    @Override
    public String toString() {
        return this.nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public char getEmPromocao() {
        return emPromocao;
    }

    public void setEmPromocao(char emPromocao) {
        this.emPromocao = emPromocao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDetalhe() {
        return detalhe;
    }

    public void setDetalhe(String detalhe) {
        this.detalhe = detalhe;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }
}
