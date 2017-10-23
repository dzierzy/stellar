package pl.com.sages.stellar.rest;

import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import pl.com.sages.stellar.utils.RestEasyUtils;


import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Path("logo")
public class FileREST {

    private Logger logger = Logger.getLogger(FileREST.class.getName());

    private static String logoFileName = "moon.png";

    private File getFile(ServletContext ctxt, String fileName) {
        return new File(ctxt.getRealPath("/img") + File.separator + fileName);
    }

    @GET
    @Produces("image/png")
    @Consumes("*/*")
    public Response getLogo(@Context ServletContext ctxt) {

        File f = getFile(ctxt, logoFileName);

        Response.ResponseBuilder response = Response.ok(f);
        response.header("Content-Disposition", "attachment; filename=\"test_text_file.txt\"");
        return response.build();

    }


    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadLogo(MultipartFormDataInput input, @Context ServletContext ctxt, @Context SecurityContext sctxt) throws IOException
    {
        logger.info("about to receive uploaded file");


        Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
        List<InputPart> inputParts = uploadForm.get("file");
        for (InputPart inputPart : inputParts){
            RestEasyUtils.saveToFile( inputPart, getFile(ctxt, logoFileName));
        }
        return Response.status(200).entity("Uploaded "+ getFile(ctxt, logoFileName)).build();
    }




}
