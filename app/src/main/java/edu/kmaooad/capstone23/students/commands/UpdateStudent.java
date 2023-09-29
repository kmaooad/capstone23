package edu.kmaooad.capstone23.students.commands;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.jboss.resteasy.reactive.PartType;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;

public class UpdateStudent {
    @RestForm
    @PartType("text/csv")
    @Schema(type = SchemaType.STRING, format = "binary", implementation = String.class)
    public FileUpload csvFile;
}
