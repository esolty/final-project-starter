package filters;

import twitter4j.Status;

import java.util.List;

public class OrFilter implements Filter {
    private Filter firstChild;
    private Filter secondChild;

    public OrFilter(Filter firstChild, Filter secondChild) {
        this.firstChild = firstChild;
        this.secondChild = secondChild;
    }

    @Override
    public boolean matches(Status s) {
        return firstChild.matches(s) || secondChild.matches(s);
    }

    @Override
    public List<String> terms() {
        List<String> l = firstChild.terms();
        l.addAll(secondChild.terms());
        return l;
    }

    @Override
    public String toString() {
        return "(" + firstChild + " or " + secondChild + ")";

    }
}
