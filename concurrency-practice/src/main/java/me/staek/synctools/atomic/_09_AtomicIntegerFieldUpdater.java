package me.staek.synctools.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * Atomic*FieldUpdater
 *
 * - 원하는 인스턴스의 필드를  addAndGet, compareAndSet 으로 원자성을 보장하는 연산을 수행한다.
 * - 업데이트할 속성은 반드시 volatile 이 선언되어야 한다.(예외발생)
 * - 런타임에 Reflection 에의해 필드를 검색하고 동작한다.
 *   - 속성은 private 이면 Reflection이 접근권한이 없어 변경 불가하다.
 *   - 아마도,, 동시성 특성상 공유자원에 해당하는 속성이 private 이면 당연히 사용불가한거 같기도 하다.
 *
 * - Atomic variable 대비 장점
 *   - 대상 class에 동기화 로직을 작성 안되 있을 경우, 호출하는 곳에서 처리할 수 있다.
 */
public class _09_AtomicIntegerFieldUpdater {
    static AtomicIntegerFieldUpdater<Class1> field1;
    static AtomicReferenceFieldUpdater<Class1, String> field2;

    public static void main(String[] args) {

        field1 = AtomicIntegerFieldUpdater.newUpdater(Class1.class, "field1");
        field2 = AtomicReferenceFieldUpdater.newUpdater(Class1.class, String.class, "field2");

        Class1 instance = new Class1();
        field1.addAndGet(instance, 111); // 정수 증가
        field2.compareAndSet(instance, null, "seongtki"); // CAS

        System.out.println("변경값: " + instance.getField1());
        System.out.println("변경값: " + instance.getField2());
    }

    public static class Class1 {
        private volatile int field1;
        private volatile String field2;

        public int getField1() {
            return field1;
        }

        public String getField2() {
            return field2;
        }
    }

}
