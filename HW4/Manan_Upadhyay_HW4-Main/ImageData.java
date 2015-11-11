import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class ImageData {
	// Actual pixel values
	public int[][] data;
	// Number rows, will always be 28
	public int rows;
	// Number of cols, will always be 28
	public int cols;
	// Digit (0-9) that is being shown in this image
	public int label;
	
	// Variable for storing the prediction of your network (only used for test images).
	// You do not need to store your predictions here, this is simply for convenience.
	public int prediction;
	
	public ImageData(int rows, int cols, int[][] data) {
		this.rows = rows;
		this.cols = cols;
		this.data = data;
		this.label = -1; // This will be set in readAndApplyLabels()

		this.prediction = -1;
	}
	
	// Use this method to load training data
	public static ArrayList<ImageData> readData(String imagesPath, String labelsPath) {
		ArrayList<ImageData> images = readImageFile(imagesPath);
		if(labelsPath!=null)		
					readAndApplyLabels(labelsPath, images);
		return images;
	}

	private static ArrayList<ImageData> readImageFile(String imagesPath) {
		ArrayList<ImageData> images = new ArrayList<ImageData>();
		try {
			// Open file and get bytes
			Path path = Paths.get(imagesPath);
			byte[] data = Files.readAllBytes(path);
			ByteBuffer buffer = ByteBuffer.wrap(data);

			// Ignore first 4-byte int
			buffer.getInt();

			// Read metadata values (4-byte ints)
			int numImages = buffer.getInt();
			int rows = buffer.getInt();
			int cols = buffer.getInt();
			
			// Read image values (1-byte ints)
			for (int imageIndex = 0; imageIndex < numImages; imageIndex++) {
				int[][] imageVals = new int[rows][cols];
				for (int c = 0; c < cols; c++) {
					for (int r = 0; r < rows; r++) {
						int val = buffer.get() & 0xFF; // Get unsigned int value
						imageVals[r][c] = val;
					}
				}
				images.add(new ImageData(rows, cols, imageVals));
			}
		} catch (IOException e) {
			System.out.println("Error reading training images!");
			return null;
		}
		return images;
	}

	private static void readAndApplyLabels(String labelsPath, ArrayList<ImageData> images) {
		try {
			// Open file and get bytes
			Path path = Paths.get(labelsPath);
			byte[] data = Files.readAllBytes(path);
			ByteBuffer buffer = ByteBuffer.wrap(data);
			
			// Ignore first 4-byte int
			buffer.getInt();

			// Read metadata values (4-byte ints)
			int numImages = buffer.getInt();
			
			// Read labels (1-byte ints)
			for (int imageIndex = 0; imageIndex < numImages; imageIndex++) {
				images.get(imageIndex).label = buffer.get();
			}
		} catch (IOException e) {
			System.out.println("Error reading training labels!");
		}
	}
	
	public void drawImage() {
		//System.out.println(label);
		BufferedImage bImage = new BufferedImage(rows, cols, BufferedImage.TYPE_BYTE_GRAY);
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				int val = data[r][c];
				int rgb = new Color(val, val, val).getRGB();
				bImage.setRGB(r, c, rgb);
			}
		}
		ImageIcon icon = new ImageIcon();
		icon.setImage(bImage);
		JOptionPane.showMessageDialog(null, icon);
	}
	
	// Quick example of using this class
	public static void main(String[] args) 
	{
		String trImage = args[0]+".idx3-ubyte";
		String trLabel = args[1]+".idx1-ubyte";
		String tstImage = args[2]+".idx3-ubyte";

		ArrayList<ImageData> imagesTrain = readData(trImage, trLabel);
		ArrayList<ImageData> imagesTest = readData(tstImage, null);

                //calling Neural
                Neural n = new Neural(784,220,imagesTrain,imagesTest);
                
                n.learnNeural(args[3]);
                System.out.println("Predictions written to File "+ args[3]);

	}
}