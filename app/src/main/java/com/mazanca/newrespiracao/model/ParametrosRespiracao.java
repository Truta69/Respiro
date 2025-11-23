package com.mazanca.newrespiracao.model;

public class ParametrosRespiracao {
    private String nomeExercicio;
    private long tempoInspirar;
    private long tempoExpirar;
    private long cicloTotais;
    private long tempoPausa;

    public ParametrosRespiracao(String nomeExercicio, long tempoInspirar, long tempoExpirar, long cicloTotais, long tempoPausa) {
        this.nomeExercicio = nomeExercicio;
        this.tempoInspirar = tempoInspirar;
        this.tempoExpirar = tempoExpirar;
        this.cicloTotais = cicloTotais;
        this.tempoPausa = tempoPausa;
    }

    public String getNomeExercicio() {
        return nomeExercicio;
    }

    public long getTempoInspirar() {
        return tempoInspirar;
    }

    public long getTempoExpirar() {
        return tempoExpirar;
    }

    public long getCicloTotais() {
        return cicloTotais;
    }

    public long getTempoPausa() {
        return tempoPausa;
    }
}
