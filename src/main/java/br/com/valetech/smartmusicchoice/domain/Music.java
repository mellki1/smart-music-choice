package br.com.valetech.smartmusicchoice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Music {
    private String id;
    private String name;
    private String singer;
    private String lastDatePlayed;
    private List<MusicianInstrument> musicians;
    private List<MusicVocalist> vocalists;
}
