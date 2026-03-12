package api.api.model;


import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "notifications")
@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name="user_id" ,nullable=false)
    private User user;
    @Enumerated(EnumType.STRING)
    private Channel channel;
    private String recipient;
    private String message;
    @Enumerated(EnumType.STRING)
    private Priority priority;
    private LocalDateTime date;



    public Notification(User user,Channel channel,String recipient,String message,Priority priority ){

        this.user=user;
        this.channel=channel;
        this.recipient=recipient;
        this.message=message;
        this.priority=priority;
        this.date= LocalDateTime.now();

    }    
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public Channel getChannel() {
        return channel;
    }
    public void setChannel(Channel channel) {
        this.channel = channel;
    }
    public String getRecipient() {
        return recipient;
    }
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Priority getPriority() {
        return priority;
    }
    public void setPriority(Priority priority) {
        this.priority = priority;
    }
    public LocalDateTime getData() {
        return LocalDateTime.now();
    }
 
}
