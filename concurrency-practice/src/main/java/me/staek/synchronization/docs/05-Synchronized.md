# Synchronized



## 개요

- 자바는 단일 연산 특성을 보장하기 위해 synchronized 키워드를 제공하고 있으며 synchronized 구문을 통해 모니터 영역을 동기화 할수 있다
- synchronized 는 명시적으로 락을 구현하는 것이 아닌 자바에 내장된 락으로서 이를 암묵적인 락(Intrinsic Lock) 혹은 모니터락 (Monitor Lock) 이라고 한다
- synchronized 은 동일한 모니터를 가진 객체에 대해 오직 하나의 스레드만 임계영역에 접근할 수 있도록 보장하며 모니터의 조건 변수를 통해 스레드간 협력으로 동기화를 보장해 준다
- synchronized 가 적용된 한 개의 메서드만 호출해도 같은 모니터의 모든 synchronized 메서드까지 락에 잠기게 되어 락이 해제될 때 까지는 접근이 안되는 특징을 가지고 있다 
- 락은 스레드가 synchronized 블록에 들어가기 전에 자동 확보되며 정상적이든 비정상적이든 예외가 발생해서든 해당 블록을 벗어날 때 자동으로 해제된다







## synchronized 동기화 

- **스레드 간 객체의 메서드를 동기화하기 위해서는 스레드는 같은 객체의 모니터를 참조하고 있어야 한다.**



### 메서드 동기화 방식 - synchronized method

- 메소드 전체가 임계 영역(critical section)이 된다.
- 동시성 문제를 한번에 편리하게 제어할 수 있는 장점은 있으나 메서드 내 코드의 세부적인 동기화 구조를 가지기 어렵다
- 메서드 전체를 동기화하기 때문에 동기화 영역이 클 경우 성능저하를 가져올 수 있다.

~~~java
public synchronized void instanceMethod() {
    instanceCount++;
    System.out.println("인스턴스 메서드 동기화: " + instanceCount);
}

public static synchronized void staticMethod() {
    staticCount++;
    System.out.println("정적 메서드 동기화: " + staticCount);
}
~~~





### 블록 동기화 방식 - synchronized block

- 특정 블록을 정해서 임계 영역(critical section)을 구성한다.
- 메서드 동기화 방식에 비해 좀 더 세부적으로 임계영역을 정해서 **필요한 블록만 동기화 구조**를 가질 수 있다
- 메서드 전체를 동기화 하는 것보다 동기화 영역이 작고 효율적인 구성이 가능하기 때문에 성능 저하가 덜하다

~~~java
public void instanceBlock() {
    synchronized (this) {
        instanceCount++;
        System.out.println("인스턴스 블록 동기화: " + instanceCount);
    }
}

public static void staticBlock() {
    synchronized (_01_Synchronized_type.class) {
        staticCount++;
        System.out.println("정적 블록 동기화: " + staticCount);
    }
}
~~~







## 재진입성

- 모니터 내에서 이미 synchronized 영역에 들어간 스레드가 다시 같은 모니터 영역으로 들어갈 수 있는데, 이를 **모니터 재진입** 이라고 한다
- 재진입 가능하다는 것은 락의 획득이 호출 단위가 아닌 **스레드 단위**로 일어난다는 것을 의미하며 이미 락을 획득한 스레드는 같은 락을 얻기 위해 대기할 필요 없이 synchronized 블록을 만났을 때 같은 락을 확보하고 진입한다





#### **자식은 부모의 락과 동일한 락을 가지게 된다**

~~~java
class Parent {
    public synchronized void parentMethod() {
        System.out.println("call Parent method");
    }
}

class Child extends Parent {
    public synchronized void childMethod() {
        System.out.println("call Child method");
        parentMethod();
    }
}
public class _07_Reentrant {
    public static void main(String[] args) {
        Child child = new Child();

        Thread t1 = new Thread(() -> {
            child.childMethod();
        });
        t1.start();
        try { t1.join(); } catch (InterruptedException e) {}
    }
}
~~~







## 예제

~~~
monitor/ 
_01_ ~ _09_ 예제 참고 ^^
~~~















