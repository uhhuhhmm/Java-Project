import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcPro {

	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		
		//executeQuery()	-	select
		//executeUpdate()	-	insert,update,delete
		try {//DB에 연결이 됐는지 확인하는 작업
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, "scott", "tiger");
			stmt = con.createStatement();
			
			System.out.println("=======================");
			System.out.println("사 원 관 리 프 로 그 램");
			System.out.println("=======================");
			System.out.println("1. 사 원 등 록");
			System.out.println("2. 사 원 조 회 (전 체)");
			System.out.println("3. 사 원 조 회 (사 번)");
			System.out.println("4. 사 원 삭 제 (사 번)");
			System.out.println("5. 사 원 정 보 수 정 (사 번)");
			System.out.print("메뉴선택 >> ");
			
			Scanner sc = new Scanner(System.in);
			
			int choice;
			choice = sc.nextInt();
			String sql;
			String sql1;
			//next는 띄어쓰기 불가
			if(choice == 1) {
				System.out.println("===사 원===");
				//사번, 이름, 부서번호
				System.out.print("사번 입력 : ");
				int empno = sc.nextInt();
				sc.nextLine();
				System.out.print("이름 입력 : ");
				String ename = sc.nextLine();
				System.out.print("부서번호 입력 : ");
				int deptno = sc.nextInt();
				
				sql = "insert into emp02 (empno, ename, hiredate, deptno)"
						+ " values(" + empno + ",'" + ename + "',sysdate," +  deptno + ")";
				
				System.out.println(sql);
				
				int result = stmt.executeUpdate(sql);
				
				if(result <= 0){
					System.out.println("실패");
				}else {
					System.out.println("성공");
				}
				
			}else if(choice == 2) {
				System.out.println("===사 원 조 회===");
				sql = "select * from emp02";
				
				rs = stmt.executeQuery(sql);
				
				while(rs.next()) {
					int empno = rs.getInt("empno");
					String ename = rs.getString("ename");
					//Date hiredate = rs.getDate("hiredate");
					String hiredate = rs.getString("hiredate");
					
					System.out.println(empno + " " + ename + " " + hiredate);
				}
				
			}else if(choice == 3) { //사번을 입력받아서 사원 조회
				System.out.println("===사 원 조 회===");
				System.out.print("사번 입력 >> ");
				int empno = sc.nextInt();
				sql = "select * from emp02 where empno = " + empno;		
				
				rs = stmt.executeQuery(sql);
				
				if(rs.next() != false) {
					empno = rs.getInt("empno");
					String ename = rs.getString("ename");
					String hiredate = rs.getString("hiredate");
					
					System.out.println(empno + " " + ename + " " + hiredate);
				}
			}else if(choice == 4) {
				System.out.println("===사 원 삭 제===");
				System.out.println("사번 입력 >> ");
				int empno = sc.nextInt();
				sql = "delete from emp02 where empno = " + empno;
				
				int result = stmt.executeUpdate(sql);
				
				if(result <= 0){
					System.out.println("삭제 실패");
				}else {
					System.out.println("삭제 완료");
				}
			}else if(choice == 5) {
				//급여, 성과금, 부서번호
				System.out.println("===사 원 정 보 수 정===");
				System.out.println("사번 입력 >> ");
				int empno = sc.nextInt();
				
				System.out.println("급여 입력 : ");
				int sal = sc.nextInt();
				System.out.println("성과금 입력 : ");
				int comm = sc.nextInt();
				System.out.println("부서번호 입력 : ");
				int deptno = sc.nextInt();
				
				sql = "update emp02" + " set sal = " + sal + ", comm = " + comm + ", deptno = " + deptno + " " 
									 + "where empno = " + empno;
				
				int result = stmt.executeUpdate(sql);
				
				if(result <= 0){
					System.out.println("실패");
				}else {
					System.out.println("성공");
				}
			
			
			}
			
			
			
			
			
			//사번, 이름, 부서명 (컬럼을 다 지정해줘야함)
//			String sql = "select * from emp02 e, dept d"
//							+ "where e.deptno = d.deptno";
			
//			String sql2 = "insert into emp02"
//					+ " values (1111, 'AAA', 'CLERK', 7788, sysdate, 1000, null, 10)";
//			int result = stmt.executeUpdate(sql2);
			
//			String sql = "select * from emp02";
			//update
//			String sql3 = "update emp02"
//					+ " set ename = 'BBB'"
//					+ " where empno = 1111";
//			int result = stmt.executeUpdate(sql3);
						
//			if(result <= 0){
//				System.out.println("실패");
//			}else {
//				System.out.println("성공");
//			}
//			
//			ResultSet rs = stmt.executeQuery(sql);
//			
//			//임시테이블의 첫번째 위치
//			while(rs.next()) {
//				int empno = rs.getInt("empno");	//getInt(1) 번호를 써도 무방
//				String ename = rs.getString("ename");
//				//Date hiredate = rs.getDate("hiredate");
//				String hiredate = rs.getString("hiredate");
////				String job = rs.getString("job");
////				int mgr = rs.getInt("mgr");
////				int sal = rs.getInt("sal");
////				int comm = rs.getInt("comm");
////				int deptno = rs.getInt("deptno");
////				String dname = rs.getString("dname");
//				
//				System.out.println(empno + " " + ename + " " + hiredate);
//			}
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null)
					rs.close();
				if(rs != null)
					stmt.close();
				if(rs != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		
	}

}
