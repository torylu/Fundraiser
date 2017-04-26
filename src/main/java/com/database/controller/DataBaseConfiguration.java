package com.database.controller;

import com.database.mapper.ContributionMapper;
import com.database.mapper.RegisterMapper;
import com.database.mapper.ServiceMapper;
import org.apache.ibatis.datasource.pooled.PooledDataSourceFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.datasource.pooled.PooledDataSourceFactory;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.io.File;
import java.lang.reflect.Proxy;
import java.util.Properties;

/**
 * Created by Tory on 4/13/17.
 */
public class DataBaseConfiguration {


    private final static String PREFIX = "com.line.core.Dao.";
    private final static String DEVELOPMENT_PATH = "./src/main/java/com/line/core/Dao";
    private final static String PRODUCTION_PATH = "./WEB-INF/classes/com/line/core/DAO";

    private static volatile SqlSessionFactory sqlSessionFactory = null;

    private DataBaseConfiguration(){}

    public static SqlSessionFactory getInstance(){
        Proxy proxy;
        if (sqlSessionFactory == null) {
            synchronized(DataBaseConfiguration.class) {
                if (sqlSessionFactory == null) {
                    configure();
                    registry();
                }
            }
        }
        return sqlSessionFactory;
    }

    private static void configure(){
        Properties properties = new Properties();
        properties.setProperty("driver","com.mysql.jdbc.Driver");
        properties.setProperty("url","jdbc:mysql://localhost:3306/fundraiser");
        properties.setProperty("username", "root");
        properties.setProperty("password", "qwe123789");
        PooledDataSourceFactory pooledDataSourceFactory = new PooledDataSourceFactory();
        pooledDataSourceFactory.setProperties(properties);
        DataSource dataSource = pooledDataSourceFactory.getDataSource();
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
    }

    private static void registry(){
//        File dir = new File(PRODUCTION_PATH);
//        for(final File file : dir.listFiles()){
//            try {
//
//                Class clazz = Class.forName(PREFIX + file.getName().split("\\.")[0]);
//                sqlSessionFactory.getConfiguration().addMapper(clazz);
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
        sqlSessionFactory.getConfiguration().addMapper(ContributionMapper.class);
        sqlSessionFactory.getConfiguration().addMapper(RegisterMapper.class);
        sqlSessionFactory.getConfiguration().addMapper(ServiceMapper.class);
    }
}
