package br.com.valetech.smartmusicchoice.infrastructure.dynamodb.config;

import br.com.valetech.smartmusicchoice.domain.Instrument;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Map;
import java.util.UUID;

public class InstrumentDynamoConfiguration extends DynamoDbTemplate<Instrument> {

    private static final String INSTRUMENT_TABLE_NAME = "instrument";
    private static final String[] INSTRUMENT_COLUMNS = {"id", "name"};

    public InstrumentDynamoConfiguration(){
        super(INSTRUMENT_COLUMNS);
    }
    @Override
    public String getTableName() {
        return INSTRUMENT_TABLE_NAME;
    }

    @Override
    public Instrument getDomainObject(Map<String, AttributeValue> item) {
        Instrument instrument = new Instrument();
        instrument.setId(UUID.fromString(item.get(INSTRUMENT_COLUMNS[0]).s()));
        instrument.setName(item.get(INSTRUMENT_COLUMNS[1]).s());
        return instrument;
    }

    @Override
    public Map<String, AttributeValue> getDomainPutItems(Map<String, AttributeValue> domainPutItem, Instrument instrument) {
        domainPutItem.put(INSTRUMENT_COLUMNS[0], AttributeValue.builder().s(instrument.getId().toString()).build());
        domainPutItem.put(INSTRUMENT_COLUMNS[1], AttributeValue.builder().s(instrument.getName()).build());
        return domainPutItem;
    }
}
