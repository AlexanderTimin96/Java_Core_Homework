import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.*;

import java.net.URL;

public class HttpClient {
    public static String URI = "https://api.nasa.gov/planetary/apod?api_key=NamzS9s1513O9c3bI7Nj7Xn1IcXOdUUxOkzilZo6";
    public static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setSocketTimeout(30000)
                        .setRedirectsEnabled(true)
                        .build())
                .build()) {
            HttpGet request = new HttpGet(URI);
            CloseableHttpResponse response = httpClient.execute(request);
            ResponseNASA responseNASA = mapper.readValue(response.getEntity().getContent(), new TypeReference<>() {
            });

            String[] path = responseNASA.getHdurl().split("/");
            String namePicture = path[path.length - 1];

            BufferedInputStream in = new BufferedInputStream(new URL(responseNASA.getHdurl()).openStream());
            FileOutputStream fos = new FileOutputStream(namePicture);
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fos.write(dataBuffer, 0, bytesRead);
            }

            response.close();
            in.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
