Il composite di trova in SessionHistory

è composto da due interfacce
GameHistory (usata per il comando comune a tutto il composite e per la leaf)
Composite: GameHistory (usata per il composite)

Le implemntazioni sono composte da 5 classi:

SessionHistory (
    è un singleton composite, non necessario sia un singleton,
    ma lo rende più facile da usare e da accedere dato che c'è una UI)

GenericComponent (
    è la base dei container (component) del composite,
    contiene i metodi comuni a tutti i container ed eredita da GameHistory per condividere la stessa funzione delle leaf,
    quest'ultima è più che altro una buona pratica in modo da poter avere una sintassi migliore nella lista children)

Difficulty (
    L'unica differenza che ha questa classe da Generic Composite è che aggiunge il "una descrizione alla session history
    nel suo caso specifico sovrascrive il getSessionBoard() e aggiunge la difficoltà (il numero di bombe) e un po' di tabulazione")

Size (
    L'unica differenza che ha questa classe da Generic Composite è che aggiunge il "una descrizione alla session history
    nel suo caso specifico sovrascrive il getSessionBoard() e aggiunge la dimensione della bord '$Xx$Y' " e un po' di tabulazione)

Game (
    Questa è la leaf, ha solo il metodo getSessionBoard implementando l'intefaccia GameHistory,
    non ha la possibilità di risalire al parent per selta,
    L'implemntazione sarebbe:

    interface GameHistory {
    	val parent: Composite? //<-- non presente e rimuoverlo da Composite, non serve che sia GameHistory dato che abbiamo detto che è solo per le leaf
    	fun getSessionBoard(): String
    }

    class Game(
        private val state: String,
        override val parent: Composite? //<-- non presente, qui in kotlin di dichiara il costruttore di base e dichiara allo stesso tempo le variabili
    ): GameHistory {
        override fun getSessionBoard(): String {
            return state
        }
    }

    )

Difficulty e Size non sono necessari al funzionamento del composite ma rendono più facile la formattazione del testo per la UI
Invece di modificare a mano l'ouptput di GenericComposite

Ho deciso di creare le classi Difficulty e size per dimostrare che il container è in grado ti avere più classi che lo compongono.
Nonostante ciò è possibile semplificarle aggiungendo a genericContainer un comapo "dato", nome e un campo "prefisso"
dove il dato rappresenterebbe X*Y o n_bombs
nome rappresenterebbe il nome del campo "Dimensione: " o "Difficoltà: "
il prefisso invece ciò che viene messo prima di ogni children
e magari un suffisso dopo ogni children

in questo modo con una classe possiamo avere realisticamente infinite combinazioni di output
ad esempio potremmo avere un contatore di vittorie sconfitte invece della lista di esse e potremmo formattarlo con:
dato: for x in ... N_vittorie
nome = "Vittorie: "
prefisso = "            "
suffisso = "\n"

inoltre se siamo interessati solo ad una parte del composite il getSessionBoard può essere chiamato in qualsiasi punto e sarebbe un risultato validi in base a dove chiamato

Pattern usati in questo progetto:
- Composite (usato per la SessionHistory, non era realmente una mia priorità ma è il pattern che dovevo fare)
- Iterator (per iterare sulla matrice di bombe, c'è ne sono 2 uno diìei quali itera in un range usato pe ril doppio click sulle celle)
- Singleton (gli object i kotlin sono singleton, non esiste il metoto static)
- Factory (per creare le classi)
- State (per le celle principalmente ma anche per lo stato della partita)
- Observer (per la UI) (non l'ho implementato io ho ricliclato Observable di kotlin)
- Proxy (
    per impedire l'accesso diretto alle celle e bloccacrne le interazioni quando il game finisce,
    se si disattiva il proxy nella factory si può vedere che le celle possono essere cliccate anche dopo che il gioco finisce
    )
- Prototype (usato nella gameboard per poter salvate e caricare il gioco)


P.S.: lo so che la console spamma un sacco di errori di CSS ma il css è marginale all'app e si vede bene lo stesso quindi se non l'ho sistematop per la consegna è perché era noioso
P.S. pt2: fare tasto destro su new game apre un popup con delle opzioni per creare una nuova partita personalizzata
il formato per i salvataggi è:

10, 10, 10// 10x10 board with 10 mines
// D E F H R Doubted, Exploded, Flagged, Hidden, Revealed - Bombs (upper)
// d e f h r doubted, exploded, flagged, hidden, revealed - Empty (lower)

le bombe sono indicare nei 3 dati perché avevo intenzione di fare in modo che se la griglia non fosse stata dichairata la generasse
mai avuta la voglia di farlo
inoltre ho implementato una funzione per assicurarsi che il primo click non si ana bomba e limitato il numero di bombe a x*y-9 per renderlo sempre possibile
noterete però che potete ancora morire al primo colpo dato che non ho cambiato il punto in cui la board viene generata.
Eh si questo si potrebbe realizzare con un bellissimo proxy che fa credere che la board ci sia finché non riceve un'azione, ma non l'ho fatto.