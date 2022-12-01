package br.com.valetech.smartmusicchoice.domain;

import java.util.UUID;

public class MusicVocalist {
    private UUID musicId;
    private UUID vocalistId;

    public UUID getMusicId() {
        return musicId;
    }

    public void setMusicId(UUID musicId) {
        this.musicId = musicId;
    }

    public UUID getVocalistId() {
        return vocalistId;
    }

    public void setVocalistId(UUID vocalistId) {
        this.vocalistId = vocalistId;
    }
}
