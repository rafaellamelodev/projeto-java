package model;

public class Aluno {
    private Integer id;
    private String matricula;
    private String nome;
    private String cpf;
    private String email;

    public Aluno() {}

    public Aluno(Integer id, String matricula, String nome, String cpf, String email) {
        this.id = id;
        this.matricula = matricula;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
