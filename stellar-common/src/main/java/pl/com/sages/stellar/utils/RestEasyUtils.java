package pl.com.sages.stellar.utils;

import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataOutput;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import java.io.*;

public class RestEasyUtils {


    public static GenericEntity multipartEntity(String filePath) throws FileNotFoundException {
        MultipartFormDataOutput mdo = new MultipartFormDataOutput();
        mdo.addFormData("file", new FileInputStream(new File(filePath)), MediaType.APPLICATION_OCTET_STREAM_TYPE);
        GenericEntity<MultipartFormDataOutput> entity = new GenericEntity<MultipartFormDataOutput>(mdo) {};
        return entity;
    }



    public static void saveToFile(InputPart inputPart, File f) {

        int read = 0;
        byte[] bytes = new byte[1024];

        try(OutputStream out = new FileOutputStream(f);InputStream inputStream = inputPart.getBody(InputStream.class, null)) {

            while ((read = inputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
