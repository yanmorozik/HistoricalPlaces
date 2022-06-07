package eu.morozik.historicalplaces.utils;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MapperUtil {

    private final ModelMapper modelMapper;

    public <X, T> Collection<X> map(Collection<T> collection, Class<X> castType){
        return collection
                .stream()
                .map(entity -> modelMapper.map(entity, castType))
                .collect(Collectors.toList());
    }
}