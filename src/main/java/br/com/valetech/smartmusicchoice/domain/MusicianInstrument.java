package br.com.valetech.smartmusicchoice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MusicianInstrument {
    private String musicianId;
    private String instrumentId;
}
