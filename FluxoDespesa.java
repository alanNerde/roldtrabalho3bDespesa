import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FluxoDespesa {

    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Despesa> arrayDeDespesas = new ArrayList<>();
    private static final List<Usuario> usuarios = new ArrayList<>(); // Para gerenciar usuários

    public static void main(String[] args) {
        int opcao;
        do {
            opcao = exibirMenuPrincipal();
            processarOpcao(opcao);
        } while (opcao != 6);
        scanner.close();
    }

    private static int exibirMenuPrincipal() {
        System.out.println("Selecione uma das opções:");
        System.out.println("1. Entrar Despesa");
        System.out.println("2. Anotar Pagamento");
        System.out.println("3. Listar Despesas em Aberto no período");
        System.out.println("4. Listar Despesas Pagas no período");
        System.out.println("5. Gerenciar Usuários");
        System.out.println("6. Sair");
        System.out.print("Digite o número da opção desejada: ");
        return obterEntradaInt();
    }

    private static void processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                entrarDespesa();
                break;
            case 2:
                anotarPagamento();
                break;
            case 3:
                listarDespesasPorStatus(Despesa.Estatus.EM_ABERTO);
                break;
            case 4:
                listarDespesasPorStatus(Despesa.Estatus.PAGO);
                break;
            case 5:
                gerenciarUsuarios();
                break;
            case 6:
                System.out.println("Saindo do sistema...");
                break;
            default:
                System.out.println("Opção inválida! Tente novamente.");
        }
    }

    private static void entrarDespesa() {
        System.out.println("Você escolheu: Entrar Despesa");

        Despesa.Ecategoria categoria = escolherCategoria();
        if (categoria == null) {
            System.out.println("Não é possível cadastrar despesa sem categoria. Operação cancelada!");
            return;
        }

        System.out.print("Digite o nome da despesa: ");
        String nome = scanner.next();

        System.out.print("Digite o valor: ");
        float valor = obterEntradaFloat();

        Despesa.EmetodoPagamento metodoPagamento = escolherMetodoPagamento();
        Despesa.Estatus status = Despesa.Estatus.EM_ABERTO;

        switch (categoria) {
            case TRANSPORTE:
                TransporteDespesa.EMeioTransporte meioTransporte = escolherMeioTransporte();
                arrayDeDespesas.add(new TransporteDespesa(nome, categoria, valor, metodoPagamento, status, LocalDate.now(), meioTransporte));
                break;
            case ALIMENTACAO:
                System.out.print("Digite o nome do estabelecimento: ");
                String nomeEstabelecimento = scanner.next();
                AlimentacaoDespesa.TipoRefeicao tipoRefeicao = escolherTipoRefeicao();
                arrayDeDespesas.add(new AlimentacaoDespesa(nome, categoria, valor, metodoPagamento, status, LocalDate.now(), nomeEstabelecimento, tipoRefeicao));
                break;
            case SUPERFLUO:
                SuperfluoDespesa.MotivoCompra motivoCompra = escolherMotivoCompra();
                arrayDeDespesas.add(new SuperfluoDespesa(nome, valor, LocalDate.now(), metodoPagamento, status, motivoCompra));
                break;
        }

        System.out.println("Despesa cadastrada com sucesso!");
    }

    private static SuperfluoDespesa.MotivoCompra escolherMotivoCompra() {
        System.out.println("Escolha motivo da compra:");
        System.out.println("1. Lazer");
        System.out.println("2. Presente");
        System.out.println("3. Impulso");
        System.out.println("4. Conveniência");
        int opcao = obterEntradaInt();

        switch (opcao) {
            case 1:
                return SuperfluoDespesa.MotivoCompra.LAZER;
            case 2:
                return SuperfluoDespesa.MotivoCompra.PRESENTE;
            case 3:
                return SuperfluoDespesa.MotivoCompra.IMPULSO;
            case 4:
                return SuperfluoDespesa.MotivoCompra.CONVENIENCIA;     
            default:
                System.out.println("Opção inválida!");
                return null;
        }
    }

    private static AlimentacaoDespesa.TipoRefeicao escolherTipoRefeicao() {
        System.out.println("Escolha o tipo de refeição:");
        System.out.println("1. Almoço");
        System.out.println("2. Janta");
        System.out.println("3. Lanche");
        int opcao = obterEntradaInt();

        switch (opcao) {
            case 1:
                return AlimentacaoDespesa.TipoRefeicao.ALMOCO;
            case 2:
                return AlimentacaoDespesa.TipoRefeicao.JANTA;
            case 3:
                return AlimentacaoDespesa.TipoRefeicao.LANCHE; 
            default:
                System.out.println("Opção inválida!");
                return null;
        }
    }

    private static TransporteDespesa.EMeioTransporte escolherMeioTransporte() {
        System.out.println("Escolha o meio de transporte:");
        System.out.println("1. Moto");
        System.out.println("2. Carro");
        System.out.println("3. Ônibus");
        System.out.println("4. Avião");
        int opcao = obterEntradaInt();

        switch (opcao) {
            case 1:
                return TransporteDespesa.EMeioTransporte.MOTO;
            case 2:
                return TransporteDespesa.EMeioTransporte.CARRO;
            case 3:
                return TransporteDespesa.EMeioTransporte.ONIBUS;
            case 4:
                return TransporteDespesa.EMeioTransporte.AVIAO;
            default:
                System.out.println("Opção inválida!");
                return null;
        }
    }

    private static Despesa.Ecategoria escolherCategoria() {
        System.out.println("Escolha a categoria:");
        System.out.println("1. Transporte");
        System.out.println("2. Alimentação");
        System.out.println("3. Supérfluo");
        int opcao = obterEntradaInt();

        switch (opcao) {
            case 1:
                return Despesa.Ecategoria.TRANSPORTE;
            case 2:
                return Despesa.Ecategoria.ALIMENTACAO;
            case 3:
                return Despesa.Ecategoria.SUPERFLUO;
            default:
                System.out.println("Opção inválida!");
                return null;
        }
    }

    private static Despesa.EmetodoPagamento escolherMetodoPagamento() {
        System.out.println("Escolha o método de pagamento:");
        System.out.println("1. À Vista");
        System.out.println("2. Parcelado");
        int opcao = obterEntradaInt();

        switch (opcao) {
            case 1:
                return Despesa.EmetodoPagamento.AVISTA;
            case 2:
                return Despesa.EmetodoPagamento.PARCELADO;
            default:
                System.out.println("Opção inválida!");
                return null;
        }
    }

    private static void anotarPagamento() {
        System.out.println("Você escolheu: Anotar Pagamento");
        listarTodasDespesas();
        System.out.print("Digite o ID da despesa que deseja marcar como paga: ");
        int id = obterEntradaInt();

        Despesa despesa = buscarDespesaPorId(id);
        if (despesa != null) {
            despesa.marcarComoPago();
            System.out.println("Despesa marcada como paga.");
        } else {
            System.out.println("Despesa não encontrada.");
        }
    }

    private static void listarTodasDespesas() {
        System.out.println("Listando todas as despesas:");
        for (Despesa despesa : arrayDeDespesas) {
            System.out.println(despesa);
        }
    }

    private static Despesa buscarDespesaPorId(int id) {
        for (Despesa despesa : arrayDeDespesas) {
            if (despesa.getId() == id) {
                return despesa;
            }
        }
        return null;
    }

    private static void listarDespesasPorStatus(Despesa.Estatus statusDesejado) {
        System.out.print("Digite a data de início (dd/MM/yyyy): ");
        LocalDate dataInicio = obterEntradaData();

        System.out.print("Digite a data de fim (dd/MM/yyyy): ");
        LocalDate dataFim = obterEntradaData();

        List<Despesa> despesasFiltradas = Despesa.listarDespesasPorStatus(statusDesejado, dataInicio, dataFim, arrayDeDespesas);

        if (despesasFiltradas.isEmpty()) {
            System.out.println("Nenhuma despesa encontrada no período e com status " + statusDesejado);
        } else {
            System.out.println("Despesas com status " + statusDesejado + ":");
            for (Despesa despesa : despesasFiltradas) {
                System.out.println(despesa);
            }
        }
    }

    private static void gerenciarUsuarios() {
        System.out.println("Gerenciamento de Usuários (em desenvolvimento)...");
        // Aqui você pode implementar funcionalidades para cadastrar, editar, listar e excluir usuários
    }

    private static int obterEntradaInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Por favor, digite um número inteiro: ");
            }
        }
    }

    private static float obterEntradaFloat() {
        while (true) {
            try {
                String entrada = scanner.nextLine();
                return Float.parseFloat(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Por favor, digite um valor decimal: ");
            }
        }
    }

    private static LocalDate obterEntradaData() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (true) {
            try {
                return LocalDate.parse(scanner.nextLine(), formatter);
            } catch (Exception e) {
                System.out.print("Data inválida. Por favor, digite novamente no formato dd/MM/yyyy: ");
            }
        }
    }
}
