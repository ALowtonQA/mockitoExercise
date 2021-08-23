package mockitoExcercise1;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class excerciseTests {
	
	@Mock
	Printer printerMock;
	
	@Mock
	NumberCalculator numberCalculatorMock;
	
	@InjectMocks
	excercise1 ex1;
	
    @Test
    public void printsCalculatorResultsHundredTimes() {
    	String limit = "100";
        
        when(numberCalculatorMock.calculate(anyInt()))
                .thenReturn("0", "1");

        ex1.printNumbers(100);
        
        verify(numberCalculatorMock, times(100)).calculate(anyInt());
        verify(printerMock, times(1)).print("0");
        verify(printerMock, times(Integer.parseInt(limit) - 1)).print("1");
    }
 
    @Test
    public void continuesOnCalculatorOrPrinterError() {
        when(numberCalculatorMock.calculate(anyInt()))
                .thenReturn("1")
                .thenThrow(new RuntimeException())
                .thenReturn("3");
        
        doThrow(new RuntimeException()).when(printerMock).print("3");
        
        ex1.printNumbers(3);
        
        verify(numberCalculatorMock, times(3)).calculate(anyInt());
        verify(printerMock).print("1");
        verify(printerMock).print("3");
    }
}
