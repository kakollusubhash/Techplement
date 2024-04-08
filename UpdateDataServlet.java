package UpdateDataServlet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import DataBaseConnection.DataBaseConnection;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/users/update")
public class UpdateDataServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Get parameters from request
        String username ="Subhash";
        String Newpassword="Kakollu1";

        // Update user data in the database
        try (Connection connection = DataBaseConnection.getConnection()) {
            String query = "UPDATE users SET password=? WHERE username=?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, Newpassword);
                statement.setString(2, username);
                statement.executeUpdate();
            }
            // Send success response
            response.getWriter().println("User data updated successfully!");
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            // Send error response if there's a database error
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error updating user data");
        }
    }
}
