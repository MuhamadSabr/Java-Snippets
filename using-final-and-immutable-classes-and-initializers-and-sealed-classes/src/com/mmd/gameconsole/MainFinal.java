package com.mmd.gameconsole;

import com.mmd.gameconsole.game.Game;
import com.mmd.gameconsole.game.GameConsole;
import com.mmd.gameconsole.pirate.Weapon;

//class specialGameAction extends /*GameAction*/ Weapon {}//There first error u might get is that there is no default constructor.
//But u know that even after creating default constructors that match super. Enums n Records cannot be extended. They are implicitly final.

class SpecialGmaeConsole<t extends Game<? extends Player>> extends GameConsole<Game<? extends Player>>{

    public SpecialGmaeConsole(Game<? extends Player> game) {
        super(game);
    }
}


public class MainFinal {
    public static void main(String[] args) {
    }
}
