package pl.com.sages.stellar.rest;

import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import pl.com.sages.stellar.rest.utils.RestEasyUtils;

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

    // TODO HTTP GET serve logoFileName
    @GET
    @Produces("image/png")
    @Consumes("*/*")
    public Response getLogo(@Context ServletContext ctxt) {

        File f = getFile(ctxt, logoFileName);

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
        String fileName = "tmp.png";
        List<InputPart> inputParts = uploadForm.get("file");
        String absolutefileName = null;
        for (InputPart inputPart : inputParts)
        {
            absolutefileName = RestEasyUtils.save(ctxt.getRealPath("/img"), fileName, inputPart);
        }
        return Response.status(200).entity("Uploaded file name : "+ absolutefileName).build();
    }




}
