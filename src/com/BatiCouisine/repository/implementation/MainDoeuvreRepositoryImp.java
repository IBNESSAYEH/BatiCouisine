package com.BatiCouisine.repository.implementation;

import com.BatiCouisine.entities.MainDoeuvre;
import com.BatiCouisine.repository.MainDoeuvreRepository;
import com.BatiCouisine.util.DBConnection;
import com.BatiCouisine.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

public class MainDoeuvreRepositoryImp implements MainDoeuvreRepository {
    private Connection connection;

    public MainDoeuvreRepositoryImp() {
        connection = DBConnection.getConnectionInstance().getConnection();
    }

    public void store(MainDoeuvre mainDoeuvre, int idProjt){
        String sqlQuery = "INSERT INTO maindoeuvre (nom, typecomposant, taux_tva, id_projet, tauxhoraire, heurestravail, productiviteouvrier) values (?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = null;
        ResultSet generatedKey = null;

        try{
            preparedStatement = connection.prepareStatement(sqlQuery, preparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,mainDoeuvre.getNom());
            preparedStatement.setString(2,mainDoeuvre.getTypeComposant());
            preparedStatement.setDouble(3, mainDoeuvre.getTauxTVA());
            preparedStatement.setInt(4, idProjt);
            preparedStatement.setDouble(5, mainDoeuvre.getTauxHoraire());
            preparedStatement.setDouble(6, mainDoeuvre.getHeurTravail());
            preparedStatement.setDouble(7, mainDoeuvre.getProductiviteOuvrier());
            int affectedRow = preparedStatement.executeUpdate();
            if(affectedRow == 0){
                throw new Exception("Erreur lors de l'insertion de la main D'oeuvre");
            }
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if(generatedKeys.next()){
                mainDoeuvre.setId(generatedKeys.getInt(1));
                
            }else {
                throw new Exception("Erreur lors de l'insertion de la main D'oeuvre");
            }
            System.out.println("la main d'euvre etais creer avec succes;");

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            DBUtils.closeResources(generatedKey, preparedStatement);
        }

    };

    public Optional<MainDoeuvre> findById(int id){
        String sqlQuery = "SELECT * FROM maindoeuvre WHERE id_projet = ?";
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        Optional<MainDoeuvre> mainDoeuvreOptional = Optional.empty();
        try{
            pStatement = connection.prepareStatement(sqlQuery);
            pStatement.setInt(1, id);
            resultSet = pStatement.executeQuery();
            if(resultSet.next()){
                MainDoeuvre mainDoeuvre = new MainDoeuvre();
                mainDoeuvre.setId(id);
                mainDoeuvre.setNom(resultSet.getString("nom"));
                mainDoeuvre.setTypeComposant(resultSet.getString("typecomposant"));
                mainDoeuvre.setTauxTVA(resultSet.getDouble("tauxTVA"));
                mainDoeuvre.setTauxHoraire(resultSet.getDouble("tauxHoraire"));
                mainDoeuvre.setHeurTravail(resultSet.getDouble("heurestravail"));
                mainDoeuvre.setProductiviteOuvrier(resultSet.getDouble("productiviteouvrier"));
                mainDoeuvre.setHeurTravail(resultSet.getDouble("heurTravail"));
                mainDoeuvreOptional = Optional.ofNullable(mainDoeuvre);
            }else{
                System.out.println("la main d'euvre avec cettte id not found");
            }
            
        }catch (Exception e){
            e.printStackTrace();
        }
        
        return mainDoeuvreOptional;
    };

    public void update(int id, MainDoeuvre mainDoeuvre){
        String sqlQuery = "UPDATE maindoeuvre SET nom = ?, typecomposant = ?, taux_tva = ?, tauxhoraire = ?, heurestravail = ?, productiviteouvrier = ? WHERE id = ?";
        PreparedStatement pStatement = null;

        try{
            pStatement = connection.prepareStatement(sqlQuery);
            pStatement.setString(1, mainDoeuvre.getNom());
            pStatement.setString(2, mainDoeuvre.getTypeComposant());
            pStatement.setDouble(3, mainDoeuvre.getTauxTVA());
            pStatement.setDouble(4, mainDoeuvre.getTauxHoraire());
            pStatement.setDouble(5, mainDoeuvre.getHeurTravail());
            pStatement.setDouble(6, mainDoeuvre.getProductiviteOuvrier());
            pStatement.setInt(7, mainDoeuvre.getId());
            int affectedRow = pStatement.executeUpdate();
            if(affectedRow == 0){
                throw new Exception("Erreur lors de l'update maindoeuvre");
            }

            System.out.println("la main d'euvre modifier avec succes;");
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            DBUtils.closeResources(pStatement);
        }
    };

    public void destroy(int id){
        String sqlQuery = "DELETE FROM maindoeuvre WHERE id = ?";
        PreparedStatement pStatement = null;
        try{
            pStatement = connection.prepareStatement(sqlQuery);
            pStatement.setInt(1, id);
            int affectedRow = pStatement.executeUpdate();
            if(affectedRow == 0){
                System.out.println("Erreur : la main d'oeuvre n'etatis pas se supprimer ;");
            }
            System.out.println("la main d'oeuvre est supprimer avec succes");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeResources(pStatement);
        }
    };
}
