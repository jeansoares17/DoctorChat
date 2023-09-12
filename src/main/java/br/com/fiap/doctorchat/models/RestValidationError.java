package br.com.fiap.doctorchat.models;

public record RestValidationError(String field, String message) {
}