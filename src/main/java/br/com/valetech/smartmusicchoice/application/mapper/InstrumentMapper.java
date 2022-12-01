package br.com.valetech.smartmusicchoice.application.mapper;

import br.com.valetech.smartmusicchoice.application.dto.InstrumentDTO;
import br.com.valetech.smartmusicchoice.domain.Instrument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "cdi",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface InstrumentMapper {


    @Mapping(source = "name", target = "name")
    Instrument toInstrument(InstrumentDTO instrumentDTO);
    InstrumentDTO toInstrumentDTO(Instrument instrument);
    List<InstrumentDTO> toInstrumentDTOList(List<Instrument> instrument);

}
