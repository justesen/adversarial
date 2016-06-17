package adv.qbf;

import java.util.List;

public class SearchTraps {
    public static void lookForSearchTrap(Formula f, List<Boolean> assignments, int level) {
        System.out.println(f);

        for (boolean a : assignments) {
            Formula g = new Formula(f, a);
            Formula h = new Formula(f, !a);

            if (g.isFullyInstantiated()) {
                break;
            }

            if (f.isExistential() != g.isExistential()) {
                if (f.isExistential() && minimax(g, level) == -1
                        || f.isExistential() && minimax(h, level) == -1
                        || f.isUniversal() && minimax(h, level) == +1
                        || f.isUniversal() && minimax(h, level) == +1) {
                    System.out.printf("Found level %d search trap at %s\n", level, f);
                }
            }

            f = g;
        }
    }

    private static int minimax(Formula f, int level) {
//        f.simplify();

        switch (f.isDetermined()) {
            case True:
                return +1;
            case False:
                return -1;
        }

        if (level == 0) {
            return 0;
        }

        Formula g = new Formula(f, true);
        Formula h = new Formula(f, false);

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
