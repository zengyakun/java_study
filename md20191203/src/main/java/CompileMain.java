
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *@ClassName CompileMain
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/8/9 13:58
 *@Version 1.0
 **/
public class CompileMain {

    public static void main(String[] args) throws IOException {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        int result = compiler.run(null, null, null, "cc.eslink.compiler.test.java");
        System.out.println(result == 0 ? "编译成功" : "编译失败");

        Process process = Runtime.getRuntime().exec("cc.eslink.compiler.test");
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = null;
        int i = 1;
        while (null != (line = br.readLine())) {
            System.out.println(i++ + ":" + line);
        }
    }





}
