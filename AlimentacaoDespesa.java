import java.time.LocalDate;

public class AlimentacaoDespesa extends Despesa {

    public enum TipoRefeicao {
        ALMOCO, JANTA, LANCHE
    }

    private String estabelecimento;
    private TipoRefeicao tipoRefeicao; // Indica se a despesa foi para almoço, jantar ou lanche

    public AlimentacaoDespesa(String nome, Ecategoria categoria, float valor, EmetodoPagamento metodo_pagamento, 
            Estatus status, LocalDate dataCompra, String estabelecimento, TipoRefeicao tipoRefeicao) {
        super(nome, categoria, valor, metodo_pagamento, status, dataCompra);
        this.estabelecimento = estabelecimento;
        this.tipoRefeicao = tipoRefeicao;
    }

    public String getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(String estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public TipoRefeicao getTipoRefeicao() {
        return tipoRefeicao;
    }

    public void setTipoRefeicao(TipoRefeicao tipoRefeicao) {
        this.tipoRefeicao = tipoRefeicao;
    }

    @Override
    public String toString() {
        return "Despesa com Alimentação: " + "\n" +
                "Nome: " + getNome() + "\n" +
                "Categoria: " + getCategoria() + "\n" +
                "Estabelecimento: " + estabelecimento + "\n" +
                "Tipo de Refeição: " + tipoRefeicao + "\n" +
                "Valor: " + getValor() + "\n" +
                "Data de Vencimento: " + getDataVencimento() + "\n" +
                "Data da Compra: " + getDataCompra() + "\n";
    }
}
