package pl.com.sages.stellar.dao.impl;

import pl.com.sages.stellar.dao.StellarDAO;
import pl.com.sages.stellar.dao.StellarException;
import pl.com.sages.stellar.entities.Constellation;
import pl.com.sages.stellar.entities.Star;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

//@Alternative
public class InMemoryStellarDAO implements StellarDAO {

    Logger logger = Logger.getLogger(InMemoryStellarDAO.class.getName());


    private static List<Constellation> constellations = new ArrayList<>();

    {
        init();
    }

    private  void init() {

        if(constellations.size()>0) return;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("constellations.csv")))) {

            String line;
            while ((line=br.readLine())!=null){
                String[] parts = line.split(";");
                Constellation c = new Constellation();
                c.setId(parts[0]);
                c.setName(parts[1]);
                constellations.add(c);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("stars.csv")))) {

            String line;
            while ((line=br.readLine())!=null){
                String[] parts = line.split(";");
                Star s = new Star();
                s.setId(parts[0]);
                s.setName(parts[1]);
                findByAbbreviation(parts[2]).addStar(s);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public long getConstellationsCount() {
        return constellations.size();
    }

    @Override
    public List<Constellation> getConstellations(String queryString, int pageNo, int pageSize) {

        logger.info("searching for constellations. query=" + queryString + ", pageNo="+pageNo+", pageSize=" + pageSize);
        List<Constellation> cs = constellations;
        if(queryString!=null){
            cs = constellations.stream().filter(c->c.getName().contains(queryString)).collect(Collectors.toList());
        }
        if(pageNo==-1) return cs;
        int startIndex = (pageNo - 1) * pageSize;
        int endIndex = pageNo * pageSize;
        if (endIndex > cs.size()) endIndex = cs.size();
        return cs.subList(startIndex, endIndex);
    }


    public Constellation addConstellation(String abbr, String name) throws StellarException {

        Constellation c = new Constellation();
        c.setId(abbr);
        c.setName(name);
        constellations.add(c);
        return c;
    }

    @Override
    public Constellation findByAbbreviation(final String abbr) {
        return constellations.stream().filter(c -> c.getId().equalsIgnoreCase(abbr)).findFirst().get();
    }

    @Override
    public List<Star> getStars(String constAbbrv) {
        return findByAbbreviation(constAbbrv).getStars();
    }

    @Override
    public Star addStar(Constellation c, String abbr, String name) throws StellarException {

        Star s = new Star();
        s.setId(abbr);
        s.setName(name);
        s.setConstellation(c);
        return s;

    }
}
