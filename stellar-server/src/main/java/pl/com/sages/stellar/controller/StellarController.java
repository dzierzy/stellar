package pl.com.sages.stellar.controller;

import pl.com.sages.stellar.dao.StellarDAO;

import pl.com.sages.stellar.dto.Page;
import pl.com.sages.stellar.entities.Constellation;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class StellarController extends HttpServlet{

    @Inject
    StellarDAO sDao;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = (request.getParameter("pageNo")!=null) ?
                Integer.parseInt(request.getParameter("pageNo")) : 1;

        int pageSize = 5;
        try {
            Thread.sleep(3*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        request.setAttribute("constellations", getConstellations(pageNo, pageSize));
        request.getServletContext().getRequestDispatcher("/jsp/stellar.jsp").forward(request,resp);
    }

    public Page<Constellation> getConstellations(int pageNo, int pageSize){

        return new Page<Constellation>(sDao.getConstellations(null, pageNo, pageSize),sDao.getConstellationsCount(), pageNo, pageSize );
    }

}
