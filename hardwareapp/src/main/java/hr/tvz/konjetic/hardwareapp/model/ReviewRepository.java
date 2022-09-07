package hr.tvz.konjetic.hardwareapp.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    //List<Review> findBy();

    List<Review> findAllByHardware_Code(String code);

    List<Review> findByTextContainingIgnoreCase(String text);
}
