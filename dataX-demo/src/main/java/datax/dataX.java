package datax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class dataX {
	//windows
	//json文件夹地址
	public static String jsonPath = "D:/客户服务中心/数据转换/绥中数据转换资料/射频系统脚本-京源/转入正式表";
	//datax的python文件地址
	public static String dataxPath = "D:/datax/bin/datax.py";
	//CCS服务器数据源
	public static String jdbcUrl = "jdbc:mysql://testeslinkmysqlwaiwang.mysql.rds.aliyuncs.com:3210/eslink_ccs_linshi?useUnicode=true^&characterEncoding=UTF-8";
	//CCS用户名 密码
	public static String userName = "eslink";
	public static String password = "eslink123456";
	//本地数据源
	public static String localJdbcUrl = "jdbc:sqlserver://localhost;DatabaseName=jy";
	//本地数据源用户名密码
	public static String localUserName = "sa";
	public static String localPassword = "123456";
	//租户
	public static String tenantId = "0467";
	//表具技术参数名
	public static String tparamName = "京源智能IC卡表";
	//城市名称-县级
	public static String cityName="葫芦岛市绥中县";
	//县级速记码
	public static String shortHandCode="1010";
	//用户名首位添加
	public static String userNoFirst="3";
	//用户名长度，最后长度=用户名长度+首位添加长度
	public static String userNoLen="9";
	//卡厂商码
	public static String cardTypeCode="60004";
	//卡号长度，除去区域码长度
	public static String cardNoLen="8";
	//卡区域码
	public static String areaCode="0238";


	public static void exeDatax(Map<String, String> map){

		try {
			System.out.println("------------------start----------------------");
//	            String[] str = {"28.转入表具止码信息.json"};
			String[] str =	getFileName(jsonPath);
			for (String name : str) {
				StringBuilder sb=new StringBuilder();
				sb.append("python ");
				sb.append(dataxPath);
				sb.append(" ");
				sb.append(jsonPath);
				sb.append("/");
				sb.append(name);
				sb.append(" -p ");
				sb.append("\"");
				map.keySet().forEach(key -> sb.append(" -D").append(key ).append("=").append(map.get(key)));
				sb.append("\"");
				String windowcmd=sb.toString();
				System.out.println(windowcmd);
				Process pr = Runtime.getRuntime().exec(windowcmd);
				BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
				String line = null;
				while ((line = in.readLine()) != null) {
					System.out.println(line);
				}
				in.close();
				pr.waitFor();
			}
			System.out.println("----------------end------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * 获取文件夹下所有 json 文件名
	 * @param path
	 * @return
	 */
	public static String[] getFileName(String path) {
		File file = new File(path);
		String[] fileName = file.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				if (name.endsWith(".json")) {
					return true;
				}
				return false;
			}
		});
		int[] aa=new int[fileName.length];
		for (int i = 0; i < fileName.length; i++) {
			String[] ss=fileName[i].split("\\.");
			aa[i]=Integer.parseInt(ss[0]);
		}
		int temp;
		String tempStr;
		for(int i=0;i<aa.length;i++)
		{
			for(int j=i+1;j<aa.length;j++)
			{
				if(aa[i]>aa[j])
				{
					temp=aa[i];
					aa[i]=aa[j];
					aa[j]=temp;

					tempStr=fileName[i];
					fileName[i]=fileName[j];
					fileName[j]=tempStr;
				}
			}
		}
		return fileName;

	}


	public static void main(String[] args) {
		//组装传入json的参数
		Map<String,String> property=new HashMap<>();
		property.put("userName", userName);
		property.put("password", password);
		property.put("jdbcUrl", jdbcUrl);
		property.put("localUserName", localUserName);
		property.put("localPassword", localPassword);
		property.put("localJdbcUrl", localJdbcUrl);
		property.put("tenantId", tenantId);
		property.put("tparamName", tparamName);
		property.put("cityName", cityName);
		property.put("shortHandCode", shortHandCode);
		property.put("userNoFirst", userNoFirst);
		property.put("userNoLen", userNoLen);
		property.put("cardTypeCode", cardTypeCode);
		property.put("cardNoLen", cardNoLen);
		property.put("areaCode", areaCode);
		exeDatax(property);
	}

}
