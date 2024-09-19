package com.BatiCouisine.repository.implementation;

import com.BatiCouisine.entities.Client;
import com.BatiCouisine.repository.ClientRepository;
import com.BatiCouisine.util.DBConnection;
import com.BatiCouisine.util.DBUtils;

import java.sql.*;
import java.util.HashMap;
import java.util.Optional;

public class ClientRepositoryImp implements ClientRepository {
    private Connection dbConnection;

    public ClientRepositoryImp() {
        this.dbConnection = DBConnection.getConnectionInstance().getConnection();
    }

    public int store(Client client) {
        String sqlQuery = "INSERT INTO client (nom, estprofessionnel, telephone, address) values (?, ?, ?, ?)";
        PreparedStatement pStatement = null;
        ResultSet generatedKeys = null;
        int generatedId = 0;
        try{
            pStatement = dbConnection.prepareStatement(sqlQuery, pStatement.RETURN_GENERATED_KEYS);
            pStatement.setString(1,client.getNom());
            pStatement.setBoolean(2, client.isProfessionnel());
            pStatement.setString(3, client.getNumeroTelephone());
            pStatement.setString(4, client.getAddress());
            int affectedRow = pStatement.executeUpdate();
            if(affectedRow == 0){
                throw new SQLException("Erreur : pendant la creation du client");
            }
            if(generatedKeys.next()){
                generatedKeys = pStatement.getGeneratedKeys();
                generatedId = generatedKeys.getInt(1);
                client.setId(generatedId);
            }else{
                throw new SQLException("Erreur : pendant la creation du client");
            }

            System.out.println("le client : " + client.getNom() + " est creer avec succes");
        } catch (SQLException e) {
            System.err.println("erreur pendant l'execution du query : " + e.getMessage() + " verifier la connexion avec la base dedonnee." );
            e.printStackTrace();
        }finally {
            DBUtils.closeResources(generatedKeys, pStatement);
        }

        return generatedId;
    }

    public HashMap<String, Client> retrieveAllClients() {
        String sqlQuery = "SELECT * FROM client";
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        HashMap<String, Client> clientHashMap = new HashMap<>();

        try{
            pStatement = dbConnection.prepareStatement(sqlQuery);
            resultSet = pStatement.executeQuery();
            while(resultSet.next()){
                Client client = new Client();
                client.setId(resultSet.getInt("id"));
                client.setNom(resultSet.getString("nom"));
                client.setAddress(resultSet.getString("address"));
                client.setEstProfessionnelle(resultSet.getBoolean("estprofessionnel"));
                client.setNumeroTelephone(resultSet.getString("telephone"));
                clientHashMap.put(resultSet.getString("nom"), client);
            }

        } catch (SQLException e) {
            System.err.println("erreur pendant l'execution du query : " + e.getMessage() + " les clients ne sont pas recuperer verifier la connexion avec la base dedonnee." );
            e.printStackTrace();
        }finally {
            DBUtils.closeResources(resultSet, pStatement);
        }
        return clientHashMap;
    }

    public Optional<Client> findById(int id) {
        String sqlQuery = "SELECT * FROM client WHERE id = ?";
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
       Optional<Client> clientOptional = Optional.empty();
        try{
            pStatement = dbConnection.prepareStatement(sqlQuery);
            pStatement.setInt(1, id);
            resultSet = pStatement.executeQuery();
            Client client = new Client();
            if(resultSet.next()){
                client.setId(id);
                client.setNom(resultSet.getString("nom"));
                client.setAddress(resultSet.getString("address"));
                client.setEstProfessionnelle(resultSet.getBoolean("estprofessionnel"));
                client.setNumeroTelephone(resultSet.getString("telephone"));
            }else{
                System.out.println("aucun client trouver avec cette id.");
            }
        } catch (SQLException e) {
            System.err.println("erreur pendant l'execution du query : " + e.getMessage() + " le client n'est pas recuperer verifier la connexion avec la base dedonnee." );
            e.printStackTrace();
        }finally {
            DBUtils.closeResources(resultSet, pStatement);

        }
        return clientOptional;
    }

    public void update(int id, Client client) {
        String sqlQuery = "UPDATE client SET nom = ?, estprofessionnel = ?, telephone = ?, address = ? WHERE id = ?";
        PreparedStatement pStatement = null;
        try{
            pStatement = dbConnection.prepareStatement(sqlQuery);
            pStatement.setString(1, client.getNom());
            pStatement.setBoolean(2, client.isProfessionnel());
            pStatement.setString(3, client.getNumeroTelephone());
            pStatement.setString(4, client.getAddress());
            int affectedRow = pStatement.executeUpdate();
            if(affectedRow == 0){
                throw new SQLException("Error : the client was not updated;");
            }
            System.out.println("le client identifier par l id : " + id + " est modifier avec succes");
        } catch (SQLException e) {
            System.err.println("erreur pendant l'execution du query : " + e.getMessage() + " le client n'est modifier verifier la connexion avec la base dedonnee." );
            e.printStackTrace();
        }finally {
            DBUtils.closeResources(pStatement);
        }

    }

    public void destroy(int id) {
        String sqlQuery = "DELETE FROM client WHERE id = ?";
        PreparedStatement pStatement = null;
        try {
            pStatement = dbConnection.prepareStatement(sqlQuery);
            pStatement.setInt(1, id);
            int affectedRow = pStatement.executeUpdate();
            if(affectedRow == 0){
                System.out.println("Erreur : le client n'etatis pas se supprimer ;");
            }
            System.out.println("le client est supprimer avec succes");
        } catch (SQLException e) {
            System.err.println("erreur pendant l'execution du query : " + e.getMessage() + " le client n'est supprimer verifier la connexion avec la base dedonnee." );
            e.printStackTrace();
        } finally {
           DBUtils.closeResources(pStatement);
        }
    }

}
