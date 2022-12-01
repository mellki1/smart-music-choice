package br.com.valetech.smartmusicchoice.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import java.util.List;
import java.util.UUID;

public class Music {
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    private String name;
    private String singer;
    private String lastDatePlayed;
    private List<MusicianInstrument> musicians;
    private List<MusicVocalist> vocalists;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getLastDatePlayed() {
        return lastDatePlayed;
    }

    public void setLastDatePlayed(String lastDatePlayed) {
        this.lastDatePlayed = lastDatePlayed;
    }

    public List<MusicianInstrument> getMusicians() {
        return musicians;
    }

    public void setMusicians(List<MusicianInstrument> musicians) {
        this.musicians = musicians;
    }

    public List<MusicVocalist> getVocalists() {
        return vocalists;
    }

    public void setVocalists(List<MusicVocalist> vocalists) {
        this.vocalists = vocalists;
    }
}
