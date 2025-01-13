package zerobyte.cheapesttransferroute.model;

import java.util.List;

public class TransferResponse {
    private List<Transfer> selectedTransfers;
    private int totalCost;
    private int totalWeight;

    public TransferResponse() {
    }

    public TransferResponse(List<Transfer> selectedTransfers, int totalCost, int totalWeight) {
        this.selectedTransfers = selectedTransfers;
        this.totalCost = totalCost;
        this.totalWeight = totalWeight;
    }

    public List<Transfer> getSelectedTransfers() {
        return selectedTransfers;
    }

    public void setSelectedTransfers(List<Transfer> selectedTransfers) {
        this.selectedTransfers = selectedTransfers;
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
