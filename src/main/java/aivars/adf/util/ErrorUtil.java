package aivars.adf.util;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

public class ErrorUtil {

    public static FieldError newFieldError(String object, String field, Object value, String code) {
        String[] codes = new String[] { code };
        return new FieldError(object, field, value, false, codes, null, null);
    }

    public static ObjectError newObjectError(String object, String code) {
        String[] codes = new String[] { code };
        return new ObjectError(object, codes, null, null);
    }

}
