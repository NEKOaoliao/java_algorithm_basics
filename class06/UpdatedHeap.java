package class06;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

//不能插入地址相同的T value, 因为HashMap只能存储一条记录 
public class UpdatedHeap {
    
    public static class MyHeap<T> {
        private ArrayList<T> heap;
        private HashMap<T, Integer> indexMap;
        private int heapSize;
        private Comparator<? super T> comparator;
        
        public MyHeap(Comparator<? super T> comp) {
            heap = new ArrayList<>();
            indexMap = new HashMap<>();
            heapSize = 0;
            comparator = comp;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public int size() {
            return heapSize;
        }

        public boolean contains(T key) {
            return indexMap.containsKey(key);
        }

        public void push(T value) {
            heap.add(value);
            indexMap.put(value, heapSize);
            heapInsert(heapSize++);
        }

        public T pop() {
            T ans = heap.get(0);
            int end = heapSize - 1;
            swap(0, end);
            heap.remove(end);
            indexMap.remove(end);
            heapify(0, --heapSize);
            return ans;
        }


        private void heapInsert(int index) {
            while(comparator.compare(heap.get(index), heap.get((index - 1) / 2)) > 0) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        private void heapify(int index, int hsize) {
            int left = index * 2 + 1;
            while(left < hsize) {
                int largest = (left + 1 < hsize && comparator.compare(heap.get(left), heap.get(left + 1)) < 0)? left + 1: left;
                largest = comparator.compare(heap.get(index), heap.get(largest)) < 0? largest: index;
                if(largest == index) {
                    break;
                }
                swap(largest, index);
                index = largest;
                left = index * 2 + 1; 
            }
        }

        private void swap(int a, int b) {
            T o1 = heap.get(a);
            T o2 = heap.get(b);
            heap.set(a, o2);
            heap.set(b, o1);
            indexMap.put(o1, b);
            indexMap.put(o2, a);
            
        }

        public void resign(T value) {
            int valueIndex = indexMap.get(value);
            heapInsert(valueIndex);
            heapify(valueIndex, heapSize);
        }

        public static class Student {
            public String name;
            public int id;
            public int age;
    
            public Student(String name, int id, int age) {
                this.name = name;
                this.id = id;
                this.age = age;
            }
        }

        public static class StudentComparator implements Comparator<Student> {

            @Override
            public int compare(Student s1, Student s2) {
                return s1.age - s2.age;
            }
        }

        public static class StudentComparatorBigger implements Comparator<Student> {

            @Override
            public int compare(Student s1, Student s2) {
                return s2.age - s1.age;
            }
        }

        public static void main(String[] args) {
            Student s1 = null;
            Student s2 = null;
            Student s3 = null;
            Student s4 = null;
            Student s5 = null;
            Student s6 = null;

            s1 = new Student("a", 11111, 50);
            s2 = new Student("b", 22222, 60);
            s3 = new Student("c", 33333, 10);
            s4 = new Student("d", 44444, 20);
            s5 = new Student("e", 55555, 72);
            s6 = new Student("f", 66666, 14);

            PriorityQueue<Student> heap = new PriorityQueue<>(new StudentComparatorBigger());
            heap.add(s1);
            heap.add(s2);
            heap.add(s3);
            heap.add(s4);
            heap.add(s5);
            heap.add(s6);
            while(!heap.isEmpty()) {
                Student cur = heap.poll();
                System.out.println(cur.name + "," + cur.id + "," + cur.age);
            }

            System.out.println("====================================");

            MyHeap<Student> myHeap = new MyHeap<>(new StudentComparator());
            myHeap.push(s1);
            myHeap.push(s2);
            myHeap.push(s3);
            myHeap.push(s4);
            myHeap.push(s5);
            myHeap.push(s6);
            while(!myHeap.isEmpty()) {
                Student cur = myHeap.pop();
                System.out.println(cur.name + "," + cur.id + "," + cur.age);
            }

            System.out.println("====================================");

            s1 = new Student("a", 11111, 50);
            s2 = new Student("b", 22222, 60);
            s3 = new Student("c", 33333, 10);
            s4 = new Student("d", 44444, 20);
            s5 = new Student("e", 55555, 72);
            s6 = new Student("f", 66666, 14);

            heap = new PriorityQueue<>(new StudentComparatorBigger());
            heap.add(s1);
            heap.add(s2);
            heap.add(s3);
            heap.add(s4);
            heap.add(s5);
            heap.add(s6);

            s2.age = 6;
            s4.age = 12;
            s5.age = 10;
            s6.age = 84;

            while(!heap.isEmpty()) {
                Student cur = heap.poll();
                System.out.println(cur.name + "," + cur.id + "," + cur.age);
            }

            System.out.println("====================================");

            s1 = new Student("a", 11111, 50);
            s2 = new Student("b", 22222, 60);
            s3 = new Student("c", 33333, 10);
            s4 = new Student("d", 44444, 20);
            s5 = new Student("e", 55555, 72);
            s6 = new Student("f", 66666, 14);

            myHeap = new MyHeap<>(new StudentComparator());
            myHeap.push(s1);
            myHeap.push(s2);
            myHeap.push(s3);
            myHeap.push(s4);
            myHeap.push(s5);
            myHeap.push(s6);
            
            s2.age = 6;
            s4.age = 12;
            s5.age = 10;
            s6.age = 84;

            myHeap.resign(s2);
            myHeap.resign(s4);
            myHeap.resign(s5);
            myHeap.resign(s6);
            
            while(!myHeap.isEmpty()) {
                Student cur = myHeap.pop();
                System.out.println(cur.name + "," + cur.id + "," + cur.age);
            }
        }

    }
}
