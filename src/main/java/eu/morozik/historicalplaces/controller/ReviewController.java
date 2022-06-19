package eu.morozik.historicalplaces.controller;

import eu.morozik.historicalplaces.dto.CountGradeDto;
import eu.morozik.historicalplaces.dto.reviewdto.ReviewDto;
import eu.morozik.historicalplaces.dto.reviewdto.ReviewWithRelationIdsDto;
import eu.morozik.historicalplaces.exception.NotFoundException;
import eu.morozik.historicalplaces.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ReviewDto save(@RequestBody ReviewWithRelationIdsDto reviewWithRelationIdsDto) {
        return reviewService.save(reviewWithRelationIdsDto);
    }

    @GetMapping("/{id}")
    public ReviewDto findById(@PathVariable Long id) throws NotFoundException {
        return reviewService.findById(id);
    }

    @GetMapping
    public List<ReviewDto> findAll() {
        return reviewService.findAll();
    }

    @PutMapping
    public ReviewDto update(@RequestBody ReviewWithRelationIdsDto reviewWithRelationIdsDto) {
        return reviewService.save(reviewWithRelationIdsDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        reviewService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/searchFirstGrade")
    public ReviewDto findFirstByGrade(@RequestParam Long grade){
        return reviewService.findFirstByGrade(grade);
    }

    @GetMapping("/countByGradeEquals")
    public CountGradeDto countByGradeEquals(@RequestParam Long grade){
        return reviewService.countByGradeEquals(grade);
    }

    @GetMapping("/existsReviewByGrade")
    public boolean existsReviewByGrade(@RequestParam Long grade){
        return reviewService.existsReviewByGrade(grade);
    }
}
