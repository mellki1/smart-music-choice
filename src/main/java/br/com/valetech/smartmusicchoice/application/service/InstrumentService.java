package br.com.valetech.smartmusicchoice.application.service;

import br.com.valetech.smartmusicchoice.application.dto.InstrumentDTO;
import br.com.valetech.smartmusicchoice.application.mapper.InstrumentMapper;
import br.com.valetech.smartmusicchoice.application.repointerface.InstrumentRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class InstrumentService {

    @Inject
    InstrumentRepository instrumentRepository;

    @Inject
    InstrumentMapper instrumentMapper;

    public List<InstrumentDTO> list() {
        return new ArrayList<>(instrumentRepository.list()
                .map(instruments -> instrumentMapper.toInstrumentDTOList(instruments))
                .await()
                .indefinitely());
    }
}
