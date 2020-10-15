package com.wang.code;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class IteratorDemo {

    public static void main(String[] args) {
        /*
         * 一、前言
         *        迭代器是一个对象,它可以让你遍历一个容器并且有选择性的删除 容器 中的元素,而我们不需要知道 容器 的内部结构.
         *        Java有两种原生的迭代器:Iterator和ListIterator, 其中 ListIterator继承自 Iterator.
         *
         * 二、Iterator接口

         *     Iterator 通常被称为轻量级对象,因为创建它的开销比较小.可以通过调用容器的 iterator()方法来获取它的Iterator.
         *
         *       下面是Iterator接口定义:
         *
         *       public interface Iterator<E> {
         *            boolean hasNext();
         *
         *            E next();
         *
         *            default void remove() {
         *                throw new UnsupportedOperationException("remove");
         *            }
         *
         *            default void forEachRemaining(Consumer<? super E> action) {
         *                Objects.requireNonNull(action);
         *                while (hasNext())
         *                    action.accept(next());
         *            }
         *        }
         *
         * Java的 Iterator只能单向移动,它只能用来:
         *     (1)使用容器的 iterator ()方法返回它的Iterator.Iterator将返回容器的第一个元素.
         *     (2)使用next()方法返回容器的下一个元素.
         *     (3)使用hasNext()方法检查容器中是否还有元素. 如果有,hasNext()方法返回 true.
         *     (4)使用 remove()方法删除由 next()方法新近返回的元素.
         *     下面来看一个使用示例:
         *
         *     代码清单-2
         *
         *
         */
        ArrayList<String> a = new ArrayList<>();
        a.add("aaa");
        a.add("bbb");
        a.add("ccc");
        System.out.println("Before iterate : " + a);
        Iterator<String> it = a.iterator();
        while (it.hasNext()) {
            String t = it.next();
            if ("bbb".equals(t)) {
                it.remove();
            }
        }
        System.out.println("After iterate : " + a);

        /*
         * 三、ListIterator接口
         *     ListIterator是一个功能更加强大的迭代器, 它只能用于各种List类型的访问。可以通过调用listIterator()方法产生一个指向List开始处的ListIterator, 还可以调用listIterator(n)方法创建一个一开始就指向列表索引为n的元素处的ListIterator.
         *
         *     ListIterator接口定义如下:
         *
         *     public interface ListIterator<E> extends Iterator<E> {
         *         boolean hasNext();
         *         E next();
         *         boolean hasPrevious();
         *         E previous();
         *         int nextIndex();
         *         int previousIndex();
         *         void remove();
         *         void set(E e);
         *         void add(E e);
         *     }
         *     由以上定义我们可以推出ListIterator可以:
         *
         *     (1)双向移动.
         *
         *     (2)产生相对于迭代器在列表中指向的当前位置的前一个和后一个元素的索引.
         *
         *     (3)可以使用set()方法替换它访问过的最后一个元素.
         *
         *     (4)可以使用add()方法在next()方法返回的元素之前或previous()方法返回的元素之后插入一个元素.
         *
         *     下面就简单演示所有这些功能:
         *
         *          代码清单-4
         *
         */

        ArrayList<String> a1 = new ArrayList<String>();
        a.add("aaa");
        a.add("bbb");
        a.add("ccc");
        System.out.println("Before iterate : " + a1);
        ListIterator<String> it1 = a1.listIterator();
        while (it.hasNext()) {
            System.out.println(it1.next() + ", " + it1.previousIndex() + ", " + it1.nextIndex());
        }
        while (it1.hasPrevious()) {
            System.out.print(it1.previous() + " ");
        }
        System.out.println();
        it1 = a1.listIterator(1);
        while (it1.hasNext()) {
            String t1 = it.next();
            if ("ccc".equals(t1)) {
                it1.set("nnn");
            } else {
                it1.add("kkk");
            }
        }
        System.out.println("After iterate : " + a);


        /*     输出结果如下:
         *
         *
         * Before iterate : [aaa, bbb, ccc]
         * aaa, 0, 1
         * bbb, 1, 2
         * ccc, 2, 3
         * ccc bbb aaa
         * After iterate : [aaa, bbb, kkk, nnn]
         *
         *
         * 四、Iterator使用场景
         *     在以下情况下可以使用Iterator接口代替for-each结构:
         *
         *     (1)删除当前的元素.for-each结构隐藏了迭代器, 所以你不能调用remove()方法. 因此for-each结构无法用于过滤.
         *     (2)并行地迭代多个集合.
         *
         *         下面的代码片段展示了如何使用一个迭代器来过滤任意一个集合——即遍历这个集合并删除指定元素.
         *
         *         代码清单-5
         *
         *
         * static void filter(Collection<?> c) {
         *     for (Iterator<?> it = c.iterator(); it.hasNext(); )
         *         if (!cond(it.next()))
         *             it.remove();
         * }
         *              以上代码是多态的, 意味着它对任何集合都起作用, 而不用去考虑具体的实现.可见使用Java集合框架来编写多态的算法是多么的简单.
         */

    }
}


