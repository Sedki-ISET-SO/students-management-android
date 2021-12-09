package com.example.gestionsetud;

public class Etudiant {
    private int id;
    private String nom;
    private String prenom;
    private String classe;
    private String groupe;
    private String login;
    private String password;

    public Etudiant() {
    }
    public Etudiant(String nom, String prenom, String classe, String groupe, String login, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.classe = classe;
        this.groupe = groupe;
        this.login = login;
        this.password = password;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getClasse() {
        return classe;
    }
    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getGroupe() {
        return groupe;
    }
    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Etudiant :\n" +
                " id = " + id +
                "\n nom = " + nom +
                "\n prenom = " + prenom +
                "\n classe = " + classe +
                "\n groupe = " + groupe +
                "\n login = " + login +
                "\n password = " + password +
                "\n\n";
    }
}
