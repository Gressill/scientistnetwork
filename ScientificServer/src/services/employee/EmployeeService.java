package services.employee;

import java.util.List;

import services.employee.Employee;
import services.employee.EmployeeDAO;


public class EmployeeService {
	
	public EmployeeService() {
	}
	
	public List getDepartments() {
		EmployeeDAO dao = new EmployeeDAO();
		return dao.getDepartments();
	}
	
	public List getEmployees() {
		EmployeeDAO dao = new EmployeeDAO();
		return dao.getEmployees();
	}
	
	public List getEmployeesByName(String name) {
		EmployeeDAO dao = new EmployeeDAO();
		return dao.getEmployeesByName(name);
	}
	
	public Employee getEmployeesById(int id)
	{
		EmployeeDAO dao = new EmployeeDAO();
		return dao.getEmployee(id);
	}
	
	public int createEmployee(Employee employee) {
		EmployeeDAO dao = new EmployeeDAO();
		return dao.create(employee);
	}
	
	public void updateEmployee(Employee employee) {
		EmployeeDAO dao = new EmployeeDAO();
		dao.update(employee);
	}
	
	public void deleteEmployee(int id) {
		EmployeeDAO dao = new EmployeeDAO();
		dao.delete(id);
	}
	
}
