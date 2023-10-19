package Meyguer.ChromaticLantern.Service;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
public class ConsumerService {
    public ConsumerService(){
    }
    public Double requested() throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet("https://api.bluelytics.com.ar/v2/latest");
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    String responseBody = EntityUtils.toString(response.getEntity());
                    return Double.parseDouble(responseBody.substring(44,47));
                }
            }
        }catch (IOException e){
            throw new IOException();
        }
        throw new IOException();
    }
}
