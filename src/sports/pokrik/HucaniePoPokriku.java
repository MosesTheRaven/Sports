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
public class HucaniePoPokriku extends WrapperPokrik {

    public HucaniePoPokriku(Pokrik X) {
        super(X);
    }

    @Override
    public void pokrik() {
        wrapped.pokrik();
        System.out.println("Héééééééééj!");
    }
    
}
