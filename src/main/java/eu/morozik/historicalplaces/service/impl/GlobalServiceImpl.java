package eu.morozik.historicalplaces.service.impl;

import eu.morozik.historicalplaces.dao.AttractionDao;
import eu.morozik.historicalplaces.dao.SettlementDao;
import eu.morozik.historicalplaces.dao.UserDao;
import eu.morozik.historicalplaces.dto.GeneralObjectDto;
import eu.morozik.historicalplaces.service.AttractionService;
import eu.morozik.historicalplaces.service.CountryService;
import eu.morozik.historicalplaces.service.GlobalService;
import eu.morozik.historicalplaces.service.SettlementService;
import eu.morozik.historicalplaces.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

@Service
@RequiredArgsConstructor
@Slf4j
public class GlobalServiceImpl implements GlobalService {

    private final AttractionService attractionService;
    private final CountryService countryService;
    private final SettlementService settlementService;
    private final UserService userService;

    @SneakyThrows
    @Override
    public GeneralObjectDto findCountEntityByName(String name) {

        ForkJoinPool fjp = new ForkJoinPool();
        Set<Callable<ArrayList<GeneralObjectDto>>> queries = new HashSet<>();

        queries.add(() -> (ArrayList<GeneralObjectDto>) attractionService.searchAsGlobal(name));
        queries.add(() -> (ArrayList<GeneralObjectDto>) countryService.searchAsGlobal(name));
        queries.add(() -> (ArrayList<GeneralObjectDto>) settlementService.searchAsGlobal(name));
        queries.add(() -> (ArrayList<GeneralObjectDto>) userService.searchAsGlobal(name));

        List<Future<ArrayList<GeneralObjectDto>>> futures = fjp.invokeAll(queries);
        fjp.shutdown();

        for (Future<ArrayList<GeneralObjectDto>> future : futures) {
            for (GeneralObjectDto dto: future.get()) {
                if (dto.getName().equals(name))
                    return dto;
            }
        }
        return null;
    }
}
