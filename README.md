Equator 基于springBoot对象比较框架 

使用说明：
我们在项目中可能经常会遇到需要对两个字段进行比较哪些字段不一致，比如从前端用户对部分字段做了一些修改，
我们需要和数据库内容比较，知道哪些字段做了修改。并且本人支持使用了ql表达式，可通过表达式的方式配置字段
比较规则配置。

在性能上：本框架采用字节码高效反射。可支持并发调用。

在这里项目中，作者本人框架使用上，部分地方参考了mapStruct（一个字段映射框架）项目的使用方式，只不过将映射改为了
字段比较，提供了三种基于字段比较的方法：基于注解方式、基于构造者模式、基于动态动态构造者模式。

注解方式：如果读者想了解这种方式，具体demo在测试模式下有给出相应的例子：com.luhui.equator.springtest.annotation
这种模式使用，使用方法几乎和mapStruct注解方式相同。

基于构造者模式：如果你使用过mybatis,那么你应该不会陌生，这种模式和Mybatis中的@SelectProvider,@UpdateProvider,
@DeleteProvider,@InsertProvider思想相似，通过代码的形式构建字段映射方法。具体demo见测试包下目录：com.luhui.equator.springtest.provider

动态构造者模式：自创新模式，基于构造者模式会在第一次调用时候，就生成好字段映射规则，然后缓存起来了，这种方式可能
性能会略好，但在有些极端情况下可能就不那么适用了，故这种模式下，和构造者模式用法相似，不同的是，每次是不会缓存起来
映射结果集的，还有一种不同的是，在构造的地方，直接直接获取到对应的方法入参。
具体demo见测试包下目录：com.luhui.equator.springtest.dynamic

本框架在代码设计上：参考了mybatis源码的设计，因此如果大家对Mybatis代码比较熟的话，可能会发现很多相似的设计。