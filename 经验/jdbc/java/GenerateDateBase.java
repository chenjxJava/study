import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import domain.Table;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * @Author: chenjx
 * @Description:
 * @Date: Created in 9:47 2017-09-29
 * @Modified By:
 */
public class GenerateDateBase {
	@Test
	public void genertate()  {
		try {
		Map<String, Table> map = JDBCUtils.getTables(JDBCUtils.getConnection());
		System.out.println(map);
		Map<String, Object> propMap = new HashMap<>();
		propMap.put("root", map);

		Configuration config  = new Configuration(Configuration.VERSION_2_3_23);
		config.setDefaultEncoding("UTF-8");
		config.setDirectoryForTemplateLoading(new File("F:\\ideaProject\\ssm-empty170920\\ssm-empty\\freemark\\src\\main\\template"));
		//Template template = config.getTemplate("template.ftl");
		Template template = config.getTemplate("template.xml");
		template.process(propMap, new FileWriter("F:\\ideaProject\\ssm-empty170920\\ssm-empty\\freemark\\src\\main\\generate\\datebase.doc"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
