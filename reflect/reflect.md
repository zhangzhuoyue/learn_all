Type类详解
什么是Type?
Type是一个空接口，所有类型的公共接口(父类)。它的意义标识java所有类型。这里的类型是从Java整个语言的角度来看，比如原始类型，参数化类型(泛型)，类 型变量及其数组等。可以理解为。Class(类)是java对现实对象的抽象。而Type是对java语言对象的抽象。

Type 的类型
实现了Type接口的子接口为GenericArrayType(泛型数组类型)，ParmeterizedType(参数化类型)，

Type的用处
Type也主要是为在运行时期获取泛型而服务。
编码过程中主要使用到的泛型情况
1.List<T>
2.Set<T> 
3.Map<K,V>
4.class<?>(自定义类或接口的泛型)

ParmeterizedType(参数化类型)的作用
parameterizedType主要是用来表示 如 Collection<String>或者Class<T>。parameterizedType表示的类型非常的简单，只要带着泛型，除了不可以是数组和本类上定义的泛型以外，其他都被列入ParameterizedType的范围。







