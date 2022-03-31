package com.keshe4ka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.commons.text.StringSubstitutor;
import org.apache.commons.text.lookup.StringLookupFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;

public class Config {

    private int indexedColumn;
    private String fileName;

    public Config(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        StringSubstitutor stringSubstitutor = new StringSubstitutor(StringLookupFactory.INSTANCE.environmentVariableStringLookup());
        File configFile = getFileFromResources();
        String contents =
                stringSubstitutor.replace(new String(Files.readAllBytes(configFile.toPath())));
        Config config = objectMapper.readValue(contents, Config.class);
        this.indexedColumn = config.getIndexedColumn();
        this.fileName = config.getFileName();
        if (args.length != 0) {
            this.indexedColumn = Integer.parseInt(args[0]);
        }
    }

    public Config() {
    }

    public int getIndexedColumn() {
        return indexedColumn;
    }

    public String getFileName() {
        return fileName;
    }

    private File getFileFromResources() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("application.yml");
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }
    }
}
