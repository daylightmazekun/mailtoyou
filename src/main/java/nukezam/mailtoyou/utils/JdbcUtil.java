package nukezam.mailtoyou.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import nukezam.mailtoyou.dao.impl.GetCommentsImpl;

/** 
* @Package :nukezam.mailtoyou.dao
* @Title: JdbcUtil.java 
* @Package nukezam.mailtoyou.dao 
* @author zekun ma burmaing@gmail.com   
* @date 2018年1月8日 下午7:58:34 
* @version V1.0   
*/
public class JdbcUtil {
    //mysql数据库驱动，固定写法。连接Oracle时又与之不同,为："oracle.jdbc.driver.OracleDriver"  
    private static final String driver = "com.mysql.cj.jdbc.Driver";   
  
    private static final String url="jdbc:mysql://localhost:3306/wangyi2?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC";   
      
    private static final String username="root";//数据库的用户名  
    private static final String password="QQpp0000";//数据库的密码:这个是自己安装数据库的时候设置的，每个人不同。  
      
    private static Connection conn=null;  //声明数据库连接对象  
      
    //静态代码块负责加载驱动  
    static{  
        try{  
            Class.forName(driver);  
        }  
        catch(Exception ex){  
            ex.printStackTrace();  
        }  
    }  
      
    //单例模式返回数据库连接对象，供外部调用  
    public static Connection getConnection() throws Exception{  
        if(conn==null){  
            conn = DriverManager.getConnection(url, username, password); //连接数据库  
            return conn;  
        }  
        return conn;  
    }  
    public static void connClose(GetCommentsImpl getCommentsImpl) throws SQLException {
    	if (getCommentsImpl.getConnection() != null) getCommentsImpl.getConnection().close();
    	if (getCommentsImpl.getPreparedStatement() != null) getCommentsImpl.getPreparedStatement().close();
    	if (getCommentsImpl.getResultSet() != null) getCommentsImpl.getResultSet().close();
    }
    //写main方法测试是否连接成功，可将本类运行为Java程序先进行测试，再做后续的数据库操作。  
    public static void main(String[] args) {  
          
        try{  
           Connection conn = JdbcUtil.getConnection();  
           if(conn!=null){  
               System.out.println("数据库连接正常！");  
           }  
           else{  
               System.out.println("数据库连接异常！");  
           }  
        }  
        catch(Exception ex){  
            ex.printStackTrace();  
        }  
          
    }  
}
