package br.com.valetech.smartmusicchoice.domain;

import java.util.UUID;

public class MusicianInstrument {
    private UUID musicianId;
    private UUID instrumentId;

    public UUID getMusicianId() {
        return musicianId;
    }

    public void setMusicianId(UUID musicianId) {
        this.musicianId = musicianId;
    }

    public UUID getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(UUID instrumentId) {
        this.instrumentId = instrumentId;
    }
}
