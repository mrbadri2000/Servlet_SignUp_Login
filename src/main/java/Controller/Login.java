package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Studentdao;
import dto.Student;

@WebServlet(urlPatterns = "/login")
public class Login extends HttpServlet
{
 @Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String email=(req.getParameter("email"));
    String password = (req.getParameter("password"));
    
	
	Studentdao dao=new Studentdao();
	//Student student=dao.fetch(email);
	
	
	Student student;
	try {
		long mobile = Long.parseLong(email);
		student = dao.fetch(mobile);
	}
	catch(NumberFormatException e){
		student = dao.fetch(email);
	}
	
	if(student==null)
	{
		resp.getWriter().print("<h1>Invalid Email/ Mobile </h1>");
		resp.getWriter().print("Login.html");
		
	}
	else {
		if(student.getPassword().equals(password))
		{
			
		resp.getWriter().print("<h1>Login Successfully</h1>");
		
		
//		resp.getWriter().print("<div>");
//		resp.getWriter().print("<table border='1'>"
//				+"<tr>"
//				+"<th>ID</th>"
//				+"<th>Name</th>"
//				+"<th>Email</th>"
//				+"<th>Mobile</th>"
//				+"<th>password</th>"
//				+"</tr>"
//				+"<tr>"
//				+"<th>"+student.getId()+"</th>"
//				+"<th>"+student.getEmail()+"</th>"
//				+"<th>"+student.getName()+"</th>"
//				+"<th>"+student.getMobile()+"</th>"
//				+"<th>"+student.getPassword()+"</th>"
//				+"</tr>" + "</table>");
//		 resp.getWriter().print("</div><br><br>");
//		 
//		 resp.getWriter().print("<div style='color:cyan'>");
//		 resp.getWriter().print("<table border='1'>" 
//				 +"<tr>"
//					+"<th>ID</th>"
//					+"<th>Name</th>"
//					+"<th>Email</th>"
//					+"<th>Mobile</th>"
//					+"<th>password</th>"
//					+"</tr>");
//		 
//		 
//		 List<Student> list=dao.fetch();
//			for(Student stud : list){
//				resp.getWriter().print("<tr>"+"<th>"+stud.getId()+"</th>"
//			+"<th>"+stud.getName()+"</th>"+"<th>"+stud.getEmail()+"</th>"+"<th>"+stud.getMobile()+"</th>"+"<th>"+stud.getPassword()+"</th>"+"</tr>");
//		
//			}
//			resp.getWriter().print("</table>");
		
	     req.setAttribute("student", student);
         req.setAttribute("list", dao.fetch());
         req.getRequestDispatcher("Success.jsp").include(req, resp);
			
	}
	  else
	{
		resp.getWriter().print("<h1> Invalid password </h1>");
		req.getRequestDispatcher("Login_Page.html").include(req, resp);
	}
	}
 }
}
	 


