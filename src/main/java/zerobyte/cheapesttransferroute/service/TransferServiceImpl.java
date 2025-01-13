package zerobyte.cheapesttransferroute.service;

import zerobyte.cheapesttransferroute.model.Transfer;
import zerobyte.cheapesttransferroute.model.TransferRequest;
import zerobyte.cheapesttransferroute.model.TransferResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Math.max;

public class TransferServiceImpl implements TransferService{
    /**
     * Selects optimal transfers according to knapsack 1/0 algorithm.
     * @param transferRequest
     * @return TransferResponse that contains totalWeight, totalCost and List of transfers
     */
    @Override
    public TransferResponse selectTransfers(TransferRequest transferRequest) {
        // Maximum weight, maxWeight, that can be transferred (Size of a Knapsack).
        int W = transferRequest.getMaxWeight();
        if(W <= 0) {
            return new TransferResponse(Collections.emptyList(), 0, 0);
        }
        List<Transfer> availableTransfers = transferRequest.getAvailableTransfers();
        int n = availableTransfers.size();
        int[] weights = new int[n+1];
        int[] costs = new int[n+1];
        getWeightsAndCosts(availableTransfers, weights, costs);

        // Dynamic Programming Matrix.
        // Saves a maximum value for each maximum weight up to 'W',
        // while considering transfers from 1 to 'i'.
        int[][] dp = new int[n+1][W +1];

        // Knapsack 0/1 algorithm.
        // If the i-th transfer is included, then maximum cost is dp[i-1][j-weights[i]]+costs[i]
        // Else, the maximum dp[i][j] is dp[i-1][j].
        // Whichever gives the maximum must be done.
        for (int i=1; i<=n; i++) {
            for (int j = W; j>=weights[i]; j--) {
                dp[i][j] = max(dp[i-1][j], dp[i-1][j-weights[i]]+costs[i]);
            }
        }
        int totalCost = dp[n][W];

        // Finding which transfers have been chosen with backtracking.
        // Assumes that no transfers had 0 cost.
        boolean[] isSelected = new boolean[n+1];
        int totalWeight = 0;
        int j=W;
        for(int i=n; i>=1; i--) {
            if(weights[i] > j) continue;
            if(dp[i][j] == dp[i-1][j-weights[i]]+costs[i]) {
                totalWeight += weights[i];
                isSelected[i] = true;
                j = j - weights[i];
            }
        }

        List<Transfer> selectedTransfers = new ArrayList<>();
        copySelectedTransfers(availableTransfers, isSelected, selectedTransfers);

        TransferResponse transferResponse = new TransferResponse(selectedTransfers, totalCost, totalWeight);

        return transferResponse;
    }

    private void copySelectedTransfers(List<Transfer> availableTransfers, boolean[] isSelected, List<Transfer> selectedTransfers) {
        int i=0;
        for (Transfer t: availableTransfers) {
            i++;
            if(isSelected[i]) selectedTransfers.add(t);
        }
    }


    private void getWeightsAndCosts(List<Transfer> availableTransfers, int[] weights, int[] costs) {
        int i = 0;
        for (Transfer t : availableTransfers) {
            i++;
            weights[i] = t.getWeight();
            costs[i] = t.getCost();
        }
    }

}
