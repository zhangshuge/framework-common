package com.ccbtrust.common.utils;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.Hashtable;

/**
 * 系列化工具类
 * 
 * @author ciyuan
 *
 */
public class SequenceUtil {
	// 定义序列化对象
	private static SequenceUtil su = null;
	// 定义序列集合
	private static final Hashtable<String, MappedByteBuffer> SERIALS = new Hashtable<String, MappedByteBuffer>();
	// 定义IO操作实体渠道
	private static final Hashtable<String, FileChannel> FCHANNELS = new Hashtable<String, FileChannel>();

	public SequenceUtil() {
	}

	public static synchronized SequenceUtil getInstance() {
		if (su == null) {
			su = new SequenceUtil();
		}
		return su;
	}

	public synchronized long getSequenceFile(String name) {
		String appKey = name + ".seq";
		FileChannel fc = null;
		MappedByteBuffer serial = null;
		RandomAccessFile RAFile = null;// 创建从中读取和向其中写入（可选）的随机访问文件流，该文件具有指定名称。
		fc = FCHANNELS.get(appKey);
		serial = SERIALS.get(appKey);
		long serno = 0L;
		if (serial == null) {
			try {
				RAFile = new RandomAccessFile(appKey, "rw");
				if (RAFile.length() < 8) {
					RAFile.writeLong(0);// 按八个字节将 long
										// 写入该文件，先写高字节。写入从文件指针的当前位置开始。
				}
				fc = RAFile.getChannel();
				int size = (int) fc.size();
				// 将此通道的文件区域直接映射到内存中。
				serial = fc.map(FileChannel.MapMode.READ_WRITE, 0, size);

				FCHANNELS.put(appKey, fc);
				SERIALS.put(appKey, serial);

				FileLock flock = fc.lock();
				serial.rewind();// 重绕此缓冲区。将位置设置为 0 并丢弃标记。在一系列通道写入或获取 操作之前调用此方法
				serno = serial.getLong();
				serno++;
				serial.flip();
				serial.putLong(serno);
				flock.release();

			} catch (IOException e) {
				e.printStackTrace();
				return -1;
			}
		}
		return serno;
	}

	public static String formatSequence(long seq, int width) {
		Long lg = new Long(seq);
		String is = lg.toString();
		if (is.length() < width) {
			while (is.length() < width) {
				is = "0" + is;
			}
		} else {
			is = is.substring(is.length() - width, is.length());
		}
		return is;
	}

	public static void main(String[] args) {
		Long id = SequenceUtil.getInstance().getSequenceFile("wapPay");
		String num = SequenceUtil.formatSequence(id, 5);
		System.out.println(id);
		System.out.println(num);
	}
}
