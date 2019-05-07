package managedBeans;

import entities.Employee;

public class TestClass {
	Employee e;
	Long nbr;
	public Employee getE() {
		return e;
	}
	public void setE(Employee e) {
		this.e = e;
	}
	public Long getNbr() {
		return nbr;
	}
	public void setNbr(Long nbr) {
		this.nbr = nbr;
	}
	public TestClass(Employee e, Long long1) {
		super();
		this.e = e;
		this.nbr = long1;
	}
	
	
	
}
