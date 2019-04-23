import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;


public class TestServlet extends HttpServlet {



    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    { 
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");

	    String title = "bonjour jeff";

	    out.println("<title>" + title + "</title>");
        out.println("</head>");
        out.println("<body bgcolor=\"white\">");

        out.println("<h1>" + title + " big boss le pd" + "</h1>");
        out.println("</body>");
        out.println("</html>");
    }

}
