package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE}

  @Test
  public void testThreeAddThreeRemove(){
    AListNoResizing<Integer> list0 = new AListNoResizing<>();
    BuggyAList<Integer> list1 = new BuggyAList<>();

    list0.addLast(4);list0.addLast(5);list0.addLast(6);
    list1.addLast(4);list1.addLast(5);list1.addLast(6);

    assertEquals(list0.size(), list1.size());
    assertEquals(list0.removeLast(), list1.removeLast());
    assertEquals(list0.removeLast(), list1.removeLast());
    assertEquals(list0.removeLast(), list1.removeLast());

  }

    @Test
    public void randomizedTest(){
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> broken = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                broken.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int correatSize = L.size();
                int brokenSize = broken.size();
                System.out.println("correat Size: " + correatSize);
                System.out.println("broken Size: " + brokenSize);

            }else if(operationNumber == 2 && L.size() != 0 ){
                int lastNum = L.getLast();
                int last_broke = broken.getLast();
                System.out.println("getLast(" + lastNum + ")" );
                System.out.println("getLast broken(" + last_broke + ")" );

            }else if(operationNumber == 3&& L.size() != 0){
                int lastNum = L.removeLast();
                int last_broke = broken.removeLast();
                
                System.out.println( "removeLast" + lastNum + ")");
                System.out.println("removeLast broken(" + last_broke + ")" );
            }
        }    
    }


}