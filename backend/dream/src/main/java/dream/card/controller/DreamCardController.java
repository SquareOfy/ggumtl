package dream.card.controller;


import dream.card.service.DreamCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DreamCardController {

    private final DreamCardService cardService;

}
