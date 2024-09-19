package com.BatiCouisine.repository.implementation;

import com.BatiCouisine.entities.Materiau;
import com.BatiCouisine.util.DBConnection;
import com.BatiCouisine.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class MateriauRepositoryImp {
    private Connection connection;

    public MateriauRepositoryImp() {
        connection = DBConnection.getConnectionInstance().getConnection();
    }

    public void store(Materiau materiau, int idProjt){
        String sqlQuery = "INSERT INTO Materiau (nom, typeComposant, taux_tva, coutunitaire, quantite, couttransport, coefficientqualite, id_Projt) VALUES (?, ? ,?, ? ,?, ? ,?, ? )";
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
    };

    public Optional<Materiau> findById(int id){

    };

    public void update(int id, Materiau materiau){

    };

    public void destroy(int id){

    };

}
