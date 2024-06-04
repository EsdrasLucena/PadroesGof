import java.util.ArrayList;

public class Notificador {
    private static Notificador instancia; //aqui eu garanto instancia unica  da classe
    private ArrayList<Observador> observadores; //Aqui onde guardo minha lista de observadores

    // Construtor  para implementar o Singleton
    private Notificador() { //o construtor é privado para impedir que outras classes criem novas
        // instancias de notificador usando o operador new.
        //Isso assegura que a única maneira de obter uma instância de Notificador
        // é através do método getInstancia().
        observadores = new ArrayList<>();
    }

    // Método para obter a instância única do Notificador
    public static Notificador getInstancia() { //é o metodo que fornece acesso global  a instancia unica de notificador
        if (instancia == null) {
            instancia = new Notificador(); //aqui ele cria a instancia unica caso ela nao exista ainda
        }
        return instancia; //retorna a instancia criada caso ja exista
        // isso me garante que a instancia é criada apenas quando é necessaria pela 1 vez
    }

    // Método para adicionar um observador
    public void adicionarObservador(Observador observador) {
        observadores.add(observador);
    }

    // Método para notificar todos os observadores
    public void notificar(String mensagem) {
        for (Observador observador : observadores) {
            observador.atualizar(mensagem);
        }
    }
}