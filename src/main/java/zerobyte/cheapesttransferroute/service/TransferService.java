package zerobyte.cheapesttransferroute.service;

import zerobyte.cheapesttransferroute.model.TransferRequest;
import zerobyte.cheapesttransferroute.model.TransferResponse;

public interface TransferService {
    /**
     * Processes TransferRequest. Maximizes costs under the total maxWeight.
     * @param transferRequest
     * @return TransferResponse
     */
    public TransferResponse selectTransfers(TransferRequest transferRequest);
}
