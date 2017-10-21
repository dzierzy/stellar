package pl.com.sages.stellar.dto;


import javax.xml.bind.annotation.XmlRootElement;


public class ConstellationDTO {

    private String abbreviation;

    private String name;

    public ConstellationDTO(String abbr, String name){
        this.abbreviation = abbr;
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ConstellationDTO{" +
                "abbreviation='" + abbreviation + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
