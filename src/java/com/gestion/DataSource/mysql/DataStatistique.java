package com.gestion.DataSource.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author messi
 */
public class DataStatistique {

    public DataStatistique() {
    }

    public static Integer ConsommationMensuelMS(String categorie, int idMS, int mois) {
        Integer quantite = 0;
        try {
             DataSource.getInstace();
            Connection c = DataSource.getConnection();
            Statement s = c.createStatement();
            String sql = "SELECT SUM(operation_consommateur.`quantite`) AS quantite FROM operation_consommateur WHERE YEAR(operation_consommateur.`date`)=YEAR(NOW()) AND \n"
                    + "MONTH(operation_consommateur.`date`)='" + mois + "' AND operation_consommateur.magasin ='" + idMS + "' AND operation_consommateur.categorie ='" + categorie + "'";
            ResultSet rs = s.executeQuery(sql);
            
            if (rs.next()) {
                quantite = rs.getInt("quantite");
            }
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(date_du_jour.class.getName()).log(Level.SEVERE, null, ex);
        }
        return quantite;
    }

    public static Integer EntrerMensuelMS(String categorie, int idMS, int mois) {
        Integer quantite = 0;
        try {
             DataSource.getInstace();
            Connection c = DataSource.getConnection();
            Statement s = c.createStatement();
            String sql = "SELECT SUM(transfert_stock.quantite) AS quantite FROM transfert_stock WHERE YEAR(transfert_stock.`date`)=YEAR(NOW()) AND MONTH(transfert_stock.`date`)=\n"
                    + "'" + mois + "' AND  transfert_stock.etat='OK' AND transfert_stock.MS='" + idMS + "' AND transfert_stock.type_produit='" + categorie + "'";
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                quantite = rs.getInt("quantite");
            }
             c.close();
        } catch (SQLException ex) {
            Logger.getLogger(date_du_jour.class.getName()).log(Level.SEVERE, null, ex);
        }
        return quantite;
    }

    public static Integer ConsommationMensuelMP(String categorie, int idMP, int mois) {
        Integer quantite = 0;
        Integer quantite2 = 0;
        try {
            DataSource.getInstace();
            Connection c = DataSource.getConnection();
            Statement s = c.createStatement();
            String sql = "SELECT SUM(transfert_stock.quantite) AS quantite FROM transfert_stock WHERE\n"
                    + "YEAR(transfert_stock.`date`)=YEAR(NOW())AND MONTH(transfert_stock.`date`)='" + mois + "' \n"
                    + " AND transfert_stock.MP='" + idMP + "' AND transfert_stock.etat='OK' AND transfert_stock.type_produit='" + categorie + "'";
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                quantite = rs.getInt("quantite");
            }
            String sqls = "SELECT SUM(operation_consommateur.quantite) AS quantite FROM operation_consommateur WHERE\n"
                    + "YEAR(operation_consommateur.`date`)=YEAR(NOW())AND MONTH(operation_consommateur.`date`)='" + mois + "' \n"
                    + " AND operation_consommateur.MagasinP='" + idMP + "' AND operation_consommateur.categorie='" + categorie + "'";
            ResultSet rss = s.executeQuery(sqls);
            if (rss.next()) {
                quantite2 = rss.getInt("quantite");
            }
                c.close();
        } catch (SQLException ex) {
            Logger.getLogger(date_du_jour.class.getName()).log(Level.SEVERE, null, ex);
        }
        return quantite + quantite2;
    }

    public static Integer EntrerMensuelMP(String categorie, int idMP, int mois) {
        Integer quantite = 0;
        try {
           DataSource.getInstace();
            Connection c = DataSource.getConnection();
            Statement s = c.createStatement();
            String sql = "SELECT SUM(bordereau.quantite) AS quantite FROM bordereau WHERE\n"
                    + "YEAR(bordereau.`date`)=YEAR(NOW())AND MONTH(bordereau.`date`)='"+mois+"' \n"
                    + " AND bordereau.magasin='"+idMP+"' AND bordereau.type_produit='"+categorie+"'";
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                quantite = rs.getInt("quantite");
            }
             c.close();
        } catch (SQLException ex) {
            Logger.getLogger(date_du_jour.class.getName()).log(Level.SEVERE, null, ex);
        }
        return quantite;
    }

    public static void UpdateCommandeMS(int id) {
        Integer d = id;
        try {
           DataSource.getInstace();
            Connection c = DataSource.getConnection();
            Statement s = c.createStatement();
            String sql = "UPDATE  commande_MS SET Etat_Commande='OK' WHERE id_commande='" + d + "'";
            s.executeUpdate(sql);

        } catch (SQLException ex) {
            Logger.getLogger(date_du_jour.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
