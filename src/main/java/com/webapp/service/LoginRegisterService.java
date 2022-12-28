package com.webapp.service;

import java.sql.PreparedStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
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
	
}