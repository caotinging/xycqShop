package caotinging.web.filter;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class MyRequest
  extends HttpServletRequestWrapper
{
  private HttpServletRequest request;
  private boolean hasEncode;
  
  public MyRequest(HttpServletRequest request)
  {
    super(request);
    this.request = request;
  }
  
  public Map<String, String[]> getParameterMap()
  {
    String method = this.request.getMethod();
    if (method.equalsIgnoreCase("post"))
    {
      try
      {
        this.request.setCharacterEncoding("UTF-8");
        return this.request.getParameterMap();
      }
      catch (UnsupportedEncodingException e)
      {
        e.printStackTrace();
      }
    }
    else if (method.equalsIgnoreCase("get"))
    {
      Map<String, String[]> parameterMap = this.request.getParameterMap();
      if (!this.hasEncode)
      {
        for (String key : parameterMap.keySet())
        {
          String[] values = (String[])parameterMap.get(key);
          if (values != null) {
            for (int i = 0; i < values.length; i++) {
              try
              {
                values[i] = new String(values[i].getBytes("ISO-8859-1"), "UTF-8");
              }
              catch (UnsupportedEncodingException e)
              {
                e.printStackTrace();
              }
            }
          }
        }
        this.hasEncode = true;
      }
      return parameterMap;
    }
    return super.getParameterMap();
  }
  
  public String getParameter(String name)
  {
    Map<String, String[]> parameterMap = getParameterMap();
    String[] values = (String[])parameterMap.get(name);
    if (values == null) {
      return null;
    }
    return values[0];
  }
  
  public String[] getParameterValues(String name)
  {
    Map<String, String[]> parameterMap = getParameterMap();
    String[] values = (String[])parameterMap.get(name);
    return values;
  }
}
