package dream.auction.service;

import dream.auction.dto.response.ResponseBidding;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuctionListener {

    private final SimpMessageSendingOperations messagingTemplate;


    public void sendBidding(ResponseBidding response){
        messagingTemplate.convertAndSend("/sub/auction/" + response.getAuctionId(), response);
    }
}
