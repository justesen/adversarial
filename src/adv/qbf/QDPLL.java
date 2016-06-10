package adv.qbf;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class QDPLL {
    private LinkedList<Quantifier> quantifiers;
    private List<Clause> clauses;
    private Map<Integer, Boolean> assignments;

    public QDPLL(QBFState init) {
        this.quantifiers = init.getQuantifiers();
        this.clauses = init.getClauses();
        this.assignments = new HashMap<>();
    }

    public boolean evaluate() {
        clauses = simplify(clauses);

        if (clauses.isEmpty()) {
            return true;
        }

        for (Clause c : clauses) {
            if (c.isEmpty()) {
                return false;
            }
        }

        Quantifier q = quantifiers.pollFirst();
        assignments.put(q.variable, true);

        if (!q.isExistential() && !evaluate()) {
            return false;
        }
        if (q.isExistential() && evaluate()) {
            return true;
        }
        assignments.put(q.variable, false);

        return evaluate();
    }

    private List<Clause> simplify(List<Clause> clauses) {
        boolean allTrue = true;

        for (Clause c : clauses) {
            switch (c.isDetermined(assignments)) {
                case Undetermined:
                    allTrue = false;
                    break;
                case False:
                    List<Clause> cs = new LinkedList<>();
                    cs.add(new Clause(new LinkedList<>()));

                    return cs;
            }
        }

        return allTrue ? new LinkedList<>() : clauses;
    }
}
