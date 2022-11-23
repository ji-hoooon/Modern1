package app.sample.messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    //리포지토리 의존성 주입
    private MessageRepository repository;



//    //리포지토리 인스턴스를 매개변수로 하는 생성자 생성해 의존성 연결
//    public MessageService (MessageRepository repository){
//        this.repository=repository;
//    }
    //리포지토리에 저장하는 메서드
    public void save(String text){
        this.repository.saveMessage(new Message(text));
    }
}
