package com.franklin.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.franklin.dao.User;


public class DbUtil {
	private PreparedStatement psmt = null;
	private ResultSet rs = null;
	/***
	 * 用于校验用户是否合法
	 * @param user
	 * @return
	 */
	public User checkUser(User user) {
		User retUser = null;
		if(user==null) {
			return retUser;
		}
		Connection conn = DbCon.getInstance().getConnection();
		String sql = "select * from user where username = ? and password = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, user.getUsername());
			psmt.setString(2, user.getPassword());
			rs = psmt.executeQuery();
			while(rs.next()) {
				String username = rs.getString("username");
				String password = rs.getString("password");
				if(username!=null&&password!=null) {
					retUser = new User(username, password);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(psmt!=null) {
				try {
					psmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			DbCon.getInstance().closeConnection(conn);
			return retUser;
		}
	}
	
	/***
	 * 添加用户
	 * @param user
	 * @return
	 */
	public boolean addUser(User user) {
		boolean addFlag = false;
		Connection conn = DbCon.getInstance().getConnection();
		if(user==null) {
			return addFlag;
		}
		String username = user.getUsername();
		String password = user.getPassword();
		String sql = "insert into user (username,password) values(?,?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, username);
			psmt.setString(2, password);
			int cnt = psmt.executeUpdate();
			if(cnt>0) {
				addFlag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(psmt!=null) {
				try {
					psmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			DbCon.getInstance().closeConnection(conn);
			return addFlag;
		}
	}
	
	/***
	 * 删除用户
	 * @param user
	 * @return
	 */
	public boolean delUser(User user) {
			boolean delFlag = false;
			if(user==null) {
				return delFlag;
			}
			Connection conn = DbCon.getInstance().getConnection();
			String sql = "delete from user where username = ? and password = ?";
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, user.getUsername());
				psmt.setString(2, user.getPassword());
				int cnt = psmt.executeUpdate();
				if(cnt>0) {
					delFlag = true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if(rs!=null) {
					try {
						rs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(psmt!=null) {
					try {
						psmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				DbCon.getInstance().closeConnection(conn);
				return delFlag;
			}
	}
	
	/***
	 * 判断某个用户名是否已经存在
	 * @param user
	 * @return
	 */
	public boolean checkExistUser(User user) {
		boolean existFlag = false;
		if(user==null) {
			return existFlag;
		}
		Connection conn = DbCon.getInstance().getConnection();
		String sql = "select count(*) from user where username = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, user.getUsername());
			rs = psmt.executeQuery();
			while(rs.next()) {
				int cnt = rs.getInt(1);
				if(cnt>0) {
					existFlag = true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(psmt!=null) {
				try {
					psmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			DbCon.getInstance().closeConnection(conn);
			return existFlag;
		}
	}
	
	public List<User> getUserByPage(int pageNow,int pageSize) {
		List<User> users = new ArrayList<User>();
		if(pageNow<1||pageSize<1) {
			return null;
		}
		Connection conn = DbCon.getInstance().getConnection();
		String sql = "select * from user limit ?,?"; //从第一个？+1  到 第二个？ 行的数据
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, (pageNow-1)*pageSize);
			psmt.setInt(2, pageSize);
			rs = psmt.executeQuery();
			while(rs.next()) {
				String username = rs.getString("username");
				String password = rs.getString("password");
				if(username!=null&&password!=null) {
					User user = new User(username, password);
					users.add(user);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(psmt!=null) {
				try {
					psmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			DbCon.getInstance().closeConnection(conn);
			return users;
		}
	}
	
	public int getUserPageCnt(int pageSize) {
		int pageCnt = 0;
		if(pageSize<1) {
			return 0;
		}
		Connection conn = DbCon.getInstance().getConnection();
		String sql = "select count(*) from user";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				 int userCnt = rs.getInt(1);
				 pageCnt = ((userCnt-1)/pageSize)+1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(psmt!=null) {
				try {
					psmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			DbCon.getInstance().closeConnection(conn);
			return pageCnt;
		}
	}
}
