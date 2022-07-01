package eu.morozik.historicalplaces.controller;

import eu.morozik.historicalplaces.dto.CountGradeDto;
import eu.morozik.historicalplaces.dto.reviewdto.ReviewDto;
import eu.morozik.historicalplaces.dto.reviewdto.ReviewWithRelationIdsDto;
import eu.morozik.historicalplaces.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ReviewDto> save(@RequestBody ReviewWithRelationIdsDto reviewWithRelationIdsDto) {
        ReviewDto dto = reviewService.save(reviewWithRelationIdsDto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ReviewDto> findById(@PathVariable Long id) {
        ReviewDto dto = reviewService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<ReviewDto>> findAll(@RequestParam int page,
                                                   @RequestParam int size,
                                                   @RequestParam String name) {
        List<ReviewDto> reviews = reviewService.findAll(page, size, name);
        return ResponseEntity.ok(reviews);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ReviewDto> update(@RequestBody ReviewWithRelationIdsDto reviewWithRelationIdsDto) {
        ReviewDto dto = reviewService.save(reviewWithRelationIdsDto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        reviewService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/searchFirstGrade")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ReviewDto> findFirstByGrade(@RequestParam Long grade) {
        ReviewDto dto = reviewService.findFirstByGrade(grade);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/countByGradeEquals")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CountGradeDto> countByGradeEquals(@RequestParam Long grade) {
        CountGradeDto dto = reviewService.countByGradeEquals(grade);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/existsReviewByGrade")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Boolean> existsReviewByGrade(@RequestParam Long grade) {
        Boolean isExist = reviewService.existsReviewByGrade(grade);
        return ResponseEntity.ok(isExist);
    }
}
