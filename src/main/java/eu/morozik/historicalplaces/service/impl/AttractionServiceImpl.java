package eu.morozik.historicalplaces.service.impl;

import eu.morozik.historicalplaces.dao.AddressDao;
import eu.morozik.historicalplaces.dao.AttractionDao;
import eu.morozik.historicalplaces.dao.ReviewDao;
import eu.morozik.historicalplaces.dao.projection.view.AttractionView;
import eu.morozik.historicalplaces.dto.attractiondto.AttractionDto;
import eu.morozik.historicalplaces.dto.attractiondto.AttractionWithRelationIdsDto;
import eu.morozik.historicalplaces.exception.NotFoundException;
import eu.morozik.historicalplaces.model.Address;
import eu.morozik.historicalplaces.model.Attraction;
import eu.morozik.historicalplaces.model.Review;
import eu.morozik.historicalplaces.service.AttractionService;
import eu.morozik.historicalplaces.utils.MapperUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AttractionServiceImpl implements AttractionService {

    private final AttractionDao attractionDao;
    private final AddressDao addressDao;
    private final ReviewDao reviewDao;
    private final ModelMapper modelMapper;
    private final MapperUtil mapperUtil;

    @Transactional
    @Override
    public AttractionDto save(AttractionWithRelationIdsDto attractionWithRelationIdsDto) {
        Attraction response = attractionDao.save(reassignment(attractionWithRelationIdsDto));
        return modelMapper.map(response, AttractionDto.class);
    }

    @Transactional(readOnly = true)
    @Override
    public AttractionDto findById(Long id) {
        Attraction attraction = attractionDao.findById(id)
                .orElseThrow(() -> {
                    NotFoundException notFoundException = new NotFoundException(id);
                    log.error(notFoundException.getLocalizedMessage());
                    return notFoundException;
                });
        return modelMapper.map(attraction, AttractionDto.class);
    }

    @Transactional(readOnly = true)
    @Override
    public List<AttractionDto> findAll(int page, int size, String name) {
        Pageable pages = PageRequest.of(page, size, Sort.by(name));
        Page<Attraction> attractions = attractionDao.findAll(pages);
        return (List<AttractionDto>) mapperUtil.map(attractions.getContent(), AttractionDto.class);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        attractionDao.deleteSimilarPlaces(id);
        attractionDao.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public AttractionView findByName(String name) {
        return attractionDao.findByName(name);
    }

    public Attraction reassignment(AttractionWithRelationIdsDto attractionWithRelationIdsDto) {
        final Attraction attraction = modelMapper.map(attractionWithRelationIdsDto, Attraction.class);

        Address address = addressDao.findById(attractionWithRelationIdsDto.getAddress())
                .orElseThrow(() -> {
                    NotFoundException notFoundException = new NotFoundException(attractionWithRelationIdsDto
                            .getAddress());
                    log.error(notFoundException.getLocalizedMessage());
                    return notFoundException;
                });
        attraction.setAddress(address);

        Set<Review> reviews = attractionWithRelationIdsDto.getReviewIds()
                .stream()
                .map(id -> reviewDao.findById(id)
                        .orElseThrow(() -> {
                            NotFoundException notFoundException = new NotFoundException(id);
                            log.error(notFoundException.getLocalizedMessage());
                            return notFoundException;
                        }))
                .collect(Collectors.toSet());
        attraction.setReviews(reviews);

        Set<Attraction> attractions = attractionWithRelationIdsDto.getAttractionIds()
                .stream()
                .map(id -> attractionDao.findById(id)
                        .orElseThrow(() -> {
                            NotFoundException notFoundException = new NotFoundException(id);
                            log.error(notFoundException.getLocalizedMessage());
                            return notFoundException;
                        }))
                .collect(Collectors.toSet());
        attraction.setSimilarAttractions(attractions);

        return attraction;
    }
}
