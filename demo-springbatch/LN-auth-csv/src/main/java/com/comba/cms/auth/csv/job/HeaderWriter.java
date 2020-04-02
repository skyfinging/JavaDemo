package com.comba.cms.auth.csv.job;

import lombok.Getter;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;

@Component
public class HeaderWriter implements FlatFileHeaderCallback {

    @Value("${csv.header}")
    private String header;

    @Getter
    @Value("${csv.split}")
    private String csvSplit;

    @Override
    public void writeHeader(Writer writer) throws IOException {
        writer.write(header);
    }

    public String[] getHeaders(){
        if(csvSplit.equals("|"))
            return header.split("\\|");
        else
            return header.split(csvSplit);
    }


}
