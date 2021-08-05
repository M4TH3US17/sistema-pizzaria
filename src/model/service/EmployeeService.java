package model.service;

import java.util.List;

import gui.util.Alerts;
import javafx.scene.control.Alert.AlertType;
import model.dao.DaoFactory;
import model.dao.EmployeeDao;
import model.entities.Employee;

public class EmployeeService {

	private EmployeeDao empDao = DaoFactory.createEmployeeDao();

	public List<Employee> findAll(){
		return empDao.findAll();
	}

	public void save(Employee emp) {
		empDao.insert(emp);

	}

	public void update(Employee emp) {
		if(emp.getId() != null && emp.getCargo() != null && emp.getName() != null
				&& emp.getSalary() != null) {
			empDao.update(emp);
		} else {
			Alerts.showAlert("Dados Inválidos.", null, "Preencha o formulário corretamente. "
					+ "Os dados informados estão inválidos.", 
					AlertType.ERROR);
		}
	}
	
	public void deleteById(Integer id) {
		empDao.deleteById(id);
	}
}
