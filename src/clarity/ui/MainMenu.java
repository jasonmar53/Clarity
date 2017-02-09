package clarity.ui;

import clarity.state.State;
import clarity.state.StateManager;
import clarity.state.levels.Level1;
import clarity.utilities.input.Mouse;

public class MainMenu extends State {

  /**
   * @param manager The state manager.
   */
  public MainMenu(StateManager manager) {
    super(manager, "/textures/backgrounds/mainMenu.png", "musicFileName", "mapFileName");
  }

  /**
   * (non-Javadoc)
   * 
   * @see clarity.state.State#update()
   */
  public void update() {
    super.update();
    if (Mouse.buttonClickAndRelease()) {
      manager.setCurrentState(new Level1(manager));
    }
  }
}
