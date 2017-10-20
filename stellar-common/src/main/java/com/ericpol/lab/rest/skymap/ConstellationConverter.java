package com.ericpol.lab.rest.skymap;

import com.ericpol.lab.rest.skymap.data.Constellation;
import com.ericpol.lab.rest.skymap.data.Star;
import com.ericpol.lab.rest.skymap.dto.ConstellationDTO;
import com.ericpol.lab.rest.skymap.dto.StarDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xdzm on 2015-09-04.
 */
public class ConstellationConverter {

    public static ConstellationDTO convert(Constellation c){

        ConstellationDTO dto = new ConstellationDTO(c.getId(), c.getName());
        return dto;
    }

    public static Constellation convert(ConstellationDTO dto){

        Constellation c = new Constellation();
        c.setId(dto.getAbbreviation());
        c.setName(dto.getName());
        return c;
    }

    public static List<ConstellationDTO> convertConstellations(List<Constellation> cs){
        List<ConstellationDTO> dtos = new ArrayList<>();
        for (Constellation c : cs){
            dtos.add(convert(c));
        }
        return dtos;
    }

    public static StarDTO convert(Star s){
        StarDTO dto = new StarDTO(s.getId(), s.getName());
        return dto;
    }

    public static List<StarDTO> convertStars(List<Star> ss){
        List<StarDTO> dtos = new ArrayList<>();
        for (Star s : ss){
            dtos.add(convert(s));
        }
        return dtos;
    }






}
