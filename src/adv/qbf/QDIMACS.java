package adv.qbf;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class QDIMACS {
    public static QBFState parse(BufferedReader in) throws IOException {
        String line;
        String[] elems;

        // Parse comments
        line = in.readLine();

        while (line.charAt(0) == 'c') {
            line = in.readLine();
        }

        // Parse problem line
        elems = line.split(" ");
        assert elems[0].equals("p");
        assert elems[1].equals("cnf");
        int atomsCount = Integer.parseInt(elems[2]);
        int clausesCount = Integer.parseInt(elems[3]);

        // Parse quantifier sets
        line = in.readLine();
        LinkedList<Quantifier> quantifiers = new LinkedList<>();

        while (line.charAt(0) == 'e' || line.charAt(0) == 'a') {
            elems = line.split(" ");
            boolean isExistential = elems[0].equals("e");

            for (int i = 1; i < elems.length - 1; i++) {
                quantifiers.add(new Quantifier(isExistential, elems[i]));
            }

            line = in.readLine();
        }

        // Parse CNF matrix
        List<Clause> clauses = new LinkedList<>();

        while (line != null) {
            elems = line.split("\\s+");
            List<String> positives = new LinkedList<>();
            List<String> negatives = new LinkedList<>();

            for (int i = 0; i < elems.length - 1; i++) {
                if (elems[i].charAt(0) == '-') {
                    negatives.add(elems[i].substring(1));
                } else {
                    positives.add(elems[i]);
                }
            }

            clauses.add(new Clause(positives, negatives));

            line = in.readLine();
        }

        return new QBFState(quantifiers, clauses);
    }
}
