package mvc.employee.model.dal;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mvc.employee.model.Employee;

public class EmployeesDAL {
	private SQLException ex;

	public SQLException getSQLException() {
		return ex;
	}

	public EmployeesDAL() {
	}

	public ObservableList<Employee> getEmployees() {

		ObservableList<Employee> employees = FXCollections.observableArrayList();
		try (Statement statement = OraConn.getConnection().createStatement();) {
			String query = "SELECT * FROM EMPLOYEES";
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				employees.add(rs2Employee(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employees;
	}

	private Employee rs2Employee(ResultSet resultSet) {
		Employee emp = null;
		try {
			int col = 1;
			emp = new Employee(resultSet.getInt(col++));
			emp.setFirstName(resultSet.getNString(col++));
			emp.setLastName(resultSet.getNString(col++));
			emp.setEmail(resultSet.getNString(col++));
			emp.setPhoneNumber(resultSet.getNString(col++));
			emp.setHireDate(resultSet.getDate(col++).toLocalDate());
			emp.setJobId(resultSet.getNString(col++));
			emp.setSalary(resultSet.getDouble(col++));
			col++;
			emp.setManagerId(resultSet.getInt(col++));
			emp.setDepartmentId(resultSet.getInt(col++));

		} catch (SQLException ex) {
			this.ex = ex;
		}
		return emp;
	}

}
