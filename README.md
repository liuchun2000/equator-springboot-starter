**Equator 基于springBoot对象比较框架** 

使用说明：由于工作中用到了大量的字段比较，而且市场上没有找到比较好的框架，决定自己造轮子。此框架则是专为字段比较而生。



注意事项：

（1）使用方法和mybatis类似，只需编写接口，即可在spring项目里直接注入。

（2）返回值说明： 必须是List类型，并且泛型是EquatorResult或者是他的子类。

EquatorResult属性说明：

| 属性名       | 类型   | 中文名                                                       |
| ------------ | ------ | ------------------------------------------------------------ |
| sourceField  | String | 源字段名（默认第一个参数为源对象）                           |
| sourceObject | Object | 源字段值：在某个字段比较不相同的情况下<br/>默认会装配这个字段的值。比如比较两个<br/>student，第一个对象sex = 'boy'，第二个<br/>对象sex = 'girl'，那么sourceObject就是<br/> ‘boy’。当然，你可能并不想装配原始的值<br/>，比如sex对象中存的是枚举，你想把他转<br/>换成中文，可通过注解或其他配置方式手动<br/>重写配置规则。 |
| targetField  | String | 目标字段名（默认第二个参数为目标对象）                       |
| targetValue  | Object | 目标字段值。同理和sourceObject相同，<br/>可通过配置手动重写装配规则。 |
| fieldName    | String | 字段名，可通过注解或其他方式配置指定                         |

（3）入参说明：入参至少要有两个，默认情况下从第三个参数开始不会被使用，除非你显式使用他。
（4）比较方式：默认情况下对于数值类型会比较值是否相等（数值类型不使用equals的原因是为了考虑bigDecimal的情况），对于其他类型会使用equals进行比较。如果仍然不满足所需，可以使用ql表达式自定义比较规则。
（5）注入方式：确保当前类能被springboot扫描到，然后可以在任何地方直接使用接口注入使用。



在性能上：本框架采用字节码高效反射。可支持并发调用。已经过线上项目测试。



这里演示几个示例：

无论以哪种方式，我们都需要编写接口，并在接口上打上@Equator注解。



示例一，默认比较：

```java
@Equator
public interface StudentAnnotationSpringEquator {

    /**
     * 不添加任何配置，对比所有相同属性的字段
     * @param student1
     * @param student2
     * @return
     */
    List<EquatorResult> defaultEquator(Student student1, Student student2);
}
```
这例子我们在接口中定义了一个defaultEquator方法，将进行两个对象进行比较。没有加任何注解修饰，则会走默认的比较逻辑。默认情况下：会对入参1和入参2所有相同的字段进行字段比较。



示例二：使用注解方式显式指定比较字段名不相同的字段：

```java
@Equator
public interface StudentAnnotationSpringEquator {

    /**
     * 手动指定比较iid和id属性
     * @param student1
     * @param student2
     * @return
     */
    @EquatorMappings(value = {
            @EquatorMapping(source = "id",target = "iid")
    },byDefault = true)
    List<EquatorResult> defaultEquator(Student student1, StudentDto student2);
}
```

入参的两个对象可以是不同类的实例，这里演示了比较不同的属性名的比较，注意我们标记了byDefault=true（默认值也是true），他的意思是，如果这两个类的相同的属性名，也会进行比较，如果指定成了false则仅对比手动指定的属性。



示例三：使用注解方式排除比较某个字段：

```java
@Equator
public interface StudentAnnotationSpringEquator {

    /**
     * 排除name属性比较
     * @param student1
     * @param student2
     * @return
     */
    @EquatorMappings(excludes = "name")
    List<EquatorResult> defaultEquator(Student student1, StudentDto student2);
}
```



示例四：使用注解方式指定字段比较不相同的时候目标对象值返回的装配规则：

```java	
@Equator
public interface StudentAnnotationSpringEquator {

    /**
     * 性别枚举转中文
     * @param student1
     * @param student2
     * @return
     */
    @EquatorMappings(value = {
            @EquatorMapping(source = "sex",target = "sex",notEquatorSourceVal='source.toSexChinese()',notEquatorTargetVal='target.toSexChinese()')
    },byDefault = true)
    List<EquatorResult> defaultEquator(@EquatorParam("source") Student student1, @EquatorParam("target")StudentDto student2);
}
```



