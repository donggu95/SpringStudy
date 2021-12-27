package pool.dbcp;

import java.sql.DriverManager;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

//아파치에서 제공하는 DBCP 라이브러리를 이용하기
public class DBCPPool {
	private static DBCPPool instance;
	private String driver="com.mysql.jdbc.Driver";
	private String url="jdbc:mysql://localhost:3307/jsp?characterEncoding=utf-8";
	private String user="root"; 
	private String password="0000";
	
	private DBCPPool() throws Exception {
		init();
	}
	
	public void init() throws Exception {
	
		//새로운 커넥션을 생성할때 사용됨
		Class.forName(driver);
		ConnectionFactory connFactory=new DriverManagerConnectionFactory(url, user, password);
		
		//커넥션을 풀에 보관하는 역할수행( close 하면 db를 닫는게 아니라 풀로 돌려보냄)
		PoolableConnectionFactory poolableConnFactory=new PoolableConnectionFactory(connFactory, null);
	
		//커넥션풀의 설정정보 담는 객체
		GenericObjectPoolConfig poolConfig=new GenericObjectPoolConfig();
		poolConfig.setMinIdle(5);//유휴 커넥션 개수
		poolConfig.setMaxTotal(10);//최대 커넥션 수
		
		//커넥션풀 생성
		GenericObjectPoolConfig<PoolableConnection> connectionPool=null;
		connectionPool=new GenericObjectPoolConfig<PoolableConnection>(poolableConnFactory,poolConfig);
//		connectionPool=new GenericObjectPool<PoolableConnection>(poolableConnFactory, poolConfig); 
	
		//커넥션풀과 팩토리 연결
		poolableConnFactory.setPool(connectionPool);
		
		//드라이버 로드
		Class.forName("org.apache.commons.dbcp2.PoolingDriver");
	
		PoolingDriver poolingDriver=(PoolingDriver)DriverManager.getDriver("jdbc:apache:commons:dbcp:");
		poolingDriver.registerPool("mydbcp", connectionPool);
	}
	
	public static DBCPPool getInstance() throws Exception{
		if(instance==null) {
			instance = new DBCPPool();
		}
		return instance;
	}
}
	
