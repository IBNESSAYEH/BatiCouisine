CREATE TYPE EtatProjet AS ENUM ('EN_COURS', 'TERMINE', 'ANNULE');

CREATE TABLE Client (
                        id SERIAL PRIMARY KEY,
                        nom VARCHAR(100) NOT NULL,
                        adress VARCHAR(255) NOT NULL,
                        telephone VARCHAR(100) NOT NULL,
                        estProfessionnel BOOLEAN NOT NULL
);

CREATE TABLE Projet (
                        id SERIAL PRIMARY KEY,
                        nom VARCHAR(100) NOT NULL,
                        margeBeneficiaire NUMERIC(10, 2) NOT NULL,
                        coutTotal NUMERIC(10, 2) NOT NULL,
                        etat EtatProjet NOT NULL,
                        surfaceCouisine NUMERIC(10, 2) NOT NULL,
                        id_client INTEGER NOT NULL,
                        FOREIGN KEY (id_client) REFERENCES Client(id)
);

CREATE TABLE Composant (
                           id SERIAL PRIMARY KEY,
                           nom VARCHAR(100) NOT NULL,
                           typeComposant VARCHAR(50) NOT NULL,  -- Indique le type: Materiau ou MainDOeuvre
                           taux_TVA NUMERIC(10, 2) NOT NULL,
                           id_projet INTEGER,
                           FOREIGN KEY (id_projet) REFERENCES Projet(id)
);

-- ici la table Materiau et la table MainDoeuvre héritent la table composant

CREATE TABLE Materiau (
                          coutUnitaire NUMERIC(10, 2) NOT NULL,
                          quantite NUMERIC(10, 2) NOT NULL,
                          coutTransport NUMERIC(10, 2) NOT NULL,
                          coefficientQualite NUMERIC(10, 2) NOT NULL
) INHERITS (Composant);

CREATE TABLE MainDOeuvre (
                             tauxHoraire NUMERIC(10, 2) NOT NULL,
                             heuresTravail NUMERIC(10, 2) NOT NULL,
                             productiviteOuvrier NUMERIC(10, 2) NOT NULL
) INHERITS (Composant);

CREATE TABLE Devis (
                       id SERIAL PRIMARY KEY,
                       montantEstime NUMERIC(10, 2) NOT NULL,
                       dateEmission DATE NOT NULL,
                       dateValidite DATE NOT NULL,
                       isAccepte BOOLEAN NOT NULL,
                       id_projet INTEGER,
                       FOREIGN KEY (id_projet) REFERENCES Projet(id)
);

