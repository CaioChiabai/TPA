package teste;

public class Aluno {
    public int matricula;
    public String nome;
    public float nota;

    public Aluno(int matricula, String nome, float nota) {
        this.matricula = matricula;
        this.nome = nome;
        this.nota = nota;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Aluno) {
            Aluno outro = (Aluno) o;
            return this.matricula == outro.matricula;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Matrícula: " + matricula + ", Nome: " + nome + ", Nota: " + nota;
    }
}
