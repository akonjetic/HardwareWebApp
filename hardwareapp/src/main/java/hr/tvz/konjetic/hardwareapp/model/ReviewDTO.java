package hr.tvz.konjetic.hardwareapp.model;

public class ReviewDTO {

    String title;
    String text;
    Integer rating;

    public ReviewDTO(String title, String text, Integer rating) {
        this.title = title;
        this.text = text;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public Integer getRating() {
        return rating;
    }
}
