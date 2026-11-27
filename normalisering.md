# Normalisering

### Ursprunglig tabell

| uthyrningsnummer | datum      | kunduppgifter                            | filmtitel   | speltid |
|------------------|------------|------------------------------------------|-------------|---------|
| 101              | 2026-11-01 | Anna Berg, anna@example.com, 070-1111111 | Stjärnresan | 119 min |
| 102              | 2026-11-02 | Erik Lind, erik@example.com, 070-2222222 | Månjakten   | 99 min  |
| 103              | 2026-11-05 | Anna Berg, anna@example.com, 070-1111111 | Månjakten   | 99 min  |

### Uppdelade celler

Uppdelningen löser problemet med flera uppgifter i samma kolumn, vilket gör informationen svårare att hantera och mindre
sökbar.

| uthyrningsnummer | datum      | förnamn | efternamn | email            | telefonnummer | filmtitel   | speltid |
|------------------|------------|---------|-----------|------------------|---------------|-------------|---------|
| 101              | 2026-11-01 | Anna    | Berg      | anna@example.com | 070-1111111   | Stjärnresan | 119 min |
| 102              | 2026-11-02 | Erik    | Lind      | erik@example.com | 070-2222222   | Månjakten   | 99 min  |
| 103              | 2026-11-05 | Anna    | Berg      | anna@example.com | 070-1111111   | Månjakten   | 99 min  |

### Fakta som upprepas och kräver ändringar på flera ställen:

- Förnamn
- Efternamn
- Email
- Telefonnummer
- Filmtitel
- Speltid

### Uppgifter som hör ihop och borde få en egen tabell:

- Förnamn, efternamn, email, telefonnummer
- Filmtitel, speltid
- Uthyrningsnummer, datum

## Egna tabeller

Med uppdelade tabeller löses problemet med upprepad information. Informationen organiseras efter kategorier som logiskt
hör ihop, och ingen kolumn får upprepad information.

## Customer

Alla kunduppgifter hamnar i *customer*-tabellen med *customer_id* som PK. *Email* är fortfarande UNIQUE för att
förhindra att samma person registrerar sig med samma emailadress flera gånger, även om den inte längre används som
kund-id.

| customer     | Attribut                         |
|--------------|----------------------------------|
| customer_id  | INT, AUTO_INCREMENT, PRIMARY KEY |
| first_name   | VARCHAR(30), NOT NULL            |
| last_name    | VARCHAR(30), NOT NULL            |
| email        | VARCHAR(100), UNIQUE, NOT NULL   |
| phone_number | VARCHAR(20), NOT NULL            |

**Exempel med ursprunglig data:**

| customer_id | first_name | last_name | email            | phone_number |
|-------------|------------|-----------|------------------|--------------|
| 1           | Anna       | Berg      | anna@example.com | 070-1111111  |
| 2           | Erik       | Lind      | erik@example.com | 070-2222222  |

## Movie

I *movie* hör allt filmrelaterat till, så bara titel och speltid med movie_id som PK. *Title* är fortfarande UNIQUE av
samma anledning som *email* i *customer* - för att förhindra att samma filmtitel registreras flera gånger.

| movie           | Attribut                         |
|-----------------|----------------------------------|
| movie_id        | INT, AUTO_INCREMENT, PRIMARY KEY |
| title           | VARCHAR (100), NOT NULL, UNIQUE  |
| runtime_minutes | INT, NOT NULL                    |

**Exempel med ursprunglig data:**

| movie_id | title       | runtime_minutes |
|----------|-------------|-----------------|
| 1        | Stjärnresan | 119             |
| 2        | Månjakten   | 99              |

## Rental

I den tredje tabellen *rental* stoppar vi de uppgifter som varken hör till kunder eller filmer, alltså
det som rör uthyrningarna själva. På så vis kan koppla vi samman de två tidigare tabellerna med hjälp
av deras id-nycklar som FOREIGN KEY. Vi använder inte *customer_id* och *movie_id* som en kombinerad nyckel
eftersom en film kan hyras av samma kund flera gånger.

| rental      | Attribut                                           |
|-------------|----------------------------------------------------|
| rental_id   | INT, AUTO_INCREMENT, PRIMARY KEY                   |
| date        | DATE, NOT NULL                                     |
| customer_id | INT, NOT NULL, FOREIGN KEY → customer(customer_id) |
| movie_id    | INT, NOT NULL, FOREIGN KEY → movie(movie_id)       |

**Exempel med ursprunglig data:**

| rental_id | date       | customer_id | movie_id |
|-----------|------------|-------------|----------|
| 101       | 2026-11-01 | 1           | 1        |
| 102       | 2026-11-02 | 2           | 2        |
| 103       | 2026-11-05 | 1           | 2        |

## Relationer

**Kund ⟷ Uthyrning**  
Kardinalitet: 1:N
Koppling: Obligatorisk. En kund kan göra flera uthyrningar men varje uthyrning är bara kopplad till en kund.

**Film ⟷ Uthyrning**  
Kardinalitet: 1:N
Koppling: Obligatorisk. Samma film kan hyras ut flera gånger och kopplas till flera uthyrningar, men varje
uthyrning är bara kopplad till en film.