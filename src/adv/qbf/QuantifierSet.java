package adv.qbf;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

class QuantifierSet {
    private final Set<Integer> variables;
    private final boolean isExistential;

    QuantifierSet(boolean isExistential) {
        this.isExistential = isExistential;
        this.variables = new HashSet<>();
    }

    QuantifierSet(boolean isExistential, Collection<Integer> variables) {
        this.isExistential = isExistential;
        this.variables = new HashSet<>(variables);
    }

    boolean isExistential() {
        return isExistential;
    }

    public void add(int v) {
        variables.add(v);
    }

    public Collection<Integer> variables() {
        return variables;
    }

    public boolean isEmpty() {
        return variables.isEmpty();
    }

    public boolean remove(int variable) {
        return variables.remove(variable);
    }

    public boolean contains(int variable) {
        return variables.contains(variable);
    }
}
