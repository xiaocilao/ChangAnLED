package filter;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class Filter
 */
@WebFilter("/Filter")
public class Filter implements javax.servlet.Filter {

    /**
     * Default constructor. 
     */
    public Filter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hreq = (HttpServletRequest) request;
		HttpServletResponse hres = (HttpServletResponse) response;
		
		System.out.println("filter");
		String uri = hreq.getRequestURI();
		  OutputStream outputStream = hres.getOutputStream();
		  if (uri.toLowerCase().endsWith(".jsp")) {
			  outputStream.write(("<script type=\"text/javascript\">parent.ht.pub.error('" +"输入错误！" + "');</script>").getBytes());
              hres.flushBuffer();
		  }else if(uri.toLowerCase().endsWith(".html")){
			  outputStream.write(("<script type=\"text/javascript\">parent.ht.pub.error('" +"输入错误！" + "');</script>").getBytes());
              hres.flushBuffer();
		  }else{
			  chain.doFilter(request, response);
		  }
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
