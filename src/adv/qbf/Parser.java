package adv.qbf;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Parser {
    public static Formula parse(String path) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(path));
        Formula s = parse(in);
        in.close();

        return s;
    }

    private static Formula parse(BufferedReader in) throws IOException {
        String line;
        String[] elems;

        // Parser comments
        line = in.readLine();

        while (line.charAt(0) == 'c') {
            line = in.readLine();
        }

        // Parser problem line
        elems = line.split("\\s+");
        assert elems[0].equals("p");
        assert elems[1].equals("cnf");
        int atomsCount = Integer.parseInt(elems[2]);
//        int clausesCount = Integer.parseInt(elems[3]);

        // Parser quantifier sets
        line = in.readLine();
        LinkedList<Quantifier> quantifiers = new LinkedList<>();
        boolean[] variables = new boolean[atomsCount + 1];

        while (line.charAt(0) == 'e' || line.charAt(0) == 'a') {
            elems = line.split("\\s+");
            boolean isExistential = elems[0].equals("e");

            for (int i = 1; i < elems.length - 1; i++) {
                int v = Integer.parseInt(elems[i]);

                if (!variables[v]) {
                    variables[v] = true;
                } else {
                    throw new IllegalArgumentException(v + " is already quantified");
                }

                quantifiers.add(new Quantifier(isExistential, v));
            }

            line = in.readLine();
        }

        // Parser CNF matrix
        List<Clause> clauses = new LinkedList<>();

        while (line != null) {
            elems = line.split("\\s+");
            List<Integer> literals = new LinkedList<>();

            for (int i = 0; i < elems.length - 1; i++) {
                int v = Integer.parseInt(elems[i]);

                if (!variables[Math.abs(v)]) {
                    quantifiers.addFirst(new Quantifier(true, Math.abs(v)));
                    variables[Math.abs(v)] = true;
                }

                literals.add(v);
            }

            clauses.add(new Clause(literals));
            line = in.readLine();
        }

        Formula state = new Formula(quantifiers, clauses);
        state.simplify();

        return state;
    }
}