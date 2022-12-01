package br.com.valetech.smartmusicchoice.infrastructure.dynamodb.config;

import br.com.valetech.smartmusicchoice.domain.MusicVocalist;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Map;
import java.util.UUID;

public class MusicVocalistDynamoConfiguration extends DynamoDbTemplate<MusicVocalist> {
    private static final String MUSIC_VOCALIST_TABLE_NAME = "musicVocalist";
    private static final String[] MUSIC_VOCALIST_COLUMNS = {"musicId", "vocalistId"};


    @Override
    public String getTableName() {
        return MUSIC_VOCALIST_TABLE_NAME;
    }

    @Override
    public MusicVocalist getDomainObject(Map<String, AttributeValue> item) {
        MusicVocalist musicVocalist = new MusicVocalist();
        musicVocalist.setMusicId(UUID.fromString(item.get(MUSIC_VOCALIST_COLUMNS[0]).s()));
        musicVocalist.setVocalistId(UUID.fromString(item.get(MUSIC_VOCALIST_COLUMNS[1]).s()));
        return musicVocalist;
    }

    @Override
    public Map<String, AttributeValue> getDomainPutItems(Map<String, AttributeValue> domainPutItem, MusicVocalist musicVocalist) {
        domainPutItem.put(MUSIC_VOCALIST_COLUMNS[0], AttributeValue.builder().s(musicVocalist.getMusicId().toString()).build());
        domainPutItem.put(MUSIC_VOCALIST_COLUMNS[1], AttributeValue.builder().s(musicVocalist.getVocalistId().toString()).build());
        return domainPutItem;
    }
}
