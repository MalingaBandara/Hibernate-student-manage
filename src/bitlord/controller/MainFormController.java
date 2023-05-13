package bitlord.controller;

import bitlord.bo.BoFactory;
import bitlord.bo.custom.StudentBo;
import bitlord.dto.StudentDto;
import bitlord.view.tm.StudentTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.Optional;

public class MainFormController {
    public TextField txtName;
    public TextField txtContact;

  private final StudentBo studentBo = BoFactory.getInstance().getBo(BoFactory.BoType.STUDENT);

    public TableView<StudentTM> tblStudents;
    public TableColumn colStudentId;
    public TableColumn colStudentName;
    public TableColumn colContctNumber;
    public TableColumn colSeeMore;
    public TableColumn colDelete;
    public Button btnStudentSave;


    public void initialize() throws SQLException, ClassNotFoundException {


                colStudentId.setCellValueFactory( new PropertyValueFactory<>("id") );
                colStudentName.setCellValueFactory( new PropertyValueFactory<>("name") );
                colContctNumber.setCellValueFactory( new PropertyValueFactory<>("contact") );
                colSeeMore.setCellValueFactory( new PropertyValueFactory<>("seeMoreBtn") );
                colDelete.setCellValueFactory( new PropertyValueFactory<>("deleteBtn") );


            loadAllStudents( );

      // ------------------ Listener ------------------

        tblStudents.getSelectionModel().selectedItemProperty().addListener( (observable, oldValue, newValue) -> {

                if ( newValue != null ) {

                    selectedStudentTm = newValue;
                    txtName.setText( newValue.getName() );
                    txtContact.setText( newValue.getContact() );

                    btnStudentSave.setText( "Update Student" ); // change  button name

                }

        } );

      // ------------------ Listener ------------------


  }

private StudentTM selectedStudentTm = null ; // assign selected values


    public void newStudentOnAction(ActionEvent actionEvent) {

        btnStudentSave.setText( "Save Student" );
        selectedStudentTm = null; // remove selected recode

    }

    private void loadAllStudents() throws SQLException, ClassNotFoundException {

        ObservableList< StudentTM > tmList = FXCollections.observableArrayList();

        for ( StudentDto dto : studentBo.findAllStudents() ) {

            Button deleteButton = new Button( "Delete" ); // create button  [import javafx.scene.control.*;] <-- use this
            deleteButton.setStyle("-fx-background-color: #c0392b");
            Button seeMoreButton = new Button( "See More" ); // create button
            seeMoreButton.setStyle("-fx-background-color: #298069");

            StudentTM tm = new StudentTM(dto.getId(), dto.getName(), dto.getContact(), deleteButton, seeMoreButton );

            tmList.add( tm );


            // delete student
            deleteButton.setOnAction( e-> {

                Alert alert = new Alert( Alert.AlertType.CONFIRMATION, "Are you sure?" , ButtonType.YES, ButtonType.NO); // ask confirmation from user

                Optional<ButtonType> selectedDuttonData = alert.showAndWait(); // asign

                        if (selectedDuttonData.get().equals( ButtonType.YES ) ) {

                                try {

                                    studentBo.deleteStudentById( tm.getId() );

                                    new Alert( Alert.AlertType.INFORMATION, "Student Deleted" ).show();
                                    loadAllStudents();

                                } catch (Exception exception ){
                                    new Alert( Alert.AlertType.ERROR, "Try Again" ).show();
                                }

                        }

            } ); // **************


        }

        tblStudents.setItems( tmList );
            tblStudents.refresh(); // refresh table
    }


    public void btnSaveStudentOnAction(ActionEvent actionEvent) {

        StudentDto dto = new StudentDto();

        dto.setName( txtName.getText() );
        dto.setContact( txtContact.getText() );

            // -------------- Update Student -------- //

                    if ( btnStudentSave.getText().equals( "Update Student" ) ) {

                        if ( selectedStudentTm == null ){ // if user not selected
                            new Alert( Alert.AlertType.ERROR, "Try Again" ).show();
                            return;
                        }

                        try{

                            dto.setId(selectedStudentTm.getId() );

                            studentBo.updateStudent( dto );

                            new Alert( Alert.AlertType.INFORMATION, "Student Updated" ).show();
                                        selectedStudentTm = null; // remove selected recode
                                    btnStudentSave.setText( "Save Student" );
                            loadAllStudents();

                        }catch ( Exception e ){
                            new Alert( Alert.AlertType.ERROR, "Try Again" ).show();
                        }

                    }

            // -------------- ***** -------- //

                                // Save student
                            else {

                                try{

                                    studentBo.saveStudent( dto );
                                    new Alert( Alert.AlertType.INFORMATION, "Student Saved" ).show();
                                    loadAllStudents();

                                }catch ( Exception e ){
                                    new Alert( Alert.AlertType.ERROR, "Try Again" ).show();
                                }

                            } // ********

    }

}
