package com.luhui.equator.springtest.provider;

import com.luhui.equator.EquatorResult;
import com.luhui.equator.Student;
import com.luhui.equator.annotations.EquatorProvider;
import com.luhui.equator.spring.Equator;

import java.util.List;

/**
 * <p>  </p>
 *
 * <pre> Created: 2019/12/5 15:53 </pre>
 *
 * @author hlu
 * @version 1.0
 */
@Equator
public interface StudentProviderSpringEquator {

    @EquatorProvider(provider = MyEquatorProvider1.class)
    List<EquatorResult> provider1(Student student1, Student student2);
}
