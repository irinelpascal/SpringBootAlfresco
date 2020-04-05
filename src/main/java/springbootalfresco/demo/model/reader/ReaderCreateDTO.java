package springbootalfresco.demo.model.reader;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

public class ReaderCreateDTO {

    @ApiModelProperty(required = true)
    @NotBlank(message = "Please add a minimum range number")
    private String minimumRange;

    @ApiModelProperty(required = true)
    @NotBlank(message = "Please add a maximum range number")
    private String maximumRange;

    public ReaderCreateDTO(String minimumRange, String maximumRange) {
        this.minimumRange = minimumRange;
        this.maximumRange = maximumRange;
    }

    public String getMinimumRange() {
        return minimumRange;
    }

    public void setMinimumRange(String minimumRange) {
        this.minimumRange = minimumRange;
    }

    public String getMaximumRange() {
        return maximumRange;
    }

    public void setMaximumRange(String maximumRange) {
        this.maximumRange = maximumRange;
    }
}
