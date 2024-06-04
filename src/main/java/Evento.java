import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Duration;
import java.time.format.DateTimeFormatter;

public class Evento {
    private static int contadorId = 1; // Contador estático para gerar IDs únicos
    private int idEvento;
    private String nomeEvento;
    private LocalDate dataEvento;
    private LocalTime horarioEvento;
    private String localEvento;
    private String tipoEvento;

    // Construtor da classe Evento
    public Evento(String nomeEvento, LocalDate dataEvento, LocalTime horarioEvento, String localEvento, String tipoEvento) {
        this.idEvento = contadorId++;
        this.nomeEvento = nomeEvento;
        this.dataEvento = dataEvento;
        this.horarioEvento = horarioEvento;
        this.localEvento = localEvento;
        this.tipoEvento = tipoEvento;
    }

    // Métodos getter e setter
    public int getIdEvento() {
        return idEvento;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public LocalDate getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(LocalDate dataEvento) {
        this.dataEvento = dataEvento;
    }

    public LocalTime getHorarioEvento() {
        return horarioEvento;
    }

    public void setHorarioEvento(LocalTime horarioEvento) {
        this.horarioEvento = horarioEvento;
    }

    public String getLocalEvento() {
        return localEvento;
    }

    public void setLocalEvento(String localEvento) {
        this.localEvento = localEvento;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    // Método para calcular o tempo restante até o evento
    public String calcularTempoRestante() {
        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime dataHoraEvento = LocalDateTime.of(this.dataEvento, this.horarioEvento);
        Duration duracao = Duration.between(agora, dataHoraEvento);

        long dias = duracao.toDays(); //Calculo dos dias restantes
        long horas = duracao.minusDays(dias).toHours(); //Calculo das horas restantes
        long minutos = duracao.minusDays(dias).minusHours(horas).toMinutes(); //Calculo dos minutos restantes

        return dias + " dias, " + horas + " horas e " + minutos + " minutos";
    }

    // Método para obter as informações do evento como string
    public String obterInformacoes() {
        return "ID: " + idEvento + "\nNome: " + nomeEvento + "\nData: " + dataEvento.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) +
                "\nHorário: " + horarioEvento + "\nLocal: " + localEvento + "\nTipo: " + tipoEvento +
                "\nTempo Restante: " + calcularTempoRestante() + "\n";
    }
}