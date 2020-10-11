# 201011-Java2-ProiectFinal

Program pe 3 nivele: 1. baza de date, 2. server, 3.client. Foloste concepte si framework-uri precum: JPA -> Hybernate, ORM, RMI.

Atentie!!! pentru ca programul sa functioneze trebuie sa aveti o biblioteca externa care se gaseste la adresa: https://spaces.hightail.com/receive/b2BMj

- pe langa dependintele de hybernate si mysql connector j partea de client mai contine o dependinta care se numeste GLG2D si se afla in pom-ul clientului si care se poate gasi la adresa:
   https://brandonborkholder.github.io/glg2d/  -> nu este functionala in proiect -> este folosita doar pentru frame-ul de loading ca sa arunce codul pe placa video. Este posibil ca intimpul
   folosirii dependintei GLG2D placa video sa dea eroare.
   

- Ideea din spatele programului este urmatoare:

- este un program destinat service-ului auto si contine:

1. Register page -> sunt creati userii care au aces la program. Atentie ca programul sa functioneze normal este nevoie de 2 useri:
    a) User WAREHOUSE -> are dreptul de a adauga piese (parts) in comenzi dar nu are voie sa deschisa comenzi.
    b) User BODY sau MECHANICAL - > acesti useri deschid comenzi pentru clienti dar nu au dreptul de a adauga piese in comnezi. Au drept de emitere factura la final.

2. Login Page -> dupa efectuarea inregistrarii user-ul urmeaza sa se logheze fie cu username fie cu email.

3) CreateClientAndVehiclePage -> pagina unde sunt introdusi clientii service-ului. Se pot introduce in acelasi timp, client impreuna cu autovehicul sau se poate introduce mai intai clientul
    fara sa aiba repartizat un autovehicul iar in cazul in care clientul are mai multe masini, daca acel client se afla in baza de date se face o cautare dupa nume dupa care se trec
    modelul masinii si seria de caroserie in campurile specifice si se adauga masina la clientul respectiv -> Create Vehicle;

4) CreateOrderPage -> pagina unde se creeeaza comenzile de service. Pentru creerea unei comenzi se cauta mai intai seria de caroserie a masinii pentru care dorim sa deschidem comanda.
    Dupa ce seria este gasita in baza de date se apasa butonul de create order si se creeaza comanda. Cu butonul de "add car problem" putem sa adaugam la comanda problemele pe care
    le are masina acestea fiind comunicate de client. Dupa creerea comenzii aceasta va aparea in tabelul din stg iar in cazul in care o selectam vor aparea pe textArea -> problemele masinii si
    in tabelul din dr piesele care sunt in comanda, daca avem.

5) PartPage -> aici se adauga piese in comenzile de service. Se selecteaza mai intai o comanda din tabelul de comenzi (OPEN sau READY) si se adauga piese in ele. Atentie -> doar user de tip 
    WAREHOUSE poate adauga piese in comanda. Iar la sfarsit cand s-au adaugat toate piesele se apasa butonul  "close part order" iar cel care a creeat comanda respectiva primeste notificare ca 
   status comenzii s-a schimbat, din OPEN in READY.

6) La final userul care a creeat comanda o poate inchide -> Status -> CLOSE prin apasarea butonul de "Bill" si efectuarea unei facturi. O data inchisa comanda nu se mai pot adauga piese in ea.
Programul are o eroare doar cand este pornit pentru prima data si initilizata baza de date. Din cate am vazut nu afecteaza cu nimic rularea programului. Daca se schimba strategia de generare a id-ului entitatilor ce sunt persiatate din TABLE in IDENTITY vad ca eroarea dispare.
sau datorita strategiei de generarea a id-ului.

p.s: operatiile pe baza de date nu sunt optimizate. Partea buna e ca macar cand fac un select nu iau toata baza de data dupa mine:D.

Warning!! the sounds and pictures from this program dont belog to me. The pictures you can find them here https://unsplash.com/
All pictures and sounds are used only for documentation or learnig purpose they are not for comercial use. If you dont like to use your piscture or sounds write me here: faraonu_ginitoru@yahoo.com and i will take them down.

