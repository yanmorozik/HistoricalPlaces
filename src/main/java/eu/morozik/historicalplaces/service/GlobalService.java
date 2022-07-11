package eu.morozik.historicalplaces.service;

import eu.morozik.historicalplaces.dto.GeneralObjectDto;

public interface GlobalService {
    GeneralObjectDto findEntityByName(String name);
}
