package hr.tvz.konjetic.hardwareapp.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Review{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long ID;
    String title;
    String text;
    Integer rating;

    @ManyToOne
    @JoinColumn(name="code_hardware", referencedColumnName="code")
    Hardware hardware;

    public Review() {}

    public Review(Long ID, String title, String text, Integer rating) {
        this.ID = ID;
        this.title = title;
        this.text = text;
        this.rating = rating;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
