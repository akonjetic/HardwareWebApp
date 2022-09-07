package hr.tvz.konjetic.hardwareapp.model;

import java.util.List;

public interface ReviewService {
    List<ReviewDTO> findAll();
    List<ReviewDTO> findAllByHardwareCode(String code);
    List<ReviewDTO> findAllByReviewText(String text);
}
