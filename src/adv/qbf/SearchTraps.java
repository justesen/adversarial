package adv.qbf;

import adv.qbf.formula.PrenexCNF;

import java.util.List;

public class SearchTraps {
    public static int lookForSearchTrap(PrenexCNF f, List<Boolean> assignments, int level) {
        int searchTrapsCount = 0;

        for (boolean a : assignments) {
            PrenexCNF g = new PrenexCNF(f, a);
            PrenexCNF h = new PrenexCNF(f, !a);

            if (g.isFullyInstantiated()) {
                break;
            }

            if (f.isExistential() != g.isExistential()) {
                if ((f.isExistential() && (minimax(h, level) == -1)) || (f.isUniversal() && (minimax(h, level) == +1))) {
                    System.err.printf("Found level %d search trap at %s \n", level, f);
                    searchTrapsCount++;
                }
            }

            f = g;
        }

        return searchTrapsCount;
    }

    private static int minimax(PrenexCNF f, int level) {
        f.simplify();

        switch (f.isDetermined()) {
            case True:
                return +1;
            case False:
                return -1;
        }

        if (level == 0) {
            return 0;
        }

        PrenexCNF g = new PrenexCNF(f, true);
        PrenexCNF h = new PrenexCNF(f, false);

        if (g.isFullyInstantiated() || f.isExistential() != g.isExistential()) {
            level = level - 1;
        }

        if (f.isExistential()) {
            return minimax(g, level) == +1 ? +1 : minimax(h, level);
        } else {
            return minimax(g, level) == -1 ? -1 : minimax(h, level);
        }
    }
}
