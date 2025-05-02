import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String username = (session != null) ? (String) session.getAttribute("username") : null;

        if (username == null) {
            response.sendRedirect("login.html");
            return;
        }

        List<Course> courses = new ArrayList<>();
        courses.add(new Course("CS101", "Intro to Programming", "A"));
        courses.add(new Course("CS102", "Data Structures", "B"));
        courses.add(new Course("CS103", "Web Development", "C"));
        courses.add(new Course("CS104", "Databases", "D"));


        request.setAttribute("username", username);
        request.setAttribute("courses", courses);

        RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
        dispatcher.forward(request, response);
    }
}