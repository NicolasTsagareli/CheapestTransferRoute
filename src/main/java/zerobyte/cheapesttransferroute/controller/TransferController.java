package zerobyte.cheapesttransferroute.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zerobyte.cheapesttransferroute.model.TransferRequest;
import zerobyte.cheapesttransferroute.model.TransferResponse;
import zerobyte.cheapesttransferroute.service.TransferService;
import zerobyte.cheapesttransferroute.service.TransferServiceImpl;

@RestController
@RequestMapping("/api/transfers")
public class TransferController {
    private final TransferService transferService;

    @Autowired
    public TransferController() {
        transferService = new TransferServiceImpl();
    }

    @GetMapping("/select")
    public TransferResponse selectTransfers(@RequestBody TransferRequest request) {
        return transferService.selectTransfers(request);
    }
}
