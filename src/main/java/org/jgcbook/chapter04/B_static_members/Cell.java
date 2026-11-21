package org.jgcbook.chapter04.B_static_members;
// ch04_1_4
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicInteger;

class Cell<T> {
    private final int id;
    private final T value;
    private final static AtomicInteger count = new AtomicInteger();
    private final static AtomicInteger count1 = new AtomicInteger();
    private static int nextId() { return count.getAndIncrement(); }
    public Cell(T value) {
        this.value = value;
        id = nextId();
    }
    public T getValue() { return value; }
    public int getId() { return id; }
    public static int getCount() { return count.get(); }

	public static void main(String[] args)  {
		Cell<String> a = new Cell<String>("one");
		Cell<Integer> b = new Cell<Integer>(2);
		assert a.getId() == 0 && b.getId() == 1 && Cell.getCount() == 2;

//        List<Thread> threads = new ArrayList<>();
//        for (int j = 0; j < 8; j++) {
//            Thread t = new Thread(() ->
//                {
//                    for (int i = 0; i < 250000; i++) {
//                        count1.incrementAndGet();
//                    }
//                });
//            threads.add(t);
//	    }
//
//        for (int j = 0; j < 8; j++) {
//            threads.get(j).start();
//        }
//        for (int j = 0; j < 8; j++) {
//            try {
//                threads.get(j).join();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        System.out.println(count1.get());

        ForkJoinPool pool = new ForkJoinPool(8);
        List<RecursiveAction> tasks = new ArrayList<>();

        for (int j = 0; j < 8; j++) {
            tasks.add(new RecursiveAction() {
                @Override
                protected void compute() {
                    for (int i = 0; i < 250000; i++) {
                        count1.incrementAndGet();
                    }
                }
            });
        }

        for (RecursiveAction task : tasks) {
            pool.execute(task);
        }

        for (RecursiveAction task : tasks) {
            task.join();
        }

        pool.shutdown();

        System.out.println(count1.get());
    }
}