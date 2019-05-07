package managedBeans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import entities.Departement;
import entities.Employee;
import entities.PFE_Form;
import service.Counter;
import service.EmployeeFacade;
import service.FormFacade;
import service.PFE_FormFacade;

@ManagedBean(name="employeeBean")
@SessionScoped
public class ProfessorBean {

	private Departement departement;
	
	@EJB
	private FormFacade serviceForm;
	
	@EJB
	private PFE_FormFacade serviceFormulaire;
	
	@EJB
	private EmployeeFacade EmpService;
	
	private List<PFE_Form> forms;
	private List<PFE_Form> waitings;
	private List<PFE_Form> archived;
	private List<PFE_Form> accepted;
	private List<PFE_Form> refused;
	private List<Employee> profs; 
	private List<Employee> profs_ord; 
	private PFE_Form acceptedForm;
	private List<Long> numberenc;
	private List<TestClass> testList;
	
	
	
	public List<TestClass> getTestList() {
		return testList;
	}

	public void setTestList(List<TestClass> testList) {
		this.testList = testList;
	}

	public ProfessorBean() {
		super();
		
		serviceFormulaire = new PFE_FormFacade();
		EmpService = new EmployeeFacade();
		
	}
	
	public String redirectToFormList()
	{
		return "/pages/employe/welcome?faces-redirect=true";
	}
	
	public String doRedirectToProfessor()
	{
		return "/pages/employe/professor?faces-redirect=true";
	}
	
	public String redirectToArchivedFormList()
	{
		return "/pages/employe/Archived?faces-redirect=true";
	}
	
	
	
	public EmployeeFacade getEmpService() {
		return EmpService;
	}

	public void setEmpService(EmployeeFacade empService) {
		EmpService = empService;
	}

	public List<Employee> getProfs_ord() {
		return profs_ord;
	}

	public void setProfs_ord(List<Employee> profs_ord) {
		this.profs_ord = profs_ord;
	}

	public List<Long> getNumberenc() {
		return numberenc;
	}

	public void setNumberenc(List<Long> numberenc) {
		this.numberenc = numberenc;
	}

	@PostConstruct
	private void init()
	{
		profs = new ArrayList<>();
		testList = new ArrayList<>();
		profs_ord = new ArrayList<>();
		numberenc = new ArrayList<>();
		forms = new ArrayList<>();
		accepted = new ArrayList<>();
		refused = new ArrayList<>();
		waitings = new ArrayList<>();
		profs_ord = EmpService.findAllProfs();
		forms = serviceFormulaire.FindProfForms(3L);
		numberenc = EmpService.getProfsOrdred();
		
		for (int i = 0 ; i<numberenc.size(); i ++)
		{
			profs.add(EmpService.find(numberenc.get(i)));
		}
		
		for (int i = 0 ; i<numberenc.size(); i ++)
		{
			TestClass tc = new TestClass(profs.get(i), numberenc.get(i));
			testList.add(tc);
		}
		
		
		
		
		
		for(PFE_Form f: forms)
		{
			if(!f.getArchieved())
			{
				//not archived
				if(f.getAccepted() == 0)
				{
					//waiting
					waitings.add(f);
				}
				else if(f.getAccepted() == 1)
				{
					//accepted
					accepted.add(f);
				}
				else{
					//refused
					refused.add(f);
				}
			}
			else{
				archived.add(f);
			}
		}
	}
	
	

	public String acceptForm()
	{
		return "/pages/employe/show?faces-redirect=true";
	}

	public Departement getDepartement() {
		return departement;
	}

	public void setDepartement(Departement departement) {
		this.departement = departement;
	}

	public FormFacade getServiceForm() {
		return serviceForm;
	}

	public void setServiceForm(FormFacade serviceForm) {
		this.serviceForm = serviceForm;
	}

	public PFE_FormFacade getServiceFormulaire() {
		return serviceFormulaire;
	}

	public void setServiceFormulaire(PFE_FormFacade serviceFormulaire) {
		this.serviceFormulaire = serviceFormulaire;
	}

	public List<PFE_Form> getForms() {
		return forms;
	}

	public void setForms(List<PFE_Form> forms) {
		this.forms = forms;
	}

	public List<PFE_Form> getWaitings() {
		return waitings;
	}

	public void setWaitings(List<PFE_Form> waitings) {
		this.waitings = waitings;
	}

	public List<PFE_Form> getArchived() {
		return archived;
	}

	public void setArchived(List<PFE_Form> archived) {
		this.archived = archived;
	}

	public List<PFE_Form> getAccepted() {
		return accepted;
	}

	public void setAccepted(List<PFE_Form> accepted) {
		this.accepted = accepted;
	}

	public List<PFE_Form> getRefused() {
		return refused;
	}

	public void setRefused(List<PFE_Form> refused) {
		this.refused = refused;
	}

	public PFE_Form getAcceptedForm() {
		return acceptedForm;
	}

	public void setAcceptedForm(PFE_Form acceptedForm) {
		this.acceptedForm = acceptedForm;
	}

	public List<Employee> getProfs() {
		return profs;
	}

	public void setProfs(List<Employee> profs) {
		this.profs = profs;
	}

	

	
	
}
