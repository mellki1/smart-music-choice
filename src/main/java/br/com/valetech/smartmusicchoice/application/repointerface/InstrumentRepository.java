package br.com.valetech.smartmusicchoice.application.repointerface;

import br.com.valetech.smartmusicchoice.domain.Instrument;
import io.smallrye.mutiny.Uni;

import java.util.List;

public interface InstrumentRepository {

    Uni<List<Instrument>> list();
}
