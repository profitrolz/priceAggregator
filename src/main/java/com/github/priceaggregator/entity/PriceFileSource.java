package com.github.priceaggregator.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PriceFileSource {

    @Enumerated(value = EnumType.STRING)
    protected SourceType source;

    @Enumerated(value = EnumType.STRING)
    protected FileType fileType;

    protected String filePath;

    protected String fileName;

}
