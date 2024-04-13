package com.example.planisa.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.example.planisa.entity.CasesEntity;
import com.example.planisa.entity.CountryEntity;
import com.example.planisa.entity.DeathsEntity;
import com.example.planisa.repository.CasesRepository;
import com.example.planisa.repository.CountryRepository;
import com.example.planisa.repository.DeathsRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ApiCovidCallService {

    @Autowired
    private CasesRepository casesRepository;
    @Autowired
    private DeathsRepository deathsRepository; 
    @Autowired
    private CountryRepository countryRepository;

    public void callApiCovidByCountry(String country, String type){
        try {
            URL url = new URL("https://api.api-ninjas.com/v1/covid19?country="+country+"&type="+type);
            this.callApiAndSaveEntity(url);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void callApiCovidByDate(String date, String type){
        try {
            URL url = new URL("https://api.api-ninjas.com/v1/covid19?date="+date+"&type="+type);
            this.callApiAndSaveEntity(url);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void callApiAndSaveEntity(URL url){
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("accept", "application/json");
            connection.setRequestProperty("X-Api-Key", "NeuSBF0ZcaDSq2i3dhvTS27qi3sS2jLflsXKv44b");
            InputStream responseStream = connection.getInputStream();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(responseStream);
            if (url.toString().contains("cases")){
                this.mapAndSaveEntities(root, CasesEntity.class, this.casesRepository);
            }
            else{
                this.mapAndSaveEntities(root, DeathsEntity.class, this.deathsRepository);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
    }

    private <T> void mapAndSaveEntities(JsonNode root, Class<T> entityClass, JpaRepository<T, Long> repository){
        try {
            // Verificar o tipo de entidade para determinar o campo a ser usado (casos ou mortes)
            String fieldName = entityClass.equals(CasesEntity.class) ? "cases" : "deaths";
    
            // Iterar sobre cada objeto no array JSON
            root.forEach(node -> {
                String countryName = node.get("country").asText();
                JsonNode casesNode = node.get(fieldName);
    
                // Utilizar a API de Stream para iterar sobre as chaves do objeto de casos ou mortes
                casesNode.fields().forEachRemaining(entry -> {
                    String dateString = entry.getKey();
                    JsonNode dateNode = entry.getValue();
    
                    try {
                        // Criar uma nova instância da entidade correspondente
                        T entity = entityClass.getDeclaredConstructor().newInstance();

                        // Configurar os campos comuns a ambas as entidades
                        entity.getClass().getMethod("setCountry", CountryEntity.class)
                                .invoke(entity, this.countryRepository.findByName(countryName));
                        entity.getClass().getMethod("setDate", LocalDate.class)
                                .invoke(entity, LocalDate.parse(dateString));
                        entity.getClass().getMethod("setTotal", String.class)
                                .invoke(entity, dateNode.get("total").asText());
                        entity.getClass().getMethod("setNewOccurrences", String.class)
                                .invoke(entity, dateNode.get("new").asText());
    
                        // Salvar a entidade no banco de dados
                        repository.save(entity);
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
    }
    
}
