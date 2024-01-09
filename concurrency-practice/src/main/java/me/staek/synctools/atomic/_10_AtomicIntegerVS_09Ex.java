package me.staek.synctools.atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Atomic variable
 * - 09번과 같은 수행조건에 대해 Atomic variable으로 로직을 작성해 보았다.
 * - 09와 다르게 대상 클래스에 Atomic 로직이 작성되어야 한다. 대신 호출자는 로직이 간단하다.
 * - 또, 런타임에 예외가 발생한다는 리스크가 없다.
 */
public class _10_AtomicIntegerVS_09Ex {

    public static void main(String[] args) {
        Class1 instance = new Class1();
        System.out.println("변경값: " + instance.getField1());
        System.out.println("변경값: " + instance.getField2());
    }

    public static class Class1 {
        private AtomicInteger field1 = new AtomicInteger();
        private AtomicReference<String> field2 = new AtomicReference<>();

        public int getField1() {
            return field1.addAndGet(111);
        }

        public String getField2() {
            if (field2.compareAndSet(null, "seongtki")) {
                return field2.get();
            } else {
                return field2.get();
            }
        }
    }
}
