import org.junit.jupiter.api.Test;

public class Test01 {


    @Test
    public void test01(){
        //身份证格式判断  18位
        String idCard = "430521200205160493";
        String regex = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$";
        boolean matches = idCard.matches(regex);
        System.out.println(matches);
    }
}
