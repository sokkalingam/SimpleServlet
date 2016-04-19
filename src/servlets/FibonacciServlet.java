package servlets;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class FibonacciServlet extends HttpServlet{
	
	private Map<Integer, Double> fiboMap;
	
	public FibonacciServlet() {
		fiboMap = new HashMap<Integer, Double>();
		fiboMap.put(1, (double) 0);
		fiboMap.put(2, (double) 1);
	}
	
	/**Calculates Fibonacci value for the xth position in the series
	 * Caches calculated values in a map
	 * @param x
	 * @return
	 */
	private Double _getFibo(Integer x) {
		
		if (x < 1)
			return (double) 0;
		
		if (fiboMap.containsKey(x))
			return fiboMap.get(x);
		
		fiboMap.put(x, _getFibo(x-1) + _getFibo(x-2));
		
		return fiboMap.get(x);
		
	}
	
	/* (non-Javadoc)
	 *	/fibonacci?n=10 will give the fibonacci value for the 10th element in the series
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String paramValue = request.getParameter("n");
		Integer n = 0;
		if (paramValue != null && !paramValue.isEmpty())
			try { n = Integer.valueOf(paramValue); } catch (NumberFormatException e) {};
		long start = new Date().getTime();
		response.getWriter().append("Nth Fibonacci value = " + _getFibo(n));
		long end = new Date().getTime();
		response.getWriter().append("\n\nTime Taken = " + (end - start) + " ms");
	}

}
