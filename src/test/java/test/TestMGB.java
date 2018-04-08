package test;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

public class TestMGB {
	@Test
	public void testgenerator()  {
	
		   try {
			List<String> warnings = new ArrayList<String>();
			   boolean overwrite = true;
			   File configFile = new File("D:/kit/workspace/yjbpoint/src/main/resources/mybatis/oracleGeneratorConfig.xml");
			   ConfigurationParser cp = new ConfigurationParser(warnings);
			   Configuration config = cp.parseConfiguration(configFile);
			   DefaultShellCallback callback = new DefaultShellCallback(overwrite);
			   MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
			   myBatisGenerator.generate(null);
		} catch (IOException | XMLParserException | InvalidConfigurationException | SQLException
				| InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   
	}
}
