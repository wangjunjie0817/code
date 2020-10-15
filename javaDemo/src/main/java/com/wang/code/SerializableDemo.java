package com.wang.code;

import java.io.*;

public class SerializableDemo {

    public static class Person implements Serializable {

        private static final long serialVersionUID = 1L;

        public Person(Integer age, String name){
            this.age = age;
            this.name = name;
        }

        private transient Integer age;
        private String name;

        private void readObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
            inputStream.defaultReadObject();
            this.age = (Integer) inputStream.readObject();
        }

        private void writeObject(ObjectOutputStream outputStream) throws IOException, ClassNotFoundException {
            outputStream.defaultWriteObject();
            this.age = 1000;
            outputStream.writeObject(this.age);
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "age: " + age + " name: " + name;
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // 写文件
        FileOutputStream fileOutputStream = new FileOutputStream("/Users/wangjunjie/Desktop/person.txt");
        ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
        Person person = new Person(27, "wang");
        outputStream.writeObject(person);
        fileOutputStream.close();
        outputStream.close();

        // 从文件中读取对象数据
        FileInputStream fileInputStream = new FileInputStream("/Users/wangjunjie/Desktop/person.txt");
        ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
        Person object = (Person) inputStream.readObject();
        fileInputStream.close();
        inputStream.close();


        System.out.println(object.toString());
        System.out.println(object == person);
    }
}
