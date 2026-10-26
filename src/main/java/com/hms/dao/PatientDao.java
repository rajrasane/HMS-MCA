package com.hms.dao;
import com.hms.model.Patient;
import java.sql.*; import java.time.LocalDate; import java.util.*;
public class PatientDao {
 private final Connection c; public PatientDao(Connection c){this.c=c;}
 private Patient row(ResultSet r)throws SQLException{return new Patient(r.getInt("id"),r.getString("name"),r.getDate("date_of_birth").toLocalDate(),r.getString("gender"),r.getString("blood_group"),r.getString("phone"),r.getString("email"),r.getString("address"),r.getString("emergency_contact"));}
 public List<Patient> find(String q)throws SQLException{List<Patient> x=new ArrayList<>();String s="SELECT * FROM patients WHERE CAST(id AS CHAR) LIKE ? OR name LIKE ? OR phone LIKE ? OR email LIKE ? ORDER BY name";try(PreparedStatement p=c.prepareStatement(s)){String qv="%"+(q==null?"":q.trim())+"%";for(int i=1;i<5;i++)p.setString(i,qv);try(ResultSet r=p.executeQuery()){while(r.next())x.add(row(r));}}return x;}
 public List<Patient> findAll()throws SQLException{return find("");}
 public void save(Patient p)throws SQLException{String s="INSERT INTO patients(name,date_of_birth,gender,blood_group,phone,email,address,emergency_contact) VALUES(?,?,?,?,?,?,?,?)";try(PreparedStatement x=c.prepareStatement(s)){set(x,p);x.executeUpdate();}}
 public void update(Patient p)throws SQLException{String s="UPDATE patients SET name=?,date_of_birth=?,gender=?,blood_group=?,phone=?,email=?,address=?,emergency_contact=? WHERE id=?";try(PreparedStatement x=c.prepareStatement(s)){set(x,p);x.setInt(9,p.id());x.executeUpdate();}}
 private void set(PreparedStatement x,Patient p)throws SQLException{x.setString(1,p.name());x.setDate(2,java.sql.Date.valueOf(p.dateOfBirth()));x.setString(3,p.gender());x.setString(4,p.bloodGroup());x.setString(5,p.phone());x.setString(6,p.email());x.setString(7,p.address());x.setString(8,p.emergencyContact());}
 public void delete(int id)throws SQLException{try(PreparedStatement x=c.prepareStatement("DELETE FROM patients WHERE id=?")){x.setInt(1,id);x.executeUpdate();}}
}
