package pl.com.sages.stellar.rest.converter;

import pl.com.sages.stellar.entities.Constellation;
import pl.com.sages.stellar.entities.Star;
import pl.com.sages.stellar.dto.ConstellationDTO;
import pl.com.sages.stellar.dto.StarDTO;

import java.util.ArrayList;
import java.util.List;

public class ConstellationConverter {


    /* entities -> dtos*/
    public static ConstellationDTO convertEntity(Constellation c){

        ConstellationDTO dto = new ConstellationDTO(c.getId(), c.getName());
        return dto;
    }

    public static List<ConstellationDTO> convertConstellationEntities(List<Constellation> cs){
        List<ConstellationDTO> dtos = new ArrayList<>();
        for (Constellation c : cs){
            dtos.add(convertEntity(c));
        }
        return dtos;
    }

    public static StarDTO convertEntity(Star s){
        StarDTO dto = new StarDTO(s.getId(), s.getName());
        return dto;
    }

    public static List<StarDTO> convertStarEntities(List<Star> ss){
        List<StarDTO> dtos = new ArrayList<>();
        for (Star s : ss){
            dtos.add(convertEntity(s));
        }
        return dtos;
    }


    /* dtos -> entities */
    public static Constellation convertDTO(ConstellationDTO dto){

        Constellation c = new Constellation();
        c.setId(dto.getAbbreviation());
        c.setName(dto.getName());
        return c;
    }










}
