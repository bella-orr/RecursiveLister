import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class RecursiveFrame extends JFrame
{
    //Declarations

    //JPanels
    JPanel mainPnl;
    JPanel titlePnl;
    JPanel returnPnl;
    JPanel buttonPnl;

    //JButtons
    JButton startBtn;
    JButton quitBtn;

    //JTextArea
    JTextArea fileLister;

    //JScrollPane
    JScrollPane scroller;

    //JLabel
    JLabel title;

    //Declarations for getting directory
    JFileChooser chooser = new JFileChooser();
    File selectedDirectory;

    public RecursiveFrame()
    {
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());

        createTitlePnl();
        mainPnl.add(titlePnl, BorderLayout.NORTH);

        createReturnPnl();
        mainPnl.add(returnPnl, BorderLayout.CENTER);

        createButtonPnl();
        mainPnl.add(buttonPnl, BorderLayout.SOUTH);

        add(mainPnl);
        setTitle("Recursive Lister");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    private void createTitlePnl() //Creates Title Panel
    {
        titlePnl = new JPanel();
        titlePnl.setLayout(new GridBagLayout());

        //creates JLabel
        title = new JLabel("Recursive Lister");

        //formats and styles JLabel title
       title.setFont(new Font("Verdana", Font.PLAIN, 22));
       title.setForeground(Color.BLUE);

       //Center JLabel title within titlePnl
        titlePnl.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));


        //Adds title to titlePnl
        titlePnl.add(title);
    }

    private void createReturnPnl() //Creates returnPnl
    {
        returnPnl = new JPanel();

        //creates JText Area
        fileLister = new JTextArea(20, 30);
        fileLister.setEditable(false);

        //creates JScrollPane
        scroller = new JScrollPane(fileLister);

        //Adds to returnPnl
        returnPnl.add(scroller);

    }

    private void createButtonPnl() //creates buttonPnl
    {
        buttonPnl = new JPanel();
        buttonPnl.setLayout(new GridLayout(1, 2)); //Set grid layout

        //creates JButtons
        startBtn = new JButton("Start");
        quitBtn = new JButton("Quit");

        //Actions for buttons
        quitBtn.addActionListener((ActionEvent ae) -> System.exit(0));
        startBtn.addActionListener((ActionEvent ae) -> getDirectory());

        //adds buttons to buttonPnl
        buttonPnl.add(startBtn);
        buttonPnl.add(quitBtn);

    }

    private void getDirectory() //picks the directory the user choosers
    {

        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // only allows user to get a directory

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            selectedDirectory = chooser.getSelectedFile();
            fileLister.append("Directory Name:" + selectedDirectory.getName() + "\n\n"); //prints name of the Directory to the JTextArea

            getList(selectedDirectory);
        }

    }

    private void getList(File selectedDirectory)
    {
        File files[] = selectedDirectory.listFiles();

        for (File f : files)
        {
            if (f.isFile())
            {
                fileLister.append("Name:" + f.getName() +"\n");
            }

            else
            {
                getList(f);
            }
        }

    }
}