这里手动指定了两个参数的名称，指定后可以直接在notEquatorSourceVal或notEquatorTargetVal中使用，当然，你也可以传个第三个参数过来，指定下参数，也可以在表达式中使用。这个表达式中，也可以通过bean的名称直接访问spring bean对象。



示例五：使用自定义表达式比较字段：

```java
@Equator
public interface StudentAnnotationSpringEquator {

    /**
     * 比较两个对象，stu1等于5就算相等
     * @param student1
     * @param student2
     * @return
     */
 @EquatorMappings(value = {
            @EquatorMapping(source = "id",
                    target = "id",
                    equalsType = EquatorType.EXPRESSION,
                    expression = "stu1.id==5")
    },byDefault = false)
    List<EquatorResult> defaultEquator(@EquatorParam("stu1") Student student1, StudentDto student2);
    
    @EquatorMappings(value = {
            @EquatorMapping(source = "id",
                    target = "id",
                    equalsType = EquatorType.EXPRESSION,
                    expression = "beanTest.test1(student1,student2,test)")
    },byDefault = false)
    List<EquatorResult> annotationEquator5(@EquatorParam("student1") Student student1,
                                           @EquatorParam("student2") Student student2,
                                           @EquatorParam("test") boolean test);
}
```



这里演示了两个通过表达式比较的示例，这个表达式中也可以使用第三个参数，也可以通过beanName访问springbean。



以上所有方式配置都是注解方式比较的，其实我们还支持通过代码去构建。

示例六、使用代码去构建配置:

```java
@Equator
public interface StudentProviderSpringEquator {

    /*
    * 这里指定了一个MyEquatorProvider1，他会在spring启动的时候去构建好这个配置，和注解配置类似
    */
    @EquatorProvider(provider = MyEquatorProvider1.class)
    List<EquatorResult> provider1(Student student1, Student student2);
}

@Component
public class MyEquatorProvider1 implements IEquatorProvider {
    
     /*
    * 这里主要是通过代码方式把EquatorFieldMapping这些字段的配置构建出来，注解里所有的属性这里都有
    * 这种方式适用于读取配置文件方式构建。
    */
    @Override
    public Set<EquatorFieldMapping> provide(Method method) {
        Set<EquatorFieldMapping> equatorFieldMappings = new HashSet<>();
        equatorFieldMappings.add(new EquatorFieldMapping("id","id"));
        return equatorFieldMappings;
    }

    @Override
    public boolean byDefault(Method method) {
        return false;
    }
}

```

注意的是：这种方式构建仅在启动的时候构建一次，后续不会重新构建！！如果是读取数据库的配置，而数据库经常会修改，就需要重启，那这种方式就不适用了，可参考示例七。



示例七：使用代码去构建，且实现生效：

```java
@Equator
public interface StudentProviderSpringEquator {

    /*
    * 这里指定了一个MyEquatorProvider1，他会在spring启动的时候去构建好这个配置，和注解配置类似
    */
    @EquatorDynamicProvider(provider = MyEquatorProvider1.class)
    List<EquatorResult> provider1(Student student1, Student student2);
}

@Component
public class MyEquatorProvider1 implements IDynamicEquatorProvider {
    
     /*
    * 这里方式也是通过代码方式把EquatorFieldMapping这些字段的配置构建出来，
    * 与IEquatorProvider不同的是，这种方式每次调用都会重新构建一次配置，且线程安全
    */
    @Override
    public Set<EquatorFieldMapping> provide(ObjectCompareContext objectCompareContext) {
        Set<EquatorFieldMapping> equatorFieldMappings = new HashSet<>();
        equatorFieldMappings.add(new EquatorFieldMapping("id","id"));
        return equatorFieldMappings;
    }

    @Override
    public boolean byDefault(Method method) {
        return false;
    }
}

```



这种方式比较适用于读取数据库配置，且修改数据配置后无需重启项目。



总结：

相信使用过mybatis或mapstruct的朋友们会很快上手这个项目，具体用法远不止这么多，可以参与项目的单元测试或源码，希望能给大家有所帮助，目前不支持级联对象的对象，如果有有志之志也欢迎一起完善。