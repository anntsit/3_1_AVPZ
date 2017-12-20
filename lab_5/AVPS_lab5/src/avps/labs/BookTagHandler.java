package avps.labs;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.JspWriter;

public class BookTagHandler extends TagSupport {
    private String pageURL;
    public String getPageURL() {
        return pageURL;
    }
    public void setPageURL(String pageURL) {
        this.pageURL = pageURL;
    }
    private String bookId;    
    public String getBookId() {
        return bookId;
    }
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    private String author;    
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    private int price;
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    private String buttonValue;
    public String getButtonValue() {
        return buttonValue;
    }
    public void setButtonValue(String buttonValue) {
        this.buttonValue = buttonValue;
    }

    public int doStartTag() {
        try {
            JspWriter out = pageContext.getOut();
            out.print("<form action = \"" + pageURL + "\">");
            out.print("<tr>");
            out.print("<td>" + bookId + "</td>");
            out.print("<td>" + name + "</td>");
            out.print("<input type = hidden name=\"bookName\" value=\"" + name + "\">");
            out.print("<td>" + author + "</td>");
            out.print("<td>" + price + "</td>");
            out.print("<input type = hidden name=\"bookPrice\" value=\"" + price + "\">");
            out.print("<input type = hidden name=\"bookCount\" value=\"" + 0 + "\">");
            out.print("<td><input type=submit value=\"" + buttonValue + "\" " + 
                    "onclick=\"this.form.elements['bookCount'].value = prompt('¬ведите количество книг', '1');\"></td>");
            out.print("</tr>");
            out.print("</form>");
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
}

