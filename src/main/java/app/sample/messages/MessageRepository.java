package app.sample.messages;


import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;


@Repository
public class MessageRepository {
    //데이터 소스에서 DB 연결을 얻을 수 있도록 스프링에 인스턴스 주입 요청
    private DataSource dataSource;
    public MessageRepository(DataSource dataSource){
        this.dataSource=dataSource;
    }

    //log 인스턴스 생성
    private static final Log logger = LogFactory.getLog(MessageRepository.class);

}

