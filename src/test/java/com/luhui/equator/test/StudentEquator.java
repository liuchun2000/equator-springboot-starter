package com.luhui.equator.test;

import com.luhui.equator.EquatorResult;
import com.luhui.equator.Student;

import java.util.List;

/**
 * <p>  </p>
 *
 * <pre> Created: 2019/11/27 10:29 </pre>
 *
 * @author hlu
 * @version 1.0
 */
public interface StudentEquator {

    List<EquatorResult> equator(Student student1, Student student2);

}
