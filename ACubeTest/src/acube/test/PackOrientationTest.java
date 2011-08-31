package acube.test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import acube.pack.Coder;
import acube.pack.Pack;
import acube.pack.PackOrientation;
import acube.pack.PackOrientationFull;
import acube.pack.PackOrientationPart;

public final class PackOrientationTest {
  private static final Integer[] PART_IDS_5 = { 0, 1, 2, 3, 4 };
  private static final Integer[] PART_IDS_9 = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };

  @Test
  public void testFlip() {
    final int[][] tw2 = { { -1, -1, -1 }, { 0, 1, 0 }, { 1, 0, 1 } };
    final PackOrientation<Integer> p2 =
        PackOrientation.instance(new boolean[] {}, new boolean[] {}, new Integer[] {}, 2);
    for (final int[] element : tw2) {
      assertEquals(element[0], p2.updateOrientation(element[1], -1));
      assertEquals(element[2], p2.updateOrientation(element[1], 1));
    }
  }

  @Test
  public void testTwist() {
    final int[][] tw3 = { { -1, -1, -1 }, { 0, 1, 2 }, { 1, 2, 0 }, { 2, 0, 1 } };
    final PackOrientation<Integer> p3 =
        PackOrientation.instance(new boolean[] {}, new boolean[] {}, new Integer[] {}, 3);
    for (final int[] element : tw3) {
      assertEquals(element[0], p3.updateOrientation(element[1], -1));
      assertEquals(element[2], p3.updateOrientation(element[1], 1));
    }
  }

  private void checkPackUnpack(final Pack<Integer> p) {
    for (int i = 0, l = p.size(); i < l; i++) {
      p.unpack(i);
      assertEquals(i, p.pack());
    }
  }

  public void testFull3() {
    final PackOrientation<Integer> p = new PackOrientationFull<Integer>(getBools("01110"), PART_IDS_5, 3);
    assertEquals(81, p.size());
    p.unpack(0);
    assertEquals("1 1 1 1 1", p.toString());
    p.unpack(1);
    assertEquals("1 1 1 2 3", p.toString());
    p.unpack(p.size() - 2);
    assertEquals("3 3 3 2 3", p.toString());
    p.unpack(p.size() - 1);
    assertEquals("3 3 3 3 2", p.toString());
    checkPackUnpack(p);
  }

  @Test
  public void testFull2() {
    final PackOrientation<Integer> p = new PackOrientationFull<Integer>(getBools("01110"), PART_IDS_5, 2);
    assertEquals(16, p.size());
    p.unpack(0);
    assertEquals("0 0 0 0 0", p.toString());
    p.unpack(1);
    assertEquals("0 0 0 1 1", p.toString());
    p.unpack(p.size() - 2);
    assertEquals("1 1 1 0 1", p.toString());
    p.unpack(p.size() - 1);
    assertEquals("1 1 1 1 0", p.toString());
    checkPackUnpack(p);
  }

  @Test
  public void testFull3Start() {
    final PackOrientation<Integer> p = new PackOrientationFull<Integer>(getBools("01110"), PART_IDS_5, 3);
    assertEquals(3, p.startSize());
    p.unpack(p.start(0));
    assertEquals("0 0 0 0 0", p.toString());
    p.unpack(p.start(1));
    assertEquals("1 0 0 0 2", p.toString());
    p.unpack(p.start(2));
    assertEquals("2 0 0 0 1", p.toString());
  }

  @Test
  public void testFull2Start() {
    final PackOrientation<Integer> p = new PackOrientationFull<Integer>(getBools("01110"), PART_IDS_5, 2);
    assertEquals(2, p.startSize());
    p.unpack(p.start(0));
    assertEquals("0 0 0 0 0", p.toString());
    p.unpack(p.start(1));
    assertEquals("1 0 0 0 1", p.toString());
  }

  @Test
  public void testPart3() {
    final PackOrientation<Integer> p =
        new PackOrientationPart<Integer>(getBools("001110011"), getBools("000001111"), PART_IDS_9, 3);
    assertEquals(81 * Coder.unordered.size(9, 4), p.size());
    p.unpack(0);
    assertEquals(". . . . . 0 0 0 0", p.toString());
    p.unpack(1);
    assertEquals(". . . . . 0 0 0 1", p.toString());
    p.unpack(p.size() - 2);
    assertEquals("2 2 2 1 . . . . .", p.toString());
    p.unpack(p.size() - 1);
    assertEquals("2 2 2 2 . . . . .", p.toString());
    checkPackUnpack(p);
  }

  @Test
  public void testPart3Start() {
    final PackOrientation<Integer> p =
        new PackOrientationPart<Integer>(getBools("001110011"), getBools("000001111"), PART_IDS_9, 3);
    assertEquals(Coder.unordered.size(4, 2), p.startSize());
    p.unpack(p.start(0));
    assertEquals(". . . . . 0 0 0 0", p.toString());
    p.unpack(p.start(1));
    assertEquals(". 0 . . . . 0 0 0", p.toString());
    p.unpack(p.start(2));
    assertEquals(". 0 . . . 0 . 0 0", p.toString());
    p.unpack(p.start(3));
    assertEquals("0 . . . . . 0 0 0", p.toString());
    p.unpack(p.start(4));
    assertEquals("0 . . . . 0 . 0 0", p.toString());
    p.unpack(p.start(5));
    assertEquals("0 0 . . . . . 0 0", p.toString());
  }

  @Test
  public void testPart3Full() {
    final PackOrientation<Integer> p =
        new PackOrientationPart<Integer>(getBools("00111"), getBools("11111"), PART_IDS_5, 3);
    assertEquals(81, p.size());
    p.unpack(0);
    assertEquals("0 0 0 0 0", p.toString());
    p.unpack(1);
    assertEquals("0 0 0 1 2", p.toString());
    p.unpack(p.size() - 2);
    assertEquals("2 2 2 1 2", p.toString());
    p.unpack(p.size() - 1);
    assertEquals("2 2 2 2 1", p.toString());
    checkPackUnpack(p);
  }

  @Test
  public void testPart3FullStart() {
    final PackOrientation<Integer> p =
        new PackOrientationPart<Integer>(getBools("00111"), getBools("11111"), PART_IDS_5, 3);
    assertEquals(1, p.startSize());
    p.unpack(p.start(0));
    assertEquals("0 0 0 0 0", p.toString());
  }

  private boolean[] getBools(final String s) {
    final boolean[] b = new boolean[s.length()];
    for (int i = 0; i < s.length(); i++)
      b[i] = s.charAt(i) == '1';
    return b;
  }
}
