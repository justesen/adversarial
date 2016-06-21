package adv.qbf;

import java.util.Collection;
import java.util.LinkedList;

class QuantifierSet {
    private final Collection<Integer> variables;
    private final boolean isExistential;

    QuantifierSet(boolean isExistential) {
        this.isExistential = isExistential;
        this.variables = new LinkedList<>();
    }

    QuantifierSet(boolean isExistential, Collection<Integer> variables) {
        this.isExistential = isExistential;
        this.variables = new LinkedList<>(variables);
    }

    boolean isExistential() {
        return isExistential;
    }

    boolean isUniversal() {
        return !isExistential();
    }

    void add(int v) {
        variables.add(v);
    }

    Collection<Integer> variables() {
        return variables;
    }

    boolean isEmpty() {
        return variables.isEmpty();
    }

    boolean remove(int variable) {
        return variables.remove(variable);
    }

    boolean contains(int variable) {
        return variables.contains(variable);
    }
}