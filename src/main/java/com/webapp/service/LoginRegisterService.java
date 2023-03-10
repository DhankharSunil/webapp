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
		String sql = "INSERT INTO T_USER_DETAILS (FIRSTNAME, LASTNAME, EMAILID, PASSWORD,MOBILENO,DISTRICT,STATES,FLAG) VALUES (?,?,?,?,?,?,?,'Y')";
		return jdbcTemplate.execute(sql, new PreparedStatementCallback<Boolean>() {
			public Boolean doInPreparedStatement(PreparedStatement ps)  {
				boolean insertStatus = false;
				try {
					ps.setString(1, user.getFname());
					ps.setString(2, user.getLname());
					ps.setString(3, user.getUname());
					ps.setString(4, user.getPass());
					ps.setString(5, user.getMobileNo());
					ps.setString(6, user.getDistrict());
					ps.setString(7, user.getState());
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
	
	public boolean emailpassCheck(final UserLoginRegister user) {
		String sql = "select * from T_USER_DETAILS where EMAILID = ? AND PASSWORD=? AND FLAG = ?";
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
	
	public UserLoginRegister getpassDetails(final UserLoginRegister login) {
		String sql="select * from T_USER_DETAILS where EMAILID = ? and PASSWORD = ? AND FLAG = ?";
		return jdbcTemplate.query(sql,new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps)  {
				try {
				ps.setString(1, login.getUname());
				ps.setString(2, login.getPass());
				ps.setString(3, "Y");
				}catch(Exception e) {System.out.println("Exception getpassDetails:"+e);}
			}
		}, new ResultSetExtractor<UserLoginRegister>() {
			public UserLoginRegister extractData(ResultSet rs)  {
				UserLoginRegister reLogin=new UserLoginRegister();
				try {
					if(rs.next()) {
						reLogin.setFname(rs.getString("FIRSTNAME"));
						reLogin.setLname(rs.getString("LASTNAME"));
						reLogin.setUname(rs.getString("EMAILID"));
						reLogin.setPass(rs.getString("PASSWORD"));
						reLogin.setMobileNo(rs.getString("MOBILENO"));
						reLogin.setDistrict(rs.getString("DISTRICT"));
						reLogin.setState(rs.getString("STATES"));
					}
				}catch(Exception e) {System.out.println("Exception getpassDetails:"+e);}
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
					case 3:
						String vsTeam = formatter.formatCellValue(nextCell);
						ipl.setVsmatch(vsTeam);
						break;
					case 4:
						String city = formatter.formatCellValue(nextCell);
						ipl.setCityPlace(city);
						break;
					}				
				}
				iplAll.add(ipl);
			}
		return iplAll;
	}
	public int[] uploadExcelintoTable(List<IplData> ipl,String userMail) {
		String sql = "INSERT INTO T_IPL_DATA(MATCH_DATE,MATCH_TIME,HOME_TEAM,AWAY_TEAM,CITY,UPDATE_BY,FROM_YEAR) VALUES(?,?,?,?,?,?,'2023')";
		return jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				IplData ipldata = ipl.get(i);
				try {
					ps.setString(1, ipldata.getDate());
					ps.setString(2, ipldata.getTime());
					ps.setString(3, ipldata.getMatch());
					ps.setString(4, ipldata.getVsmatch());
					ps.setString(5, ipldata.getCityPlace());
					ps.setString(6, userMail);
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
	
	public IplData getmatchdetails(final IplData ipl) {
		String sql="SELECT DISTINCT MATCH_DATE,MATCH_TIME,HOME_TEAM,AWAY_TEAM,CITY \r\n"
				+ "FROM T_IPL_DATA WHERE FROM_YEAR = ? AND CITY LIKE DECODE (?,NULL,'%',?)"
				+ "AND HOME_TEAM LIKE DECODE (?,NULL,'%',?);";
		return jdbcTemplate.query(sql,new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps)  {
				try {
				ps.setString(1, "2023");
				ps.setString(2, ipl.getCityPlace());
				ps.setString(3, ipl.getCityPlace());
				ps.setString(4, ipl.getMatch());
				ps.setString(5, ipl.getMatch());
				}catch(Exception e) {System.out.println("Exception getmatchdetails : "+e);}
			}
		}, new ResultSetExtractor<IplData>() {
			public IplData extractData(ResultSet rs)  {
				IplData ipldata = new IplData();
				try {
					if(rs.next()) {
						ipldata.setDate(rs.getString("MATCH_DATE"));
						ipldata.setTime(rs.getString("MATCH_TIME"));
						ipldata.setMatch(rs.getString("HOME_TEAM"));
						ipldata.setVsmatch(rs.getString("AWAY_TEAM"));
						ipldata.setCityPlace(rs.getString("CITY"));
					}
				}catch(Exception e) {System.out.println("Exception getmatchdetails : "+e);}
				return ipldata;
			}
		});
	}
	
	public List<IplData> getcricketdata(final String yearfrom) {
		String sql="select * from T_IPL_DATA where FROM_YEAR = ?";
			return jdbcTemplate.query(sql,new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps)   {
					try {
						ps.setString(1, yearfrom);
					} catch (Exception e) {
						System.out.println("Got error in injecting parameters into sql query in --getcricketdata() "+e);
					}
				}
			},new ResultSetExtractor<List<IplData>>() {

				public List<IplData> extractData(ResultSet rs)    {
						
					List<IplData> voucherAuditTrails=new ArrayList<IplData>();
					try {		
					while(rs.next())
					{
						IplData ipldata=new IplData();
						try {
							ipldata.setDate(rs.getString("MATCH_DATE"));
							ipldata.setTime(rs.getString("MATCH_TIME"));
							ipldata.setMatch(rs.getString("HOME_TEAM"));
							ipldata.setVsmatch(rs.getString("AWAY_TEAM"));
							ipldata.setCityPlace(rs.getString("CITY"));
							voucherAuditTrails.add(ipldata);
						} catch (Exception e) {
							System.out.println("Got error in fetching result from DB in -- getcriketdata1()"+e);
						}
					}
					}catch(Exception e){System.out.println("Exceptin :"+e);}


				return voucherAuditTrails;
				}
			});
		}
}