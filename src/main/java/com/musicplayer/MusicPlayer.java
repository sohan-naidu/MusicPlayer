package com.musicplayer;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


class MusicPlayer implements ActionListener {

		JFrame frame, tabframe;
		JLabel songName, queueEmptyLabel;
		JButton select, addSong, searchButton, previous, pause, resume, next, play;
		JPanel queuePanel, controlPanel, emptyQueuePanel, tabpanel, searchPanel;
		Icon iconPrevious, iconPause, iconResume, iconNext;
		JTextField searchTermTextField;
		JTable table, qtable;

		DefaultTableModel model;


		FileInputStream fileInputStream;
		BufferedInputStream bufferedInputStream;

		String filename, filePath, query;
		long totalLength, pauseLength;


		Player player;
		Thread playThread, resumeThread;

		Node current = new Node(null, null, null);
		Queue queue;


private DefaultTableModel update(Queue queue)
{
		String[] col = new String[]{"id", "title", "artist", "duration"}; 
		String[][] data = new String[10][4];
		int i = 0;
		if(queue.getHead().getNextSong() != null)
		{
				Node node = queue.getHead();
				
				while(node.getNextSong() != null)
				{	
						String[] row = new String[4];
						node = node.getNextSong();
						row[0] = node.getSong().getID();
						row[1] = node.getSong().getTitle();
						row[2] = node.getSong().getArtist();
						row[3] = node.getSong().getDuration();
						data[i] = row;
						i++;
						
				}
		}

		DefaultTableModel model = new DefaultTableModel(data, col);

		return model;
}

	private MusicPlayer(Queue q) 
	{
		queue = q;
		current = queue.getHead().getNextSong();
		initUI();
		addActionEvents();
	}

	private void initUI() {

				songName = new JLabel("", SwingConstants.CENTER);

				queuePanel = new JPanel();
				controlPanel = new JPanel();
				tabpanel = new JPanel();
				searchPanel = new JPanel();

				DefaultTableModel mod = new DefaultTableModel();
				mod = update(queue);
				qtable = new JTable(mod);
				//qtable.setSize(40, 50);
				play = new JButton("Play");
				JPanel playpanel = new JPanel();
				playpanel.add(play);
				queuePanel.add(new JScrollPane(qtable));

				queuePanel.add(play);
				
				

				iconPrevious= new ImageIcon("C:\\Study\\Third Year\\Object Oriented Development with Java\\Project\\music-player-java-code\\DataFlair-Mp3MusicPlayer\\music-player-icons\\rewind-icon.png");
				iconResume = new ImageIcon("C:\\Study\\Third Year\\Object Oriented Development with Java\\Project\\music-player-java-code\\DataFlair-Mp3MusicPlayer\\music-player-icons\\play-icon.png");
				iconPause = new ImageIcon("C:\\Study\\Third Year\\Object Oriented Development with Java\\Project\\music-player-java-code\\DataFlair-Mp3MusicPlayer\\music-player-icons\\pause-icon.png");
				iconNext = new ImageIcon("C:\\Study\\Third Year\\Object Oriented Development with Java\\Project\\music-player-java-code\\DataFlair-Mp3MusicPlayer\\music-player-icons\\forward-icon.png");
				
				
				searchTermTextField = new JTextField(26);

				previous = new JButton(iconPrevious);
				pause = new JButton(iconPause);
				resume = new JButton(iconResume);
				next = new JButton(iconNext);
				searchButton = new JButton("Search");


				controlPanel.setLayout(new GridLayout(1, 4));


				controlPanel.add(previous);
				controlPanel.add(pause);
				controlPanel.add(resume);
				controlPanel.add(next);

				searchPanel.add(searchTermTextField);
				searchPanel.add(searchButton);

				previous.setBackground(Color.WHITE);
				pause.setBackground(Color.WHITE);
				resume.setBackground(Color.WHITE);
				next.setBackground(Color.WHITE);


				frame = new JFrame();

				frame.setTitle("Music Player");

				//frame.add(play);

				frame.add(searchPanel, BorderLayout.NORTH);
				frame.add(queuePanel, BorderLayout.CENTER);
				frame.add(playpanel, BorderLayout.LINE_END);
				frame.add(controlPanel, BorderLayout.SOUTH);
				

				frame.setBackground(Color.white);
				frame.setSize(800, 600);
				frame.setVisible(true);
				frame.setResizable(false);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLocationRelativeTo(null);

		}

