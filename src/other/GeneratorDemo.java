package other;


import javax.sql.DataSource;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.generator.Generator;
import com.jfinal.plugin.c3p0.C3p0Plugin;

/**
 * GeneratorDemo
 */
public class GeneratorDemo {
	
	public static DataSource getDataSource() {
		C3p0Plugin c3p0Plugin = new C3p0Plugin("jdbc:mysql://127.0.0.1/assignsys?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull"
				,"root","950308");
		c3p0Plugin.start();
		return c3p0Plugin.getDataSource();
	}
	
	public static void main(String[] args) {
		// base model 所使用的包名
		String baseModelPackageName = "model";
		// base model 文件保存路径
		String baseModelOutputDir = PathKit.getWebRootPath() + "/../src/model";
		
		// model 所使用的包名 (MappingKit 默认使用的包名)
		String modelPackageName = "model";
		// model 文件保存路径 (MappingKit 与 DataDictionary 文件默认保存路径)
		String modelOutputDir = baseModelOutputDir;
		
		// 创建生成器
		
		Generator gernerator = new Generator(getDataSource(), baseModelPackageName, baseModelOutputDir, modelPackageName, modelOutputDir);

		// 设置是否在 Model 中生成 dao 对象
		gernerator.setGenerateDaoInModel(true);
		// 设置是否生成字典文件
		gernerator.setGenerateDataDictionary(false);

		// 生成
		gernerator.generate();
	}
}




