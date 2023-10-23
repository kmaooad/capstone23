package edu.kmaooad.capstone23.students;

import org.jboss.resteasy.reactive.multipart.FileUpload;

import java.io.File;
import java.nio.file.Path;

public class MockFileUpload implements FileUpload {
    private File file;
    private String contentType;

    public MockFileUpload(File file, String contentType) {
        this.file = file;
        this.contentType = contentType;
    }

    MockFileUpload(File file) {
        this(file, null);
    }

    @Override
    public String name() {
        return file.getName();
    }

    @Override
    public Path filePath() {
        return file.toPath();
    }

    @Override
    public String fileName() {
        return file.getName();
    }

    @Override
    public long size() {
        return file.length();
    }

    @Override
    public String contentType() {
        return contentType;
    }

    @Override
    public String charSet() { return null; }

    @Override
    public Path uploadedFile() {
        return file.toPath();
    }
}
