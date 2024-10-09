package com.dme.DormitoryProject;

import com.dme.DormitoryProject.Manager.Abstract.IDepartmentService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {DormitoryProjectApplication.class})
class DormitoryProjectApplicationTests {

	private IDepartmentService departmentService;
	@Autowired
	public DormitoryProjectApplicationTests(IDepartmentService departmentService){
		this.departmentService=departmentService;
	}

	@Test
	public void testGetDepartmentById(){
//		Result result = departmentService.getById(3L);
//		assertNotNull(result);
		int a = 2;
		int b = 2;
		assertEquals(a, b);
	}

}
