import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class SistemaNotificacao { //minha classe de sistemanotificacao utiliza o notificador para
    // notificar sobre eventos criados,alterados ou deletados
    private ArrayList<Evento> eventos;
    private Notificador notificador;
    Scanner scanner = new Scanner(System.in);

    // Construtor da classe SistemaNotificacao
    public SistemaNotificacao() {
        eventos = new ArrayList<>();
        notificador = Notificador.getInstancia(); //mais um exemplo de onde é usado singleton
    }

    // Método para criar um novo evento
    public void criarEvento(String nome, LocalDate data, LocalTime horario, String local, String tipo) {
        Evento evento = new Evento(nome, data, horario, local, tipo);
        eventos.add(evento);
        notificador.notificar("Evento criado com sucesso:\n" + evento.obterInformacoes());
    }

    // Método para listar todos os eventos
    public void listarEventos() {
        if (eventos.isEmpty()) {
            System.out.println("Nenhum evento criado ainda.");
        } else {
            for (Evento evento : eventos) {
                System.out.println(evento.obterInformacoes());
            }
        }
    }

    // Método para alterar um evento
    public void alterarEvento(int id, String nome, LocalDate data, LocalTime horario, String local, String tipo) {
        Evento evento = buscarEventoPorId(id);
        if (evento == null) {
            System.out.println("Evento não encontrado.");
        } else {
            evento.setNomeEvento(nome);
            evento.setDataEvento(data);
            evento.setHorarioEvento(horario);
            evento.setLocalEvento(local);
            evento.setTipoEvento(tipo);
            notificador.notificar("Evento alterado com sucesso:\n" + evento.obterInformacoes());
        }
    }

    // Método para deletar um evento
    public void deletarEvento(int id) {
        Evento evento = buscarEventoPorId(id);
        if (evento == null) {
            System.out.println("Evento não encontrado.");
        } else {
            boolean confirmado = false;
            while (!confirmado) {
                // Confirmar a operação de exclusão
                System.out.print("Deseja realmente deletar este evento? (1 para Sim, 2 para Não): ");
                int confirmacao = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer
                if (confirmacao == 1) {
                    eventos.remove(evento);
                    notificador.notificar("Evento deletado com sucesso.");
                    confirmado = true;
                } else if (confirmacao == 2) {
                    System.out.println("Operação de exclusão cancelada.");
                    confirmado = true;
                } else {
                    System.out.println("Opção inválida. Digite novamente.");
                }
            }
        }
    }

    // Método para buscar um evento pelo ID
    public Evento buscarEventoPorId(int id) {
        for (Evento evento : eventos) {
            if (evento.getIdEvento() == id) {
                return evento;
            }
        }
        return null;
    }

    // Método para verificar se há eventos cadastrados
    public boolean temEventos() {
        return !eventos.isEmpty();
    }
}