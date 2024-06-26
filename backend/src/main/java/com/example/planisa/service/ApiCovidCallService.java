package com.example.planisa.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.planisa.entity.CasesEntity;
import com.example.planisa.entity.CountryEntity;
import com.example.planisa.entity.DeathsEntity;
import com.example.planisa.repository.CountryRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ApiCovidCallService {

    @Autowired
    private CountryRepository countryRepository;

    public <T> List<T> callApiCovidByCountry(String country, String type){
        try {
            URL url = new URL("https://api.api-ninjas.com/v1/covid19?country="+country+"&type="+type);
            if (type.contentEquals("cases")){
                return (List<T>) this.callApiCovidData(null, url, CasesEntity.class);
            } else{
                return (List<T>) this.callApiCovidData(null, url, DeathsEntity.class);
            }
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public <T> List<T> callApiCovidByDate(String date, String type){
        try {
            URL url = new URL("https://api.api-ninjas.com/v1/covid19?date="+date+"&type="+type);
            if (type.contentEquals("cases")){
                return (List<T>) this.callApiCovidData(date, url, CasesEntity.class);
            } else{
                return (List<T>) this.callApiCovidData(date, url, DeathsEntity.class);
            }  
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public <T> List<T> callApiCovidData(String date, URL url, Class<T> entityClass) {
        List<T> entities = new ArrayList<>();
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("accept", "application/json");
            connection.setRequestProperty("X-Api-Key", "NeuSBF0ZcaDSq2i3dhvTS27qi3sS2jLflsXKv44b");
            InputStream responseStream = connection.getInputStream();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(responseStream);
            entities.addAll(mapEntities(date, root, entityClass));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return entities; 
    }
    
    private <T> List<T> mapEntities(String date, JsonNode root, Class<T> entityClass) {
        List<T> entities = new ArrayList<>();
        try {
            // Verificar o tipo de entidade para determinar o campo a ser usado (casos ou mortes)
            String fieldName = entityClass.equals(CasesEntity.class) ? "cases" : "deaths";
    
            // Iterar sobre cada objeto no array JSON
            root.forEach(node -> {
                String countryName = node.has("country") ? node.get("country").asText() : ""; // Verificar se o nó possui o campo 'country'
                JsonNode casesNode = node.has(fieldName) ? node.get(fieldName) : node; // Verificar se o nó possui o campo 'cases'
    
                // Utilizar a API de Stream para iterar sobre as chaves do objeto de casos ou mortes
                casesNode.fields().forEachRemaining(entry -> {
                    String dateJson = entry.getKey();
                    JsonNode dateNode = entry.getValue();
                    try {
                        // Criar uma nova instância da entidade correspondente
                        T entity = entityClass.getDeclaredConstructor().newInstance();
    
                        // Configurar os campos comuns a ambas as entidades
                        entity.getClass().getMethod("setCountry", CountryEntity.class)
                                .invoke(entity, this.countryRepository.findByName(countryName));
                        
                        // Verificar o formato da resposta e ajustar a extração de dados conforme necessário
                        if (casesNode.toString().contains("cases") || casesNode.toString().contains("deaths")) { // Se o nó pai de 'total' e 'new' for o nó raiz
                            entity.getClass().getMethod("setDate", LocalDate.class)
                                    .invoke(entity, LocalDate.parse(date));
                        } else { // Se o nó pai de 'total' e 'new' for alguma data
                            entity.getClass().getMethod("setDate", LocalDate.class)
                                    .invoke(entity, LocalDate.parse(dateJson));
                        }
                        entity.getClass().getMethod("setTotal", String.class)
                                .invoke(entity, dateNode.get("total").asText());
                        entity.getClass().getMethod("setNewOccurrences", String.class)
                                .invoke(entity, dateNode.get("new").asText());
    
                        // Adicionar a entidade à lista de entidades
                        entities.add(entity);
                    } catch (Exception e) {
                        // Tratar exceções adequadamente
                        e.printStackTrace();
                    }
                });
            });
        } catch (Exception e) {
            // Tratar exceções adequadamente
            e.printStackTrace();
        }
        return entities;
    }
    
}
