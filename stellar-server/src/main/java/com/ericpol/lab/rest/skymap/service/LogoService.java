package com.ericpol.lab.rest.skymap.service;

import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Path("logo")
public class LogoService {

    private Logger logger = Logger.getLogger(LogoService.class.getName());

    private static String logoFileName = "ericpol.png";

    private File getFile(ServletContext ctxt, String fileName) {
        return new File(ctxt.getRealPath("/WEB-INF/img") + File.separator + fileName);
    }
    private File getFile(ServletContext ctxt) {
        return getFile(ctxt, logoFileName);
    }

    // TODO HTTP GET serve logoFileName
    @GET
    @Produces("image/png")
    @Consumes("*/*")
    public Response getLogo(@Context ServletContext ctxt, @Context SecurityContext sctxt) {

        File f = getFile(ctxt);

        Response.ResponseBuilder response = Response.ok((Object) f);
        response.header("Content-Disposition", "attachment; filename=\"" + logoFileName + "\"");
        return response.build();
    }


    // TODO HTTP POST upload png and  logoFileName
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(MultipartFormDataInput input, @Context ServletContext ctxt, @Context SecurityContext sctxt) throws IOException
    {
        Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
        logger.info("upload form:" + uploadForm);
        String fileName = "tmp.png";//uploadForm.get("fileName").get(0).getBodyAsString();
        List<InputPart> inputParts = uploadForm.get("file");
        String absolutefileName = null;
        for (InputPart inputPart : inputParts)
        {
            absolutefileName = save(ctxt, fileName, inputPart);
        }
        return Response.status(200).entity("Uploaded file name : "+ absolutefileName).build();
    }

    private String save(ServletContext ctxt, String fileName, InputPart inputPart) {
        try {
            MultivaluedMap<String, String> header = inputPart.getHeaders();
            InputStream inputStream = inputPart.getBody(InputStream.class, null);
            String absolutefileName = ctxt.getRealPath("/WEB-INF/img") + File.separator + fileName;
            saveToFile(inputStream, absolutefileName);
            logger.info("Success !!!!! Uploaded to " + absolutefileName);
            logoFileName = fileName;
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return fileName;
    }

    private void saveToFile(InputStream uploadedInputStream,
                            String uploadedFileLocation) {

        try {
            OutputStream out = null;
            int read = 0;
            byte[] bytes = new byte[1024];
            File f = new File(uploadedFileLocation);
            if(!f.exists()){
                f.createNewFile();
            }
            out = new FileOutputStream(f);
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
