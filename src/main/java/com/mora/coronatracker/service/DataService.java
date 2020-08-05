package com.mora.coronatracker.service;

import com.mora.coronatracker.model.LocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataService {

    private static String CORONA_DATA_URL =
            "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    private List<LocationStats> allStats = new ArrayList<>();

    public List<LocationStats> getAllStats() {
        return allStats;
    }

    @PostConstruct
    @Scheduled(cron = ("0 1 1 * * *"), zone = "Europe/Tallinn")
    public void fetchCoronaData() throws IOException, InterruptedException {
        List<LocationStats> newStats = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(CORONA_DATA_URL)).GET().build();
        HttpResponse httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        StringReader csvReader = new StringReader((String) httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvReader);
        for (CSVRecord record : records) {
            LocationStats locationStats = new LocationStats();
            locationStats.setState(record.get(0));
            locationStats.setCountry(record.get(1));
            locationStats.setLatestTotalConfirmedCases(Integer.parseInt(record.get(record.size()-1)));
            newStats.add(locationStats);
        }
        this.allStats = newStats;
    }
    public int totalCases(){
       int totalConfirmedCases =  allStats.stream().mapToInt(stats -> stats.getLatestTotalConfirmedCases()).sum();
        return totalConfirmedCases;
    }

}
