package com.ohgiraffers.section02.preparedstatement;

import com.ohgiraffers.model.dto.EmployeeDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;


public class Application3 {
    public static void main(String[] args) {

        /* title. */
        /* comment. */
        /* index. */

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        // 1명의 회원정보를 관리할 EmployeeDTO 사용
        EmployeeDTO emp = null;

        Scanner sc = new Scanner(System.in);
        System.out.print("조회하실 사번을 입력해주세요 : ");
        String empId = sc.nextLine();

        String query = "SELECT * FROM employee WHERE EMP_ID = ?";

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, empId);

            rset = pstmt.executeQuery();

            if (rset.next()) {
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
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(rset);
            close(con);
            close(pstmt);
        }

        System.out.println("emp = " + emp);

    }
}
/* title. */
/* comment. */
/* index. */