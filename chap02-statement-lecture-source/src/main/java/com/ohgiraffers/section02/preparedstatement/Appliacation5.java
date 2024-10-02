package com.ohgiraffers.section02.preparedstatement;

import com.ohgiraffers.model.dto.EmployeeDTO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Appliacation5 {
    public static void main(String[] args) {

        /* index. 1. EMPLOYEE 테이블에서 조회할 사원의 성씨를 입력받아
         *       해당하는 성을 가지고 있는 사원의 정보를 모두 출력 */

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        EmployeeDTO emp = null;
        List<EmployeeDTO> empList = null;

        Scanner sc = new Scanner(System.in);
        System.out.print("조회하실 사원의 성을 입력해주세요 : ");
        String empName = sc.nextLine();

        // 프로퍼리 파일에 SQL 작성
        Properties prop = new Properties();

        try {

            prop.loadFromXML(
                    new FileInputStream("src/main/java/com/ohgiraffers/section02/preparedstatement/employee-query.xml"));

            String query = prop.getProperty("selectByFamilyName");
            System.out.println("query = " + query);

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, empName);
            rset = pstmt.executeQuery();
            empList = new ArrayList<>();

            while (rset.next()) {
                emp = new EmployeeDTO();

                emp.setEmpID(rset.getString("EMP_ID"));
                emp.setEmpName(rset.getString("EMP_NAME"));
                emp.setEmpNo(rset.getString("EMP_NO"));
                emp.setEmail(rset.getString("EMAIL"));
                emp.setPhone(rset.getString("PHONE"));
                emp.setDeptCode(rset.getString("Dept_Code"));
                emp.setJobCode(rset.getString("Job_Code"));
                emp.setSalLevel(rset.getString("Sal_Level"));
                emp.setSalary(rset.getInt("Salary"));
                emp.setBonus(rset.getDouble("Bonus"));
                emp.setHireDate(rset.getDate("HIRE_DATE"));
                emp.setEntDate(rset.getDate("ENT_DATE"));
                emp.setEntYn(rset.getString("ENT_YN"));

                empList.add(emp);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InvalidPropertiesFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(con);
            close(pstmt);
        }

        for (EmployeeDTO empDTO : empList){
            System.out.println("empDTO = " + empDTO);
        }

    }
}
/* title. */
/* comment. */
/* index. */
