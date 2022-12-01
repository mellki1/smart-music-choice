package br.com.valetech.smartmusicchoice.userinterface.resource;

import br.com.valetech.smartmusicchoice.application.dto.InstrumentDTO;
import br.com.valetech.smartmusicchoice.application.service.InstrumentService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/instrument")
public class InstrumentResource {

    @Inject
    InstrumentService instrumentService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<InstrumentDTO> list() {
        return instrumentService.list();
    }
}
