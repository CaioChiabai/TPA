package teste;

import java.util.Comparator;

public class ComparadorMatricula implements Comparator<Aluno> {
    public int compare(Aluno a1, Aluno a2) {
        return Integer.compare(a1.matricula, a2.matricula);
    }
}
