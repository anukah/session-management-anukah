import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private HashMap<String, String> users;

    @Override
    public void init() throws ServletException {
        users = new HashMap<>();
        users.put("student1", "pass1");
        users.put("student2", "pass2");
        users.put("student3", "pass3");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (users.containsKey(username) && users.get(username).equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);

            Cookie userCookie = new Cookie("username", username);
            userCookie.setMaxAge(30 * 60); //30 minutes
            response.addCookie(userCookie);

            response.sendRedirect("DashboardServlet");
        } else {
            response.sendRedirect("login.html");
        }
    }
}