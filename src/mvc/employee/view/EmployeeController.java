package mvc.employee.view;

import java.time.LocalDate;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import mvc.employee.model.Employee;

public class EmployeeController {

	// TableView, TableColumn
	@FXML
	private TableView<Employee> employeeTable;
	@FXML
	private TableColumn<Employee, Integer> employeeIdColumn;
	@FXML
	private TableColumn<Employee, String> firstNameColumn;
	@FXML
	private TableColumn<Employee, String> lastNameColumn;
	@FXML
	private TableColumn<Employee, String> emailColumn;
	@FXML
	private TableColumn<Employee, String> phoneNameColumn;
	@FXML
	private TableColumn<Employee, LocalDate> hireDateColumn;
	@FXML
	private TableColumn<Employee, String> hireDateAsStrColumn;
	@FXML
	private TableColumn<Employee, String> jobIdColumn;
	@FXML
	private TableColumn<Employee, Double> salaryColumn;
	@FXML
	private TableColumn<Employee, Integer> managerIdColumn;
	@FXML
	private TableColumn<Employee, String> departmentNameColumn;

	// Label
	@FXML
	private Label employeeIdLabel;
	@FXML
	private Label firstNameLabel;
	@FXML
	private Label lastNameLabel;
	@FXML
	private Label emailLabel;
	@FXML
	private Label phoneNumberLabel;
	@FXML
	private Label hireDateLabel;
	@FXML
	private Label jobIdLabel;
	@FXML
	private Label salaryLabel;
	@FXML
	private Label managerIdLabel;
	@FXML
	private Label departmentNameLabel;

	@FXML
	private void initialize() {
		employeeTable.setTableMenuButtonVisible(true);
		employeeIdColumn.setCellValueFactory(cellData -> cellData.getValue().employeeIdProperty().asObject());
		firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
		lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
		emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
		phoneNameColumn.setCellValueFactory(cellData -> cellData.getValue().phoneNumberProperty());
		hireDateColumn.setCellValueFactory(cellData -> cellData.getValue().hireDateProperty());
		jobIdColumn.setCellValueFactory(cellData -> cellData.getValue().jobIdProperty());
		salaryColumn.setCellValueFactory(cellData -> cellData.getValue().salaryProperty().asObject());

		managerIdColumn.setCellValueFactory(cellData -> cellData.getValue().managerIdProperty().asObject());
		departmentNameColumn.setCellValueFactory(cellData -> cellData.getValue().departmentNameProperty());

		// ustaw wartości pól
		refreshEmployee(null);
		// słuchaj zmiany zaznaczonego wiersza
		employeeTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> refreshEmployee(newValue));
	}

	public void setEmployees(ObservableList<Employee> olEmployees) {
		employeeTable.getItems().clear();
		employeeTable.setItems(olEmployees);
		// zaznacz pierwszy wiersz w widoku tabeli (o ile nie jest pusta)
		if (!employeeTable.getItems().isEmpty())
			employeeTable.getSelectionModel().select(0);
	}

	private void refreshEmployee(Employee emp) {
		if (emp != null) {
			employeeIdLabel.setText(Integer.toString(emp.getEmployeeId()));
			firstNameLabel.setText(emp.getFirstName());
			lastNameLabel.setText(emp.getLastName());
			emailLabel.setText(emp.getEmail());
			phoneNumberLabel.setText(emp.getPhoneNumber());
			hireDateLabel.setText(emp.getHireDate().toString());
			jobIdLabel.setText(emp.getJobId());
			salaryLabel.setText(Double.toString(emp.getSalary()));
			managerIdLabel.setText(Integer.toString(emp.getManagerId()));
			departmentNameLabel.setText(emp.getDepartmentName().toString());
		} else {
			employeeIdLabel.setText("");
			firstNameLabel.setText("");
			lastNameLabel.setText("");
			emailLabel.setText("");
			phoneNumberLabel.setText("");
			hireDateLabel.setText("");
			jobIdLabel.setText("");
			salaryLabel.setText("");
			managerIdLabel.setText("");
			departmentNameLabel.setText("");
		}
	}

	@FXML
	private void deleteEmployee() {
		int selIdx = employeeTable.getSelectionModel().getSelectedIndex();
		if (selIdx >= 0)
			employeeTable.getItems().remove(selIdx);
	}
}