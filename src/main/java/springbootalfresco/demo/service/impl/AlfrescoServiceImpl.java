package springbootalfresco.demo.service.impl;

import org.springframework.stereotype.Service;
import springbootalfresco.demo.model.reader.ReaderCreateDTO;
import springbootalfresco.demo.service.AlfrescoService;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static springbootalfresco.demo.constants.ApplicationConstants.ALFRESCO;
import static springbootalfresco.demo.constants.ApplicationConstants.FIZZ;
import static springbootalfresco.demo.util.ApplicationUtil.checkIfNumberDivisibleBy_3_Or_5_Or_15_AndPopulateList;

@Service("alfrescoServiceImpl")
public class AlfrescoServiceImpl implements AlfrescoService {

    private static final Logger LOG = Logger.getLogger(AlfrescoServiceImpl.class.getName());

    @Override
    public String getMalformedStream(ReaderCreateDTO readerCreateDTO) {
        String minimumRange = readerCreateDTO.getMinimumRange();
        String maximumRange = readerCreateDTO.getMaximumRange();
        try {
            checkValidityOnRange(Integer.parseInt(minimumRange), Integer.parseInt(maximumRange));
        } catch (NumberFormatException e) {
           LOG.severe("Try to insert a proper integer number \n" + e.getMessage());
        }
        List<Integer> integers = new ArrayList<>();
        List<String> strings = new ArrayList<>();

        for (int i = Integer.parseInt(minimumRange); i <= Integer.parseInt(maximumRange); i++) {
            integers.add(i);
        }
        convertIntegersAndAddToList(integers, strings);

        List<String> stringCollection = strings.stream()
                .filter(string -> string.length() != 0)
                .map(string -> {
                    if (string.contains("3")) {
                        string = string.replace(string, ALFRESCO);
                    }
                    return string;
                }).collect(Collectors.toList());

        return stringCollection.stream()
                .map(string -> {
                    try {
                        if (Integer.parseInt(string) % 3 == 0) {
                            string = string.replace(string, FIZZ);
                        }
                    } catch (NumberFormatException e) {
                        LOG.info("Number format exception has been caught for this value " + string);
                    }
                    return string;
                }).collect(Collectors.joining(" "));
    }

    private void checkValidityOnRange(int minRange, int maximumRange) {
        if ((minRange == 0 || minRange < 0) || (minRange >= maximumRange)) {
            throw new IllegalStateException("Please enter a number which is greater than 0");
        }
    }

    private void convertIntegersAndAddToList(List<Integer> integers, List<String> strings) {
        integers.stream()
                .forEach(integer -> {
                    checkIfNumberDivisibleBy_3_Or_5_Or_15_AndPopulateList(integer, strings, String.valueOf(integer), String.valueOf(integer));
                });
    }
}
