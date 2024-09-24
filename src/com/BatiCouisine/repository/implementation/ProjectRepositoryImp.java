package com.BatiCouisine.repository.implementation;

import com.BatiCouisine.entities.EtatProject;
import com.BatiCouisine.entities.Projet;
import com.BatiCouisine.repository.ProjectRepository;
import com.BatiCouisine.util.DBConnection;
import com.BatiCouisine.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class ProjectRepositoryImp implements ProjectRepository {
    private Connection connection;

    public ProjectRepositoryImp() {
        this.connection = DBConnection.getConnectionInstance().getConnection();
    }

    public int store(Projet project, int idClient) {
        String sqlQuery = "INSERT INTO projet (nom, margebeneficiaire, couttotal, etat, surfacecouisine, id_client) VALUES (?, ?, ?,  ?::etatprojet, ?, ?)";
        PreparedStatement preparedStatement = null;
        ResultSet generatedKey = null;

        try {
            preparedStatement = connection.prepareStatement(sqlQuery, preparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, project.getNom());
            preparedStatement.setDouble(2, project.getMargeBeneficiaire());
            preparedStatement.setDouble(3, project.getCoutTotal());
            preparedStatement.setString(4, project.getEtat().toString());
            preparedStatement.setDouble(5, project.getSurfaceCouisine());
            preparedStatement.setInt(6, idClient);
            int affectedRow = preparedStatement.executeUpdate();
            if (affectedRow == 0) {
                throw new Exception("Erreur lors de l'insertion du projet");
            }
            generatedKey = preparedStatement.getGeneratedKeys();
            if (generatedKey.next()) {
                project.setId(generatedKey.getInt(1));

            } else {
                throw new Exception("Erreur lors de l'insertion du projet");
            }
            System.out.println("Le projet a été créé avec succès");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeResources(generatedKey, preparedStatement);
        }
        return project.getId();
    }


    public void update(int id, Projet project) {
        String sqlQuery = "UPDATE projet SET nom = ?, margebeneficiaire = ?, couttotal = ?, etat = ?::etatprojet, surfacecouisine = ? WHERE id = ?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, project.getNom());
            preparedStatement.setDouble(2, project.getMargeBeneficiaire());
            preparedStatement.setDouble(3, project.getCoutTotal());
            preparedStatement.setString(4, project.getEtat().toString());
            preparedStatement.setDouble(5, project.getSurfaceCouisine());
            preparedStatement.setInt(6, id);
            int affectedRow = preparedStatement.executeUpdate();
            if (affectedRow == 0) {
                throw new Exception("Erreur lors de la mise à jour du projet");
            }
            System.out.println("Le projet a été mis à jour avec succès");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeResources(null, preparedStatement);
        }
    }

    public void destroy(int id) {
        String sqlQuery = "DELETE FROM project WHERE id = ?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, id);
            int affectedRow = preparedStatement.executeUpdate();
            if (affectedRow == 0) {
                throw new Exception("Erreur lors de la suppression du projet");
            }
            System.out.println("Le projet a été supprimé avec succès");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeResources(null, preparedStatement);
        }
    }

    public Optional<Projet> findById(int id) {
        String sqlQuery = "SELECT * FROM project WHERE id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Optional<Projet> projectOptional = Optional.empty();

        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Projet project = new Projet();
                project.setId(id);
                project.setNom(resultSet.getString("nom"));
                project.setMargeBeneficiaire(resultSet.getDouble("margebeneficiaire"));
                project.setCoutTotal(resultSet.getDouble("couttotal"));
                project.setEtat(EtatProject.valueOf(resultSet.getString("etat")));
                project.setSurfaceCouisine(resultSet.getDouble("surfacecouisine"));
                projectOptional = Optional.ofNullable(project);
            } else {
                System.out.println("Aucun projet trouvé avec cet id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeResources(resultSet, preparedStatement);
        }

        return projectOptional;

    }


    public HashMap<String, Projet> retrieveAll() {
        String sqlQuery = "SELECT * FROM project";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        HashMap<String, Projet> projects = new HashMap<>();
        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Projet project = new Projet();
                project.setId(resultSet.getInt("id"));
                project.setNom(resultSet.getString("nom"));
                project.setMargeBeneficiaire(resultSet.getDouble("margebeneficiaire"));
                project.setCoutTotal(resultSet.getDouble("couttotal"));
                project.setEtat(EtatProject.valueOf(resultSet.getString("etat")));
                project.setSurfaceCouisine(resultSet.getDouble("surfacecouisine"));
                projects.put(resultSet.getString("nom"), project);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeResources(resultSet, preparedStatement);
        }
        return projects;
    }

}
