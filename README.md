# Programowanie_urządzeń_mobilnych

## Aleksandra Witek
 
### Lista 1
 
 - [x] zad1 <br />
 
 - [x] zad2 (dodatkowo działający przycisk ",") 

### Lista 2

- [x] zad1 

### Lista 3

- [x] zad1 

### Lista 4

- [ ] zad1

### Lista 5

- [x] zad1

### Lista 6

- [x] zad1 - bez zapisywania do bazy danych 

### Lista 7

- [ ] zad1

### Lista 8

- [ ] zad1
 
 
### Projekt:

#### Aplikacja do monitorowania wydatków

Główny celem aplikacji jest monitorowanie dziennych wydatków.
Wpis zawiera kwotę, opis, datę itp.

Główne założenia:

- [x] dopracowane, przejrzyste GUI
- [x] logowanie 
- [x] połączenie z bazą danych, do której będą dodawane wpisy odnośnie wydatków
- [x] wyświetlanie danych za pomocą recyclerview
- [x] CRUD
- [x] alertDialogi

w trakcie tworzenia aplikacji mogą zostać jeszcze dodane nowe funkcjonalości. 

<b> GUI: </b>

- opracowane przeze mnie grafiki w Canvie (unikalne, stworzone na potrzeby projektu)

<b> Logowanie: </b>

- za pomocą konta Google
- łączenie z FireBase, gdzie przechowuję w GoogleCloud bazę danych wykorzystaną w projekcie

<b> Baza danych: </b>

- baza danych online (Firebase, google)
- aktualizowana w czasie rzeczywistym
- trzyma wszystkie dane, ale w RecyclerView wyświetla tylko te z data dzisiejsza
- CRUD (aktualizowany jest za rowno adaper (notifysetdatachange) oraz wysylana jest informacja do firebase o zmianach)

<b> Inne: </b>

- alertDialogi
- Toasty
- menu (u gory ikona account)
- string array z itemami (lista rozwijana)


