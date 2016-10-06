package Project.Configs;

/**
 * Created by User on 19.03.2016.
 */
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharsetFilter implements Filter
{
    private String encoding;

    public void init(FilterConfig config) throws ServletException
    {
        encoding = config.getInitParameter("requestEncoding");

        System.out.println("Hello");
        if( encoding==null ) encoding="UTF-8";
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain       next)
            throws IOException, ServletException
    {
        // Respect the client-specified character encoding
        // (see HTTP specification section 3.4.1)


        System.out.println("Hello");
        if(null == request.getCharacterEncoding())
            request.setCharacterEncoding(encoding);


        /**
         * Set the default response content type and encoding
         */
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        //request.setCharacterEncoding("UTF-8");


        next.doFilter(request, response);
    }

    public void destroy(){}
}
