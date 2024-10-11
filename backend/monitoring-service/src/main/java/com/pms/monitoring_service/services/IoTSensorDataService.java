package com.pms.monitoring_service.services;

import com.pms.monitoring_service.dto.SensorData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class IoTSensorDataService {
    private final RestTemplate restTemplate = new RestTemplate();
    //restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());




    // Inject the Flask service URL from the yml file
    @Value("${flask.service.url}")
    private String flaskBaseUrl; // http://127.0.0.1:5000

    // Method to retrieve sensor data from Flask API
    public List<SensorData> getSensorData() {
        String url = flaskBaseUrl + "/get-data";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        try {
            ResponseEntity<List<SensorData>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<List<SensorData>>() {}
            );

            //return response.getBody();

            // Check if response is successful
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();  // Return the list of sensor data
            } else {
                throw new RuntimeException("Error: Unable to fetch data from Flask service.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

}
