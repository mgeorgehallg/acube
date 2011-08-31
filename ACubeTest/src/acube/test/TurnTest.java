package acube.test;

import static acube.Metric.FACE;
import static acube.Metric.QUARTER;
import static acube.Metric.ROTATION_FACE;
import static acube.Metric.ROTATION_QUARTER;
import static acube.Metric.SLICE;
import static acube.Metric.SLICE_QUARTER;
import static acube.Turn.B1;
import static acube.Turn.B2;
import static acube.Turn.E1;
import static acube.Turn.E2;
import static acube.Turn.M3;
import static acube.Turn.R1;
import static acube.Turn.U1;
import static acube.Turn.U2;
import static acube.Turn.r1;
import static acube.Turn.r2;
import static acube.Turn.r3;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.EnumSet;
import org.junit.Test;
import acube.Turn;

public final class TurnTest {
  @Test
  public void testMetric() {
    assertEquals(1, QUARTER.length(U1));
    assertEquals(1, FACE.length(U1));
    assertEquals(1, SLICE.length(U1));
    assertEquals(1, SLICE_QUARTER.length(U1));
    assertEquals(2, QUARTER.length(U2));
    assertEquals(1, FACE.length(U2));
    assertEquals(1, SLICE.length(U2));
    assertEquals(2, SLICE_QUARTER.length(U2));
    assertEquals(2, QUARTER.length(E1));
    assertEquals(2, FACE.length(E1));
    assertEquals(1, SLICE.length(E1));
    assertEquals(1, SLICE_QUARTER.length(E1));
    assertEquals(4, QUARTER.length(E2));
    assertEquals(2, FACE.length(E2));
    assertEquals(1, SLICE.length(E2));
    assertEquals(2, SLICE_QUARTER.length(E2));
    assertEquals(1, QUARTER.length(r1));
    assertEquals(1, FACE.length(r1));
    assertEquals(1, SLICE.length(r1));
    assertEquals(1, SLICE_QUARTER.length(r1));
    assertEquals(2, QUARTER.length(r2));
    assertEquals(1, FACE.length(r2));
    assertEquals(1, SLICE.length(r2));
    assertEquals(2, SLICE_QUARTER.length(r2));
    // rotation of cube centers counts as one move too
    assertEquals(1, ROTATION_QUARTER.length(U1));
    assertEquals(1, ROTATION_FACE.length(U1));
    assertEquals(2, ROTATION_QUARTER.length(U2));
    assertEquals(1, ROTATION_FACE.length(U2));
    assertEquals(3, ROTATION_QUARTER.length(E1));
    assertEquals(3, ROTATION_FACE.length(E1));
    assertEquals(6, ROTATION_QUARTER.length(E2));
    assertEquals(3, ROTATION_FACE.length(E2));
    assertEquals(2, ROTATION_QUARTER.length(r1));
    assertEquals(2, ROTATION_FACE.length(r1));
    assertEquals(4, ROTATION_QUARTER.length(r2));
    assertEquals(2, ROTATION_FACE.length(r2));
  }

  @Test
  public void testAB() {
    int countB = 0;
    for (final Turn t : Turn.values())
      if (t.isB())
        countB++;
    assertTrue(U1.isB());
    assertTrue(U2.isB());
    assertFalse(B1.isB());
    assertTrue(B2.isB());
    assertTrue(r2.isB());
    assertFalse(r3.isB());
    assertEquals(countB, Turn.getValidB(Turn.valueSet).size());
    assertEquals(countB, Turn.getValidB(Turn.getValidB(Turn.valueSet)).size());
    assertEquals(0, Turn.getValidB(EnumSet.of(R1)).size());
  }

  @Test
  public void testMetricFilter() {
    assertEquals(18 * 2, FACE.filterForElementary(Turn.valueSet).size());
    assertEquals(18, ROTATION_FACE.filterForElementary(Turn.valueSet).size());
  }

  @Test
  public void testIntConversions() {
    assertEquals(U1, Turn.turn(0));
    assertEquals(U2, Turn.turn(1));
    assertEquals(M3, Turn.turn(Turn.size - 1));
  }
}
