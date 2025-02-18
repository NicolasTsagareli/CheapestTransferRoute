package zerobyte.cheapesttransferroute.model;

import java.util.List;

public class TransferRequest {
    private int maxWeight;
    private int maxBatchWeight;
    private List<Transfer> availableTransfers;

    public TransferRequest() {
    }

    public TransferRequest(int maxWeight, int maxBatchWeight, List<Transfer> availableTransfers) {
        this.maxWeight = maxWeight;
        this.maxBatchWeight = maxBatchWeight;
        this.availableTransfers = availableTransfers;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public void setMaxBatchWeight(int maxBatchWeight) {
        this.maxBatchWeight = maxBatchWeight;
    }

    public void setAvailableTransfers(List<Transfer> availableTransfers) {
        this.availableTransfers = availableTransfers;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public int getMaxBatchWeight() {
        return maxBatchWeight;
    }

    public List<Transfer> getAvailableTransfers() {
        return availableTransfers;
    }
}
