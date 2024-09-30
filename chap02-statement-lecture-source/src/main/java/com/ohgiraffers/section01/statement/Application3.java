package com.ohgiraffers.section01.statement;

import com.ohgiraffers.model.dto.EmployeeDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application3 {

    public static void main(String[] args) {

        /* title. Scanner 사용해서 사번을 입력받고, 해당 사번의 사원 정보를
        *       EmployeeDTO 를 통해 객체에 담아서 출력
        * */

        Connection con = getConnection();

        Statement stmt = null;

        ResultSet rset = null;

        EmployeeDTO emp = null;

        Scanner sc = new Scanner(System.in);
        System.out.print("조회하실 사번을 입력해주세요 : ");
        String empId = sc.nextLine();

        String query = "SELECT * FROM EMPLOYEE WHERE EMP_ID = '" + empId + "'";

        try {
            stmt = con.createStatement();

            rset = stmt.executeQuery(query);

            /* 조회한 결과를 객체에 담기 */
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
        } finally {
            close(rset);
            close(stmt);
            close(con);
        }

        System.out.println("emp = " + emp);




        /* comment. */
        /* index. */






    }
}
/* title. */
/* comment. */
/* index. */