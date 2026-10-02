package br.com.nonna_ai.dto;
public class ClienteResponseDto {
    private String id;
    private String cpf;
    private String nome;
    private String sobrenome;
    private String email;
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getSobrenome() { return sobrenome; }
    public void setSobrenome(String sobrenome) { this.sobrenome = sobrenome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}