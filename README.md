# PadroesGof
Padrões usados: Observer e Singleton

. A Unica classe que atua como observador no meu código é a MenuPrincipal
. A minha classe de MenuPrincipal implementa a interface Observador, por isso é um observador
. O meu notificador gerencia a lista de observadores e os notifica quando for necessário
(quando houver métodos que criem,alterem ou deletem eventos)
. A classe responsável por isso é a classe de SistemaNotificacao.
    ela utiliza o Notificador para notificar sobre eventos criados, alterados ou deletados.


. O meu singleton está presente na classe notificador
. O construtor 'private notificador' é onde eu garanto ele de impedir a criação de instâncias fora da classe
. No momento que instancio ele sendo estático, ele é compartilhado pelas outras
    instancias da classe e isso garante que todas referenciem a essa mesma instancia.
    Então eu instancio uma unica vez para que ela seja usada e referenciadas por ela
. Como há apenas uma instância do Notificador, ele pode gerenciar sozinho a lista de observadores
    e a notificação dos mesmos.
. Isso evita conflitos que poderiam ocorrer se varias instâncias criadas
    tentassem modificar a lista de observadores ao msm tempo.
. Vantagens do singleton: economia de memoria, como só tem uma unica instancia, é menos custosa
. O meu singleton é usado