		private void addActionEvents()
		{
			previous.addActionListener(this);
			pause.addActionListener(this);
			resume.addActionListener(this);
			next.addActionListener(this);
			searchButton.addActionListener(this);
			play.addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {


			if(e.getSource().equals(play))
			{
				current = queue.getHead().getNextSong();
				if(current != null)
				{
					new runnablePlay(current);
					new Thread()
					{
						public void run()
						{
							try {
								fileInputStream = new FileInputStream(myFile);
								bufferedInputStream = new BufferedInputStream(fileInputStream);
								player = new Player(bufferedInputStream);
								totalLength = fileInputStream.available();
								player.play();
	
							} 
							catch (JavaLayerException | IOException e) 
							{
								
								e.printStackTrace();
							}
						}
					}.start();
						
				}

				else
				{
					JOptionPane.showMessageDialog(frame, "Queue is empty");
				}
				

			}

 
			
			if (e.getSource().equals(previous)) {
					
					player.close();
					current = current.getPreviousSong();
					new runnablePlay(current);
					new Thread()
					{
						public void run()
						{
							try {
								fileInputStream = new FileInputStream(myFile);
								bufferedInputStream = new BufferedInputStream(fileInputStream);
								player = new Player(bufferedInputStream);
								totalLength = fileInputStream.available();
								player.play();
	
							} 
							catch (JavaLayerException | IOException e) 
							{
								
								e.printStackTrace();
							}
						}
					}.start();
					

			}

			if (e.getSource().equals(pause)) {

					System.out.println("Paused");
					flag = false;
					if (player != null && filename != null) {
							try {
									pauseLength = fileInputStream.available();
									//System.out.print(pauseLength);
									player.close();
							} catch (IOException e1) {
									e1.printStackTrace();
							}
					}
			}

			if (e.getSource().equals(resume)) {
				
					System.out.println("Resume");
					Runnable res = new runnableResume();
					Thread rest = new Thread(res);
					rest.start();
			}


			if (e.getSource().equals(next)) {
					
				player.close();
				current = current.getNextSong();
				new runnablePlay(current);
				new Thread()
				{
					public void run()
					{
						try {
							fileInputStream = new FileInputStream(myFile);
							bufferedInputStream = new BufferedInputStream(fileInputStream);
							player = new Player(bufferedInputStream);
							totalLength = fileInputStream.available();
							player.play();

						} 
						catch (JavaLayerException | IOException e) 
						{
							
							e.printStackTrace();
						}
					}
				}.start();
				

			}

			if(e.getSource().equals(searchButton))
			{
					query = searchTermTextField.getText();
					model = searchDB.search(query);
					table = new JTable(model);
					table.getColumn("Add to Queue").setCellRenderer(new ButtonRenderer());
					table.getColumn("Add to Queue").setCellEditor(
					new ButtonEditor(new JCheckBox()));
					tabframe = new JFrame();
					tabpanel = new JPanel();
					tabpanel.add(new JScrollPane(table));
					tabframe.add(tabpanel);
					tabframe.setBackground(Color.white);
					tabframe.setSize(800, 800);
					tabframe.setVisible(true);
					tabframe.setResizable(false);
					tabframe.setLocationRelativeTo(null);
			}


			

				
		}
		

		
		File myFile = new File("C:\\Study\\Third Year\\Object Oriented Development with Java\\Project\\Final\\src\\main\\java\\com\\musicplayer\\cursong.mp3");
		
		private class ButtonRenderer extends JButton implements TableCellRenderer {

				public ButtonRenderer() {
					setOpaque(true);
				}
			
				public Component getTableCellRendererComponent(JTable table, Object value,
						boolean isSelected, boolean hasFocus, int row, int column) {
					if (isSelected) {
						setForeground(table.getSelectionForeground());
						setBackground(table.getSelectionBackground());
					} else {
						setForeground(table.getForeground());
						setBackground(UIManager.getColor("Button.background"));
					}
					setText("Add");
					return this;
				}
			}


			private class ButtonEditor extends DefaultCellEditor {
				protected JButton button;
			
				private String label;
			
				private boolean isPushed;
			
				public ButtonEditor(JCheckBox checkBox) {
					super(checkBox);
					button = new JButton();
					button.setOpaque(true);
					button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							fireEditingStopped();
						}
					});
				}

		
				public Component getTableCellEditorComponent(JTable table, Object value,
				boolean isSelected, int row, int column) {
			if (isSelected) {
				button.setForeground(table.getSelectionForeground());
				button.setBackground(table.getSelectionBackground());
			} else {
				button.setForeground(table.getForeground());
				button.setBackground(table.getBackground());
			}
			label = "Add";
			button.setText(label);
			isPushed = true;
			return button;
		}
	
		public Object getCellEditorValue() {
			if (isPushed) {
				int row = table.getSelectedRow();
				String id = table.getModel().getValueAt(row, 0).toString();
				String title = table.getModel().getValueAt(row, 1).toString();
				String artist = table.getModel().getValueAt(row, 2).toString();
				String duration = table.getModel().getValueAt(row, 3).toString();
				Song song = new Song(id, title, artist, duration);
				//song = song.createSong(id, title, artist, duration);
				queue = queue.addToQueue(song, queue);
				DefaultTableModel model = new DefaultTableModel();
				model = update(queue);
				qtable.setModel(model);
				current = queue.getHead().getNextSong();
				JOptionPane.showMessageDialog(tabframe, "Successfully added to queue!");
		
			}
			isPushed = false;
			return new String(label);
		}
	
		public boolean stopCellEditing() {
			isPushed = false;
			return super.stopCellEditing();
		}
	
		protected void fireEditingStopped() {
			super.fireEditingStopped();
		}
	}

		public volatile boolean flag = true;
		public class runnablePlay implements Runnable
		{
				public runnablePlay(Node current)
				{
						Song currentSong;
						String curSong = current.getSong().getID();
						System.out.println(curSong);
						fetchSongFromDB fetched = new fetchSongFromDB();
						currentSong = fetched.fetch(curSong);
						filename = currentSong.getTitle();
						System.out.println(currentSong.getArtist());
				}


				public void run() {
					while(flag)
					{
							try {                
								fileInputStream = new FileInputStream(myFile);
								bufferedInputStream = new BufferedInputStream(fileInputStream);
								player = new Player(bufferedInputStream);
								totalLength = fileInputStream.available();
								player.play();
						} catch (Exception e) {
								e.printStackTrace();
						}
					}
						
		
				};

		}


		public class runnableResume implements Runnable
		{

				public void run() {
						try {
								fileInputStream = new FileInputStream(myFile);
								fileInputStream.skip(totalLength - pauseLength);
								bufferedInputStream = new BufferedInputStream(fileInputStream);
								player = new Player(bufferedInputStream);
								player.play();
						} catch (Exception e) {
								e.printStackTrace();
						}
		
				};

			}
	
	private static MusicPlayer musicplayer = null;
	public static MusicPlayer getMusicPlayer(Queue queue)
	{
		if(musicplayer == null)
		{
			musicplayer = new MusicPlayer(queue);
		}

		return musicplayer;
	}

}