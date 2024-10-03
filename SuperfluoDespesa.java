import java.time.LocalDate;

public class SuperfluoDespesa extends Despesa {

    public enum MotivoCompra {
        LAZER, PRESENTE, IMPULSO, CONVENIENCIA;
    }

    private MotivoCompra motivoCompra; // Motivo da compra

    public SuperfluoDespesa(String nome, float valor, LocalDate dataVencimento, Despesa.EmetodoPagamento metodoPagamento,
                            Despesa.Estatus status, MotivoCompra motivoCompra) {
        super(nome, Despesa.Ecategoria.SUPERFLUO, valor, metodoPagamento, status, dataVencimento);
        this.motivoCompra = motivoCompra;
    }

    // Getters e Setters
    public MotivoCompra getMotivoCompra() {
        return motivoCompra;
    }

    public void setMotivoCompra(MotivoCompra motivoCompra) {
        this.motivoCompra = motivoCompra;
    }

    @Override
    public String toString() {
        return "Despesa Supérflua: " + getNome() + "\n" +
                "Valor: " + getValor() + "\n" +
                "Motivo da Compra: " + motivoCompra + "\n" +
                "Data de Vencimento: " + getDataVencimento() + "\n" +
                "Método de Pagamento: " + getMetodoPagamento();
    }
}

