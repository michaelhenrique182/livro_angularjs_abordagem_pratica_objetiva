package app;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class L10NLoader extends HttpServlet {
  private static final long serialVersionUID = 1L;
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String lang = request.getParameter("lang");
    if (lang != null) {
      response.setContentType("application/json");
      InputStream in = getClass().getClassLoader().getResourceAsStream(lang + ".json");
      writeToAndClose(in, response.getOutputStream());
    }
  }

  private void writeToAndClose(InputStream in, OutputStream out)throws IOException {
    byte[] buffer = new byte[1024];
    int len;
    while ((len = in.read(buffer)) != -1) {
    out.write(buffer, 0, len);
    }
    in.close();
    out.close();
  }
}