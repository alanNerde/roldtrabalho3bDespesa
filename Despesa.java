import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Despesa {

    public enum Ecategoria {
        TRANSPORTE, ALIMENTACAO, SUPERFLUO;
    }

    public enum Estatus {
        PAGO, VENCIDO, EM_ABERTO;
    }

    public enum EmetodoPagamento {
        AVISTA, PARCELADO;
    }

    private static int contador = 0;
    private final int id;
    private String nome;
    private Ecategoria categoria;
    private float valor;
    private EmetodoPagamento metodoPagamento;
    private Estatus status;
    private LocalDate dataCompra;
    private LocalDate dataVencimento;

    // Construtor com vencimento padrão de 30 dias
    public Despesa(String nome, Ecategoria categoria, float valor, EmetodoPagamento metodoPagamento, Estatus status, LocalDate dataCompra) {
        this(nome, categoria, valor, metodoPagamento, status, dataCompra, dataCompra.plusDays(30));
    }

    // Construtor com vencimento personalizado
    public Despesa(String nome, Ecategoria categoria, float valor, EmetodoPagamento metodoPagamento, Estatus status, LocalDate dataCompra, LocalDate dataVencimento) {
        this.id = ++contador;
        this.nome = nome;
        this.categoria = categoria;
        this.valor = valor;
        this.metodoPagamento = metodoPagamento;
        this.status = status;
        this.dataCompra = dataCompra;
        this.dataVencimento = dataVencimento;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Ecategoria getCategoria() {
        return categoria;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public EmetodoPagamento getMetodoPagamento() {
        return metodoPagamento;
    }

    public Estatus getStatus() {
        return status;
    }

    public void setStatus(Estatus status) {
        this.status = status;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    // Método para marcar despesa como paga
    public void marcarComoPago() {
        this.status = Estatus.PAGO;
    }

    // Métodos para listar despesas por status
    public static List<Despesa> listarDespesasPorStatus(Estatus statusDesejado, LocalDate dataInicio, LocalDate dataFim, List<Despesa> todasDespesas) {
        List<Despesa> despesasFiltradas = new ArrayList<>();
        for (Despesa despesa : todasDespesas) {
            if (despesa.getStatus() == statusDesejado && 
                !despesa.getDataCompra().isBefore(dataInicio) && 
                !despesa.getDataCompra().isAfter(dataFim)) {
                despesasFiltradas.add(despesa);
            }
        }
        return despesasFiltradas;
    }

    @Override
    public String toString() {
        return "Id: " + id + "|" + "Despesa: " + nome + " | Categoria: " + categoria + " | Valor: " + valor +
                " | Status: " + status + " | Data de Vencimento: " + dataVencimento;
    }
}
