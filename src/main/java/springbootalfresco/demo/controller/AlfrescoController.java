package springbootalfresco.demo.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springbootalfresco.demo.ResponseMessage;
import springbootalfresco.demo.model.reader.ReaderCreateDTO;
import springbootalfresco.demo.service.AlfrescoService;

import javax.validation.Valid;

@RestController("alfrescoController")
public class AlfrescoController {

    private AlfrescoService alfrescoService;

    @Autowired
    public AlfrescoController(AlfrescoService alfrescoService) {
        this.alfrescoService = alfrescoService;
    }

    @ApiOperation(value = "get stream to string", response = String.class)
    @PostMapping(value = "/api/stream/toString",
                    consumes = MediaType.APPLICATION_JSON_VALUE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage getMalformedStream(@RequestBody @Valid ReaderCreateDTO readerCreateDTO) {
        String streamToString = alfrescoService.getMalformedStream(readerCreateDTO);
        return new ResponseMessage(streamToString);
    }
}
