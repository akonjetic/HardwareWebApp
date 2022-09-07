package hr.tvz.konjetic.hardwareapp.model;


import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("review")
@CrossOrigin(origins = "http://localhost:4200")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping
    public List<ReviewDTO> getAllReviews(){
        return reviewService.findAll();
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping(params = "code")
    public List<ReviewDTO> getReviewsByHardwareCode(@RequestParam String code){
        return reviewService.findAllByHardwareCode(code);
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("getreviews/{text}")
    public List<ReviewDTO> getReviewsByText(@PathVariable final String text){
        return reviewService.findAllByReviewText(text);
    }
}
