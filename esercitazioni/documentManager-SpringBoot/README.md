# Documenti API Management

Un'applicazione backend per la gestione di documenti classificati in base a tre livelli di sicurezza: Pubblici, Confidenziali e Segreti. 

Il sistema implementa una logica di accesso restrittiva tramite "Codici Presidente" e firme crittografate (BCrypt), includendo misure di sicurezza estreme come il **Protocollo di Emergenza**, che distrugge i documenti segreti in caso di accesso non autorizzato.

---

## Tecnologie Utilizzate

* **Core:** Java 21, Spring Boot 3.2.2
* **Data Access:** Spring Data JPA, Hibernate
* **Database:** * SQLite (Profilo di default per sviluppo/test rapido)
    * PostgreSQL (Profilo `postgres` per produzione/container)
    * H2 (Database in-memory per i test)
* **Sicurezza:** Spring Security Crypto (BCrypt per l'hashing delle firme)
* **Mapping & Boilerplate:** MapStruct (1.5.5.Final), Lombok
* **Documentazione API:** SpringDoc OpenAPI (Swagger UI 2.3.0)

---

## Architettura e Logica di Business

Il sistema gestisce tre tipologie di documenti che ereditano da una classe astratta base `Document`. 

### 1. Documenti Pubblici (`/document/public`)
* **Accesso:** Libero. Chiunque può creare e leggere questi documenti.
* **Caratteristiche:** Ogni volta che un documento pubblico viene letto tramite il suo numero di protocollo, un contatore di visualizzazioni (`viewsNumber`) viene incrementato automaticamente.

### 2. Documenti Confidenziali (`/document/confidential`)
* **Creazione:** Richiede un Codice Presidente valido.
* **Lettura:** Per leggere un documento confidenziale è necessario fornire il numero di protocollo e, in aggiunta, o la **Firma originale** (verificata tramite BCrypt) o un **Codice Presidente** valido.

### 3. Documenti Segreti (`/document/secret`)
* **Creazione:** Richiede un Codice Presidente valido.
* **Lettura & Protocollo di Emergenza:** L'accesso richiede un Codice Presidente esatto. **Attenzione:** se viene effettuata una richiesta di lettura per un documento segreto esistente fornendo un Codice Presidente errato, scatta il *Protocollo d'Emergenza* (`EmergencyProtocolException`): **il documento viene immediatamente e permanentemente distrutto dal database** per evitare fughe di dati.

### Codici Presidente Ammessi
Il sistema utilizza un'enumerazione (`PresidentCodeEnum`) per validare le autorizzazioni. I codici validi sono:
* `PRT45` (President Trump)
* `PRB46` (President Biden)
* `PRO44` (President Obama)
* `PRL16` (President Lincoln)
* `PRW01` (President Washington)