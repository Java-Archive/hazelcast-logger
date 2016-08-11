package com.hazelcast.util;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;

import static org.junit.Assert.assertEquals;

public class ConcurrencyUtilTest {

  @Test
  public void setMax() {
    setMax(10, 9);
    setMax(10, 10);
    setMax(10, 11);
  }

  public void setMax(long current, long update) {
    LongValue longValue = new LongValue();
    longValue.value = current;

    ConcurrencyUtil.setMax(longValue, LongValue.UPDATER, update);

    long max = Math.max(current, update);
    assertEquals(max, longValue.value);
  }

  private final static class LongValue {
    final static AtomicLongFieldUpdater UPDATER = AtomicLongFieldUpdater.newUpdater(LongValue.class, "value");
    volatile long value;
  }
}
