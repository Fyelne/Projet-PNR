DROP TABLE utilisateur;

CREATE TABLE utilisateur (
    idUtilisateur INT NOT NULL,
    nomUtilisateur VARCHAR(255) NOT NULL,
    prenomUtilisateur VARCHAR(255) NOT NULL,
    mdpUtilisateur VARCHAR(255) NOT NULL,
    telephone VARCHAR(255),
    adresse VARCHAR(255),
    estAdmin bool NOT NULL DEFAULT false,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT pk_utilisateur PRIMARY KEY(idUtilisateur)
);