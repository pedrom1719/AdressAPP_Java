record ProfileCEP (String cep, String logradouro, String complemento, String bairro, String localidade, String uf, String estado, String regiao, String ddd){

    @Override
    public String toString() {
        return "(CEP: " + this.cep() + " | " + this.uf() + " - " + this.estado() + ", " + this.localidade() + " - " + this.regiao() +  " | " + this.bairro() + " - " + this.logradouro() + " " + this.complemento() + ")";
    }
}
