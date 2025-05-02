import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/EnrollServlet")
public class EnrollServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String courseId = request.getParameter("courseId");

        HttpSession session = request.getSession();

        List<Course> allCourses = (List<Course>) session.getAttribute("allCourses");

        Course selectedCourse = null;
        for (Course c : allCourses) {
            if (c.getCourseId().equals(courseId)) {
                selectedCourse = c;
                break;
            }
        }

        if (selectedCourse != null) {
            List<Course> enrolledCourses = (List<Course>) session.getAttribute("enrolledCourses");
            if (enrolledCourses == null) {
                enrolledCourses = new ArrayList<>();
            }

            if (!enrolledCourses.contains(selectedCourse)) {
                enrolledCourses.add(selectedCourse);
            }

            session.setAttribute("enrolledCourses", enrolledCourses);
        }
        
        response.sendRedirect("DashboardServlet");
    }
}