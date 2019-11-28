# 关于model
参考：[springMVC4(7)模型视图方法源码综合分析](http://blog.csdn.net/qwe6112071/article/details/51044414)
### 一、关于el表达式
<pre>
package javax.servlet.jsp.PageContext;
实现类PageContextImpl;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package javax.servlet.jsp;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.BodyContent;

public abstract class PageContext extends JspContext {
  public static final int PAGE_SCOPE = 1;
  public static final int REQUEST_SCOPE = 2;
  public static final int SESSION_SCOPE = 3;
  public static final int APPLICATION_SCOPE = 4;
  public static final String PAGE = "javax.servlet.jsp.jspPage";
  public static final String PAGECONTEXT = "javax.servlet.jsp.jspPageContext";
  public static final String REQUEST = "javax.servlet.jsp.jspRequest";
  public static final String RESPONSE = "javax.servlet.jsp.jspResponse";
  public static final String CONFIG = "javax.servlet.jsp.jspConfig";
  public static final String SESSION = "javax.servlet.jsp.jspSession";
  public static final String OUT = "javax.servlet.jsp.jspOut";
  public static final String APPLICATION = "javax.servlet.jsp.jspApplication";
  public static final String EXCEPTION = "javax.servlet.jsp.jspException";

  public PageContext() {
  }

  public abstract void initialize(Servlet var1, ServletRequest var2, ServletResponse var3, String var4, boolean var5, int var6, boolean var7) throws IOException, IllegalStateException, IllegalArgumentException;

  public abstract void release();

  public abstract HttpSession getSession();

  public abstract Object getPage();

  public abstract ServletRequest getRequest();

  public abstract ServletResponse getResponse();

  public abstract Exception getException();

  public abstract ServletConfig getServletConfig();

  public abstract ServletContext getServletContext();

  public abstract void forward(String var1) throws ServletException, IOException;

  public abstract void include(String var1) throws ServletException, IOException;

  public abstract void include(String var1, boolean var2) throws ServletException, IOException;

  public abstract void handlePageException(Exception var1) throws ServletException, IOException;

  public abstract void handlePageException(Throwable var1) throws ServletException, IOException;

  public BodyContent pushBody() {
    return null;
  }

  public ErrorData getErrorData() {
    return new ErrorData((Throwable)this.getRequest().getAttribute("javax.servlet.error.exception"), ((Integer)this.getRequest().getAttribute("javax.servlet.error.status_code")).intValue(), (String)this.getRequest().getAttribute("javax.servlet.error.request_uri"), (String)this.getRequest().getAttribute("javax.servlet.error.servlet_name"));
  }
}
</pre>