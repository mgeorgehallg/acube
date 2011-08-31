package acube.transform;

import java.util.EnumSet;
import acube.Edge;
import acube.Turn;
import acube.pack.PackKit;

final class MEdgePosB extends Move<Edge> {
  public MEdgePosB(final EnumSet<Edge> edgeMask) {
    super(PackKit.mEdgePosB(edgeMask));
  }

  @Override
  public void turn(final Turn turn) {
    switch (turn) {
    case U1:
      break;
    case D1:
      break;
    case F2:
      swap(Edge.FR, Edge.FL);
      break;
    case B2:
      swap(Edge.BR, Edge.BL);
      break;
    case L2:
      swap(Edge.FL, Edge.BL);
      break;
    case R2:
      swap(Edge.FR, Edge.BR);
      break;
    default:
      throw new IllegalArgumentException("Unsupported or non primitive turn " + turn);
    }
  }

  @Override
  protected int getIndex(final Edge e) {
    return PackKit.mEdgeIndexB(e);
  }
}
