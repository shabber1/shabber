package testng;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class sampleTest {

  @Test
  public void demo() {
    Reporter.log("demo",true);
  }
}
