import java.util.Collections;
import java.util.PriorityQueue;

public class MedianFinder {
    PriorityQueue<Integer> maxheap;
    PriorityQueue<Integer> minheap;

    MedianFinder() {
        maxheap = new PriorityQueue<>(Collections.reverseOrder());
        minheap = new PriorityQueue<>();
    }

    void addNum(int num) {
        int lsize = maxheap.size();
        int rsize = minheap.size();
        if (lsize == 0) {
            maxheap.offer(num);
        }

        else if (lsize == rsize) {
            if (num < minheap.peek()) {
                maxheap.offer(num);
            }
            else {
                int temp = minheap.peek();
                minheap.poll();
                minheap.offer(num);
                maxheap.offer(temp);
            }
        }

        else {
            if (minheap.size() == 0) {
                if (num > maxheap.peek()) {
                    minheap.offer(num);
                }
                else {
                    int temp = maxheap.peek();
                    maxheap.poll();
                    maxheap.offer(num);
                    minheap.offer(temp);
                }
            }

            else if (num >= minheap.peek()) {
                minheap.offer(num);
            }

            else {
                if (num < maxheap.peek()) {
                    int temp = maxheap.peek();
                    maxheap.poll();
                    maxheap.offer(num);
                    minheap.offer(temp);
                }

                else {
                    minheap.offer(num);
                }
            }
        }
    }

    double findMedian() {
        int lsize = maxheap.size();
        int rsize = minheap.size();
        if (lsize > rsize) {
            return (double) maxheap.peek();
        }
        else {
            return ((double) maxheap.peek() + (double) minheap.peek()) / 2;
        }
    }

    void display() {
        System.out.println("MaxHeap : " + maxheap);
        System.out.println("MinHeap : " + minheap);
    }

    public static void main(String[] args) {
        MedianFinder mf = new MedianFinder();
        mf.addNum(5);
        mf.display();
        System.out.println("Median : " + mf.findMedian());
        System.out.println();

        mf.addNum(3);
        mf.display();
        System.out.println("Median : " + mf.findMedian());
        System.out.println();

        mf.addNum(4);
        mf.display();
        System.out.println("Median : " + mf.findMedian());
        System.out.println();

        mf.addNum(2);
        mf.display();
        System.out.println("Median : " + mf.findMedian());
        System.out.println();

        mf.addNum(6);
        mf.display();
        System.out.println("Median : " + mf.findMedian());
    }
}