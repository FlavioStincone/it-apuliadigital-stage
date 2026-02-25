# Funzionalità aggiunte in un progetto base CRUD #

## Lombok, Dipendenza presente su Spring Initializr ##

## Funzionamento delle Annotation della Dipendenza:##

**Lombok** è una libreria che ti libera dal **boilerplate**, cioè da tutto quel codice noioso e ripetitivo come getter, setter e costruttori.
Ti basta mettere qualche annotazione e Lombok genera tutto da solo durante la compilazione.

- **@NoArgsConstructor** per generare un costruttore senza argomenti.
- **@AllArgsConstructor** per generare un costruttore con tutti i campi dichiarati nel nostro Bean.
- **@Data** che a sua volta include @ToString, @EqualsAndHashCode, @Getter / @Setter e @RequiredArgsConstructor.
- **@Getter** e **@Setter**: per generare automaticamente i getter e i setter per ogni proprietà della classe.
- **@EqualsAndHashCode**: per generare i metodi equals e hashCode della classe;
- **@ToString**: per generare la rappresentazione testuale;
- **@RequiredArgsConstructor**: per un costruttore con i campi richiesti (cioè i campi con final e @NonNull);
- **@Builder**: genera il builder del Bean


