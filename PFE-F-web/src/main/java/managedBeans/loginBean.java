package managedBeans;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import entities.Admin;
import entities.Employee;
import entities.Employee.Role;
import entities.Student;
import service.AutheticatorService;

@ManagedBean(name="loginBean",eager = true)
@SessionScoped
public class loginBean {

	
	private String username;
	private String password;
	private Admin admin;
	private Employee employee;
	private Student student;
	private Boolean loggedIn;
	
	@EJB
	private AutheticatorService service;

	
	public loginBean() {
		super();
		// TODO Auto-generated constructor stub
		service = new AutheticatorService();
	}

	
	public String doLogin()
	{
		String navigateTo = "null";
		//admin = service.getAutheticatedAdmin(username, password);
		employee = service.logInToEmp(username, password);
		//student = service.getAutheticatedStudent(username, password);
		//System.out.println("Admin logged in :: " + admin);
		if((employee.getEmployee_id() != 0L) && (employee.getRole().equals(Role.professor) ) )
		{
			navigateTo = "/pages/employe/welcome?faces-redirect=true";
			System.out.println("Employee logged in :: " + employee);
			loggedIn = true;
		}
		else if((employee.getEmployee_id() != 0L) && (employee.getRole().equals(Role.departement_supervisor) ) ){
			navigateTo = "/pages/director/welcome?faces-redirect=true";
			System.out.println("Employee logged in :: " + employee);
			loggedIn = true;
		}
		else{
			FacesContext.getCurrentInstance().addMessage("form:btn", new FacesMessage("Wrong data!"));
		}
		return navigateTo;
	}
	
	public String doLogout()
	{
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		
		loggedIn = false;
        return "/login?faces-redirect=true";
	}
	
	public String doRedirectToHomePage()
	{
		String navigateTo = "";
		
		if(admin != null)
		{
			if(!(admin.getEmail().equals("*")) && !(admin.getEmail().equals("-")))
			{
				navigateTo = "/pages/admin/welcome?faces-redirect=true";
				loggedIn = true;
			}
		}
		else if(employee != null)
		{
			if(employee.getEmployee_id() != 0L && employee.getEmployee_id() != -1L)
			{
				//if(employee.getRole() == Role.director_k)
					System.out.println("it's " + employee.getRole());
				navigateTo = "/pages/employe/welcome?faces-redirect=true";
				loggedIn = true;
			}
		}
		else if(student != null)
		{
			if((student.getStudent_id() != -1L) && (student.getStudent_id() != 0L))
			{
				navigateTo = "/pages/student/welcome?faces-redirect=true";
				loggedIn = true;
			}
		}
		
		else{
			navigateTo = "/login?faces-redirect=true";
			loggedIn = false;
		}
		
		System.out.println("redirecting to  " + navigateTo);
		
		return navigateTo;
	}
	
	public String verifyLoggedIn()
	{
		FacesContext ctx = FacesContext.getCurrentInstance();
		HttpServletRequest servletRequest = (HttpServletRequest) ctx.getExternalContext().getRequest();
		String fullURI = servletRequest.getRequestURI();
		
		System.out.println("Actual page " + fullURI);
		System.out.println("Student " + student);
		
		if(fullURI.contains("welcome.jsf"))
		{
			System.out.println("it contains...");
			return this.doRedirectToHomePage();
		}
		
		return this.doRedirectToHomePage();
	}
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AutheticatorService getService() {
		return service;
	}

	public void setService(AutheticatorService service) {
		this.service = service;
	}


	public Admin getAdmin() {
		return admin;
	}


	public void setAdmin(Admin admin) {
		this.admin = admin;
	}


	public Employee getEmployee() {
		return employee;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


	public Student getStudent() {
		return student;
	}


	public void setStudent(Student student) {
		this.student = student;
	}


	public Boolean getLoggedIn() {
		return loggedIn;
	}


	public void setLoggedIn(Boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	
}
