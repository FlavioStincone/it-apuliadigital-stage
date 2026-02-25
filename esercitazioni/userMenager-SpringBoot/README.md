# Esercitazione CRUD – Stage Exprivia

## Descrizione del progetto

Questo progetto consiste nella **creazione di un’applicazione CRUD** (*Create, Read, Update, Delete*) sviluppata con **Spring Boot**, che permette la gestione di utenti suddivisi in due tipologie principali: **Client** ed **Employee**.  

L’applicazione interpreta il concetto di “utente” attraverso una gerarchia di classi:

- Una **classe astratta** `User` (senza `id`), che definisce le proprietà comuni (`name`, `email`, `role`)
- Due **entità concrete**, `Client` e `Employee`, che **estendono `User`** e possiedono un **id indipendente**
- Un **enum `Role`** per distinguere i tipi di utente

Le operazioni CRUD sono attualmente implementate separatamente per `Client` ed `Employee`.  
In futuro, il progetto verrà esteso con nuove funzionalità e componenti.

---

## Funzionalità principali

- **Creazione** di un nuovo utente (`Client` o `Employee`, determinato dal `Role`)
- **Lettura** della lista completa degli utenti di ciascun tipo
- **Aggiornamento** dei dati di un utente esistente
- **Eliminazione** di un utente
- **Mapping automatico** tra entità e DTO tramite *MapStruct*
- **Riduzione del boilerplate code** grazie a *Lombok*
- **Uso di record Java** per la definizione dei DTO

---

## Struttura e concetti chiave

- **Classi astratte** → `User` funge da base comune per le entità concrete  
- **Enum `Role`** → definisce i ruoli possibili (`CLIENT`, `EMPLOYEE`)  
- **Record DTO** → `ClientDTO` e `EmployeeDTO` semplificano il trasferimento dati  
- **Repository** → interfacce `JpaRepository` per la gestione dei dati  
- **Service layer** → logica CRUD separata per Client e Employee  
- **Controller REST** → endpoint per ogni entità con API CRUD standard  

---

## Tecnologie utilizzate

- **Java 21**
- **Spring Boot 3.3.x**
- **Spring Data JPA**
- **SQLite** (database locale)
- **Lombok**
- **MapStruct**
- **Maven**
- **Git / GitHub**

---

## Obiettivi formativi

- Comprendere la **struttura di un progetto Spring Boot**
- Implementare una **gerarchia di entità** con ereditarietà JPA
- Utilizzare **record**, **enum**, e **classi astratte**
- Gestire il **mapping automatico** tra entità e DTO con MapStruct
- Ridurre il codice boilerplate con le annotazioni Lombok
- Implementare **CRUD completi** per entità distinte ma correlate
