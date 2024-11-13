# Online Store System
Online Store System to system sklepu internetowego, umożliwiający dodawanie produktów do bazy, składanie zamówień, aktualizacje bazy danych, dodawanie produktów do koszyka oraz generowanie faktury.
Projekt stworzony w oparciu o wzorzec **MVC**. Modelem są klasy modelu danych takie jak np. `Product` (oraz klasy po nim dziedziczące), `ShoppingCart`, `Order`, `Client` itp. Kontrolerem są klasy logiki biznesowej, czyli te, które operują na encjach i zazwyczaj mają "manager" w nazwie. Widokiem jest klasa `CommandLineInterface`, która umożliwia interakcję z użytkownikiem.

## Kluczowe klasy:

- **`Product.java`** - główna klasa modelu danych dla innych klas po niej dziedziczących. Typ dziedziczenia został ustawiony na `JOINED`, co pozwala na przechowywanie wszystkich typów produktów w jednej tabeli, a dodatkowe pola umieszczone są w dodatkowych tabelach.
- **`Client.java`** - klasa modelu danych przechowująca dane o użytkowniku. Klient ma przypisany tylko jeden `ShoppingCart.java`.
- **`ShoppingCart.java`** - klasa modelu danych przechowująca informacje o właścicielu oraz produktach w nim się znajdujących.
- **`Order.java`** - klasa modelu danych, przechowuje niezbędne dane o zamówieniu.
- **`AsyncConfig.java`** - klasa stworzona w celu ustawienia 20 wątków do wykorzystania.
- **`Discount.java`** - klasa modelu danych ułatwiająca wprowadzanie rabatów itp.

## Kluczowe metody:

- **`InvoicePdfSaver.saveInvoiceToPdf`** - metoda zapisująca fakturę za zamówienie w postaci PDF. Metoda wykorzystuje bibliotekę `org.apache.pdfbox`.
- **`OrderService.placeOrder`** - metoda odpowiedzialna za złożenie zamówienia. Wykorzystuje parę metod będących detalami implementacyjnymi, które służą m.in. do zmniejszenia ilości produktu w bazie po jego zakupie oraz opróżnieniu koszyka klienta.
- **`OrderProcessor.processOrder`** - metoda odpowiedzialna za przetwarzanie zamówień asynchronicznie i wielowątkowo, czyli nie blokuje głównego wątku.
- **`CommandLineInterface.start()`** - odpowiada za uruchomienie widoku aplikacji.

Projekt został stworzony w sposób umożliwiający jego łatwą skalowalność i dalszy rozwój.
