package com.webapp.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import com.web.application.IplData;
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
		String sql = "select * from T_USER_DETAILS where EMAILID = ? AND FLAG = ?";
		return jdbcTemplate.execute(sql, new PreparedStatementCallback<Boolean>() {
			public Boolean doInPreparedStatement(PreparedStatement ps) {
				boolean emailStatus = false;
			try {
				ps.setString(1, user.getUname());
				ps.setString(2, "Y");
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
	
	public boolean checkAccountExist(UserLoginRegister user) {
		String sql = "select * from T_USER_DETAILS where EMAILID = ? AND FLAG = ?";
		return jdbcTemplate.execute(sql, new PreparedStatementCallback<Boolean>() {
			public Boolean doInPreparedStatement(PreparedStatement ps) {
				boolean emailStatus = false;
			try {
				ps.setString(1, user.getUname());
				ps.setString(2, "Y");
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
		String sql = "select * from T_USER_DETAILS where EMAILID = ? and PASSWORD = ? AND FLAG = ?";
		return jdbcTemplate.execute(sql, new PreparedStatementCallback<Boolean>() {
			public Boolean doInPreparedStatement(PreparedStatement ps) {
				boolean emailStatus = false;
			try {
				ps.setString(1, user.getUname());
				ps.setString(2, user.getPass());
				ps.setString(3, "Y");
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
		String sql="select * from T_USER_DETAILS where EMAILID = ? AND FLAG = ?";
		return jdbcTemplate.query(sql,new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps)  {
				try {
				ps.setString(1, login.getUname());
				ps.setString(2, "Y");
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
	public boolean deleteAccountid(UserLoginRegister user) {
		String sql = "UPDATE T_USER_DETAILS SET FLAG = ?\r\n"
				+ "WHERE EMAILID=?";
		return jdbcTemplate.execute(sql, new PreparedStatementCallback<Boolean>() {
			public Boolean doInPreparedStatement(PreparedStatement ps) {
				boolean emailStatus = false;
			try {
				ps.setString(1, "N");
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
	
	public List<IplData> readExcel(final File file,final String fileType) throws IOException, ParseException {
		List<IplData> iplAll = new ArrayList<IplData>();	
		FileInputStream inputStream = new FileInputStream(file);
		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = firstSheet.iterator();
		DataFormatter formatter = new DataFormatter();
		rowIterator.next();
			while (rowIterator.hasNext()) {
				IplData ipl = new IplData();
				Row nextRow = rowIterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();

				while (cellIterator.hasNext()) {
					Cell nextCell = cellIterator.next();				
					int columnIndex = nextCell.getColumnIndex();				
					switch (columnIndex) {
					case 0:
						String date = formatter.formatCellValue(nextCell);
						ipl.setDate(date);
						break;
					case 1:
						String time = formatter.formatCellValue(nextCell);
						ipl.setTime(time);
						break;
					case 2:
						String Team = formatter.formatCellValue(nextCell);
						ipl.setMatch(Team);
						break;
					}				
				}
				iplAll.add(ipl);
			}
		return iplAll;
	}
	public int[] uploadExcelintoTable(List<IplData> ipl) {
		String sql = "INSERT INTO T_IPL_DATA (DATE_OF_MATCH,TIME_OF_MATCH,MATCH,YEAR_OF) VALUES (?,?,?,'2023')";
		return jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				IplData ipldata = ipl.get(i);
				try {
					ps.setString(1, ipldata.getDate());
					ps.setString(2, ipldata.getTime());
					ps.setString(3, ipldata.getMatch());
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			@Override
			public int getBatchSize() {
				return ipl.size();
			}
		});
	}
}