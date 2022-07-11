package eu.morozik.historicalplaces.service.impl;

import eu.morozik.historicalplaces.dto.GeneralObjectDto;
import eu.morozik.historicalplaces.service.AttractionService;
import eu.morozik.historicalplaces.service.CountryService;
import eu.morozik.historicalplaces.service.GlobalService;
import eu.morozik.historicalplaces.service.SettlementService;
import eu.morozik.historicalplaces.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

@Service
@RequiredArgsConstructor
public class GlobalServiceImpl implements GlobalService {

    private final AttractionService attractionService;
    private final CountryService countryService;
    private final SettlementService settlementService;
    private final UserService userService;

    @SneakyThrows
    @Override
    public GeneralObjectDto findEntityByName(String name) {

        ForkJoinPool fjp = new ForkJoinPool();
        Set<Callable<GeneralObjectDto>> queries = new HashSet<>();

        queries.add(() -> attractionService.searchAsGlobal(name));
        queries.add(() -> countryService.searchAsGlobal(name));
        queries.add(() -> settlementService.searchAsGlobal(name));
        queries.add(() -> userService.searchAsGlobal(name));

        List<Future<GeneralObjectDto>> futures = fjp.invokeAll(queries);
        fjp.shutdown();

        for (Future<GeneralObjectDto> future : futures) {
            if (future.get() != null)
                return future.get();
        }

        return null;
    }
}
