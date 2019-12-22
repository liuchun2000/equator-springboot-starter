package com.luhui.equator;

import org.springframework.stereotype.Component;

/**
 * <p>  </p>
 *
 * <pre> Created: 2019/12/5 15:56 </pre>
 *
 * @author hlu
 * @version 1.0
 */
@Component("beanTest")
public class BeanTest {

    public boolean test1(Student stu1,Student stu2,boolean test){
        System.out.println("test1....");
        return test;
    }
}
