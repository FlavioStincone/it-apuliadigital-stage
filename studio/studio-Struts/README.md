# Struts 2 Saluto Web App

Un'applicazione web Java basata sul framework **Apache Struts 2**, progettata per dimostrare il pattern architetturale MVC (Model-View-Controller). 

Il progetto espone una semplice azione di saluto (`SalutoAction`) gestita attraverso le configurazioni classiche XML, dimostrando come le richieste HTTP vengano intercettate, processate dal framework e reindirizzate alla vista corretta.

---

## Tecnologie Utilizzate

* **Linguaggio:** Java
* **Framework MVC:** Apache Struts 2
* **Build System & Dipendenze:** Apache Maven (`pom.xml`)
* **Configurazione Web:** Java EE Deployment Descriptor (`web.xml`)
* **Diagrammi e Architettura:** Excalidraw (`study.excalidraw`)

---

## Architettura e Struttura del Progetto

Il progetto si basa sulla configurazione standard di una web application Struts 2. I componenti principali sono:

### 1. Il Filtro di Struts (`web.xml`)
Il file `web.xml` agisce da punto di ingresso. Configura il `StrutsPrepareAndExecuteFilter`, il filtro principale che intercetta tutte le richieste in arrivo all'applicazione (solitamente mappato su `/*`) e le passa al framework Struts per la risoluzione.

### 2. Configurazione delle Azioni (`struts.xml`)
Il file `struts.xml` è il cuore del routing di Struts 2. Qui vengono definiti i "package", le "action" e i "result". Mappa le URL in ingresso (es. `/saluto`) alla classe Java corrispondente (`SalutoAction`) e definisce quale pagina JSP (o altra vista) mostrare in base al risultato dell'azione (es. `SUCCESS` o `ERROR`).

### 3. L'Azione Controller (`SalutoAction.java`)
Questa classe funge da Controller. Estende solitamente `ActionSupport` (o implementa l'interfaccia `Action`). Contiene la logica di business, le variabili (con i relativi getter e setter per l'injection automatico dei parametri della form HTTP) e il metodo `execute()` che restituisce la stringa di navigazione verso la View.

### 4. Diagramma di Flusso (`study.excalidraw`)
Il file Excalidraw allegato contiene gli schemi e i diagrammi di studio utilizzati per progettare l'architettura dell'applicazione e il flusso della richiesta dal client, al filtro, all'Action, fino al rendering della View.