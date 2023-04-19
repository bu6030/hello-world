package com.example.demo;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.boot.CommandLineRunner;

import java.io.*;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestScriptRunner implements CommandLineRunner {

    public static void main(String[] args) throws Exception {
        TestScriptRunner test = new TestScriptRunner();
        test.run("");
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            //Oracle版本
            Connection conn = getConnection();
            //MySQL版本
//            Connection conn = getMySQLConnection();
            ScriptRunner scriptRunner = new ScriptRunner(conn);
            // 设置字符集,不然中文乱码插入错误
            Resources.setCharset(Charset.forName("UTF-8"));
            scriptRunner.setEscapeProcessing(false);
            scriptRunner.setRemoveCRs(true);
            scriptRunner.setSendFullScript(false);
            scriptRunner.setAutoCommit(true);
            // 分隔符，还未验证具体功能
            scriptRunner.setFullLineDelimiter(false);
            // 每条命令间的分隔符，注意这个不建议用分号分隔
            // 因为SQL脚本中可以写存储过程，中间存在分号，导致存储过程执行失败
            scriptRunner.setDelimiter("&&");
            // 读取SQL文件路径获取SQL文件执行
            List<String> files = getFiles("TO_CHANGE_PATH");
            for (String file : files) {
                InputStream inputStream = new FileInputStream(new File(file));
                Reader reader = new InputStreamReader(inputStream, "utf-8");
                scriptRunner.runScript(reader);
            }
            scriptRunner.closeConnection();
            conn.close();
            System.out.println("sql脚本执行完毕");
        } catch (Exception e) {
            System.out.println("sql脚本执行发生异常");
            e.printStackTrace();
        }
    }

    // 获取某个目录下所有直接下级文件，不包括目录下的子目录的下的文件，所以不用递归获取
    public List<String> getFiles(String path) {
        List<String> files = new ArrayList<String>();
        File file = new File(path);
        File[] tempList = file.listFiles();

        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile() && tempList[i].getName().endsWith("sql")) {
                files.add(tempList[i].toString());
            }
        }
        return files;
    }

    // 获取ORACLE数据库Connection
    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.OracleDriver");
        //需要修改成你的数据库url，以及账号密码
        return DriverManager.getConnection(
                "ORACLE_URL",
                "USER_NAME", "PASSWORD");
    }
    // 获取MySQL数据库Connection
    private Connection getMySQLConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        //需要修改成你的数据库url，以及账号密码
        return DriverManager.getConnection(
                "jdbc:mysql://URL_TO_CHANGE:PORT_TO_CHANGE/DATABASE_TO_CHANGE",
                "USERNAME", "PASSWORD");
    }
}