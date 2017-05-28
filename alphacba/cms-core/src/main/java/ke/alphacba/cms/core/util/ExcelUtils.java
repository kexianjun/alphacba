package ke.alphacba.cms.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import ke.alphacba.cms.core.exception.BizException;

public class ExcelUtils {
	
	
	/**
	 * @param inputStream 
	 * @param columnsName 列名（必须有顺序）
	 * @return
	 * @throws BiffException
	 * @throws IOException
	 * @throws BizException
	 */
	public static List<Map<String, String> > readObject(InputStream inputStream, List<String> columnsName) 
			throws BiffException, IOException, BizException{
		Workbook workbook = Workbook.getWorkbook(inputStream);
		Sheet[] sheets = workbook.getSheets();
		List<Map<String, String>> contents = new ArrayList<>();
		for(Sheet sheet : sheets){//遍历sheet页
			if (sheet.getColumns() != columnsName.size()) {
				throw new BizException("error: number of column not match");
			}
			for(int i = 1; i < sheet.getRows(); i++){
				Map<String, String> map = new HashMap<>();
				for(int j = 0; j < sheet.getColumns(); j++){
					Cell cell = sheet.getCell(j, i);
					map.put(columnsName.get(j), cell.getContents());
				}
				contents.add(map);
			}
		}
		return contents;
		
	}
	
	public static void main(String[] args) {
		/*try (FileInputStream fileInputStream = new FileInputStream("C:/Users/Administrator/Desktop/测试excel.xls")){
			List<String> list = new ArrayList<>();
			list.add("id");
			list.add("name");
			list.add("sex");
			list.add("birth");
			List<Map<String, String>> contents = readObject(fileInputStream, list);
			System.out.println(contents);
		} catch (Exception e) {
		}*/
		
		Object aObject = new Object();
		Object bObject = new Object();
		Object cObject = new Object();
		Object dObject = new Object();
		
		Thread threadDemoA = new Thread(new ThreadDemo("A", aObject, dObject, bObject));
		Thread threadDemoB = new Thread( new ThreadDemo("B", bObject, aObject, cObject));
		Thread threadDemoC = new Thread(new ThreadDemo("C", cObject, bObject, dObject));
		Thread threadDemoD = new Thread(new ThreadDemo("D", dObject, cObject, aObject));
		threadDemoA.start();
		threadDemoB.start();
		threadDemoC.start();
		threadDemoD.start();
		
	}
	
	public static class ThreadDemo implements Runnable{
		
		private Object current;
		private Object previous;
		private Object next;
		private String contents;
		
		public ThreadDemo(String contents, Object curr, Object pre, Object next) {
			this.contents = contents;
			this.current = curr;
			this.previous = pre;
			this.next = next;
		}
		@Override
		public void run() {
			while (true) {
				synchronized (current) {
					try {
						current.wait();
						System.out.print(contents);
						Thread.sleep(1000);
						current.notify();
					} catch (Exception e) {
						
					}
					
				}
			}
		}
		
	}
}
