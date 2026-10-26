package com.hms.dao;
import com.hms.model.Doctor; import java.sql.*; import java.util.*; import java.math.BigDecimal;
public class DoctorDao {
 private final Connection c; public DoctorDao(Connection c){this.c=c;}
 public List<Doctor> find(String q)throws SQLException{List<Doctor>x=new ArrayList<>();String s="SELECT * FROM doctors WHERE name LIKE ? OR specialization LIKE ? OR department LIKE ? OR license_number LIKE ? ORDER BY name";try(PreparedStatement p=c.prepareStatement(s)){String v="%"+(q==null?"":q.trim())+"%";for(int i=1;i<5;i++)p.setString(i,v);try(ResultSet r=p.executeQuery()){while(r.next())x.add(new Doctor(r.getInt("id"),r.getString("name"),r.getString("specialization"),r.getString("department"),r.getString("license_number"),r.getString("phone"),r.getString("email"),r.getString("room"),r.getBigDecimal("consultation_fee"),r.getBoolean("available")));}}return x;}
 public List<Doctor> findAll()throws SQLException{return find("");}
 public void save(Doctor d)throws SQLException{String s="INSERT INTO doctors(name,specialization,department,license_number,phone,email,room,consultation_fee,available) VALUES(?,?,?,?,?,?,?,?,?)";try(PreparedStatement p=c.prepareStatement(s)){set(p,d);p.executeUpdate();}}
 public void update(Doctor d)throws SQLException{String s="UPDATE doctors SET name=?,specialization=?,department=?,license_number=?,phone=?,email=?,room=?,consultation_fee=?,available=? WHERE id=?";try(PreparedStatement p=c.prepareStatement(s)){set(p,d);p.setInt(10,d.id());p.executeUpdate();}}
 private void set(PreparedStatement p,Doctor d)throws SQLException{p.setString(1,d.name());p.setString(2,d.specialization());p.setString(3,d.department());p.setString(4,d.licenseNumber());p.setString(5,d.phone());p.setString(6,d.email());p.setString(7,d.room());p.setBigDecimal(8,d.consultationFee());p.setBoolean(9,d.available());}
 public void delete(int id)throws SQLException{try(PreparedStatement p=c.prepareStatement("DELETE FROM doctors WHERE id=?")){p.setInt(1,id);p.executeUpdate();}}
}
