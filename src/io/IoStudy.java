package io;

import java.io.*;

/**
 * @author number47
 * @date 2020/6/19 23:36
 * @description
 */
public class IoStudy {
	/**
	 * 读取键盘输入，打印到控制台 在刷题网站刷算法题的时候，在程序开头都需要和键盘进行交互，
	 * 常常用到行夺取器 BufferedReader 和转换流 InputStreamReader。
	 *
	 * @throws IOException
	 */
	public static void keyInAndPrintConsole() throws IOException {
		try (PrintWriter out = new PrintWriter(System.out, true);
			 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));) {
			System.out.println("请输入:");
			String line = null;
			while ((line = br.readLine()) != null) {
				if (line.equals("exit")) {
					System.exit(1);
				}
				out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 用字节流来读媒介，所以对应的流是 InputStream 和 OutputStream，
	 * 并且媒介对象是文件，所以用到子类是 FileInputStream 和 FileOutputStream，
	 * 这里还可以通过 BufferedInputStream 用缓冲流来读取文件
	 *
	 * @throws IOException
	 */
	public static void readAndWriteByteToFile() throws IOException {
		try (InputStream is = new FileInputStream("resource/FileInputStreamTest.txt");
			 OutputStream os = new FileOutputStream("resource/FileOutputStreamTest.txt")) {
			byte[] buf = new byte[4];
			int hasRead = 0;
			while ((hasRead = is.read(buf)) > 0) {
				os.write(buf, 0, hasRead);
			}
			System.out.println("write success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 用字符流进行读写操作
	 *
	 * @throws IOException
	 */
	public static void readAndWriteCharToFile() throws IOException {
		File readFile = new File("resource/FileInputStreamTest.txt");
		File writeFile = new File("resource/FileOutputStreamTest.txt");
		try (Reader reader = new FileReader(readFile);
			 Writer writer = new FileWriter(writeFile)){
			char[] byteArray = new char[(int) readFile.length()];
			int size = reader.read(byteArray);
			System.out.println("大小:" + size + "个字符;内容:" + new String(byteArray));
			writer.write(byteArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * StringReader 和 StringWriter
	 *
	 * @throws IOException
	 */
	public static void stringNode() throws IOException {
		String str = "学习不刻苦，不如卖红薯\n";
		// StringReader将以String字符串为节点读取数据
		try (StringReader sr = new StringReader(str);
			 StringWriter sw = new StringWriter();){
			char[] buf = new char[32];
			int hasRead = 0;
			System.out.println("读");
			while ((hasRead = sr.read(buf)) > 0) {
				System.out.print(new String(buf, 0, hasRead));
			}
			// 由于String是一个不可变类，因此创建StringWriter时，实际上是以一个StringBuffer作为输出节点
			System.out.println("写");
			sw.write("黑夜给了我黑色的眼睛");
			sw.write("我却用它寻找光明");
			// toString()返回sw节点内的数据
			System.out.println(sw.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 字节流转换为字符流
	 *
	 * @throws IOException
	 */
	public static void convertByteToChar() throws IOException {
		File file = new File("resource/FileInputStreamTest.txt");
		try (InputStream is = new FileInputStream(file);
			 Reader reader = new InputStreamReader(is, "utf-8");){
			char[] byteArray = new char[(int) file.length()];
			int size = reader.read(byteArray);
			System.out.println("大小:" + size + ";内容:" + new String(byteArray));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 随机读写文件 使用 RandomAccessFile 可以实现对文件的随机读取，主要是通过 seek（） 方法实现指针偏移。
	 * @throws IOException
	 */
	public static void randomAccessFileReadAndWrite() throws IOException {
		RandomAccessFile randomAccessFile = null;
		try {
			// 创建一个RandomAccessFile对象
			randomAccessFile = new RandomAccessFile("resource/File.txt", "rw");
			// 通过seek方法来移动指针
			randomAccessFile.seek(10);
			// 获取当前指针
			long pointerBegin = randomAccessFile.getFilePointer();
			// 从当前指针开始读
			byte[] contents = new byte[10];
			randomAccessFile.read(contents);
			long pointerEnd = randomAccessFile.getFilePointer();
			System.out.println("pointerBegin:" + pointerBegin + "" + " pointerEnd:" + pointerEnd + "" + new String(contents));
			randomAccessFile.seek(20);
			// 获取当前指针
			long begin = randomAccessFile.getFilePointer();
			randomAccessFile.write(contents);
			long end = randomAccessFile.getFilePointer();
			System.out.println("begin:" + begin + "" + " end:" + end + "");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			randomAccessFile.close();
		}
	}

	/**
	 * 读取管道
	 * @throws IOException
	 */
	public static void piped() throws IOException {
		final PipedOutputStream output = new PipedOutputStream();
		final PipedInputStream input = new PipedInputStream(output);
		Thread thread1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					output.write("Hello world, pipe!".getBytes());
				} catch (IOException e) {
				}
			}
		});
		Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					int data = input.read();
					while (data != -1) {
						System.out.print((char) data);
						data = input.read();
					}
				} catch (IOException e) {
				} finally {
					try {
						input.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		thread1.start();
		thread2.start();
	}

	/**
	 * 将多个输入流当成一个输入流依次读取
	 * @throws IOException
	 */
	public static void sequeue() throws IOException {
		try (FileInputStream fistream1 = new FileInputStream("resource/A.txt");
			 FileInputStream fistream2 = new FileInputStream("resource/B.txt");
			 SequenceInputStream sistream = new SequenceInputStream(fistream1, fistream2);
			 FileOutputStream fostream = new FileOutputStream("resource/C.txt");){
			int temp;
			while( ( temp = sistream.read() ) != -1) {
				System.out.print( (char) temp );
				fostream.write(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		//读取键盘输入，打印到控制台
		//keyInAndPrintConsole();
		//用字节流读写文件
		//readAndWriteByteToFile();
		//用字符流进行读写操作
		//readAndWriteCharToFile();
		//StringReader 和 StringWriter
		//stringNode();
		//字节流转换为字符流
		convertByteToChar();
		//随机读写文件 使用 RandomAccessFile 可以实现对文件的随机读取
//		randomAccessFileReadAndWrite();
//		piped();
//		sequeue();
	}
}
