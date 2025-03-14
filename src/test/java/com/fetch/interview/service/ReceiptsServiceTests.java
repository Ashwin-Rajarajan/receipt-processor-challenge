package com.fetch.interview.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fetch.interview.dto.request.Receipt;
import com.fetch.interview.repository.ReceiptsRepository;
import org.junit.jupiter.api.*;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReceiptsServiceTests {

    private static final ReceiptsRepository repository = new ReceiptsRepository();
    private static final ReceiptsService service = new ReceiptsService(repository);
    private static final ObjectMapper mapper = new ObjectMapper();
    private final static Map<String, Long> rewardsById = new HashMap<>();

    @BeforeAll
    public static void init() {
        mapper.registerModule(new JavaTimeModule());
    }

    @Test
    @Order(1)
    public void testProcessReceipts() {
        long[] rewards = new long[]{28L,109L};
        int index = 0;
        for(String receiptName : new String[]{"receipt1.json", "receipt2.json"}) {
            Receipt receipt = readFromFile(receiptName, Receipt.class);
            String id = service.processReceipts(receipt);
            try {
                //To check if the UUID is valid:
                UUID.fromString(id);
                rewardsById.put(id, rewards[index++]);
            } catch (Exception e) {
                throw new RuntimeException("Invalid UUID");
            }
        }
    }


    @Test
    @Order(2)
    public void testFetchPoints() {
        for(String id : rewardsById.keySet()) {
            assertEquals(rewardsById.get(id), service.fetchPoints(id));
        }
        assertNull(service.fetchPoints("121212"));
    }

    private <T> T readFromFile(String path, Class<T> type) {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path);
            T obj = mapper.readValue(inputStream, type);
            inputStream.close();
            return obj;
        } catch (Exception e) {
            throw new RuntimeException("Error while reading file: " + e);
        }
    }

}
