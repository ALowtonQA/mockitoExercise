package mockitoExcercise1;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class excerciseTests {
    @Test
    public void printsCalculatorResultsHundredTimes() {
    	Printer printer = mock(Printer.class);
    	NumberCalculator numberCalculator = mock(NumberCalculator.class);
    	
    	excercise1 ex = new excercise1(numberCalculator, printer);
    	
        String limit = "100";
        
        when(numberCalculator.calculate(anyInt()))
                .thenReturn("0")
                .thenReturn("1");
        
        printer.print(limit);
        
        ex.printNumbers(100);
        
        verify(numberCalculator, times(100)).calculate(anyInt());
        verify(printer, times(1)).print("0");
        verify(printer, times(Integer.parseInt(limit) - 1)).print("1");
        
        verifyNoMoreInteractions(numberCalculator, printer);
    }
 
    @Test
    public void continuesOnCalculatorOrPrinterError() {

    	Printer printer = mock(Printer.class);
    	NumberCalculator numberCalculator = mock(NumberCalculator.class);
    	
        when(numberCalculator.calculate(anyInt()))
                .thenReturn("1")
                .thenThrow(new RuntimeException())
                .thenReturn("3");

        doThrow(new RuntimeException()).when(printer).print("3");

        printer.print("3");

        verify(numberCalculator, times(3)).calculate(anyInt());
        verify(printer).print("1");
        verify(printer).print("3");
 
        verifyNoMoreInteractions(numberCalculator, printer);
    }
}