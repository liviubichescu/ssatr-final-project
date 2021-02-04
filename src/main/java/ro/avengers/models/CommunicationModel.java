package ro.avengers.models;

import java.io.Serializable;

public class CommunicationModel implements Serializable {

    private String message;

    public CommunicationModel() {
    }

    public CommunicationModel(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    

}
