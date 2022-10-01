package com.rest.dao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.rest.exception.InitializationFailedException;
import com.rest.model.Billionaire;
import com.rest.service.Forbes400Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class InitializeDatabase implements InitializingBean {

    private static final Logger LOG = LoggerFactory.getLogger(InitializeDatabase.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private BillionaireJPARepository repository;

    @Autowired
    private Forbes400Properties properties;

    @Override
    public void afterPropertiesSet() throws Exception {
        LOG.info("Initializing DB...");
        fillDatabaseDuringStartup();
        LOG.info("DB init Complete...");
    }

    private void fillDatabaseDuringStartup() throws IOException {
        List<Billionaire> billionaireList = new ArrayList<>();
        final ResponseEntity<String> forbed400ResponseEntity =
                restTemplate.getForEntity(properties.buildEndPoint(), String.class);

        if(!forbed400ResponseEntity.getStatusCode().is2xxSuccessful()) {
            throw new InitializationFailedException();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        final JsonNode jsonNode = objectMapper.readTree(forbed400ResponseEntity.getBody());
        if(jsonNode.isArray()) {
            for(JsonNode eachBillionaire : jsonNode) {
                Billionaire newEntry = new Billionaire();
                final String billionaireString = eachBillionaire.toString();

                String name = JsonPath.read(billionaireString, "$.person.name");
                String[] firstAndLastName = name.split(" ");
                newEntry.setFirstName(firstAndLastName[0]);
                newEntry.setLastName(firstAndLastName[1]);

                String company = JsonPath.read(billionaireString, "$.source");
                newEntry.setCompany(company);

                Number netWorth = JsonPath.read(billionaireString, "$.finalWorth");
                newEntry.setWealth(netWorth + "B");

                billionaireList.add(newEntry);
            }
        }
        repository.save(billionaireList);
    }
}
