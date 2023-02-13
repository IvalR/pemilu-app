package barrans.devel.api.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserUtil {

    public static Boolean isEmail(String input){
        Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    public static Boolean isNama(String input){
        Pattern pattern = Pattern.compile("^[a-zA-Z ]*$");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }
}
