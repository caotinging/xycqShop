package caotinging.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet
  extends HttpServlet
{
  private static final long serialVersionUID = 1L;
  
  public void service(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException
  {
    String methodName = req.getParameter("method");
    if (methodName != null) {
      try
      {
        System.out.println(methodName);
        Method method = getClass().getMethod(methodName, new Class[] { HttpServletRequest.class, HttpServletResponse.class });
        method.invoke(req, new Object[] { resp });
      }
      catch (NoSuchMethodException|SecurityException|IllegalAccessException|IllegalArgumentException|InvocationTargetException e)
      {
        e.printStackTrace();
      }
    }
  }
}
