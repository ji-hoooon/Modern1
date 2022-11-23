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


@Component
public class MessageRepository {
    //데이터 소스에서 DB 연결을 얻을 수 있도록 스프링에 인스턴스 주입 요청
    private DataSource dataSource;
    public MessageRepository(DataSource dataSource){
        this.dataSource=dataSource;
    }

    //log 인스턴스 생성
    private static final Log logger = LogFactory.getLog(MessageRepository.class);

    //Message 객체를 매개변수로 받는 저장 메서드 생성 -> DB 연결 후설정
    public Message saveMessage(Message message) {
        Connection c = DataSourceUtils.getConnection(dataSource);
        try {
            String insertSql = "INSERT INTO messages (`id`, `text`, `created_date`) VALUE (null, ?, ?)";
            PreparedStatement ps = c.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
            // SQL에 필요한 매개변수를 준비한다.
            ps.setString(1, message.getText());
            ps.setTimestamp(2, new Timestamp(message.getCreatedDate().getTime()));
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                // 새로 저장된 메시지 id 가져오기
                ResultSet result = ps.getGeneratedKeys();
                if (result.next()) {
                    int id = result.getInt(1);
                    return new Message(id, message.getText(), message.getCreatedDate());
                } else {
                    logger.error("Failed to retrieve id. No row in result set");
                    return null;
                }
            } else {
                // insert 실패
                return null;
            }
        } catch (SQLException ex) {
            logger.error("Failed to save message", ex);
            try {
                c.close();
            } catch (SQLException e) {
                logger.error("Failed to close connection", e);
            }
        } finally {
            DataSourceUtils.releaseConnection(c, dataSource);
        }
        return null;
    }
}

