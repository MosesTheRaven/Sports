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
public class CountingPokrik extends WrapperPokrik{

    public CountingPokrik(Pokrik X) {
        super(X);
    }

    @Override
    public void pokrik() {
        System.out.println("Desať, deväť, osem, sedem, šesť, päť, štyri, tri, dva, jedna!");
        wrapped.pokrik();
    }
    
}
