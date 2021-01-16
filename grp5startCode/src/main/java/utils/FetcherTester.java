/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import dtos.BoredDTO;
import java.io.IOException;
import rest.DailyFunResource;

/**
 *
 * @author GRP 5
 */
public class FetcherTester {
    
    public static void main(String[] args) throws IOException {
        
        DailyFunResource dfr = new DailyFunResource();
        System.out.println("dailyfun: " + dfr.getDailyFun());
        
    }
    
}
