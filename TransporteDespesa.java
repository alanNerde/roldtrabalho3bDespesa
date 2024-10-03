import java.time.LocalDate;

public class TransporteDespesa extends Despesa {

    public enum EMeioTransporte {
        MOTO, CARRO, ONIBUS, AVIAO;
    }

    private EMeioTransporte meioTransporte;

    // Construtor
    public TransporteDespesa(String nome, Ecategoria categoria, float valor,
                             EmetodoPagamento metodoPagamento, Estatus status,
                             LocalDate dataCompra, EMeioTransporte meioTransporte) {
        super(nome, categoria, valor, metodoPagamento, status, dataCompra);
        this.meioTransporte = meioTransporte;
    }

    // Getters e Setters
    public EMeioTransporte getMeioTransporte() {
        return meioTransporte;
    }

    public void setMeioTransporte(EMeioTransporte meioTransporte) {
        this.meioTransporte = meioTransporte;
    }

    // Sobrescrita do método toString para incluir o meio de transporte
    @Override
    public String toString() {
        return super.toString() + " | Meio de Transporte: " + meioTransporte;
    }
}
