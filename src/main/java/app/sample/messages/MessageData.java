package app.sample.messages;

public class MessageData {
    //JSON으로 만들기 위한 클래스
    private String text;
    public String getText(){
        return this.text;
    }

    public void setText(String text){
        this.text=text;
    }
}
