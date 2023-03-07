import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

import java.util.List;


public class HttpClient {
    public static String URI = "https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats";
    public static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setSocketTimeout(30000)
                        .setRedirectsEnabled(false)
                        .build())
                .build();) {
            HttpGet request = new HttpGet(URI);
            CloseableHttpResponse response = httpClient.execute(request);

            List<Facts> list = mapper.readValue(response.getEntity().getContent(), new TypeReference<>() {
            });
            list.stream().filter(facts -> facts.getUpvotes() > 0)
                    .forEach(System.out::println);
            response.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
