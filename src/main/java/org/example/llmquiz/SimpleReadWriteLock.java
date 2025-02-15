package org.example.llmquiz;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class SimpleReadWriteLock {
  private int readers = 0;
  private boolean isWriting = false;
  private final Lock lock = new ReentrantLock();
  private final Condition canRead = lock.newCondition();
  private final Condition canWrite = lock.newCondition();

  public void lockRead() throws InterruptedException {
    lock.lock();
    try {
      while (isWriting) { // Wait if a writer is active
        canRead.await();
      }
      readers++; // Allow multiple readers
    } finally {
      lock.unlock();
    }
  }

  public void unlockRead() {
    lock.lock();
    try {
      readers--;
      if (readers == 0) {
        canWrite.signal(); // Notify waiting writers when last reader is done
      }
    } finally {
      lock.unlock();
    }
  }

  public void lockWrite() throws InterruptedException {
    lock.lock();
    try {
      while (isWriting || readers > 0) { // Wait if there are active readers/writers
        canWrite.await();
      }
      isWriting = true; // Acquire write lock
    } finally {
      lock.unlock();
    }
  }

  public void unlockWrite() {
    lock.lock();
    try {
      isWriting = false;
      canRead.signalAll(); // Allow readers to proceed
      canWrite.signal(); // Allow next writer to proceed
    } finally {
      lock.unlock();
    }
  }
}
