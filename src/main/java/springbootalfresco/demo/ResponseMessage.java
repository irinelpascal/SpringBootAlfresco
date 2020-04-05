package springbootalfresco.demo;

public class ResponseMessage {

    private Object object;


    public ResponseMessage(Object object) {
        this.object = object;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
