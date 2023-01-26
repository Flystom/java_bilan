package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class ConnexionService {

    Connection cn = null;

    private String URL = "jdbc:derby:java_bilan;create=true";
    private String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private String LOGIN = "";
    private String PWD = "";

    //Méthode pour créer la table des utilisateur (utilisé une seule fois)
    public void tableUtilisateur() throws SQLException {

        Statement st = cn.createStatement();
        //st.execute("drop table utilisateur");
//        st.execute("create table utilisateur (id INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY,"
//                + "nom varchar(255), mdp varchar(255))");
//        st.executeUpdate("INSERT INTO utilisateur (nom, mdp) VALUES ('Antonin', 'Antonin')");
//        st.executeUpdate("INSERT INTO utilisateur (nom, mdp) VALUES ('Florian', 'Florian')");
    }


    //Méthode pour vérifier la connexion
    public boolean verificationConnexion(String nom, String mdp) throws SQLException, IOException {
        cn = DriverManager.getConnection(URL, LOGIN, PWD);
       
        Statement st = cn.createStatement();
        ResultSet verification = st.executeQuery("SELECT nom, mdp FROM utilisateur where nom = '"+ nom + "' and mdp = '"+ mdp +"'");
        if (verification.next()) {
            return true;
        } else {
            return false;
        }

    }

}
