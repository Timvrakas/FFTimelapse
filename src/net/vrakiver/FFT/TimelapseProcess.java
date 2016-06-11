package net.vrakiver.FFT;

import java.io.File;

import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import net.bramp.ffmpeg.job.FFmpegJob;

public class TimelapseProcess {

	FFmpegExecutor executor;
	long bitRate = 20000;
	double ratio = 10.0;

	public long getBitRate() {
		return bitRate;
	}

	public void setBitRate(long bitRate) {
		this.bitRate = bitRate;
	}

	public double getRatio() {
		return ratio;
	}

	public void setRatio(double ratio) {
		this.ratio = ratio;
	}

	public TimelapseProcess(FFmpegExecutor executor) {
		this.executor = executor;
	}

	public void run(File input, File output) {
		
		FFmpegBuilder builder = new FFmpegBuilder().setInput(input.getAbsolutePath()).overrideOutputFiles(true)
				.addExtraArgs("-v","info").addOutput(output.getAbsolutePath()).disableSubtitle().disableAudio()
				.setVideoFilter("\"setpts=(1/" + ratio + ")*PTS\"").setVideoCodec("libx264")
				.setVideoBitRate(bitRate * 1000).done();

		FFmpegJob job = executor.createJob(builder);
		job.run();
	}
}
