package zerobyte.cheapesttransferroute.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zerobyte.cheapesttransferroute.model.Transfer;
import zerobyte.cheapesttransferroute.model.TransferRequest;
import zerobyte.cheapesttransferroute.model.TransferResponse;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TransferServiceTest {
    private TransferService transferService;

    @BeforeEach
    public void setup() {
        transferService = new TransferServiceImpl();
    }

    @Test
    public void testEmptyTransferList() {
        TransferRequest request = new TransferRequest(10, Collections.emptyList());
        TransferResponse response = transferService.selectTransfers(request);

        assertTrue(response.getSelectedTransfers().isEmpty());
        assertEquals(0, response.getTotalWeight());
        assertEquals(0, response.getTotalCost());
    }

    @Test
    public void testSingleTransferList() {
        Transfer t1 = new Transfer(10, 5);
        TransferRequest request1 = new TransferRequest(15, Collections.singletonList(t1));

        TransferResponse response = transferService.selectTransfers(request1);

        assertEquals(1, response.getSelectedTransfers().size());
        assertEquals(10, response.getTotalWeight());
        assertEquals(5, response.getTotalCost());

        // Exceeding weight.
        TransferRequest request2 = new TransferRequest(5, Collections.singletonList(t1));

        TransferResponse response2 = transferService.selectTransfers(request2);

        assertTrue(response2.getSelectedTransfers().isEmpty());
        assertEquals(0, response2.getTotalWeight());
        assertEquals(0, response2.getTotalCost());
    }

    @Test
    public void testListWith2Transfers() {
        List<Transfer> transferList = Arrays.asList(
            new Transfer(10, 5),
            new Transfer(5, 7)
        );

        TransferRequest request = new TransferRequest(15, transferList);

        TransferResponse response = transferService.selectTransfers(request);

        assertEquals(2, response.getSelectedTransfers().size());
        assertEquals(15, response.getTotalWeight());
        assertEquals(12, response.getTotalCost());
    }

    @Test
    public void test2ListWith2Transfers() {
        List<Transfer> transferList = Arrays.asList(
                new Transfer(10, 5),
                new Transfer(5, 7)
        );

        TransferRequest request = new TransferRequest(14, transferList);

        TransferResponse response = transferService.selectTransfers(request);

        assertEquals(1, response.getSelectedTransfers().size());
        assertEquals(5, response.getTotalWeight());
        assertEquals(7, response.getTotalCost());
    }

    @Test void testListWith4Transfers() {
        List<Transfer> transferList = Arrays.asList(
                new Transfer(5, 10),
                new Transfer(10, 20),
                new Transfer(3, 5),
                new Transfer(8, 15)
        );

        TransferRequest request = new TransferRequest(15, transferList);

        TransferResponse response = transferService.selectTransfers(request);

        assertEquals(2, response.getSelectedTransfers().size());
        assertEquals(15, response.getTotalWeight());
        assertEquals(30, response.getTotalCost());
    }

    @Test
    void testSingleTransferWithinWeight() {
        Transfer transfer = new Transfer(5, 10); // weight, cost
        TransferRequest request = new TransferRequest(10, Collections.singletonList(transfer));

        TransferResponse response = transferService.selectTransfers(request);

        assertEquals(1, response.getSelectedTransfers().size());
        assertEquals(10, response.getTotalCost());
        assertEquals(5, response.getTotalWeight());
    }

    @Test
    void testComplexTransferSelection() {
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
        TransferResponse response = transferService.selectTransfers(request);

        // Verify total weight doesn't exceed maximum
        assertTrue(response.getTotalWeight() <= 15);

        // Verify we got some transfers but not all
        assertFalse(response.getSelectedTransfers().isEmpty());
        assertTrue(response.getSelectedTransfers().size() < transfers.size());

        // Verify total cost and weight calculations
        int calculatedWeight = 0;
        int calculatedCost = 0;
        for (Transfer t : response.getSelectedTransfers()) {
            calculatedWeight += t.getWeight();
            calculatedCost += t.getCost();
        }

        assertEquals(calculatedWeight, response.getTotalWeight());
        assertEquals(calculatedCost, response.getTotalCost());

        // The optimal solution includes transfers with 12+8+17+5 costs.
        assertEquals(4, response.getSelectedTransfers().size());
        assertEquals(42, response.getTotalCost());
        assertEquals(15, response.getTotalWeight());
    }

    @Test
    void testListWith10Transfers() {
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

        TransferResponse response = transferService.selectTransfers(request);

        assertEquals(6, response.getSelectedTransfers().size());
        assertEquals(295, response.getTotalCost());
        assertEquals(269, response.getTotalWeight());
    }
}
