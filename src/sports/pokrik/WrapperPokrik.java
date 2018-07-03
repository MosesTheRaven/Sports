/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports.pokrik;

/**
 *
 * @author nanka
 */
abstract public class WrapperPokrik implements Pokrik {
    Pokrik wrapped;
    
    WrapperPokrik(Pokrik X){
        this.wrapped=X;
    }
}
