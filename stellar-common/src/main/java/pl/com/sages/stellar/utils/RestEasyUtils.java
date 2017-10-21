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
        mdo.addFormData("fileName", new FileInputStream(new File(filePath)), MediaType.APPLICATION_OCTET_STREAM_TYPE);
        GenericEntity<MultipartFormDataOutput> entity = new GenericEntity<MultipartFormDataOutput>(mdo) {
        };
        return entity;
    }


    public static String save(String folder, String fileName, InputPart inputPart) {
        try {
            InputStream inputStream = inputPart.getBody(InputStream.class, null);
            String absolutefileName = folder + File.separator + fileName;
            saveToFile(inputStream, absolutefileName);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName;
    }

    private static void saveToFile(InputStream uploadedInputStream,
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
