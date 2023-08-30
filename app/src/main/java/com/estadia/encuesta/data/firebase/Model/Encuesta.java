package com.estadia.encuesta.data.firebase.Model;

public class Encuesta {
    private String eid;
    private boolean drespirar;
    private boolean tosSec;
    private boolean dcabeza;
    private boolean secnasal;
    private boolean dmuscular;
    private boolean dgarganta;
    private boolean polfato;
    private boolean dtorasico;
    private boolean resfriado;
    private boolean ccovid;

    public Encuesta() {
    }



    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public boolean isDrespirar() {
        return drespirar;
    }

    public void setDrespirar(boolean drespirar) {
        this.drespirar = drespirar;
    }

    public boolean isTosSec() {
        return tosSec;
    }

    public void setTosSec(boolean tosSec) {
        this.tosSec = tosSec;
    }

    public boolean isDcabeza() {
        return dcabeza;
    }

    public void setDcabeza(boolean dcabeza) {
        this.dcabeza = dcabeza;
    }

    public boolean isSecnasal() {
        return secnasal;
    }

    public void setSecnasal(boolean secnasal) {
        this.secnasal = secnasal;
    }

    public boolean isDgarganta() {
        return dgarganta;
    }

    public boolean isDmuscular() {
        return dmuscular;
    }

    public void setDmuscular(boolean dmuscular) {
        this.dmuscular = dmuscular;
    }

    public void setDgarganta(boolean dgarganta) {
        this.dgarganta = dgarganta;
    }

    public boolean isPolfato() {
        return polfato;
    }

    public void setPolfato(boolean polfato) {
        this.polfato = polfato;
    }

    public boolean isDtorasico() {
        return dtorasico;
    }

    public void setDtorasico(boolean dtorasico) {
        this.dtorasico = dtorasico;
    }

    public boolean isResfriado() {
        return resfriado;
    }

    public void setResfriado(boolean resfriado) {
        this.resfriado = resfriado;
    }

    public boolean isCcovid() {
        return ccovid;
    }

    public void setCcovid(boolean ccovid) {
        this.ccovid = ccovid;
    }
}
