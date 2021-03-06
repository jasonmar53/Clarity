
package test.loop;

import static org.junit.Assert.assertEquals;

import clarity.graphics.tile.TileSet;

import org.junit.Test;

public class TestTileSet {
  @Test
  public void testLoadTiles() {
    TileSet ts = new TileSet();
    ts.loadTiles("/textures/tiles/castleTiles.png");
    int actual = 30;
    assertEquals(actual, TileSet.getTileSize(), 0);
  }

}