package com.ibm.nfvodriver;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/*@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")*/
@ContextConfiguration( loader = AnnotationConfigContextLoader.class)
public class NFVODriverApplicationTests{

	@Test
	public void contextLoads() {
	}

}
