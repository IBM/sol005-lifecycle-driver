package com.ibm.nfvodriver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/*@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( loader = AnnotationConfigContextLoader.class)
public class NFVODriverApplicationTests{

	@Test
	public void contextLoads() {
	}

}
