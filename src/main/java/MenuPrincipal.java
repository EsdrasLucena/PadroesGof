import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class MenuPrincipal implements Observador { //meu menu principal implementa a interface de observador
    // portanto é um observador
    private SistemaNotificacao sistemaNotificacao;
    private Scanner scanner;

    // Construtor da classe MenuPrincipal
    public MenuPrincipal() {
        sistemaNotificacao = new SistemaNotificacao(); // Instancia SistemaNotificacao
        Notificador.getInstancia().adicionarObservador(this); // Adiciona a instância atual como observador, é aqui que é usado o singleton
        // o meu notificador gerencia a lista de observadores  e vai notificar quando necessario
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        MenuPrincipal menu = new MenuPrincipal(); // cria uma instancia de MenuPrincipal
        menu.executar(); // chama o metodo executar para iniciar o menu
    }

    // Método para exibir o menu e processar as opções
    public void executar() {
        int opcao = 0;

        while (opcao != 5) {
            System.out.println("1. Criar Evento");
            System.out.println("2. Listar Eventos");
            System.out.println("3. Alterar Evento");
            System.out.println("4. Deletar Evento");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1:
                    criarEvento();
                    break;
                case 2:
                    listarEventos();
                    break;
                case 3:
                    alterarEvento();
                    break;
                case 4:
                    deletarEvento();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }

    // Método para criar um evento
    public void criarEvento() {
        System.out.print("Nome do Evento: ");
        String nome = scanner.nextLine(); // atribui nome do evento

        System.out.print("Data do Evento (DDMMYYYY): ");
        String dataInput = scanner.nextLine();
        StringBuilder dataFormatada = new StringBuilder();
        for (int i = 0; i < dataInput.length(); i++) {
            if (i == 2 || i == 4) {
                dataFormatada.append("-");
            }
            dataFormatada.append(dataInput.charAt(i));
        }
        LocalDate data = LocalDate.parse(dataFormatada.toString(), DateTimeFormatter.ofPattern("dd-MM-yyyy")); // Converte para LocalDate

        System.out.print("Horário do Evento (HHMM): ");
        String horarioInput = scanner.nextLine();
        StringBuilder horarioFormatado = new StringBuilder();
        for (int i = 0; i < horarioInput.length(); i++) {
            if (i == 2) {
                horarioFormatado.append(":");
            }
            horarioFormatado.append(horarioInput.charAt(i));
        }
        LocalTime horario = LocalTime.parse(horarioFormatado.toString(), DateTimeFormatter.ofPattern("HH:mm"));

        System.out.print("Local do Evento: ");
        String local = scanner.nextLine();
        System.out.print("Tipo do Evento: ");
        String tipo = scanner.nextLine();

        sistemaNotificacao.criarEvento(nome, data, horario, local, tipo); //cria o evento
        atualizar("Atenção: um novo evento foi criado");
    }

    // Método para listar eventos
    public void listarEventos() {
        sistemaNotificacao.listarEventos();
    }

    // Método para obter um ID válido do evento
    public int obterIdValido(String acao) {
        int id = -1;
        while (id == -1) {
            System.out.print("Digite o ID do evento que deseja " + acao + ": ");
            id = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer
            if (sistemaNotificacao.buscarEventoPorId(id) == null) {
                System.out.println("ID inválido. Por favor, tente novamente.");
                id = -1; //atribui -1 pra voltar pro loop
            }
        }
        return id;
    }

    // Método para alterar um evento
    public void alterarEvento() {
        if (sistemaNotificacao.temEventos()) {
            listarEventos();
            int id = obterIdValido("alterar");

            Evento evento = sistemaNotificacao.buscarEventoPorId(id);

            System.out.print("Novo Nome do Evento: ");
            String nome = scanner.nextLine();

            System.out.print("Nova Data do Evento (DDMMYYYY): ");
            String dataInput = scanner.nextLine();
            StringBuilder dataFormatada = new StringBuilder();
            for (int i = 0; i < dataInput.length(); i++) {
                if (i == 2 || i == 4) {
                    dataFormatada.append("-");
                }
                dataFormatada.append(dataInput.charAt(i));
            }
            LocalDate data = LocalDate.parse(dataFormatada.toString(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));

            System.out.print("Novo Horário do Evento (HHMM): ");
            String horarioInput = scanner.nextLine();
            StringBuilder horarioFormatado = new StringBuilder();
            for (int i = 0; i < horarioInput.length(); i++) {
                if (i == 2) {
                    horarioFormatado.append(":");
                }
                horarioFormatado.append(horarioInput.charAt(i));
            }
            LocalTime horario = LocalTime.parse(horarioFormatado.toString(), DateTimeFormatter.ofPattern("HH:mm"));

            System.out.print("Novo Local do Evento: ");
            String local = scanner.nextLine();
            System.out.print("Novo Tipo do Evento: ");
            String tipo = scanner.nextLine();

            sistemaNotificacao.alterarEvento(id, nome, data, horario, local, tipo);
            atualizar("Atenção: um evento foi alterado");

        } else {
            System.out.println("Nenhum evento criado ainda.");
        }
    }

    // Método para deletar um evento
    public void deletarEvento() {
        if (sistemaNotificacao.temEventos()) {
            listarEventos();
            int id = obterIdValido("deletar");
            sistemaNotificacao.deletarEvento(id);
            atualizar("Atenção: um evento foi deletado");
        } else {
            System.out.println("Nenhum evento criado ainda.");
        }
    }

    // Método para atualizar a notificação
    @Override
    public void atualizar(String mensagem) {
        System.out.println(mensagem);
    }
}
