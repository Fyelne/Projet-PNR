DROP TABLE utilisateur;

CREATE TABLE utilisateur (
    idUtilisateur INT NOT NULL AUTO_INCREMENT,
    nomUtilisateur VARCHAR(255) NOT NULL,
    prenomUtilisateur VARCHAR(255) NOT NULL,
    mdpUtilisateur VARCHAR(255) NOT NULL,
    telephone VARCHAR(255),
    adresse VARCHAR(255),
    estAdmin bool NOT NULL DEFAULT false,
    dateCreation TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT pk_utilisateur PRIMARY KEY(idUtilisateur)
);

INSERT INTO Utilisateur VALUES (0, "Admin", "Admin", "admin", "0123456789", "1 rue de la Paix", 1, DEFAULT);