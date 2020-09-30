package com.wang.code.jdbc;

import netscape.security.UserTarget;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;

public class JDBCUse {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/test";

    //  Database credentials -- 数据库名和密码自己修改
    static final String USER = "root";
    static final String PASS = "";

    static Connection conn;

    static {
        //STEP 2: Register JDBC driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static void main(String[] args) {

        update();

    }

    public static void createTable(){
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String sql = "CREATE TABLE student " +
                    "(id INTEGER not NULL, " +
                    " first VARCHAR(255), " +
                    " last VARCHAR(255), " +
                    " age INTEGER, " +
                    " PRIMARY KEY ( id ))";

            stmt.execute(sql);
            stmt.close();
            conn.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    conn.close();
            }catch(SQLException se){
            }// do nothing
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");


    }

    public static void query() {
        Statement stmt = null;
        try {
            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            conn.createStatement();
            String sql;
            sql = "SELECT id, first, last, age FROM student";
            ResultSet rs = stmt.executeQuery(sql);

            rs.afterLast();
            //STEP 5: Extract data from result set
            while (rs.previous()) {
                System.out.println(rs.getRow());
                //Retrieve by column name
                int id = rs.getInt("id");
                int age = rs.getInt("age");
                String first = rs.getString("first");
                String last = rs.getString("last");

                //Display values
                System.out.print("ID: " + id);
                System.out.print(", Age: " + age);
                System.out.print(", First: " + first);
                System.out.println(", Last: " + last);
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            System.out.println(se.getMessage());
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");

    }

    public static void update(){
        PreparedStatement stmt = null;
        try {
            conn.setAutoCommit(false);
            String SQL = "Update student SET age = ? WHERE age = ?";
            Savepoint savePoint1 = conn.setSavepoint("savePoint1");
            stmt = conn.prepareStatement(SQL);
            stmt.setInt(1, 25);
            stmt.setInt(2, 24);
            int i = stmt.executeUpdate();
            conn.rollback(savePoint1);
            conn.commit();
            System.out.println("complate..." + i);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }


    }

    public static void insert(){
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String sql = "INSERT INTO student " +
                    "VALUES (100, 'C++', 'Li', 18)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO student " +
                    "VALUES (101, 'Python', 'Py', 25)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO student " +
                    "VALUES (102, 'Ruby', 'Ru', 30)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO student " +
                    "VALUES(103, 'Java', 'Ja', 28)";
            stmt.executeUpdate(sql);
            System.out.println("Inserted records into the table...");
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }


    }


}
