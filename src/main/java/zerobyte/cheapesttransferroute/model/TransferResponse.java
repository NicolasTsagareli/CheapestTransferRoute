package zerobyte.cheapesttransferroute.model;

import java.util.List;

public class TransferResponse {
    private List<List<Transfer>> batches;
    private int totalCost;
    private int totalWeight;

    public TransferResponse() {
    }

    public TransferResponse(List<List<Transfer>> batches, int totalCost, int totalWeight) {
        this.batches = batches;
        this.totalCost = totalCost;
        this.totalWeight = totalWeight;
    }

    public List<List<Transfer>> getBatches() {
        return batches;
    }

    public void setBatches(List<List<Transfer>> batches) {
        this.batches = batches;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public int getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(int totalWeight) {
        this.totalWeight = totalWeight;
    }
}
