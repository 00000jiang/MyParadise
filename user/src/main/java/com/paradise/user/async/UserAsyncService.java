package com.paradise.user.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author jrf
 * @date 2023-3-31 10:25
 */
@Slf4j
@Component
public class UserAsyncService {

   @Async
   public void userAsync(){

   }

}
