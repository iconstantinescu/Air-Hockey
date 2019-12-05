package client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public abstract class DatabaseController {

    protected transient ConnectionFactory connectionFactory;
    protected transient Connection conn;
    protected transient PreparedStatement ps;
    protected transient ResultSet rs;

    protected static final String URL = "jdbc:mysql://"
            + "projects-db.ewi.tudelft.nl/"
            + "projects_sem-project-42"
            + "?sslmode=require"
            + "&user=pu_f1X2r36H3wklF"
            + "&password=ZL3Al54DKc66"
            + "&serverTimezone=UCT";

    public DatabaseController(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    protected void closeConnections() {
        try {
            rs.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        try {
            ps.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        try {
            conn.close();
        } catch (Exception e) {
            System.out.println(e.toString());

        }
    }
}
