package adv.qbf;

import adv.qbf.formula.Clause;
import adv.qbf.formula.PrenexCNF;
import adv.qbf.formula.Quantifier;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class QDIMACS {
    public static PrenexCNF parse(String path) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(path));
        PrenexCNF s = parse(in);
        in.close();

        return s;
    }

    private static PrenexCNF parse(BufferedReader in) throws IOException {
        String line;
        String[] elems;

        // Parse comments
        line = in.readLine();

        while (line.charAt(0) == 'c') {
            line = in.readLine();
        }

        // Parse problem line
        elems = line.split("\\s+");
        assert elems[0].equals("p");
        assert elems[1].equals("cnf");
        int atomsCount = Integer.parseInt(elems[2]);
//        int clausesCount = Integer.parseInt(elems[3]);

        // Parse quantifier sets
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

        // Parse CNF matrix
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

        return new PrenexCNF(quantifiers, clauses);
    }

    public static PrenexCNF generate(int atomsCount, int clausesCount, int literalsPerClause) {
        LinkedList<Quantifier> quantifiers = new LinkedList<>();
        Random rnd = new Random();

        for (int var = 1; var <= atomsCount; var++) {
            quantifiers.add(new Quantifier(rnd.nextBoolean(), var));
        }

        List<Clause> clauses = new LinkedList<>();

        for (int i = 0; i < clausesCount; i++) {
            List<Integer> literals = new LinkedList<>();

            for (int j = 0; j < literalsPerClause; j++) {
                int var = rnd.nextInt(atomsCount) + 1;
                var = rnd.nextBoolean() ? var : -var;
                literals.add(var);
            }

            clauses.add(new Clause(literals));
        }

        return new PrenexCNF(quantifiers, clauses);
    }
}