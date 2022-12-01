package br.com.valetech.smartmusicchoice.infrastructure.dynamodb.config;

import br.com.valetech.smartmusicchoice.domain.MusicianInstrument;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Map;
import java.util.UUID;

public class MusicianInstrumentDynamoConfiguration extends DynamoDbTemplate<MusicianInstrument> {
    private static final String MUSICIAN_INSTRUMENT_TABLE_NAME = "musician";
    private static final String[] MUSICIAN_INSTRUMENT_COLUMNS = {"instrumentId", "musicianId"};


    @Override
    public String getTableName() {
        return MUSICIAN_INSTRUMENT_TABLE_NAME;
    }

    @Override
    public MusicianInstrument getDomainObject(Map<String, AttributeValue> item) {
        MusicianInstrument musicianInstrument = new MusicianInstrument();
        musicianInstrument.setInstrumentId(UUID.fromString(item.get(MUSICIAN_INSTRUMENT_COLUMNS[0]).s()));
        musicianInstrument.setMusicianId(UUID.fromString(item.get(MUSICIAN_INSTRUMENT_COLUMNS[1]).s()));
        return musicianInstrument;
    }

    @Override
    public Map<String, AttributeValue> getDomainPutItems(Map<String, AttributeValue> domainPutItem, MusicianInstrument musicianInstrument) {
        domainPutItem.put(MUSICIAN_INSTRUMENT_COLUMNS[0], AttributeValue.builder().s(musicianInstrument.getInstrumentId().toString()).build());
        domainPutItem.put(MUSICIAN_INSTRUMENT_COLUMNS[1], AttributeValue.builder().s(musicianInstrument.getMusicianId().toString()).build());
        return domainPutItem;
    }
}
