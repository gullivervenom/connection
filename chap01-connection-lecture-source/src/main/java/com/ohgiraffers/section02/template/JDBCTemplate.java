package com.ohgiraffers.section02.template;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCTemplate {


        /*title. jdbc 커넥션 정보를 하나의 툴로 만들어 필요한 곳에서 호출 */

        // 싱글톤
        public static Connection getConnection() {

            Connection con = null;

            Properties prop = new Properties();

            try {
                prop.load(new FileReader("src/main/java/com/ohgiraffers/config/connection-info.properties"));

                String driver = prop.getProperty("driver");
                String url = prop.getProperty("url");

                Class.forName(driver);

                con = DriverManager.getConnection(url, prop);

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } // finally close 하면 안된다.

            return con;

        }

        public static void close(Connection con) {

            // 비트연산자(&) > 좌항과 우항을 모두 비교, && 는 앞쪽이 false 이면 뒤쪽 확인을 하지 않음
            try {
                if (con != null & !con.isClosed()) {
                   con.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }


    }

/*title. */
/*comment. */
/*index. */
