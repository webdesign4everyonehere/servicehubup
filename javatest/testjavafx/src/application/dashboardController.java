package application;

import java.net.URL;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class dashboardController  implements Initializable{


    @FXML
    private TextField StudentGrade_search;

    @FXML
    private TableView<?> StudentGrade_tableView;

    @FXML
    private Button addClient_addBtn;

    @FXML
    private Button addClient_btn;

    @FXML
    private Button addClient_clearBtn;

    @FXML
    private TextField addClient_clientNum;

    @FXML
    private TableColumn<clientDatabase, String> addClient_col_client;

    @FXML
    private TableColumn<clientDatabase, String> addClient_col_description;

    @FXML
    private TableColumn<clientDatabase, String> addClient_col_email;

    @FXML
    private TableColumn<clientDatabase, String> addClient_col_firstName;

    @FXML
    private TableColumn<clientDatabase, String> addClient_col_gender;

    @FXML
    private TableColumn<clientDatabase, String> addClient_col_lastName;

    @FXML
    private TableColumn<clientDatabase, String> addClient_col_registrationDate;

    @FXML
    private TableColumn<clientDatabase, String> addClient_col_service;

    @FXML
    private TableColumn<clientDatabase, String> addClient_col_tel;

    @FXML
    private Button addClient_deleteBtn;

    @FXML
    private TextField addClient_email;

    @FXML
    private TextField addClient_firstName;

    @FXML
    private ComboBox<String> addClient_gender;

    @FXML
    private TextField addClient_lastName;

    @FXML
    private DatePicker addClient_registirationDate;

    @FXML
    private TextField addClient_search;

    @FXML
    private ComboBox<?> addClient_service;

    @FXML
    private TableView<clientDatabase> addClient_tableView;

    @FXML
    private TextField addClient_tel;

    @FXML
    private Button addClient_updateBtn;
    @FXML
    private TextField addClient_description;

    @FXML
    private Button close;

    @FXML
    private Button home_btn;

    @FXML
    private AnchorPane home_form;

    @FXML
    private Label home_totaleClient;

    @FXML
    private LineChart<?, ?> home_totaleSpecialist_chart;

    @FXML
    private Label home_totaleSpecialists;

    @FXML
    private Label home_totaleUsers;

    @FXML
    private BarChart<?, ?> home_totaleUsers_chart;

    @FXML
    private AnchorPane main_form;


    @FXML
    private Button service_addBtn;

    @FXML
    private Button service_btn;

    @FXML
    private Button service_clearBtn;

    @FXML
    private TableColumn<serviceDatabase, String> service_col_description;

    @FXML
    private TableColumn<serviceDatabase, String> service_col_service;

    @FXML
    private Button service_deleteBtn;

    @FXML
    private TextField service_description;

    @FXML
    private AnchorPane service_form;

    @FXML
    private TextField service_service;

    @FXML
    private TableView<serviceDatabase> service_tableView;

    @FXML
    private Button service_updateBtn;
    
    @FXML
    private AnchorPane addClient_form;
    
    @FXML
    private AnchorPane idk_form;
    
    @FXML
    private Button idk_btn;
    
    @FXML
    private Button logout;
    
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    
    
    //after adding the table of specialists
    public void homeDisplayTotalUsers() {
    	
    	String sql = "SELECT COUNT(id)  FROM client";
    	
    	connect = database.connectDb();
    	
    	int countUsers = 0;
    	
    	try {
    		prepare = connect.prepareStatement(sql);
    		result = prepare.executeQuery();
    		
    		if(result.next()) {
    			countUsers = result.getInt("COUNT(id)");
    		}
    		
    		home_totaleUsers.setText(String.valueOf(countUsers));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
  
    public void homeDisplayTotalClients() {
       String sql = "SELECT COUNT(id)  FROM client";
    	
    	connect = database.connectDb();
    	
    	int countUsers = 0;
    	
    	try {
    		prepare = connect.prepareStatement(sql);
    		result = prepare.executeQuery();
    		
    		if(result.next()) {
    			countUsers = result.getInt("COUNT(id)");
    		}
    		
    		home_totaleClient.setText(String.valueOf(countUsers));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
   
    //after adding the table of specialists

    public void homeDisplayTotalSpecialists() {
        String sql = "SELECT COUNT(id)  FROM client";
     	
     	connect = database.connectDb();
     	
     	int countUsers = 0;
     	
     	try {
     		prepare = connect.prepareStatement(sql);
     		result = prepare.executeQuery();
     		
     		if(result.next()) {
     			countUsers = result.getInt("COUNT(id)");
     		}
     		
     		home_totaleSpecialists.setText(String.valueOf(countUsers));
 			
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
     }
    
    public ObservableList<clientDatabase> addClinetListData(){
		
		ObservableList<clientDatabase> listClient = FXCollections.observableArrayList();
		
		String sql = "SELECT * FROM client";
		
		connect = database.connectDb();
		
		try {
			clientDatabase clientD;
		    prepare = connect.prepareStatement(sql);
	        result = prepare.executeQuery();
			
	        while(result.next()) {
	        	clientD = new clientDatabase(result.getString("clientNum")
	        			,result.getString("firstName")
	        			,result.getString("lastName")
	        			,result.getString("gender")
	        			,result.getString("email")
	        			,result.getString("tel")
	        			,result.getString("service")
	        			,result.getString("description")
	        			,result.getDate("registration_date"));
	        	
	        	listClient.add(clientD);
	        }
	        
		}catch(Exception e) {
            e.printStackTrace();

		}
		
		return listClient;
		
	}
    
    
    private ObservableList<clientDatabase> addClinetListD;
    public void addClientShowListData() {
    	addClinetListD = addClinetListData();
    	
    	addClient_col_client.setCellValueFactory(new PropertyValueFactory<>("clientNum"));
    	addClient_col_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
    	addClient_col_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
    	addClient_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
    	addClient_col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
    	addClient_col_tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
    	addClient_col_service.setCellValueFactory(new PropertyValueFactory<>("service"));
    	addClient_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
    	addClient_col_registrationDate.setCellValueFactory(new PropertyValueFactory<>("registration_date"));
    	
    	
    	addClient_tableView.setItems(addClinetListD);
    	
    }

    public void addClientSelect() {
        clientDatabase clientD = addClient_tableView.getSelectionModel().getSelectedItem();
        int num = addClient_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        addClient_clientNum.setText(clientD.getClientNum());
        addClient_firstName.setText(clientD.getFirstName());
        addClient_lastName.setText(clientD.getLastName());
        addClient_email.setText(clientD.getEmail());
        addClient_tel.setText(clientD.getTel());
        addClient_description.setText(clientD.getDescription()); // Populate description field
        addClient_registirationDate.setValue(clientD.getRegistration_Date().toLocalDate());
        addClient_gender.getSelectionModel().select(clientD.getGender()); // assuming gender is a string, adjust as needed
    }
    
    public void addClientAdd() {
        String insertData = "INSERT INTO client (clientNum, firstName, lastName, gender, email, tel, service, description, registration_date, date)" 
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        connect = database.connectDb();

        try {
            Alert alert;
            // Validate all fields
            if (addClient_clientNum.getText().isEmpty() 
                    || addClient_service.getSelectionModel().getSelectedItem() == null
                    || addClient_firstName.getText().isEmpty() 
                    || addClient_lastName.getText().isEmpty()
                    || addClient_gender.getSelectionModel().getSelectedItem() == null
                    || addClient_email.getText().isEmpty()
                    || addClient_tel.getText().isEmpty()
                    || addClient_description.getText().isEmpty() // Validate description
                    || addClient_registirationDate.getValue() == null) {
                
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields.");
                alert.showAndWait();
            } else {
                // Check if the client number already exists
                String checkData = "SELECT clientNum FROM client WHERE clientNum = '" + addClient_clientNum.getText() + "'";
                statement = connect.createStatement();
                result = statement.executeQuery(checkData);

                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Client #" + addClient_clientNum.getText() + " already exists!");
                    alert.showAndWait();
                } else {
                    // Prepare SQL statement
                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, addClient_clientNum.getText());
                    prepare.setString(2, addClient_firstName.getText());
                    prepare.setString(3, addClient_lastName.getText());
                    prepare.setString(4, (String) addClient_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(5, addClient_email.getText());
                    prepare.setString(6, addClient_tel.getText());
                    prepare.setString(7, (String) addClient_service.getSelectionModel().getSelectedItem());
                    prepare.setString(8, addClient_description.getText()); // Add description
                    prepare.setString(9, String.valueOf(addClient_registirationDate.getValue()));

                    // Set current date
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare.setDate(10, sqlDate);

                    // Execute the query
                    prepare.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();

                    // Refresh the client list
                    addClientShowListData();
                    // Clear input fields
                    addClientClear();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addClientUpdate() {
        String updateData = "UPDATE client SET firstName = ?, lastName = ?, gender = ?, email = ?, tel = ?, service = ?, description = ?, registration_date = ? WHERE clientNum = ?";

        connect = database.connectDb();

        try {
            Alert alert;
            // Validate fields
            if (addClient_clientNum.getText().isEmpty() 
                    || addClient_service.getSelectionModel().getSelectedItem() == null
                    || addClient_firstName.getText().isEmpty() 
                    || addClient_lastName.getText().isEmpty()
                    || addClient_gender.getSelectionModel().getSelectedItem() == null
                    || addClient_email.getText().isEmpty()
                    || addClient_tel.getText().isEmpty()
                    || addClient_registirationDate.getValue() == null) {
                
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields.");
                alert.showAndWait();
            } else {
                // Prepare the SQL update statement
                prepare = connect.prepareStatement(updateData);
                prepare.setString(1, addClient_firstName.getText());
                prepare.setString(2, addClient_lastName.getText());
                prepare.setString(3, (String) addClient_gender.getSelectionModel().getSelectedItem());
                prepare.setString(4, addClient_email.getText());
                prepare.setString(5, addClient_tel.getText());
                prepare.setString(6, (String) addClient_service.getSelectionModel().getSelectedItem());
                prepare.setString(7, addClient_description.getText()); // Update description field
                prepare.setString(8, String.valueOf(addClient_registirationDate.getValue()));
                prepare.setString(9, addClient_clientNum.getText()); // Update based on clientNum

                // Execute the update query
                int rowsUpdated = prepare.executeUpdate();

                if (rowsUpdated > 0) {
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Client information successfully updated!");
                    alert.showAndWait();

                    // Refresh the client list
                    addClientShowListData();
                    // Clear input fields
                    addClientClear();
                } else {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("No changes were made. Please verify the client number.");
                    alert.showAndWait();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    public void addClientDelete() { 
        String deleteData = "DELETE FROM client WHERE clientNum = ?";

        connect = database.connectDb();

        try {
            Alert alert;
            if (addClient_clientNum.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a valid Client Number to delete.");
                alert.showAndWait();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete Client #" + addClient_clientNum.getText() + "?");

                Optional<ButtonType> option = alert.showAndWait();

                if (option.isPresent() && option.get() == ButtonType.OK) {
                    prepare = connect.prepareStatement(deleteData);
                    prepare.setString(1, addClient_clientNum.getText());

                    int rowsDeleted = prepare.executeUpdate();

                    if (rowsDeleted > 0) {
                        alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Information Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Client successfully deleted!");
                        alert.showAndWait();

                        addClientShowListData();
                        addClientClear();
                    } else {
                        alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Client not found. Please verify the Client Number.");
                        alert.showAndWait();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addClientClear() {
        addClient_clientNum.setText("");
        addClient_service.getSelectionModel().clearSelection();
        addClient_firstName.setText("");
        addClient_lastName.setText("");
        addClient_gender.getSelectionModel().clearSelection();
        addClient_email.setText("");
        addClient_tel.setText("");
        addClient_registirationDate.setValue(null);
        addClient_description.setText(""); // Clear the description field
        getData.path = "";
    }
    
    
    public void addClientSearch() {
        FilteredList<clientDatabase> filter = new FilteredList<>(addClinetListD, e -> true);

        addClient_search.textProperty().addListener((observable, oldValue, newValue) -> {
            filter.setPredicate(predicateClientData -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicateClientData.getClientNum() != null 
                    && predicateClientData.getClientNum().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateClientData.getFirstName() != null 
                    && predicateClientData.getFirstName().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateClientData.getLastName() != null 
                    && predicateClientData.getLastName().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateClientData.getEmail() != null 
                    && predicateClientData.getEmail().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateClientData.getTel() != null 
                    && predicateClientData.getTel().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateClientData.getService() != null 
                    && predicateClientData.getService().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateClientData.getDescription() != null 
                    && predicateClientData.getDescription().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateClientData.getRegistration_Date() != null 
                    && String.valueOf(predicateClientData.getRegistration_Date()).toLowerCase().contains(searchKey)) {
                    return true;
                }
                return false;
            });
        });

        SortedList<clientDatabase> sortedList = new SortedList<>(filter);
        sortedList.comparatorProperty().bind(addClient_tableView.comparatorProperty());
        addClient_tableView.setItems(sortedList);
    }

    public void addClientServiceList() {
    	
    	String listService = "SELECT * FROM service";
    	
    	connect = database.connectDb();
    	
    	try {
    		
    		ObservableList listS = FXCollections.observableArrayList();
    		
    		prepare = connect.prepareStatement(listService);
    		result = prepare.executeQuery();
    		
    		while(result.next()) {
    			listS.add(result.getString("service"));
    		}
    		addClient_service.setItems(listS);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
    
    
    private String[] genderList = {"Male", "Female"};

    public void addClientGenderList() {
        List<String> genderL = new ArrayList<>();

        // Populate the genderL list with the gender options
        for (String data : genderList) {
            genderL.add(data);
        }

        // Convert the list to an ObservableList
        ObservableList<String> obList = FXCollections.observableArrayList(genderL);

        // Set the items in the ComboBox
        addClient_gender.setItems(obList);
    }

    
    public void switchForm(ActionEvent event) {
        if (event.getSource() == home_btn) {
            home_form.setVisible(true);
            addClient_form.setVisible(false);
            service_form.setVisible(false);
            idk_form.setVisible(false);
            
            home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            addClient_btn.setStyle("-fx-background-color:transparent");
            service_btn.setStyle("-fx-background-color:transparent");
            idk_btn.setStyle("-fx-background-color:transparent");

            homeDisplayTotalUsers();
            homeDisplayTotalClients();
            homeDisplayTotalSpecialists();
        }else if (event.getSource() == addClient_btn) {
            home_form.setVisible(false);
            addClient_form.setVisible(true);
            service_form.setVisible(false);
            idk_form.setVisible(false);
            
            home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            addClient_btn.setStyle("-fx-background-color:transparent");
            service_btn.setStyle("-fx-background-color:transparent");
            idk_btn.setStyle("-fx-background-color:transparent");

            addClientShowListData();
            addClientServiceList();
            addClientGenderList();
            addClientSearch();
            
        }else if (event.getSource() == service_btn) {
            home_form.setVisible(false);
            addClient_form.setVisible(false);
            service_form.setVisible(true);
            idk_form.setVisible(false);
            
            home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            addClient_btn.setStyle("-fx-background-color:transparent");
            service_btn.setStyle("-fx-background-color:transparent");
            idk_btn.setStyle("-fx-background-color:transparent");
            
            serviceShowListData();

        }else if (event.getSource() == idk_btn) {
            home_form.setVisible(false);
            addClient_form.setVisible(false);
            service_form.setVisible(false);
            idk_form.setVisible(true);
            
            home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            addClient_btn.setStyle("-fx-background-color:transparent");
            service_btn.setStyle("-fx-background-color:transparent");
            idk_btn.setStyle("-fx-background-color:transparent");

        }
    }
	
    public void close() {
    	System.exit(0);
    }
    
    public void minimize() {
    	Stage stage = (Stage)main_form.getScene().getWindow();
    	stage.setIconified(true);
    }
    
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		 homeDisplayTotalUsers();
		  homeDisplayTotalClients();
	    homeDisplayTotalSpecialists();
		
        addClientShowListData();
        addClientGenderList();
        addClientServiceList();
        // Bind columns to clientDatabase properties
        addClient_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        addClient_col_registrationDate.setCellValueFactory(new PropertyValueFactory<>("registration_Date"));

        // Ensure the TableView is populated with data
        addClient_tableView.setItems(addClinetListD);
        
        serviceShowListData();
		
	}
	
	public void serviceAdd() {

	    String insertData = "INSERT INTO service (service, description) VALUES(?, ?)";
	    connect = database.connectDb();

	    try {
	        Alert alert;

	        // V�rification si les champs sont vides
	        if (service_service.getText().isEmpty() || service_description.getText().isEmpty()) {
	            alert = new Alert(AlertType.ERROR);
	            alert.setTitle("Error Message");
	            alert.setHeaderText(null);
	            alert.setContentText("Please fill all blank fields");
	            alert.showAndWait();
	        } else {
	            // Requ�te pour v�rifier si le service existe d�j�
	            String checkData = "SELECT service FROM service WHERE service = '"
	                + service_service.getText() + "'";

	            statement = connect.createStatement();
	            result = statement.executeQuery(checkData);

	            if (result.next()) {
	                alert = new Alert(AlertType.ERROR);
	                alert.setTitle("Error Message");
	                alert.setHeaderText(null);
	                alert.setContentText("Service: " + service_service.getText() + " already exists!");
	                alert.showAndWait();
	            } else {
	                // Pr�paration de l'insertion
	                prepare = connect.prepareStatement(insertData);
	                prepare.setString(1, service_service.getText());
	                prepare.setString(2, service_description.getText());

	                // Ex�cution de l'insertion
	                prepare.executeUpdate();

	                alert = new Alert(AlertType.INFORMATION);
	                alert.setTitle("Information Message");
	                alert.setHeaderText(null);
	                alert.setContentText("Successfully Added!");
	                alert.showAndWait();

	                // Mise � jour de la liste des services
	                serviceShowListData();

	                // Nettoyage des champs
	                ServiceClear();
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

    public void serviceDelete() {

	        String deleteData = "DELETE FROM service WHERE service = '"
	                + service_service.getText() + "'";

	        connect = database.connectDb();

	        try {
	            Alert alert;

	            if (service_service.getText().isEmpty()
	                    || service_description.getText().isEmpty()) {
	                alert = new Alert(AlertType.ERROR);
	                alert.setTitle("Error Message");
	                alert.setHeaderText(null);
	                alert.setContentText("Please fill all blank fields");
	                alert.showAndWait();
	            } else {
//	                ALL GOOD GUYS! NOW LETS PROCEED TO ADD STUDENTS FORM
	                alert = new Alert(AlertType.CONFIRMATION);
	                alert.setTitle("Confirmation Message");
	                alert.setHeaderText(null);
	                alert.setContentText("Are you sure you want to DELETE Service: " + service_service.getText() + "?");
	                Optional<ButtonType> option = alert.showAndWait();

	                if (option.get().equals(ButtonType.OK)) {
	                    statement = connect.createStatement();
	                    statement.executeUpdate(deleteData);

	                    alert = new Alert(AlertType.INFORMATION);
	                    alert.setTitle("Information Message");
	                    alert.setHeaderText(null);
	                    alert.setContentText("Successfully Deleted!");
	                    alert.showAndWait();

	                 // Mise � jour de la liste des services
		                serviceShowListData();

		                // Nettoyage des champs
		                ServiceClear();

	                } else {
	                    return;
	                }
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	    }
	
    public void serviceUpdate() {

        // Requ�te SQL pour la mise � jour
        String updateData = "UPDATE service SET description = ? WHERE service = ?";

        connect = database.connectDb();

        try {
            Alert alert;

            // V�rification si les champs sont vides
            if (service_service.getText().isEmpty() || service_description.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                // Confirmation de la mise � jour
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Service: " + service_service.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.isPresent() && option.get().equals(ButtonType.OK)) {
                    // Pr�paration de la requ�te avec param�tres
                    prepare = connect.prepareStatement(updateData);
                    prepare.setString(1, service_description.getText());
                    prepare.setString(2, service_service.getText());

                    // Ex�cution de la mise � jour
                    prepare.executeUpdate();

                    // Message de succ�s
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    // Mise � jour de la liste des services
                    serviceShowListData();

                    // Nettoyage des champs
                    ServiceClear();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	public void ServiceClear() {
		 service_service.setText("");
		 service_description.setText("");
    }
	
	
	public ObservableList<serviceDatabase> serviceListData(){
		
		ObservableList<serviceDatabase> listData = FXCollections.observableArrayList();
		
		String sql = "SELECT * FROM service";
		
		connect = database.connectDb();
		
	    try {
			serviceDatabase serviceD;
			prepare = connect.prepareStatement(sql);
			result = prepare.executeQuery();
			
			while(result.next()) {
				serviceD = new serviceDatabase(result.getString("service")
						, result.getString("description"));
				
				listData.add(serviceD);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	    return listData;
	}
	
	public ObservableList<serviceDatabase> serviceList;
	public void serviceShowListData() {
		serviceList = serviceListData();
		
		service_col_service.setCellValueFactory(new PropertyValueFactory<>("service"));
		service_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
		
		service_tableView.setItems(serviceList);
	}
	
	public void serviceSelect() {
		serviceDatabase serviceD = service_tableView.getSelectionModel().getSelectedItem();
		int num = service_tableView.getSelectionModel().getSelectedIndex();
		
		if((num - 1) < -1) {
			return;
		}
		
		service_service.setText(serviceD.getService());
		service_description.setText(serviceD.getDescription());
	}
	
	 
	
	 private double x = 0;
	 private double y = 0;
	 
	  public void logout() {

	        try {

	            Alert alert = new Alert(AlertType.CONFIRMATION);
	            alert.setTitle("Confirmation Message");
	            alert.setHeaderText(null);
	            alert.setContentText("Are you sure you want to logout?");

	            Optional<ButtonType> option = alert.showAndWait();

	            if (option.get().equals(ButtonType.OK)) {

	                //HIDE YOUR DASHBOARD FORM
	                logout.getScene().getWindow().hide();

	                //LINK YOUR LOGIN FORM 
	                Parent root = FXMLLoader.load(getClass().getResource("layout.fxml"));
	                Stage stage = new Stage();
	                Scene scene = new Scene(root);

	                root.setOnMousePressed((MouseEvent event) -> {
	                    x = event.getSceneX();
	                    y = event.getSceneY();
	                });

	                root.setOnMouseDragged((MouseEvent event) -> {
	                    stage.setX(event.getScreenX() - x);
	                    stage.setY(event.getScreenY() - y);

	                    stage.setOpacity(.8);
	                });

	                root.setOnMouseReleased((MouseEvent event) -> {
	                    stage.setOpacity(1);
	                });

	                stage.initStyle(StageStyle.TRANSPARENT);

	                stage.setScene(scene);
	                stage.show();

	            } else {
	                return;
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	    }

	   
	    public void defaultNav(){
	        home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
	    }
	    
}

	   