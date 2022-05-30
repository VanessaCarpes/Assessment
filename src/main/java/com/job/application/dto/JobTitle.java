package com.job.application.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class JobTitle {
    @EqualsAndHashCode.Include
    private String name;

    private List<String> composition;
    private Integer match = 0;
    private Integer totalMatch = 1;

    public JobTitle(String name, List<String> composition) {
        this.name = name;
        this.composition = composition;
    }

    public BigDecimal getMatches() {
        return new BigDecimal(match).divide(new BigDecimal(totalMatch), RoundingMode.HALF_EVEN);
    }
}
