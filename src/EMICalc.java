import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EMICalc extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("<html><style>"
				+ "table, th, td {"
				+ "  border: 1px solid black;"
				+ "  background-color: aqua;"
				+ "}"
				+ "</style><body>");
		out.println("<h1>After Calculation</h1><br>");
		
		double amount=Double.parseDouble(request.getParameter("amount"));
		float rate=Float.parseFloat(request.getParameter("rate"));
		int time=Integer.parseInt(request.getParameter("time"));
		
		out.println("<div>"
				+ "Principal Amount = "+amount+"<br>"
				+ "Interest Rate = "+rate+"%<br>"
				+ "Time Period = "+time+" months"
				+ "<div><br><br>");
		
		rate=rate/12/100;
		int emi=(int) ((amount*rate*Math.pow(1+rate, time))/(Math.pow(1+rate,time)-1));//emi pm
		int totalEmi=(int)(emi*time); //total amount payable after emi
		int extra_amount=(int) (totalEmi-amount); //extra payable amount
		
		out.println("<table>"
				+ "<tr>"
				+ "    <th>Every month EMI</th>"
				+ "    <th>Total amount paid after EMI</th>"
				+ "    <th>Extra payable amount after EMI</th>"
				+ "  </tr>"
				+ "<tr>"
				+ "    <td>"+emi+"</td>"
				+ "    <td>"+totalEmi+"</td>"
				+ "    <td>"+extra_amount+"</td>"
				+ "  </tr>"
				
				+ "</table>");
	}
}
