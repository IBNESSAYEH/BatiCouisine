package com.BatiCouisine.repository.implementation;

import com.BatiCouisine.entities.Devis;
import com.BatiCouisine.repository.DevisRepositry;
import com.BatiCouisine.util.DBConnection;
import com.BatiCouisine.util.DBUtils;

import java.sql.*;
import java.util.Optional;

public class DevisRepositoryImp implements DevisRepositry {
    private Connection dbConnection;

    public DevisRepositoryImp() {
        dbConnection = DBConnection.getConnectionInstance().getConnection();
    }

    public void store(Devis devis, int idProjt){
        String sqlQuery = "INSERT INTO devis (montantestime, dateemission, datevalidite, isaccept, id_projet) values(?,?,?,?,?)";
        PreparedStatement pStatement = null;
        ResultSet generatedKeys = null;
        try{
            pStatement = dbConnection.prepareStatement(sqlQuery, pStatement.RETURN_GENERATED_KEYS);
            pStatement.setDouble(1, devis.getMontantEstime());
            pStatement.setDate(2, new java.sql.Date(devis.getDateMission().getTime()));
            pStatement.setDate(3, new java.sql.Date(devis.getDateValidite().getTime()));
            pStatement.setBoolean(4, devis.isAccepted());
            pStatement.setInt(5, idProjt);
            int affectedRow = pStatement.executeUpdate();
            if(affectedRow == 0){
                throw new SQLException("No rows affected");
            }
            generatedKeys = pStatement.getGeneratedKeys();
            devis.setId(generatedKeys.getInt(1));
            System.out.println("le devis est creer avec succes");
        } catch (SQLException e) {
            System.err.println("erreur pendant l'execution du query : " + e.getMessage() + " verifier la connexion avec la base dedonnee." );
            e.printStackTrace();
        }finally {
            DBUtils.closeResources(generatedKeys, pStatement);
        }



    };

//    public List<Devis> retrieveAllClients(){
//
//    };
    public Optional<Devis> findById(int id){
        String sqlQuery = "SELECT * FROM devis WHERE id = ?";
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        Optional<Devis> devisOptional = Optional.empty();
        try{
            pStatement = dbConnection.prepareStatement(sqlQuery);
            pStatement.setInt(1, id);
            resultSet = pStatement.executeQuery();
            if(resultSet.next()){
                Devis devis = new Devis();
                devis.setId(id);
                devis.setMontantEstime(resultSet.getDouble("montantestime"));
                devis.setDateMission(resultSet.getDate("dateemission"));
                devis.setDateValidite(resultSet.getDate("datevalidite"));
                devis.setAccepted(resultSet.getBoolean("isaccept"));
                devisOptional = Optional.ofNullable(devis);
            }else{
                throw new SQLException("Le devis n'existe pas");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeResources(resultSet, pStatement);
        }
        return devisOptional;
    }

    public void update(int id, Devis devis){
    String sqlQuery = "UPDATE devis SET montantestime = ?, dateemission = ?, datevalidite = ?, isaccept = ?, id_projet = ? WHERE id = ?";
    PreparedStatement pStatement = null;
    try{
        pStatement = dbConnection.prepareStatement(sqlQuery);
        pStatement.setDouble(1, devis.getMontantEstime());
        pStatement.setDate(2, new java.sql.Date(devis.getDateMission().getTime()));
        pStatement.setDate(3, new java.sql.Date(devis.getDateValidite().getTime()));
        pStatement.setBoolean(4, devis.isAccepted());
        pStatement.setInt(5, id);
        int affectedRow = pStatement.executeUpdate();
        if(affectedRow == 0){
            throw new SQLException("Erreur : le dvis n'etatis pas se modifier ;");
        }
        System.out.println("le devis est modifier avec succes");
    } catch (SQLException e) {
        e.printStackTrace();
    }finally {
        DBUtils.closeResources(pStatement);
    }
    };

    public void destroy(int id){
        String sqlQuery = "DELETE FROM devis WHERE id = ?";
        PreparedStatement pStatement = null;
        try{
            pStatement = dbConnection.prepareStatement(sqlQuery);
            pStatement.setInt(1, id);
            int affectedRow = pStatement.executeUpdate();
            if(affectedRow == 0){
                System.out.println("Erreur : le dvis n'etatis pas se supprimer ;");
            }
            System.out.println("le devis est supprimer avec succes");
        }catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DBUtils.closeResources(pStatement);
        }
    };

}
