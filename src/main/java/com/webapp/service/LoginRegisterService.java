package com.webapp.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import com.web.application.UserLoginRegister;

@Service
public class LoginRegisterService {
	@Autowired	
	private SimpleJdbcCall simpleJdbcCall;
	
	public SimpleJdbcCall getSimpleJdbcCall() {
		return simpleJdbcCall;
	}
	public void setSimpleJdbcCall(SimpleJdbcCall simpleJdbcCall) {
		this.simpleJdbcCall = simpleJdbcCall;
	}

	public JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public boolean insertuserdetails(final UserLoginRegister user) {
		String sql = "INSERT INTO T_USER_DETAILS (FIRSTNAME, LASTNAME, EMAILID, PASSWORD,FLAG) VALUES (?,?,?,?,'Y')";
		return jdbcTemplate.execute(sql, new PreparedStatementCallback<Boolean>() {
			public Boolean doInPreparedStatement(PreparedStatement ps)  {
				boolean insertStatus = false;
				try {
					ps.setString(1, user.getFname());
					ps.setString(2, user.getLname());
					ps.setString(3, user.getUname());
					ps.setString(4, user.getPass());
					if (ps.executeUpdate() > 0) {
						insertStatus = true;
					}
				} catch (Exception e) {
					System.out.println("SQL Exception "+e);
				}
				return insertStatus;
			}
		});
	}
	
	public boolean emailExistCheck(final UserLoginRegister user) {
		String sql = "select * from T_USER_DETAILS where EMAILID = ?";
		return jdbcTemplate.execute(sql, new PreparedStatementCallback<Boolean>() {
			public Boolean doInPreparedStatement(PreparedStatement ps) {
				boolean emailStatus = false;
			try {
				ps.setString(1, user.getUname());
				if(ps.executeUpdate() > 0) {
					emailStatus = true;
				}
			} catch (Exception e) {
				System.out.println("SQL email Check "+e);
			}
			return emailStatus;
			}
		});
	}
	
	public boolean getloginDetails(final UserLoginRegister user) {
		String sql = "select * from T_USER_DETAILS where EMAILID = ?";
		return jdbcTemplate.execute(sql, new PreparedStatementCallback<Boolean>() {
			public Boolean doInPreparedStatement(PreparedStatement ps) {
				boolean emailStatus = false;
			try {
				ps.setString(1, user.getUname());
				if(ps.executeUpdate() > 0) {
					emailStatus = true;
				}
			} catch (Exception e) {
				System.out.println("SQL email Check "+e);
			}
			return emailStatus;
			}
		});
	}
	
	public boolean updatepass(final UserLoginRegister user) {
		String sql = "UPDATE T_USER_DETAILS SET PASSWORD = ?\r\n"
				+ "WHERE EMAILID=?";
		return jdbcTemplate.execute(sql, new PreparedStatementCallback<Boolean>() {
			public Boolean doInPreparedStatement(PreparedStatement ps) {
				boolean emailStatus = false;
			try {
				ps.setString(1, user.getPass());
				ps.setNString(2, user.getUname());
				if(ps.executeUpdate() > 0) {
					emailStatus = true;
				}
			} catch (Exception e) {
				System.out.println("SQL email Check "+e);
			}
			return emailStatus;
			}
		});
	}
	
	public boolean getpassDetails(final UserLoginRegister user) {
		String sql = "select * from T_USER_DETAILS where EMAILID = ? and PASSWORD = ?";
		return jdbcTemplate.execute(sql, new PreparedStatementCallback<Boolean>() {
			public Boolean doInPreparedStatement(PreparedStatement ps) {
				boolean emailStatus = false;
			try {
				ps.setString(1, user.getUname());
				ps.setString(2, user.getPass());
				if(ps.executeUpdate() > 0) {
					emailStatus = true;
				}
			} catch (Exception e) {
				System.out.println("SQL email Check "+e);
			}
			return emailStatus;
			}
		});
	}
	
	public UserLoginRegister getUserCredentialsbyUserCode(final UserLoginRegister login) {
		String sql="select * from T_USER_DETAILS where EMAILID = ?";
		return jdbcTemplate.query(sql,new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps)  {
				try {
				ps.setString(1, login.getUname());
				}catch(Exception e) {System.out.println("Exception getUserCredentialsbyUserCode:"+e);}
			}
		}, new ResultSetExtractor<UserLoginRegister>() {
			public UserLoginRegister extractData(ResultSet rs)  {
				UserLoginRegister reLogin=new UserLoginRegister();
				try {
					if(rs.next()) {
						reLogin.setFlag(rs.getString("FLAG"));
						reLogin.setFname(rs.getString("FIRSTNAME"));
						reLogin.setLname(rs.getString("LASTNAME"));
						reLogin.setPass(rs.getString("PASSWORD"));;
					}
				}catch(Exception e) {System.out.println("Exception getUserCredentialsbyUserCode:"+e);}
				return reLogin;
			}
		});
	}
	
}