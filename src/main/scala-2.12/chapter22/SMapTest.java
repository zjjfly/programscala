package chapter22;

import org.junit.Before;
import org.junit.Test;
import scala.Option;
import scala.collection.mutable.LinkedHashMap;

import static org.junit.Assert.*;

/**
 * Created by zjjfly on 2017/6/4.
 */
public class SMapTest extends org.scalatest.junit.JUnitSuite{
    static class Name{
        String firstName;
        String lastName;

        Name(String firstName,String lastName){
            this.firstName=firstName;
            this.lastName=lastName;
        }
    }
    private LinkedHashMap<Integer,Name> map;
    @Before
    public void setup(){
        map=new LinkedHashMap<>();
        map.update(1,new Name("jj","zi"));
    }

    @Test
    public void usingMapGetWithOptionName() {
        assertEquals(1, map.size());
        Option<Name> n1 = map.get(1);
        assertTrue(n1.isDefined());
        assertEquals("jj", n1.get().firstName);
    }

    @Test
    public void usingMapGetWithOptionExistential() {
        assertEquals(1, map.size());
        Option<?> n1 = map.get(1);
        assertTrue(n1.isDefined());
        assertEquals("jj", ((Name) n1.get()).firstName);
    }
}
