package com.BatiCouisine.repository.implementation;

import com.BatiCouisine.entities.Materiau;
import com.BatiCouisine.repository.MateriauRepository;
import com.BatiCouisine.util.DBConnection;
import com.BatiCouisine.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MateriauRepositoryImp implements MateriauRepository {
    private Connection connection;

    public MateriauRepositoryImp() {
        connection = DBConnection.getConnectionInstance().getConnection();
    }

    public int store(Materiau materiau, int idProjt){
        String sqlQuery = "INSERT INTO Materiau (nom, typeComposant, taux_tva, coutunitaire, quantite, couttransport, coefficientqualite, id_Projet) VALUES (?, ? ,?, ? ,?, ? ,?, ? )";
        PreparedStatement preparedStatement = null;
        ResultSet generatedKey = null;

        try{
            preparedStatement = connection.prepareStatement(sqlQuery, preparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, materiau.getNom());
            preparedStatement.setString(2, materiau.getTypeComposant());
            preparedStatement.setDouble(3, materiau.getTauxTVA());
            preparedStatement.setDouble(4, materiau.getCoutUnitaire());
            preparedStatement.setDouble(5, materiau.getQuantite());
            preparedStatement.setDouble(6, materiau.getCoutTransport());
            preparedStatement.setDouble(7, materiau.getCoefficientQualite());
            preparedStatement.setInt(8, idProjt);
            int affectedRow = preparedStatement.executeUpdate();
            if(affectedRow == 0){
                throw new SQLException("Erreur pendant l'insertion du materiau .");
            }
            generatedKey = preparedStatement.getGeneratedKeys();
            if(generatedKey.next()){
                materiau.setId(generatedKey.getInt(1));
            }

            System.out.println("l'insetion effectuer avec succes");

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeResources(generatedKey, preparedStatement);
        }
        return materiau.getId();
    };

    public Optional<Materiau> findById(int id){
        String sqlQuery = "SELECT * FROM Materiau WHERE id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Optional<Materiau> materiauOptional = Optional.empty();
        try{
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Materiau materiau = new Materiau();
                materiau.setId(id);
                materiau.setNom(resultSet.getString("nom"));
                materiau.setTypeComposant(resultSet.getString("typeComposant"));
                materiau.setTauxTVA(resultSet.getDouble("taux_tva"));
                materiau.setCoutUnitaire(resultSet.getDouble("coutunitaire"));
                materiau.setQuantite(resultSet.getDouble("quantite"));
                materiau.setCoefficientQualite(resultSet.getDouble("coefficientqualite"));
                materiau.setCoutTransport(resultSet.getDouble("couttransport"));
                materiauOptional = Optional.ofNullable(materiau);
            }else{
                System.out.println("aucun materiau trouver avec cette id.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeResources(resultSet, preparedStatement);
        }

        return materiauOptional;
    };

    public void update(int id, Materiau materiau){
        String sqlQuery = "UPDATE materiau SET nom = ?, typeComposant = ?, taux_tva = ?, coutunitaire = ?, quantite = ?, couttransport = ?, coefficientqualite = ? WHERE id = ?";
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, materiau.getNom());
            preparedStatement.setString(2, materiau.getTypeComposant());
            preparedStatement.setDouble(3, materiau.getTauxTVA());
            preparedStatement.setDouble(4, materiau.getCoutUnitaire());
            preparedStatement.setDouble(5, materiau.getQuantite());
            preparedStatement.setDouble(6, materiau.getCoutTransport());
            preparedStatement.setDouble(7, materiau.getCoefficientQualite());
            preparedStatement.setInt(8, id);
            int affectedRow = preparedStatement.executeUpdate();
            if(affectedRow == 0){
                throw new SQLException("Erreur pendant l'update materiau .");
            }else{
                System.out.println("l'insetion effectuer avec succes");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    };

    public void destroy(int id){
        String sqlQuery = "DELETE FROM Materiau WHERE id = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, id);
            int affectedRow = preparedStatement.executeUpdate();
            if(affectedRow == 0){
                System.out.println("Erreur : le materiau n'etatis pas se supprimer ;");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeResources(preparedStatement);
        }

    };

    public List<Materiau> findAll(){
        String sqlQuery = "SELECT * from materiau;";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Materiau> materiauList = null;
        try{
            preparedStatement = connection.prepareStatement(sqlQuery);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Materiau materiau = new Materiau();
                materiau.setId(resultSet.getInt("id"));
                materiau.setNom(resultSet.getString("nom"));
                materiau.setTypeComposant(resultSet.getString("typeComposant"));
                materiau.setTauxTVA(resultSet.getDouble("taux_tva"));
                materiau.setCoutUnitaire(resultSet.getDouble("coutunitaire"));
                materiau.setQuantite(resultSet.getDouble("quantite"));
                materiau.setCoutTransport(resultSet.getDouble("couttransport"));
                materiau.setCoefficientQualite(resultSet.getDouble("coefficientqualite"));
                materiauList.add(materiau);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeResources(resultSet, preparedStatement);
        }

        return materiauList;
    }

}
