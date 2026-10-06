package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public class CreateLinkRequest {

    @NotNull(message = "ownerId é obrigatório")
    @Positive(message = "ownerId deve ser um número positivo")
    private Long ownerId;

    @NotBlank(message = "URL é obrigatória")
    @Pattern(regexp = "^(https?|ftp)://[^\\s/$.?#].[^\\s]*$", message = "URL inválida")
    private String url;

    public CreateLinkRequest() {
    }

    public CreateLinkRequest(Long ownerId, String url) {
        this.ownerId = ownerId;
        this.url = url;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
