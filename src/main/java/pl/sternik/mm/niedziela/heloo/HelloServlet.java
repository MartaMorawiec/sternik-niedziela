package pl.sternik.mm.niedziela.heloo;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getOutputStream().println("Mój pierwszy servlet");
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Moj doGET...");
        response.getOutputStream().println("Mój pierwszy servlet z obsluga POST ->"
                + request.getParameter("param1"));
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("Moj init");
    }

    @Override
    public void destroy() {
        super.destroy();
        System.out.println("Moj destroy");
    }
}
