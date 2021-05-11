package edu.cugb.jts.cugber.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * An formatted object that all controller return. The reason why
 * using CommonResult but not the original object is that frontend
 * developers will resolve it smoothly.
 *
 * @author Derrings
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult implements Serializable {
    private Integer code;
    private String message;
    private Object result;

    /**
     * Maybe you just want to return a message about whether this request
     * be handled successfully.
     */
    public static CommonResult justBoolean(boolean success) {
        return success
                ? new CommonResult(200, "Success", null)
                : new CommonResult(400, "Fail", null);
    }

    /**
     * If an request is the type of finding some resources, use this.
     *
     * @param found whether you have found something.
     * @param result if you have any result no matter found or not,
     *               you can input and the method will return it as well.
     */
    public static CommonResult foundOrNot(boolean found, Object result) {
        return found
                ? new CommonResult(200, "Find successfully", result)
                : new CommonResult(404, "Resource not found.", result);
    }

    /**
     * If result is null, method will consider it as a failed result.
     */
    public static CommonResult foundOrNot(Object result) {
        return result != null
                ? new CommonResult(200, "Find successfully", result)
                : new CommonResult(404, "Resource not found.", null);
    }
}
