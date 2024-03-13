package laboratory_webapp_Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;


import com.mysql.cj.protocol.Resultset;

import laboratory_webapp_Model.Patient;
import laboratory_webapp_Dao.DbConnector;
import laboratory_webapp_Dao.DbConnectorFactory;
import laboratory_webapp_Dao.MySqlDbConnectorFactoryImpl;

public class PatientManager {
	
	public DbConnector getDbConnector() {
        DbConnectorFactory factory = new MySqlDbConnectorFactoryImpl(); 
        return factory.getDbConnector();
    }

	private Connection getConnection() throws ClassNotFoundException, SQLException {
        DbConnector connector = getDbConnector();
        return connector.getDbConnection();
    }

    public boolean addPatient(Patient patient) throws ClassNotFoundException, SQLException {
    	 Connection connection = getConnection(); 
         String query = "INSERT INTO patient (PatientFullName, PatientPhoneNumber, DateOfBirth, PatientAddress, Gender, PatientEmail, PatientUsername, PatientPassword) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
         PreparedStatement ps = connection.prepareStatement(query);
         ps.setString(1, patient.getPatientFullName());
         ps.setInt(2, patient.getPatientPhoneNumber());
         ps.setString(3, patient.getDateOfBirth());
         ps.setString(4, patient.getPatientAddress());
         ps.setString(5, patient.getGender());
         ps.setString(6, patient.getPatientEmail());
         ps.setString(7, patient.getPatientUsername());
         ps.setString(8, patient.getPatientPassword());
         int result = ps.executeUpdate();
         ps.close();
         connection.close();        
         return result > 0;
     }
    public Patient getSpecificPatient(int patientID) throws SQLException, ClassNotFoundException {
    	Connection connection = getConnection();
        String query = "SELECT * FROM patient WHERE PatientID = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, patientID);
        ResultSet rs = ps.executeQuery();
        Patient patient = null;
        if (rs.next()) {
            patient = new Patient(
                    rs.getInt("PatientID"),
                    rs.getString("Name"),
                    rs.getInt("ContactNumber"),
                    rs.getString("DateOfBirth"),
                    rs.getString("Address"),
                    rs.getString("Gender"),
                    rs.getString("Email"),
                    rs.getString("Username"),
                    rs.getString("Password")
            );
        }
        ps.close();
        connection.close();
        return patient;
    }
    
    public boolean updatePatient(Patient patient) throws ClassNotFoundException, SQLException {
        Connection connection = getConnection();
        String query = "UPDATE patient SET PatientFullName = ?, PatientPhoneNumber = ?, DateOfBirth = ?, PatientAddress = ?, Gender = ?, PatientEmail = ?, PatientUsername = ?, PatientPassword = ?  WHERE PatientID = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, patient.getPatientFullName());
        ps.setInt(2, patient.getPatientPhoneNumber());
        ps.setString(3, patient.getDateOfBirth());
        ps.setString(4, patient.getPatientAddress());
        ps.setString(5, patient.getGender());
        ps.setString(6, patient.getPatientEmail());
        ps.setString(7, patient.getPatientUsername());
        ps.setString(8, patient.getPatientPassword());
        ps.setInt(9, patient.getPatientID());
        int result = ps.executeUpdate();
        ps.close();
        connection.close();
        return result > 0;
    }
    
    public List<Patient> getAllPatients() throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        List<Patient> patientList = new ArrayList<>();
        String query = "SELECT * FROM patient";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            Patient patient = new Patient(
                    rs.getInt("PatientID"),
                    rs.getString("PatientFullName"),
                    rs.getInt("PatientPhoneNumber"),
                    rs.getString("DateOfBirth"),
                    rs.getString("PatientAddress"),
                    rs.getString("Gender"),
                    rs.getString("PatientEmail"),
                    rs.getString("PatientUsername"),
                    rs.getString("PatientPassword")
            );
            patientList.add(patient);
        }
        st.close();
        connection.close();
        return patientList;
    }
    
    public boolean deletePatient(int patientID) throws ClassNotFoundException, SQLException {
        Connection connection = getConnection();
        String query = "DELETE FROM patient WHERE PatientID = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, patientID);
        int result = ps.executeUpdate();
        ps.close();
        connection.close();
        return result > 0;
    }
}



