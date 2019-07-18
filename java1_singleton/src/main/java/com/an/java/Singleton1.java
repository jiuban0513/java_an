package com.an.java;

/**
 * 懒汉式，线程不安全
 * 是否 Lazy 初始化：是
 * 是否多线程安全：否
 * 实现难度：易
 * 描述：这种方式是最基本的实现方式，这种实现最大的问题就是不支持多线程。因为没有加锁 synchronized，所以严格意义上它并不算单例模式。
 * 这种方式 lazy loading 很明显，不要求线程安全，在多线程不能正常工作。
 */
public class Singleton1 {
    public static void main(String[] args) {
        String a = new String("ab"); // a 为一个引用
        String b = new String("ab"); // b为另一个引用,对象的内容一样
        String aa = "ab"; // 放在常量池中
        String bb = "ab"; // 从常量池中查找
        System.out.println(a==b);
        System.out.println(a==aa);
        System.out.println(a==bb);
        System.out.println(b==aa);
        System.out.println(b==bb);
        System.out.println(aa==bb);
        System.out.println("-------------");
        System.out.println(a.equals(b));
        System.out.println(a.equals(aa));
        System.out.println(a.equals(bb));
        System.out.println(b.equals(aa));
        System.out.println(b.equals(bb));
        System.out.println(aa.equals(bb));
    }
}
