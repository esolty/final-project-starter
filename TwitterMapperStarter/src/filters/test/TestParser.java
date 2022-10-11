package filters.test;

import filters.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test the parser.
 */
public class TestParser {
    @Test
    public void testBasic() throws SyntaxError {
        Filter f = new Parser("trump").parse();
        assertTrue(f instanceof BasicFilter);
        assertTrue(((BasicFilter)f).getWord().equals("trump"));
    }

    @Test
    public void testHairy() throws SyntaxError {
        Filter x = new Parser("trump and (evil or blue) and red or green and not not purple").parse();
        assertTrue(x.toString().equals("(((trump and (evil or blue)) and red) or (green and not not purple))"));
    }

    @Test
    public void test1AndOr() throws SyntaxError {
        Filter x = new Parser("molasses and cornstarch or sandwiches and burgers or tacos").parse();
        assertTrue(x.toString().equals("(((molasses and cornstarch) or (sandwiches and burgers)) or tacos)"));
    }

    @Test
    public void test2AndOr() throws SyntaxError {
        Filter x = new Parser("hockey and swimming and running or sitting or listening").parse();
        assertTrue(x.toString().equals("((((hockey and swimming) and running) or sitting) or listening)"));
    }

    @Test
    public void test3AndOr() throws SyntaxError {
        Filter x = new Parser("spoons or forks or sporks or orcs and borkss").parse();
        assertTrue(x.toString().equals("(((spoons or forks) or sporks) or (orcs and borkss))"));
    }
}
