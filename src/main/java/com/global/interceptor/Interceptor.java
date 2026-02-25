package com.global.interceptor;

import com.global.rq.Rq;

public interface Interceptor {
  boolean run(Rq rq);
}
