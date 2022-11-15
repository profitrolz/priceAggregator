package com.github.priceaggregator.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Getter
public class ReadProperties {

    protected String source;

    protected String fileType;

    protected String fileName;

    protected int partNumberColumn;

    protected int brandColumn;

    protected int priceColumn;

    protected int amountColumn;

    protected int nameColumn;

    protected int multiplicityColumn;

    protected int firstRow;

}
