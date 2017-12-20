package avps.labs;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

public class BookOrderTagHandler extends TagSupport {
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    private int price;
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    private int count;
    public int getCount() {
    	return count;
    }
    public void setCount(int count) {
    	this.count = count;
    }

    public int doStartTag() {
        try {
        	HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
    		HttpSession session = request.getSession(true);
    		HashMap<String, Integer[]> booksBasket = (HashMap<String, Integer[]>) session.getAttribute("booksBasket");
            JspWriter out = pageContext.getOut();
            if (!booksBasket.containsKey(name))
            	return SKIP_BODY;
            
            count = booksBasket.get(name)[1];
            out.print("<form action = 'DeleteBookServlet'>");
            out.print("<tr>");
            out.print("<td>" + name + "</td>");
            out.print("<input type = hidden name=\"bookName\" value=\"" + name + "\">");
            out.print("<td>" + count + " шт.</td>");
            Integer sum = price * count;
	    	out.print("<td>" + sum.toString() + " грн.</td>");
	    	out.print("<td><input type='submit' value='delete'" + 
                    "onclick=\"this.form.elements['bookCount'].value = prompt('¬ведите количество книг', " +
                    count + ");\"></td>");
            out.print("<input type = hidden name=\"bookCount\" value=\"" + count + "\">");
            out.print("</tr>");
            out.print("</form>");
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
}

