package springbootalfresco.demo.model.reader;

import io.swagger.annotations.ApiModelProperty;

public class ReaderCreateDTO {

    @ApiModelProperty(required = true)
    private int minimumRange;

    @ApiModelProperty(required = true)
    private int maximumRange;

    public ReaderCreateDTO(int minimumRange, int maximumRange) {
        this.minimumRange = minimumRange;
        this.maximumRange = maximumRange;
    }

    public int getMinimumRange() {
        return minimumRange;
    }

    public void setMinimumRange(int minimumRange) {
        this.minimumRange = minimumRange;
    }

    public int getMaximumRange() {
        return maximumRange;
    }

    public void setMaximumRange(int maximumRange) {
        this.maximumRange = maximumRange;
    }
}
