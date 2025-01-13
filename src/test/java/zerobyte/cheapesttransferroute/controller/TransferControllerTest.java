package zerobyte.cheapesttransferroute.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import zerobyte.cheapesttransferroute.model.Transfer;
import zerobyte.cheapesttransferroute.model.TransferRequest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TransferControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testEmptyTransferList() throws Exception {
        TransferRequest request = new TransferRequest(10, Collections.emptyList());

        mockMvc.perform(post("/api/transfers/select")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.selectedTransfers").isEmpty())
                .andExpect(jsonPath("$.totalCost").value(0))
                .andExpect(jsonPath("$.totalWeight").value(0));
    }

    @Test
    void testSingleTransferWithinWeight() throws Exception {
        Transfer transfer = new Transfer(5, 10);
        TransferRequest request = new TransferRequest(10, Collections.singletonList(transfer));

        mockMvc.perform(post("/api/transfers/select")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.selectedTransfers.length()").value(1))
                .andExpect(jsonPath("$.totalCost").value(10))
                .andExpect(jsonPath("$.totalWeight").value(5));
    }

    @Test
    void testListWith2Transfers() throws Exception {
        List<Transfer> transfers = Arrays.asList(
                new Transfer(10, 5),
                new Transfer(5, 7)
        );
        TransferRequest request = new TransferRequest(15, transfers);

        mockMvc.perform(post("/api/transfers/select")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.selectedTransfers.length()").value(2))
                .andExpect(jsonPath("$.totalCost").value(12))
                .andExpect(jsonPath("$.totalWeight").value(15));
    }

    @Test
    void testListWith4Transfers() throws Exception {
        List<Transfer> transfers = Arrays.asList(
                new Transfer(5, 10),
                new Transfer(10, 20),
                new Transfer(3, 5),
                new Transfer(8, 15)
        );
        TransferRequest request = new TransferRequest(15, transfers);

        mockMvc.perform(post("/api/transfers/select")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.selectedTransfers.length()").value(2))
                .andExpect(jsonPath("$.totalCost").value(30))
                .andExpect(jsonPath("$.totalWeight").value(15));
    }

    @Test
    void testComplexTransferSelection() throws Exception {
        List<Transfer> transfers = Arrays.asList(
                new Transfer(2, 3),
                new Transfer(8, 20),
                new Transfer(4, 12),
                new Transfer(1, 2),
                new Transfer(3, 8),
                new Transfer(5, 10),
                new Transfer(7, 15),
                new Transfer(6, 17),
                new Transfer(2, 5),
                new Transfer(9, 19)
        );
        TransferRequest request = new TransferRequest(15, transfers);

        mockMvc.perform(post("/api/transfers/select")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalWeight").value(15))
                .andExpect(jsonPath("$.totalCost").value(42))
                .andExpect(jsonPath("$.selectedTransfers.length()").value(4));
    }

    @Test
    void testListWith10Transfers() throws Exception {
        List<Transfer> transfers = Arrays.asList(
                new Transfer(95, 55),
                new Transfer(4, 10),
                new Transfer(60, 47),
                new Transfer(32, 5),
                new Transfer(23, 4),
                new Transfer(72, 50),
                new Transfer(80, 8),
                new Transfer(62, 61),
                new Transfer(65, 85),
                new Transfer(46, 87)
        );
        TransferRequest request = new TransferRequest(269, transfers);

        mockMvc.perform(post("/api/transfers/select")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.selectedTransfers.length()").value(6))
                .andExpect(jsonPath("$.totalCost").value(295))
                .andExpect(jsonPath("$.totalWeight").value(269));
    }
}
